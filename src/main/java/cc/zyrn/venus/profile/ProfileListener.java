package cc.zyrn.venus.profile;

import cc.zyrn.venus.Venus;
import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

@AllArgsConstructor
public class ProfileListener implements Listener {

    private final ProfileHandler profileHandler;
    private final Venus venus;

    @EventHandler
    public final void onPlayerJoinEvent(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        profileHandler.loadProfile(player.getUniqueId()).setPriority(venus.getQueueHandler().getPriority(player));
    }

    @EventHandler
    public final void onPlayerQuitEvent(PlayerQuitEvent event) {
        profileHandler.getProfile(event.getPlayer().getUniqueId()).ifPresent(profileHandler::handleRemoval);
    }

}
