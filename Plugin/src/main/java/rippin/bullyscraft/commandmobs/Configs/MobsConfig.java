package rippin.bullyscraft.commandmobs.Configs;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import rippin.bullyscraft.commandmobs.CommandMobs;
import rippin.bullyscraft.commandmobs.MobsManager;

import java.io.*;

public class MobsConfig {
    private static CommandMobs plugin;
    private static FileConfiguration config;
    private static File configFile;



    public static void setUp(CommandMobs pluginInstance){
        plugin = pluginInstance;
        configFile = new File(plugin.getDataFolder(), "mobs.yml");
        config = YamlConfiguration.loadConfiguration(configFile);
        create();
    }

    private static void create(){
        if (!(getFile().exists())){
            plugin.getServer().getLogger().info("mobs.yml not found. Creating now...");
            try {
                plugin.getDataFolder().mkdir();
                getFile().createNewFile();
                copy(plugin.getResource("mobs.yml"), configFile);
                reload(); //reload yml just in case

                saveFile();
                plugin.getServer().getLogger().info("mobs.yml has been created!");
            } catch (IOException e) {

                e.printStackTrace();
            }
        }

    }

    private static void copy(InputStream in, File file) {
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while((len=in.read(buf))>0){
                out.write(buf,0,len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static File getFile(){
        return configFile;
    }

    public static FileConfiguration getConfig(){
        return config;
    }

    public static void saveFile() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void reload(){
        config = YamlConfiguration.loadConfiguration(configFile);
        MobsManager.loadMobs(plugin);
    }
}
