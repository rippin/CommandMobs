package rippin.bullyscraft.commandmobs;

import org.bukkit.plugin.java.JavaPlugin;
import rippin.bullyscraft.commandmobs.Configs.ConfigManager;


import java.lang.reflect.Method;
import java.util.logging.Logger;

public class CommandMobs extends JavaPlugin {

    public Logger logger = Logger.getLogger("Minecraft");
    public static CommandMobs instance;
    private boolean flag = false;

    public void onLoad(){
        try {
            Class<?> clazz = Class.forName("rippin.bullyscraft.nms." + Utils.parseMinecraftVersion() + ".Entities.NMSHandler");
            Method m = clazz.getMethod("loadEntities");
            m.invoke(null);


        } catch (Exception e){

            e.printStackTrace();
            flag = true;
            this.getLogger().severe("Could not find support for this Minecraft version.");
        }
    }
    public void onEnable(){
     if (flag) this.setEnabled(false);
        instance = this;
        ConfigManager.setupFiles(this);
        Msgs.loadMessages();
        MobsManager.loadMobs(this);
   getServer().getPluginManager().registerEvents(new MobManagerListener(this), this);
   // new TeleportMobCountdown(this, getConfig().getLong("Teleport-Task-Ticks")).startCountdown();
    this.getCommand("commandmobs").setExecutor(new BMCommands(this));
    VaultHook.setupEconomy(this);

    logger.info(" has been enabled");
    }

    public void onDisable(){
        instance = null;
        MobsManager.removeAllActiveMobs();
        logger.info(" has been enabled");

    }

}
