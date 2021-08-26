package com.milkory.forceresourcepack.listener;

import com.milkory.forceresourcepack.FRPLimbo;
import com.milkory.forceresourcepack.ResourcePackManager;
import com.milkory.forceresourcepack.common.Message;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

/**
 * @author Milkory
 */
public class CommonListener implements Listener {

    private static final ResourcePackManager manager = ResourcePackManager.getInstance();

    @EventHandler
    public void onPlayerRespond(PlayerResourcePackStatusEvent event) {
        Player player = event.getPlayer();
        switch (event.getStatus()) {
            case ACCEPTED:
                Message.sendTo(player, "accepted");
                break;
            case DECLINED:
                Message.sendTo(player, "declined");
                break;
            case FAILED_DOWNLOAD:
                Message.sendTo(player, "failed-download");
                break;
            case SUCCESSFULLY_LOADED:
                Message.sendTo(player, "successfully-loaded");
                FRPLimbo.getInstance().remove(player);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        FRPLimbo.getInstance().remove(event.getPlayer());
    }

}
