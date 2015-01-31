package rippin.bullyscraft.commandmobs;



import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import rippin.bullyscraft.commandmobs.Configs.MobsConfig;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MobsManager {
    private static List<Mob> mobs = new ArrayList<Mob>();
    private static List<String> activeMobs = new ArrayList<String>();

    public static void loadMobs(CommandMobs plugin){
        if (MobsConfig.getConfig().getConfigurationSection("Mobs") != null){
        for (String key : MobsConfig.getConfig().getConfigurationSection("Mobs").getKeys(false)){
            if (!isMob(key)) {
            Mob m = new Mob(key);
            mobs.add(m);
            if (m.isEnable()) {
            if (!isActiveMob(key)) {
            m.spawnMob(Bukkit.getConsoleSender());
               }
            }
            plugin.logger.info(m.getName() + " mob has been loaded from file");
           }
        }
         for (Mob m : getAllMobs()){
             m.loadData();
         }

       }

    }

     public static boolean isMob(String name){
        for (Mob m : mobs){
            if (m.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
     }

    public static boolean isActiveMob(String name){
        for (String key : getActiveMobs()){
            String split[] = key.split(":");
            if (split[1].equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }
    public static void removeActiveMob(String name){
        if (activeMobs != null && !activeMobs.isEmpty()) {
        Iterator<String> it = activeMobs.iterator();
        while (it.hasNext()){
           String split[] = it.next().split(":");
            if (split[1].equalsIgnoreCase(name)){
               Mob m = getMob(name);
               if (!m.getLoc().getChunk().isLoaded()){
                   m.getEnt().getLocation().getChunk().load();
               }
               m.getEnt().remove();
               it.remove();
           }
        }
       saveActiveMobsToFile();
        }
 }
    public static Mob getMob(String s){
        for (Mob m : getAllMobs()){
            if (m.getName().equalsIgnoreCase(s)){
                return m;
            }
        }
        return null;
    }

    public static Mob getMobByUUID(String UUID){
        for (Mob m : getAllMobs()){
            if (m.getEnt() != null){
                if (m.getEnt().getUniqueId().toString().equalsIgnoreCase(UUID)){
                    return  m;
                }
            }
        }
        return null;
    }


    public static List<String> getActiveMobs() {
        return  activeMobs;
    }

    public static void saveActiveMobsToFile(){
        MobsConfig.getConfig().set("Active-Mobs", activeMobs);
        MobsConfig.saveFile();
    }

    public static void removeAllActiveMobs(){
        Iterator<String> it = activeMobs.iterator();
        while (it.hasNext()){
            String split[] = it.next().split(":");
                Mob m = getMob(split[1]);
                if (m != null && m.getEnt() != null) {
                if (!m.getLoc().getChunk().isLoaded()){
                    m.getEnt().getLocation().getChunk().load();
                }
                m.getEnt().remove();
                it.remove();
            }
        }
        saveActiveMobsToFile();
    }

    public static List<Mob> getAllMobs(){
        return  mobs;
    }

    public static void moveMob(Mob m, Player player){
        if (m != null){
            m.setLoc(player.getLocation());
            if (m.getEnt() != null) {
            if (!player.getLocation().getChunk().isLoaded())
                player.getLocation().getChunk().load();
             m.getEnt().teleport(player.getLocation());
            }
            player.sendMessage(Msgs.messages.get("Move-Mob").replace("%mobname%", m.getName()));
        }
        else {
            player.sendMessage(Msgs.messages.get("Move-Mob-Error").replace("%mobname%", m.getName()));
        }

    }

    public static void createNewMob(String name){
       Mob m = new Mob(name);
        getAllMobs().add(m);
        MobsConfig.getConfig().createSection("Mobs." + name);
        MobsConfig.getConfig().set("Mobs." + name + ".Enable", true);
        MobsConfig.getConfig().set("Mobs." + name + ".CommandAsConsole", false); // run as player default

        MobsConfig.saveFile();
    }

    public static void reloadMobs(CommandMobs plugin){
        removeAllActiveMobs();
        loadMobs(plugin);
    }
}
