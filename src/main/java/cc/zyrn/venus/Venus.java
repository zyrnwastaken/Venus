package cc.zyrn.venus;

import cc.zyrn.venus.util.ConfigFile;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class Venus extends JavaPlugin {

    @Getter
    private static Venus instance;

    private ConfigFile priorityFile;

    @Override
    public void onLoad() {
        instance = this;
        this.saveDefaultConfig();
    }

    @Override
    public void onEnable() {
        this.priorityFile = new ConfigFile(this.getDataFolder(), "priority.yml");
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}