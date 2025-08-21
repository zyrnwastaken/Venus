package cc.zyrn.venus.profile;

import org.bukkit.configuration.ConfigurationSection;

import java.util.UUID;

public class Profile {

    private final UUID uuid;

    public Profile(UUID uuid) {
        this.uuid = uuid;
    }

    public Profile(ConfigurationSection configurationSection) {
        this.uuid = UUID.fromString(configurationSection.getName());
    }

    public void save() {

    }

}
