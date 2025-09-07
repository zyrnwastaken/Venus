package cc.zyrn.venus.hub.listener;

import cc.zyrn.venus.hub.HubHandler;
import lombok.AllArgsConstructor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityAirChangeEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

@AllArgsConstructor
public class HubListener implements Listener {

    private final HubHandler hubHandler;

    @EventHandler
    public final void onPlayerLoginEvent(PlayerLoginEvent event) {
        final Player player = event.getPlayer();

        if (hubHandler.getHubSettings().isDoubleJump())
            player.setAllowFlight(true);

        player.getInventory().clear();
        hubHandler.getHubItems().forEach(hubItem -> player.getInventory().setItem(hubItem.getSlot(), hubItem.getItem()));
        hubHandler.getHubSettings().getSpawnLocation().ifPresent(player::teleport);
    }

    @EventHandler
    public final void onPlayerInteractEvent(PlayerInteractEvent event) {
        final Player player = event.getPlayer();

        if (!event.getAction().equals(Action.RIGHT_CLICK_AIR) && !event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
            return;

        if (event.getHand() != EquipmentSlot.HAND)
            return;

        final ItemStack stack = player.getInventory().getItemInMainHand();

        if (stack.getType().equals(Material.AIR))
            return;

        hubHandler.getHubItem(stack, player.getInventory().getHeldItemSlot())
                .ifPresent(hubItem -> hubItem.use(player, event));
    }

    @EventHandler
    public final void onPlayerDamageEvent(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player)
            event.setCancelled(true);
    }

    @EventHandler
    public final void onHungerLossEvent(FoodLevelChangeEvent event) {
        if (event.getEntity() instanceof Player)
            event.setCancelled(true);
    }

    @EventHandler
    public final void onDrownEvent(EntityAirChangeEvent event) {
        if (event.getEntity() instanceof Player)
            event.setCancelled(true);
    }

    @EventHandler
    public final void onPlayerFlyEvent(PlayerToggleFlightEvent event) {
        if (!hubHandler.getHubSettings().isDoubleJump())
            return;

        final Player player = event.getPlayer();

        if (!player.getAllowFlight())
            return;

        event.setCancelled(true);
        player.setVelocity(player.getLocation().getDirection().add(new Vector(0, 1.5, 0)));
        player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
    }

}
