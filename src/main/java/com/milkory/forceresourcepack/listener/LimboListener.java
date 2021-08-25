package com.milkory.forceresourcepack.listener;

import com.milkory.forceresourcepack.FRPLimbo;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.*;

/**
 * @author Milkory
 */
public class LimboListener implements Listener {

    private void limboCancel(PlayerEvent event) {
        if (limboCheck(event.getPlayer())) {
            ((Cancellable) event).setCancelled(true);
        }
    }

    private void limboCancel(EntityEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        if (limboCheck((Player) event.getEntity())) {
            ((Cancellable) event).setCancelled(true);
        }
    }

    private boolean limboCheck(Player player) {
        return FRPLimbo.getInstance().getPlayerToLastLocation().containsKey(player);
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        limboCancel(event);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        limboCancel(event);
    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent event) {
        limboCancel(event);
    }

    @EventHandler
    public void onEggThrow(PlayerEggThrowEvent event) {
        limboCancel(event);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        limboCancel(event);
    }

    @EventHandler
    public void onPickupItem(EntityPickupItemEvent event) {
        limboCancel(event);
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent event) {
        event.setCancelled(limboCheck(event.getPlayer()));
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {
        event.setCancelled(limboCheck(event.getPlayer()));
    }

}
