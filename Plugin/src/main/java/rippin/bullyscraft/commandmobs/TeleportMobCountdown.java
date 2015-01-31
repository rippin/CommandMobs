package rippin.bullyscraft.commandmobs;


public class TeleportMobCountdown {

    private CommandMobs plugin;
    private long ticks;
    public TeleportMobCountdown(CommandMobs plugin, long ticks){
        this.plugin = plugin;
        this.ticks = ticks;
    }

    public void startCountdown(){
        plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
             for (Mob m :MobsManager.getAllMobs()){
                 if (m != null) {
                 if (m.isEnable() && m.getEnt() != null){
                     m.getEnt().teleport(m.getLoc());
                 }
               }
             }
            }
        },1L, ticks);
    }


}
