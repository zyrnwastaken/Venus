package cc.zyrn.venus.hub.settings;

import cc.zyrn.venus.Venus;
import cc.zyrn.venus.util.LocationUtil;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

import java.util.Optional;

@Getter
@Setter
public class HubSettings {

    private Optional<Location> spawnLocation;
    private boolean doubleJump;

    public HubSettings(Venus venus) {
        this.spawnLocation = LocationUtil.STL(venus.getConfig().getString("settings.spawn-location"));
        this.doubleJump = venus.getConfig().getBoolean("settings.double-jump");
    }

}
