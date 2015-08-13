package rippin.bullyscraft.commandmobs;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;


public class CommandDelayCountdown {
    private Mob m;
    private CommandMobs plugin;
    private  Player player;
    private CommandSender sender;
    private BukkitTask task;

    public CommandDelayCountdown(CommandMobs plugin, Mob m, Player player, CommandSender sender){
    this.plugin = plugin;
        this.m = m;
        this.player = player;
        this.sender = sender;
    }
    public void startCountdown(){
        final List<String> cmds = m.getCommands();
        task = plugin.getServer().getScheduler().runTaskTimer(plugin, new Runnable() {
            int j = 0;

            public void run() {
                if (j < cmds.size()) {
                    if (m.getSound() != null) {
                        player.playSound(player.getLocation(), m.getSound(), 10, 1);
                    }
                    Bukkit.getServer().dispatchCommand(sender, cmds.get(j).replace("%player%", player.getName()));
                } else {
                    getTask().cancel();

                }
                ++j;
            }
        }, 1L, m.getCommandDelay() * 20);
    }

    public BukkitTask getTask(){
        return  task;
    }

}
