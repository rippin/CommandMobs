package rippin.bullyscraft.commandmobs;

import org.bukkit.ChatColor;
import rippin.bullyscraft.commandmobs.Configs.MessagesConfig;

import java.util.HashMap;

    public class Msgs {

    public static HashMap<String, String> messages = new HashMap<String, String>();

       public static void loadMessages(){
        messages.clear();
           for (String key : MessagesConfig.getConfig().getKeys(true)){
           messages.put(key, Utils.prefix + ChatColor.translateAlternateColorCodes('&', MessagesConfig.getConfig().getString(key)));
       }
      }

    }
