package rippin.bullyscraft.commandmobs.Configs;

import rippin.bullyscraft.commandmobs.CommandMobs;

/**
 * Created by EF on 1/19/15.
 */
public class ConfigManager {

    public static void setupFiles(CommandMobs plugin){
        Config.setUp(plugin);
        MobsConfig.setUp(plugin);
        MessagesConfig.setUp(plugin);
    }
    public static void reloadConfigs(CommandMobs plugin){
        Config.reload();
        MobsConfig.reload();
        MessagesConfig.reload();
    }


}
