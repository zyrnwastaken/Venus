package cc.zyrn.venus;

import cc.zyrn.venus.hub.HubHandler;
import cc.zyrn.venus.profile.ProfileHandler;
import cc.zyrn.venus.queue.QueueHandler;
import cc.zyrn.venus.util.ConfigFile;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class Venus extends JavaPlugin {

    @Getter
    private static Venus instance;

    private QueueHandler queueHandler;
    private HubHandler hubHandler;
    private ProfileHandler profileHandler;

    private ConfigFile priorityFile;
    private ConfigFile profilesFile;
    private ConfigFile queuesFile;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        this.priorityFile = new ConfigFile(this.getDataFolder(), "priority.yml");
        this.queuesFile = new ConfigFile(this.getDataFolder(), "queues.yml");
        this.profilesFile = new ConfigFile(this.getDataFolder(), "profiles.yml");

        this.queueHandler = new QueueHandler(this);
        this.hubHandler = new HubHandler(this);
        this.profileHandler = new ProfileHandler(this);
    }

}