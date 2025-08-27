package cc.zyrn.venus.hub;

import cc.zyrn.venus.Venus;
import cc.zyrn.venus.hub.items.HubItem;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HubHandler {

    private final Venus venus;
    private final List<HubItem> hubItems;

    public HubHandler(Venus venus) {
        this.venus = venus;
        this.hubItems = new ArrayList<>();
    }

    public final Optional<HubItem> getHubItem(ItemStack stack, int slot) {
        return hubItems.stream().filter(hubItem -> hubItem.getItem().isSimilar(stack) && hubItem.getSlot() == slot).findFirst();
    }

}
