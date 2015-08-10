package rippin.bullyscraft.commandmobs;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import rippin.bullyscraft.commandmobs.Configs.ConfigManager;
import rippin.bullyscraft.commandmobs.Configs.MobsConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BMCommands implements CommandExecutor {

    private CommandMobs plugin;
     public BMCommands(CommandMobs plugin){
         this.plugin = plugin;
     }

    public boolean onCommand(CommandSender commandSender, Command cmd, String s, String[] args) {
        if (cmd.getName().equalsIgnoreCase("commandmobs")) {
        if (args.length == 1){
            if (args[0].equalsIgnoreCase("reload")){
                ConfigManager.reloadConfigs(plugin);
                commandSender.sendMessage(Msgs.messages.get("Reload-Configs"));
            }
            else if (args[0].equalsIgnoreCase("help")){
                commandSender.sendMessage(ChatColor.GREEN + "/cm list active|all");
                commandSender.sendMessage(ChatColor.GREEN + "/cm create [name]");
                commandSender.sendMessage(ChatColor.GREEN + "/cm delete|remove [name]");
                commandSender.sendMessage(ChatColor.GREEN + "/cm move|setlocation [name]");
                commandSender.sendMessage(ChatColor.GREEN + "/cm tp|teleport [name]");
                commandSender.sendMessage(ChatColor.GREEN + "/cm [name] spawn");
                commandSender.sendMessage(ChatColor.GREEN + "/cm [name] despawn");
                commandSender.sendMessage(ChatColor.GREEN + "/cm [name] setPermission [permission]");
                commandSender.sendMessage(ChatColor.GREEN + "/cm [name] removePermission");
                commandSender.sendMessage(ChatColor.RED + "/cm help 2 {To continue}");


            }
            return true;
        }
        else if (args.length == 2){
            if (args[0].equalsIgnoreCase("help")){
                if (args[1].equalsIgnoreCase("2")){
                    commandSender.sendMessage(ChatColor.GREEN + "/cm [name] setName [ args[] ]");
                    commandSender.sendMessage(ChatColor.GREEN + "/cm [name] setType [EntityType]");
                    commandSender.sendMessage(ChatColor.GREEN + "/cm [name] setSound [Sound]");
                    commandSender.sendMessage(ChatColor.GREEN + "/cm [name] setBaby {Only if ageable}");
                    commandSender.sendMessage(ChatColor.GREEN + "/cm [name] setCost [integer]");
                    commandSender.sendMessage(ChatColor.GREEN + "/cm [name] setWeapon hand|Material");
                    commandSender.sendMessage(ChatColor.GREEN + "/cm [name] setArmor slot|Material");
                    commandSender.sendMessage(ChatColor.GREEN + "/cm [name] setCommandSender player|console {Command run as player or console.}");
                    commandSender.sendMessage(ChatColor.GREEN + "/cm [name] addCommand [ args[] ]");
                    commandSender.sendMessage(ChatColor.GREEN + "/cm clearcommands [name]");
                }
            }
           else  if (args[0].equalsIgnoreCase("list")){
                if (args[1].equalsIgnoreCase("active")){
                commandSender.sendMessage(Msgs.messages.get("Active-Mobs-List"));
                    for (String m : MobsManager.getActiveMobs()){
                        String split[] = m.split(":");
                        commandSender.sendMessage(Msgs.messages.get("Active-Mobs-List-Format").replace("%mobname%", split[1]));
                    }
                }
                else if (args[1].equalsIgnoreCase("all")) {
                    commandSender.sendMessage(Msgs.messages.get("All-Mobs-List"));
                    for (Mob m : MobsManager.getAllMobs()){
                        commandSender.sendMessage(Msgs.messages.get("All-Mobs-List-Format").replace("%mobname%", m.getName()));
                    }
                }
            }
            else if (args[0].equalsIgnoreCase("create")){
                if (!MobsManager.isMob(args[1])){
                    MobsManager.createNewMob(args[1]);
                    commandSender.sendMessage(Msgs.messages.get("Mob-Created"));
                }
                else {
                    commandSender.sendMessage(Msgs.messages.get("Mob-Already-Exists"));
                }
            }
            else if (args[0].equalsIgnoreCase("delete") || args[0].equalsIgnoreCase("remove")){
                if (MobsManager.isMob(args[1])){
                    if (MobsManager.isActiveMob(args[0]))
                        MobsManager.removeActiveMob(args[0]);
                    MobsConfig.getConfig().set("Mobs." + args[0], null);
                    commandSender.sendMessage(Msgs.messages.get("Mob-Deleted").replace("%mobname%", args[0]));
                }
                else {
                    commandSender.sendMessage(Msgs.messages.get("Not-A-Mob-Error").replace("%mobname%", args[0]));
                }
            }
            else if (args[0].equalsIgnoreCase("clearCommands")){
                if (MobsManager.isMob(args[1])){
                    Mob m = MobsManager.getMob(args[1]);
                    m.setCommands(new ArrayList<String>());
                    commandSender.sendMessage(Msgs.messages.get("Clear-Commands").replace("%mobname%", args[0]));
                }
                else {
                    commandSender.sendMessage(Msgs.messages.get("Not-A-Mob-Error").replace("%mobname%", args[0]));
                }
            }

            else if (args[1].equalsIgnoreCase("move") ||args[1].equalsIgnoreCase("setlocation") ) {
                if (commandSender instanceof  Player){
                        Mob m = MobsManager.getMob(args[0]);
                        MobsManager.moveMob(m, (Player) commandSender);
                }
            }
            else if (args[1].equalsIgnoreCase("teleport") ||args[1].equalsIgnoreCase("tp")){
                if (commandSender instanceof  Player){
                    Player p = (Player) commandSender;
                    if (MobsManager.isActiveMob(args[0])){
                        Mob m = MobsManager.getMob(args[0]);
                        p.teleport(m.getLoc());
                        p.sendMessage(Msgs.messages.get("Teleport-To-Mob").replace("%mobname%", m.getName()));
                }
                else {
                   p.sendMessage(Msgs.messages.get("Not-A-Active-Mob-Error").replace("%mobname%", args[0]));
                }
              }
                else {
                    //only players
                }

            }

            else if (args[1].equalsIgnoreCase("removePermission")){
                if (MobsManager.isMob(args[0])){
                    Mob m = MobsManager.getMob(args[0]);
                    m.setPermission(null);
                    commandSender.sendMessage(Msgs.messages.get("Remove-Permission").replace("%mobname%", m.getName()));
                }
                else {
                    commandSender.sendMessage(Msgs.messages.get("Not-A-Mob-Error").replace("%mobname%", args[0]));
                }
            }

            else if (args[1].equalsIgnoreCase("spawn")){
                if (!MobsManager.isActiveMob(args[0])){
                    Mob m = MobsManager.getMob(args[0]);
                    m.setEnable(true);
                    m.spawnMob(commandSender);
                    commandSender.sendMessage(Msgs.messages.get("Spawn-Mob").replace("%mobname%", m.getName()));
                }
                else {
                    commandSender.sendMessage(Msgs.messages.get("Already-A-Active-Mob").replace("%mobname%", args[0]));
                }

            }
           else if (args[1].equalsIgnoreCase("despawn")){
                if (MobsManager.isActiveMob(args[0])){
                    Mob m = MobsManager.getMob(args[0]);
                    m.setEnable(false);
                    MobsManager.removeActiveMob(args[0]);
                    commandSender.sendMessage(Msgs.messages.get("Despawn-Mob").replace("%mobname%", args[0]));
                }
                else {
                    commandSender.sendMessage(Msgs.messages.get("Not-A-Active-Mob-Error").replace("%mobname%", args[0]));
                }
            }
            return true;
        }
        else if (args.length > 2){
            if (MobsManager.getMob(args[0]) != null) {
                Mob m = MobsManager.getMob(args[0]);
                if (args[1].equalsIgnoreCase("addCommand")){
                    String concat = "";
                    for (int i = 2; i <= args.length - 1; i++){
                        concat += " " + args[i];
                    }
                    m.addCommand(concat.trim());
                    commandSender.sendMessage(Msgs.messages.get("Add-Command").replace("%mobname%", args[0]));
                }
                else if (args[1].equalsIgnoreCase("setName")){
                    String concat = "";
                    for (int i = 2; i <= args.length - 1; i++){
                        concat += " " + args[i];
                    }
                    m.setDisplayName(concat.trim());
                    commandSender.sendMessage(Msgs.messages.get("Set-Name").replace("%mobname%", args[0]));
                }
            }
            return true;
        }
            if (args.length == 3) {
                if (runCommand(args, commandSender)){
                    return  true;
                }
            }


        }
        return false;

    }

    private static boolean runCommand(String[] args, CommandSender sender){
        if (MobsManager.getMob(args[0]) != null) {
            Mob m = MobsManager.getMob(args[0]);
            if (args[1].equalsIgnoreCase("settype")) {
                m.setType(args[2]);
            sender.sendMessage(Msgs.messages.get("Set-Type").replace("%mobname%", m.getName()));
            }
            else if (args[1].equalsIgnoreCase("setPermission")){
                m.setPermission(args[2]);
                sender.sendMessage(Msgs.messages.get("Set-Permission").replace("%mobname%", m.getName()));
            }
            else if (args[1].equalsIgnoreCase("setSound")){
                m.setSound(Sound.valueOf(args[2]));
                sender.sendMessage(Msgs.messages.get("Set-Sound").replace("%mobname%", m.getName()));

            }
            else if (args[1].equalsIgnoreCase("setCommandSender")){
                if (args[2].equalsIgnoreCase("player")){
                    m.setCommandAsConsole(false);
                    sender.sendMessage(Msgs.messages.get("Set-CommandSender").replace("%mobname%", m.getName()));
                }
                else if (args[2].equalsIgnoreCase("console")){
                    m.setCommandAsConsole(true);
                    sender.sendMessage(Msgs.messages.get("Set-CommandSender").replace("%mobname%", m.getName()));
                }
            }
            else if (args[1].equalsIgnoreCase("setbaby")) {
                    if (m != null || m.getEnt() != null){
                       if (m.getEnt() instanceof Ageable){
                           m.setBaby(Boolean.valueOf(args[2]));
                           sender.sendMessage(Msgs.messages.get("Set-Baby").replace("%mobname%", args[0]));
                       }
                    }
                }
            else if (args[1].equalsIgnoreCase("setCost")){
                m.setAmount(Integer.parseInt(args[2]));
                sender.sendMessage(Msgs.messages.get("Set-Cost").replace("%mobname%", m.getName()));
            }
            else if (args[1].equalsIgnoreCase("setweapon")) {
                if (m != null || m.getEnt() != null){
                    if (args[2] != null){
                    if (args[2].equalsIgnoreCase("hand")) {
                       if (sender instanceof  Player) {
                        Player p = (Player) sender;
                        if (p.getItemInHand() != null) {
                        m.setWeapon(p.getItemInHand());
                        p.sendMessage(Msgs.messages.get("Set-Weapon").replace("%mobname%", m.getName()));
                        }
                    }
                  }
                        else{
                        if (Material.getMaterial(args[2].toUpperCase()) != null) {
                            ItemStack i = new ItemStack(Material.getMaterial(args[2]));
                            m.setWeapon(i);
                            sender.sendMessage(Msgs.messages.get("Set-Weapon").replace("%mobname%", m.getName()));
                        }
                        else {
                            sender.sendMessage(Msgs.messages.get("Not-A-Material"));
                        }
                    }
                }
               }
            }
           else if (args[1].equalsIgnoreCase("setArmor")){
                if (m != null || m.getEnt() != null){
                    if (args[2] != null){
                        if (args[2].equalsIgnoreCase("slot")) {
                            //get armor from slot
                            if (sender instanceof  Player) {
                                Player p = (Player) sender;
                                if (p.getInventory().getArmorContents() != null || p.getInventory().getArmorContents().length != 0)
                                m.setArmor(Arrays.asList(p.getInventory().getArmorContents()));
                                p.sendMessage(Msgs.messages.get("Set-Armor").replace("%mobname%", m.getName()));
                            }

                        }
                        else {
                            if (Material.getMaterial(args[2]+"_CHESTPLATE") != null) {
                                ItemStack boots = new ItemStack(Material.getMaterial(args[2]+"_BOOTS".toUpperCase()));
                                ItemStack legs = new ItemStack(Material.getMaterial(args[2]+"_LEGGINGS".toUpperCase()));
                                ItemStack chestplate = new ItemStack(Material.getMaterial(args[2]+"_CHESTPLATE".toUpperCase()));
                                ItemStack helmet = new ItemStack(Material.getMaterial(args[2]+"_HELMET"));

                                List<ItemStack> armor = new ArrayList<ItemStack>();
                                armor.add(boots);
                                armor.add(legs);
                                armor.add(chestplate);
                                armor.add(helmet);
                                m.setArmor(armor);
                                sender.sendMessage(Msgs.messages.get("Set-Armor").replace("%mobname%", m.getName()));

                            }
                            else {
                                sender.sendMessage(Msgs.messages.get("Not-Armor-Material"));
                            }
                        }
                    }
                }
            }
            return  true;
        }
            else {
            sender.sendMessage(Msgs.messages.get("Not-A-Mob-Error").replace("%mobname%", args[0])); // not a valid mob
            return  true;
        }
    }

}
