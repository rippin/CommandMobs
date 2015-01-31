package rippin.bullyscraft.commandmobs;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import rippin.bullyscraft.commandmobs.Configs.MobsConfig;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Mob {
    private String name;
    private List<ItemStack> armor = new ArrayList<ItemStack>();
    private ItemStack weapon;
    private Set<PotionEffect> potions = new HashSet<PotionEffect>();
    private double health;
    private String displayName;
    private FileConfiguration config;
    private String type;
    private String vehicle;
    private boolean baby = false;
    private Sound sound;
    private LivingEntity ent;
    private List<String> commands = new ArrayList<String>();
    private String permission;
    private int amount;
    private Location loc;
    private boolean enable = false;
    private boolean commandAsConsole = true;

    public Mob(String name){
        this.name = name;
        config = MobsConfig.getConfig();
        loadData();
    }

    public void loadData(){
        if (config.getBoolean("Mobs." + name + ".Enable"))
            this.enable = true;
        if (config.getString("Mobs." + name + ".Permission") != null)
            permission = config.getString("Mobs." + name + ".Permission");
        if (config.getString("Mobs." + name + ".Location") != null)
            loc = Utils.parseLoc(config.getString("Mobs." + name + ".Location"));
        if (config.getInt("Mobs." + name + ".Cost") > 0)
            amount = config.getInt("Mobs." + name + ".Cost");
            commandAsConsole = config.getBoolean("Mobs." + name + ".CommandAsConsole");

        if (config.getString("Mobs." + name + ".Weapon") != null)
        weapon = ParseItems.parseItems(config.getString("Mobs." + name + ".Weapon"));
        if (config.getStringList("Mobs." + name + ".Armor") != null)
        armor = ParseItems.getArmor(config.getStringList("Mobs." + name + ".Armor"));
        if (config.getStringList("Mobs." + name + ".Potions") != null)
        potions = ParseItems.parsePotions(config.getStringList("Mobs." + name + ".Potions"));
        if (config.getString("Mobs." + name + ".DisplayName") != null)
        displayName = config.getString("Mobs." + name + ".DisplayName");
        if ((Double) config.getDouble("Mobs." + name + ".Health") != null)
        health = config.getDouble("Mobs." + name + ".Health");
        if (config.getString("Mobs." + name + ".Type") != null)
            type = config.getString("Mobs." + name + ".Type");
        if (config.getString("Mobs." + name + ".Vehicle") != null){
            this.vehicle = config.getString("Mobs." + name + ".Vehicle");
        }
        if (config.getBoolean("Mobs." + name + ".Baby"))
            baby = config.getBoolean("Mobs." + name + ".Baby");
        if (config.getString("Mobs." + name + ".Sound") != null)
            sound = Sound.valueOf(config.getString("Mobs." + name + ".Sound").toUpperCase());
        if (config.getStringList("Mobs." + name + ".Commands") != null)
            commands = config.getStringList("Mobs." + name + ".Commands");
    }

    public LivingEntity spawnMob(CommandSender sender){

        if (!isEnable()){
            sender.sendMessage(Msgs.messages.get("Mob-is-Disabled").replace("%mobname%", getName()));
            return null;
        }

        if (type == null){
         Msgs.messages.get("Spawn-Error-Type").replace("%mobname", getName());
             return null;
        }
        if (loc == null){
            Msgs.messages.get("Spawn-Error-Loc").replace("%mobname", getName());
            return  null;
        }

        if (!loc.getChunk().isLoaded()){
            loc.getChunk().load();
        }
        //LivingEntity ent = (LivingEntity) Bukkit.getServer().getWorld(loc.getWorld().getName()).spawnEntity(loc, EntityType.valueOf(type.toUpperCase()));
        try {
        /* use reflection */
        Class<?> clazz = Class.forName("rippin.bullyscraft.nms." + Utils.parseMinecraftVersion() + ".Entities.EntityUtils");
        Method m = clazz.getMethod("spawnCustomEntity", Location.class, String.class);

        LivingEntity ent = (LivingEntity) m.invoke(null, loc, type.toString());
            setEnt(ent);
        } catch (Exception e){
            e.printStackTrace();
        }
        //check if baby
        if (ent instanceof Ageable){
            if (baby)
            ((Ageable) ent).setBaby();
            ((Ageable) ent).setAgeLock(true);
        }
        EntityEquipment ee = ent.getEquipment();
     if (weapon != null){
         ee.setItemInHand(weapon);
     }
     if (armor != null && !armor.isEmpty()){
         ee.setArmorContents(armor.toArray(new ItemStack[armor.size()]));
     }
     if (potions != null && !potions.isEmpty()){
        ent.addPotionEffects(potions);
     }
      if (displayName != null){
          ent.setCustomName(ChatColor.translateAlternateColorCodes('&', displayName));
          ent.setCustomNameVisible(true);
      }
            if (health > 0){
            ent.setMaxHealth(health);
            ent.setHealth(health);
        }
        if (vehicle != null){
            if (MobsManager.isMob(vehicle)){
                Mob veh = MobsManager.getMob(vehicle);
                LivingEntity entVeh = veh.spawnMob(sender);
                entVeh.setPassenger(ent);

            }
            else {
                try {
                if (EntityType.valueOf(vehicle) != null){
                    Entity e = Bukkit.getWorld(loc.getWorld().getName()).spawnEntity(loc, EntityType.valueOf(vehicle));
                    if (e instanceof Horse){
                        if (config.getString("Mobs." + name + ".HorseType") != null){
                           //dont bother checking just take string.
                            Horse.Variant variant = Horse.Variant.valueOf(config.getString("Mobs." + name + ".HorseType"));
                            ((Horse) e).setVariant(variant);
                        }
                    }
                    e.setPassenger(ent);
                }
                } catch (IllegalArgumentException ex){
                    System.out.println("Not a valid entitytype.");
                }
            }
        }
        ent.setCanPickupItems(false);
        //
        ent.setRemoveWhenFarAway(false);

        MobsManager.getActiveMobs().add(ent.getUniqueId().toString()+ ":" + getName()); // add to active mobs
        MobsManager.saveActiveMobsToFile();

        return ent;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ItemStack> getArmor() {
        return armor;
    }

    public void setArmor(List<ItemStack> armor) {

        this.armor = armor;
        List<String> s = new ArrayList<String>();
        for (ItemStack i : armor){
            s.add(ParseItems.serializeItem(i));
        }
        config.set("Mobs." + name + ".Armor",s);
        MobsConfig.saveFile();
        if (armor != null && !armor.isEmpty() && getEnt() != null){
            getEnt().getEquipment().setArmorContents(this.armor.toArray(new ItemStack[this.armor.size()]));
        }
    }

    public ItemStack getWeapon() {
        return weapon;
    }

    public void setWeapon(ItemStack weapon) {
        this.weapon = weapon;
        config.set("Mobs." + name + ".Weapon", ParseItems.serializeItem(weapon));
        MobsConfig.saveFile();
        if (weapon != null && getEnt() != null){
            getEnt().getEquipment().setItemInHand(this.weapon);
        }
    }

    public Set<PotionEffect> getPotions() {
        return potions;
    }

    public void setPotions(Set<PotionEffect> potions) {
        this.potions = potions;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
        config.set("Mobs." + name + ".DisplayName", this.displayName);
        MobsConfig.saveFile();

        if (this.displayName != null && getEnt() != null){
            ent.setCustomName(ChatColor.translateAlternateColorCodes('&', this.displayName));
            ent.setCustomNameVisible(true);
        }
    }

    public boolean isBaby() { return baby; }

    public void setBaby(boolean baby) {
        this.baby = baby;
        config.set("Mobs." + name + ".Baby", this.baby);
        if (baby) {
        ((Ageable) ent).setBaby();
        }
        else {
            ((Ageable) ent).setAdult();
        }
        ((Ageable) ent).setAgeLock(true);
        MobsConfig.saveFile();

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        config.set("Mobs." + name + ".Type", this.type);
        MobsConfig.saveFile();
        MobsManager.removeActiveMob(this.getName());
        this.spawnMob(Bukkit.getConsoleSender());

    }

    public Sound getSound() {
        return sound;
    }

    public void setSound(Sound sound) {
        this.sound = sound;
        config.set("Mobs." + name + ".Sound", this.sound.toString());
        MobsConfig.saveFile();
    }

    public LivingEntity getEnt() {
        return ent;
    }

    public void setEnt(LivingEntity ent) {
        this.ent = ent;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
        config.set("Mobs." + name + ".Enable", this.enable);
        MobsConfig.saveFile();
    }

    public List<String> getCommands() {
        return commands;
    }

    public void setCommands(List<String> commands) {
        this.commands.clear();
        this.commands.addAll(commands);
        config.set("Mobs." + name + ".Commands", this.commands);
        MobsConfig.saveFile();
    }

    public void addCommand(String commands) {
        this.commands.add(commands);
        config.set("Mobs." + name + ".Commands", this.commands);
        MobsConfig.saveFile();
    }



    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
        config.set("Mobs." + name + ".Permission", this.permission);
        MobsConfig.saveFile();
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
        config.set("Mobs." + name + ".Cost", this.amount);
        MobsConfig.saveFile();
    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {

        this.loc = loc;
        config.set("Mobs." + name + ".Location", Utils.serializeCompleteLoc(this.loc));
        MobsConfig.saveFile();
    }

    public boolean isCommandAsConsole() {
        return commandAsConsole;
    }

    public void setCommandAsConsole(boolean commandAsConsole) {

        this.commandAsConsole = commandAsConsole;
        config.set("Mobs." + name + ".CommandAsConsole", this.commandAsConsole);
        MobsConfig.saveFile();
    }
}
