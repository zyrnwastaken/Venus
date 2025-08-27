package cc.zyrn.venus.hub.items;

import org.bukkit.inventory.ItemStack;

public interface HubItem {

    ItemStack getItem();
    int getSlot();
    void use();

}
