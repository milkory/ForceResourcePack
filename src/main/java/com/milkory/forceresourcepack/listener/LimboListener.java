package com.milkory.forceresourcepack.listener;

import com.milkory.forceresourcepack.limbo.FRPLimbo;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.CraftItemEvent;
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
        if (limboCheck(event.getEntity())) {
            ((Cancellable) event).setCancelled(true);
        }
    }

    private boolean limboCheck(Entity entity) {
        return entity instanceof Player && FRPLimbo.getInstance().has((Player) entity);
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

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        limboCancel(event);
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event) {
        limboCancel(event);
    }

    @EventHandler(ignoreCancelled = true)
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        event.setCancelled(limboCheck(event.getDamager()));
    }

    @EventHandler(ignoreCancelled = true)
    public void onCraft(CraftItemEvent event) {
        event.setCancelled(limboCheck(event.getWhoClicked()));
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
