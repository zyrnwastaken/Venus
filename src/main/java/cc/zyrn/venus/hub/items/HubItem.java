package cc.zyrn.venus.hub.items;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public interface HubItem {

    ItemStack getItem();
    int getSlot();
    void use(Player player, PlayerInteractEvent playerInteractEvent);

}
