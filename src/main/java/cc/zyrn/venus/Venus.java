package cc.zyrn.venus;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class Venus extends JavaPlugin {

    @Getter
    private static Venus instance;

    @Override
    public void onLoad() {
        instance = this;
        this.saveDefaultConfig();
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}