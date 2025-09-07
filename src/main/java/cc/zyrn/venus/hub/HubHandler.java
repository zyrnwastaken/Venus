package cc.zyrn.venus.hub;

import cc.zyrn.venus.Venus;
import cc.zyrn.venus.hub.items.HubItem;
import cc.zyrn.venus.hub.listener.HubListener;
import cc.zyrn.venus.hub.settings.HubSettings;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
public class HubHandler {

    private final Venus venus;

    private final List<HubItem> hubItems;
    private final HubSettings hubSettings;

    public HubHandler(Venus venus) {
        this.venus = venus;

        this.hubItems = new ArrayList<>();
        this.hubSettings = new HubSettings(venus);

        venus.getServer().getPluginManager().registerEvents(new HubListener(this), venus);
    }

    public final Optional<HubItem> getHubItem(ItemStack stack, int slot) {
        return hubItems.stream().filter(hubItem -> hubItem.getItem().isSimilar(stack) && hubItem.getSlot() == slot).findFirst();
    }

}
