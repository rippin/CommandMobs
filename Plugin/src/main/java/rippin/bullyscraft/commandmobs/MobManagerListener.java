package rippin.bullyscraft.commandmobs;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.world.ChunkUnloadEvent;


public class MobManagerListener implements Listener {
    private CommandMobs plugin;
    public MobManagerListener(CommandMobs plugin){
        this.plugin = plugin;
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onEntityDamage(EntityDamageEvent event){
        if (event.getEntity() instanceof LivingEntity){
            for (String key : MobsManager.getActiveMobs()){
                String split[] = key.split(":");
                if (event.getEntity().getUniqueId().toString().equalsIgnoreCase(split[0])){
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event){
        if (event.getEntity() instanceof  LivingEntity && event.getDamager() instanceof Player){
            Player player = (Player) event.getDamager();
            for (String key : MobsManager.getActiveMobs()){
                String split[] = key.split(":");
                String uuid = split[0];
                if (event.getEntity().getUniqueId().toString().equalsIgnoreCase(uuid)){
                    Mob m = MobsManager.getMobByUUID(uuid);
                    event.setCancelled(true);
                    if (m == null) {
                        return;
                    }
                    if (m.getPermission() == null || player.hasPermission(m.getPermission())) {
                        if (m.getAmount() > 0 && VaultHook.hasAmount((Player)event.getDamager(), m.getAmount())) {
                            VaultHook.deductAmount(player, m.getAmount());
                        }
                        else if (m.getAmount() > 0 && (!VaultHook.hasAmount(player, m.getAmount()))) {
                            player.sendMessage(Msgs.messages.get("Not-Enough-Money").replace("%player%", player.getName()));
                            return;
                        }
                        if (m.getCooldownUUIDS().containsKey(player.getUniqueId().toString())){
                            long lastTime = m.getCooldownUUIDS().get(player.getUniqueId().toString());
                            long cooldown = (m.getCooldown() * 1000);
                            if (System.currentTimeMillis() - lastTime < cooldown){
                                return;
                            }
                        }
                        m.getCooldownUUIDS().put(player.getUniqueId().toString(), System.currentTimeMillis());

                        if (m.getSound() != null){
                            player.playSound(player.getLocation(), m.getSound(), 10, 1);
                        }
                        if (m.getCommands() != null && !m.getCommands().isEmpty()){

                            if (m.isCommandAsConsole()) {
                                for (String cmds : m.getCommands()) {
                                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), cmds.replace("%player%", player.getName()));
                                }
                            } else {
                                for (String cmds : m.getCommands()) {
                                    Bukkit.getServer().dispatchCommand(player, cmds.replace("%player%", player.getName()));
                                }
                            }
                        }
                    }
                    else {
                        player.sendMessage(Msgs.messages.get("No-Permission-For-Mob-Usage").replace("%player%", player.getName()));
                    }
                }
            }
        }

    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onCreatureSpawn(CreatureSpawnEvent event){
        if (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.CUSTOM) {
                event.setCancelled(false);

      }
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onCombustEvent(EntityCombustEvent event){
        if (event.getEntity() instanceof LivingEntity){
            for (String key : MobsManager.getActiveMobs()){
                String split[] = key.split(":");
                if (event.getEntity().getUniqueId().toString().equalsIgnoreCase(split[0])){
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onTargetEvent(EntityTargetEvent event){
        if (event.getEntity() instanceof LivingEntity){
            for (String key : MobsManager.getActiveMobs()){
                String split[] = key.split(":");
                if (event.getEntity().getUniqueId().toString().equalsIgnoreCase(split[0])){
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onClick(PlayerInteractEntityEvent event){
        if (event.getRightClicked() instanceof  LivingEntity){
            for (String key : MobsManager.getActiveMobs()){
                String split[] = key.split(":");
                String uuid = split[0];
                if (event.getRightClicked().getUniqueId().toString().equalsIgnoreCase(uuid)){
                    Mob m = MobsManager.getMobByUUID(uuid);
                    if (m == null) {
                        return;
                    }
                    if (m.getEnt() instanceof Villager){
                        event.setCancelled(true);
                    }
                    if (m.getPermission() == null || event.getPlayer().hasPermission(m.getPermission())) {
                    if (m.getAmount() > 0 && VaultHook.hasAmount(event.getPlayer(), m.getAmount())) {
                       VaultHook.deductAmount(event.getPlayer(), m.getAmount());
                    }
                     else if (m.getAmount() > 0 && (!VaultHook.hasAmount(event.getPlayer(), m.getAmount()))) {
                            event.getPlayer().sendMessage(Msgs.messages.get("Not-Enough-Money").replace("%player%", event.getPlayer().getName()));
                            return;
                       }
                       if (m.getCooldownUUIDS().containsKey(event.getPlayer().getUniqueId().toString())){
                           long lastTime = m.getCooldownUUIDS().get(event.getPlayer().getUniqueId().toString());
                           long cooldown = (m.getCooldown() * 1000);
                           if (System.currentTimeMillis() - lastTime < cooldown){
                               return;
                           }
                       }
                           m.getCooldownUUIDS().put(event.getPlayer().getUniqueId().toString(), System.currentTimeMillis());

                     if (m.getSound() != null){
                       event.getPlayer().playSound(event.getPlayer().getLocation(), m.getSound(), 10, 1);
                     }
                     if (m.getCommands() != null && !m.getCommands().isEmpty()){

                            if (m.isCommandAsConsole()) {
                            for (String cmds : m.getCommands()) {
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), cmds.replace("%player%", event.getPlayer().getName()));
                         }
                           } else {
                               for (String cmds : m.getCommands()) {
                                   Bukkit.getServer().dispatchCommand(event.getPlayer(), cmds.replace("%player%", event.getPlayer().getName()));
                               }
                           }
                     }
                    }
                    else {
                        event.getPlayer().sendMessage(Msgs.messages.get("No-Permission-For-Mob-Usage").replace("%player%", event.getPlayer().getName()));
                    }
            }
          }
        }
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onChunkUnload(ChunkUnloadEvent event){
        for (Mob m : MobsManager.getAllMobs()){
            if (m != null && m.getLoc() != null && m.isEnable()){
                Chunk c = m.getLoc().getChunk();
                if (c.getX() == event.getChunk().getX() && c.getZ() == event.getChunk().getZ()){
                    event.setCancelled(true);
                }
            }
        }
    }

}
