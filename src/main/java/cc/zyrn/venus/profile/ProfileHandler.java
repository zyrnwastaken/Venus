package cc.zyrn.venus.profile;

import cc.zyrn.venus.Venus;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProfileHandler {

    private final Map<UUID, Profile> profileMap;
    private final Venus venus;

    public ProfileHandler(Venus venus) {
        this.venus = venus;
        this.profileMap = new HashMap<>();
    }



}
