package rippin.bullyscraft.commandmobs;



import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;


import java.lang.reflect.Field;
import java.util.*;

public class Utils {
    public static final String prefix = ChatColor.GRAY + "[" + ChatColor.DARK_RED + "BullyMobs" + ChatColor.GRAY + "] ";

    public static Location parseLoc(String string){

        String split[] = string.split(":");
        if (split.length > 4) {
            return new Location(Bukkit.getWorld(split[0]), Double.parseDouble(split[1]),
                    Double.parseDouble(split[2]), Double.parseDouble(split[3]), Float.parseFloat(split[4]), Float.parseFloat(split[5]));
        }
        else {
        return new Location(Bukkit.getWorld(split[0]), Double.parseDouble(split[1]),Double.parseDouble(split[2]), Double.parseDouble(split[3]));
        }
    }


    public static String serializeLoc(Location loc){
        return new String(loc.getWorld().getName() + ":" + loc.getX() + ":" + loc.getY() + ":" + loc.getZ());
    }
    public static String serializeCompleteLoc(Location loc){
        return new String(loc.getWorld().getName() + ":" + loc.getX() + ":" + loc.getY()
                + ":" + loc.getZ()) + ":" + loc.getYaw() + ":" + loc.getPitch();
    }

    public static List<Location> getSpawns(List<String> strings){
        List<Location> locs = new ArrayList<Location>();

        for (String s : strings){
            locs.add(parseLoc(s));
        }
        return locs;
    }

    public static String parseMinecraftVersion(){
        String name = Bukkit.getServer().getClass().getPackage().getName();
        String version = name.substring(name.lastIndexOf('.') + 1);
        return version;
    }

    private static void setValue(Object instance, String fieldName, Object value) throws Exception {
        Field field = instance.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(instance, value);
    }


}
