package idk.plugin.dynamicplayercount;

import cn.nukkit.event.player.PlayerKickEvent;
import cn.nukkit.event.server.QueryRegenerateEvent;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.utils.Config;

public class Main extends PluginBase implements Listener {

    private Config c;

    public void onEnable() {
        saveDefaultConfig();
        c = getConfig();
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void query(QueryRegenerateEvent e) {
        if (!c.getBoolean("noPlayerLimit") && e.getPlayerCount() >= getServer().getMaxPlayers()) return;
        e.setMaxPlayerCount(e.getPlayerCount() + 1);
    }

    @EventHandler
    public void kick(PlayerKickEvent e) {
        if (e.getReasonEnum() == PlayerKickEvent.Reason.SERVER_FULL && c.getBoolean("noPlayerLimit")) {
            e.setCancelled(true);
        }
    }
}
