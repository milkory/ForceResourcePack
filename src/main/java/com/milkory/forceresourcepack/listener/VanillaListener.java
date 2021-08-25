package com.milkory.forceresourcepack.listener;

import com.milkory.forceresourcepack.ResourcePackManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * @author Milkory
 */
public class VanillaListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        ResourcePackManager.getInstance().loadResourcePackFor(event.getPlayer());
    }

}
