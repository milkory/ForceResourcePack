package com.milkory.forceresourcepack.listener;

import com.milkory.forceresourcepack.ResourcePackManager;
import fr.xephi.authme.events.LoginEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * @author Milkory
 */
public class AuthMeListener implements Listener {

    @EventHandler
    public void onPlayerLogin(LoginEvent event) {
        ResourcePackManager.getInstance().loadResourcePackFor(event.getPlayer());
    }

}
