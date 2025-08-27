package cc.zyrn.venus.profile;

import cc.zyrn.venus.Venus;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class ProfileHandler {

    private final Map<UUID, Profile> profileMap;
    private final Venus venus;

    public ProfileHandler(Venus venus) {
        this.venus = venus;
        this.profileMap = new HashMap<>();

        venus.getServer().getPluginManager().registerEvents(new ProfileListener(this, venus), venus);
    }

    public final Profile loadProfile(UUID uuid) {
        final ConfigurationSection profileSection = venus.getProfilesFile().getConfig().getConfigurationSection("profiles." + uuid);

        if (profileSection == null) {
            return profileMap.put(uuid, new Profile(uuid));
        }

        return profileMap.put(uuid, new Profile(profileSection));
    }

    public final void handleRemoval(Profile profile) {
        profile.save(venus.getProfilesFile());
        profileMap.remove(profile.getUuid());
    }

    public final Optional<Profile> getProfile(UUID uuid) {
        return Optional.ofNullable(profileMap.get(uuid));
    }

}
