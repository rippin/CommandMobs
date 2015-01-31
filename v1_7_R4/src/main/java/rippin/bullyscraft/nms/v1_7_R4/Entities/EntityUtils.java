package rippin.bullyscraft.nms.v1_7_R4.Entities;



import net.minecraft.server.v1_7_R4.World;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_7_R4.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.lang.reflect.Field;
import java.util.Map;

public class EntityUtils {

    public static Entity spawnCustomEntity(Location loc, String str)
    {

        World nmsWorld = ((CraftWorld) loc.getWorld()).getHandle();
        if (str.equalsIgnoreCase("BAT")) {
            CustomBat custom = new CustomBat(nmsWorld);
            custom.setPositionRotation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
            nmsWorld.addEntity(custom, CreatureSpawnEvent.SpawnReason.CUSTOM);
            return custom.getBukkitEntity();
        }
        else if (str.equalsIgnoreCase("WITHER_SKELETON")){
            CustomWitherSkeleton custom = new CustomWitherSkeleton(nmsWorld);
            custom.setPosition(loc.getX(), loc.getY(), loc.getZ());
            nmsWorld.addEntity(custom, CreatureSpawnEvent.SpawnReason.CUSTOM);
            return custom.getBukkitEntity();
        }

        else if (str.equalsIgnoreCase("BLAZE")){
            CustomBlaze custom = new CustomBlaze(nmsWorld);
            custom.setPosition(loc.getX(), loc.getY(), loc.getZ());
            nmsWorld.addEntity(custom, CreatureSpawnEvent.SpawnReason.CUSTOM);
            return custom.getBukkitEntity();
        }
        else if (str.equalsIgnoreCase("CAVE_SPIDER")){
            CustomCaveSpider custom = new CustomCaveSpider(nmsWorld);
            custom.setPosition(loc.getX(), loc.getY(), loc.getZ());
            nmsWorld.addEntity(custom, CreatureSpawnEvent.SpawnReason.CUSTOM);
            return custom.getBukkitEntity();
        }
        else if (str.equalsIgnoreCase("CHICKEN")){
            CustomChicken custom = new CustomChicken(nmsWorld);
            custom.setPosition(loc.getX(), loc.getY(), loc.getZ());
            nmsWorld.addEntity(custom, CreatureSpawnEvent.SpawnReason.CUSTOM);
            return custom.getBukkitEntity();
        }
        else if (str.equalsIgnoreCase("COW")){
            CustomCow custom = new CustomCow(nmsWorld);
            custom.setPosition(loc.getX(), loc.getY(), loc.getZ());
            nmsWorld.addEntity(custom, CreatureSpawnEvent.SpawnReason.CUSTOM);
            return custom.getBukkitEntity();
        }
        else if (str.equalsIgnoreCase("CREEPER")){
            CustomCreeper custom = new CustomCreeper(nmsWorld);
            custom.setPosition(loc.getX(), loc.getY(), loc.getZ());
            nmsWorld.addEntity(custom, CreatureSpawnEvent.SpawnReason.CUSTOM);
            return custom.getBukkitEntity();
        }
        else if (str.equalsIgnoreCase("ENDER_DRAGON")){
            CustomEnderDragon custom = new CustomEnderDragon(nmsWorld);
            custom.setPosition(loc.getX(), loc.getY(), loc.getZ());
            nmsWorld.addEntity(custom, CreatureSpawnEvent.SpawnReason.CUSTOM);
            return custom.getBukkitEntity();
        }
        else if (str.equalsIgnoreCase("GHAST")){
            CustomGhast custom = new CustomGhast(nmsWorld);
            custom.setPosition(loc.getX(), loc.getY(), loc.getZ());
            nmsWorld.addEntity(custom, CreatureSpawnEvent.SpawnReason.CUSTOM);
            return custom.getBukkitEntity();
        }
        else if (str.equalsIgnoreCase("GIANT")){
            CustomGiant custom = new CustomGiant(nmsWorld);
            custom.setPosition(loc.getX(), loc.getY(), loc.getZ());
            nmsWorld.addEntity(custom, CreatureSpawnEvent.SpawnReason.CUSTOM);
            return custom.getBukkitEntity();
        }

        else if (str.equalsIgnoreCase("HORSE")){
            CustomHorse custom = new CustomHorse(nmsWorld);
            custom.setPosition(loc.getX(), loc.getY(), loc.getZ());
            nmsWorld.addEntity(custom, CreatureSpawnEvent.SpawnReason.CUSTOM);
            return custom.getBukkitEntity();
        }
        else if (str.equalsIgnoreCase("IRON_GOLEM")){
            CustomIronGolem custom = new CustomIronGolem(nmsWorld);
            custom.setPosition(loc.getX(), loc.getY(), loc.getZ());
            nmsWorld.addEntity(custom, CreatureSpawnEvent.SpawnReason.CUSTOM);
            return custom.getBukkitEntity();
        }
        else if (str.equalsIgnoreCase("MAGMA_CUBE")){
            CustomMagma custom = new CustomMagma(nmsWorld);
            custom.setPosition(loc.getX(), loc.getY(), loc.getZ());
            nmsWorld.addEntity(custom, CreatureSpawnEvent.SpawnReason.CUSTOM);
            return custom.getBukkitEntity();
        }
        else if (str.equalsIgnoreCase("MUSHROOM_COW")){
            CustomMushroomCow custom = new CustomMushroomCow(nmsWorld);
            custom.setPosition(loc.getX(), loc.getY(), loc.getZ());
            nmsWorld.addEntity(custom, CreatureSpawnEvent.SpawnReason.CUSTOM);
            return custom.getBukkitEntity();
        }
        else if (str.equalsIgnoreCase("OCELOT")){
            CustomOcelot custom = new CustomOcelot(nmsWorld);
            custom.setPosition(loc.getX(), loc.getY(), loc.getZ());
            nmsWorld.addEntity(custom, CreatureSpawnEvent.SpawnReason.CUSTOM);
            return custom.getBukkitEntity();
        }
        else if (str.equalsIgnoreCase("PIG")){
            CustomPig custom = new CustomPig(nmsWorld);
            custom.setPosition(loc.getX(), loc.getY(), loc.getZ());
            nmsWorld.addEntity(custom, CreatureSpawnEvent.SpawnReason.CUSTOM);
            return custom.getBukkitEntity();
        }
        else if (str.equalsIgnoreCase("PIG_ZOMBIE")){
            CustomPigZombie custom = new CustomPigZombie(nmsWorld);
            custom.setPosition(loc.getX(), loc.getY(), loc.getZ());
            nmsWorld.addEntity(custom, CreatureSpawnEvent.SpawnReason.CUSTOM);
            return custom.getBukkitEntity();
        }
        else if (str.equalsIgnoreCase("SHEEP")){
            CustomSheep custom = new CustomSheep(nmsWorld);
            custom.setPosition(loc.getX(), loc.getY(), loc.getZ());
            nmsWorld.addEntity(custom, CreatureSpawnEvent.SpawnReason.CUSTOM);
            return custom.getBukkitEntity();
        }
        else if (str.equalsIgnoreCase("SILVERFISH")){
            CustomSilverfish custom = new CustomSilverfish(nmsWorld);
            custom.setPosition(loc.getX(), loc.getY(), loc.getZ());
            nmsWorld.addEntity(custom, CreatureSpawnEvent.SpawnReason.CUSTOM);
            return custom.getBukkitEntity();
        }
        else if (str.equalsIgnoreCase("SKELETON")){
            CustomSkeleton custom = new CustomSkeleton(nmsWorld);
            custom.setPosition(loc.getX(), loc.getY(), loc.getZ());
            nmsWorld.addEntity(custom, CreatureSpawnEvent.SpawnReason.CUSTOM);
            return custom.getBukkitEntity();
        }
        else if (str.equalsIgnoreCase("SLIME")){
            CustomSlime custom = new CustomSlime(nmsWorld);
            custom.setPosition(loc.getX(), loc.getY(), loc.getZ());
            nmsWorld.addEntity(custom, CreatureSpawnEvent.SpawnReason.CUSTOM);
            return custom.getBukkitEntity();
        }
        else if (str.equalsIgnoreCase("SNOWMAN")){
            CustomSnowman custom = new CustomSnowman(nmsWorld);
            custom.setPosition(loc.getX(), loc.getY(), loc.getZ());
            nmsWorld.addEntity(custom, CreatureSpawnEvent.SpawnReason.CUSTOM);
            return custom.getBukkitEntity();
        }
        else if (str.equalsIgnoreCase("SPIDER")){
            CustomSpider custom = new CustomSpider(nmsWorld);
            custom.setPosition(loc.getX(), loc.getY(), loc.getZ());
            nmsWorld.addEntity(custom, CreatureSpawnEvent.SpawnReason.CUSTOM);
            return custom.getBukkitEntity();
        }
        else if (str.equalsIgnoreCase("SQUID")){
            CustomSquid custom = new CustomSquid(nmsWorld);
            custom.setPosition(loc.getX(), loc.getY(), loc.getZ());
            nmsWorld.addEntity(custom, CreatureSpawnEvent.SpawnReason.CUSTOM);
            return custom.getBukkitEntity();
        }
        else if (str.equalsIgnoreCase("VILLAGER")){
            CustomVillager custom = new CustomVillager(nmsWorld);
            custom.setPosition(loc.getX(), loc.getY(), loc.getZ());
            nmsWorld.addEntity(custom, CreatureSpawnEvent.SpawnReason.CUSTOM);
            return custom.getBukkitEntity();
        }
        else if (str.equalsIgnoreCase("WITCH")){
            CustomWitch custom = new CustomWitch(nmsWorld);
            custom.setPosition(loc.getX(), loc.getY(), loc.getZ());
            nmsWorld.addEntity(custom, CreatureSpawnEvent.SpawnReason.CUSTOM);
            return custom.getBukkitEntity();
        }
        else if (str.equalsIgnoreCase("WITHER")){
            CustomWither custom = new CustomWither(nmsWorld);
            custom.setPosition(loc.getX(), loc.getY(), loc.getZ());
            nmsWorld.addEntity(custom, CreatureSpawnEvent.SpawnReason.CUSTOM);
            return custom.getBukkitEntity();
        }
        else if (str.equalsIgnoreCase("WOLF")){
            CustomWolf custom = new CustomWolf(nmsWorld);
            custom.setPosition(loc.getX(), loc.getY(), loc.getZ());
            nmsWorld.addEntity(custom, CreatureSpawnEvent.SpawnReason.CUSTOM);
            return custom.getBukkitEntity();
        }
        else if (str.equalsIgnoreCase("ZOMBIE")){
            CustomZombie custom = new CustomZombie(nmsWorld);
            custom.setPosition(loc.getX(), loc.getY(), loc.getZ());
            nmsWorld.addEntity(custom, CreatureSpawnEvent.SpawnReason.CUSTOM);
            return custom.getBukkitEntity();
        }
            Entity e = null;
            return e;

    }

    @SuppressWarnings("rawtypes")
    private static Object getPrivateStatic(Class clazz, String f) throws Exception {
        Field field = clazz.getDeclaredField(f);
        field.setAccessible(true);
        return field.get(null);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void addCustomEntity(Class paramClass, String paramString, int paramInt)
    {
        try {
            Class clazz = Class.forName("net.minecraft.server." + parseMinecraftVersion() + ".EntityTypes");
            ((Map) getPrivateStatic(clazz, "c")).put(paramString, paramClass);
            ((Map) getPrivateStatic(clazz, "d")).put(paramClass, paramString);
            //((Map) getPrivateStatic(clazz, "e")).put(Integer.valueOf(paramInt), paramClass);
            ((Map) getPrivateStatic(clazz, "f")).put(paramClass, Integer.valueOf(paramInt));
            ((Map) getPrivateStatic(clazz, "g")).put(paramString, Integer.valueOf(paramInt));
        } catch (Exception exc) {
        }
    }
    public static String parseMinecraftVersion(){
        String name = Bukkit.getServer().getClass().getPackage().getName();
        String version = name.substring(name.lastIndexOf('.') + 1);
        return version;
    }
}
