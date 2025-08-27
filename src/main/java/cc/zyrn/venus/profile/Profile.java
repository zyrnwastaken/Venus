package cc.zyrn.venus.profile;

import cc.zyrn.venus.queue.priority.Priority;
import cc.zyrn.venus.util.ConfigFile;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.UUID;

@Getter
@Setter
public class Profile {

    private final UUID uuid;

    private Priority priority;
    private int kills, deaths, coins;

    public Profile(UUID uuid) {
        this.uuid = uuid;
    }

    public Profile(ConfigurationSection configurationSection) {
        this.uuid = UUID.fromString(configurationSection.getName());

        this.coins = configurationSection.getInt("coins");
        this.deaths = configurationSection.getInt("deaths");
        this.kills = configurationSection.getInt("kills");
    }

    @SneakyThrows
    public void save(ConfigFile configFile) {
        final FileConfiguration fileConfiguration = configFile.getConfig();

        fileConfiguration.set("profiles." + uuid.toString() + ".kills", kills);
        fileConfiguration.set("profiles." + uuid.toString() + ".deaths", deaths);
        fileConfiguration.set("profiles." + uuid.toString() + ".coins", coins);

        configFile.save();
    }

}
