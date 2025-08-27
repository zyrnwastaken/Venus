package cc.zyrn.venus.hub.settings;

import cc.zyrn.venus.Venus;
import cc.zyrn.venus.hub.settings.listener.HubSettingsListener;
import cc.zyrn.venus.util.LocationUtil;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

@Getter
@Setter
public class HubSettings {

    private Location spawnLocation;

    public HubSettings(Venus venus) {
        LocationUtil.STL(venus.getConfig().getString("settings.spawn-location")).ifPresent(location -> this.spawnLocation = location);
        venus.getServer().getPluginManager().registerEvents(new HubSettingsListener(), venus);
    }

}
