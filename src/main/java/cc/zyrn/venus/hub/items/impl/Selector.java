package cc.zyrn.venus.hub.items.impl;

import cc.zyrn.venus.hub.items.HubItem;
import cc.zyrn.venus.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Selector implements HubItem {

    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.COMPASS).setName("&eSelector")
                .setLore("&7Use this tool to open the server selector!")
                .setGlow(true).getStack();
    }

    @Override
    public int getSlot() {
        return 4;
    }

    @Override
    public void use(Player player, PlayerInteractEvent playerInteractEvent) {

    }

}
