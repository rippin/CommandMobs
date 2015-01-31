package rippin.bullyscraft.commandmobs;



import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.logging.Level;

public class VaultHook {

    public static Economy economy = null;

    public static boolean hasAmount(Player player, int amount){
    if (economy == null) { return false; }
      if (economy.has(player, amount)){
            return true;
       }
      else {
      return  false;
    }
}

    public static void deductAmount(Player player, int amount){
       EconomyResponse re = economy.withdrawPlayer(player, amount);
        if (re.transactionSuccess()){
            //success message here
        }
        else {
            player.sendMessage(re.errorMessage);
        }
    }


    public static boolean setupEconomy(CommandMobs plugin)
    {
        RegisteredServiceProvider<Economy> economyProvider = plugin.getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
            plugin.getLogger().log(Level.INFO, "Vault dependency has been found.");
        }

        return (economy != null);
    }


}
