package com.milkory.forceresourcepack.hook;

import com.milkory.forceresourcepack.common.Logger;
import com.onarandombox.MultiverseCore.MultiverseCore;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Nullable;

/**
 * @author Milkory
 */
public class MultiverseCoreHook {

    @Getter private static final MultiverseCoreHook hook = new MultiverseCoreHook();

    @Getter @Nullable private MultiverseCore plugin;

    public void hook() {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("Multiverse-Core");
        if (plugin == null) return;
        this.plugin = (MultiverseCore) plugin;
        Logger.info("Hooked into Multiverse-Core.");
    }

    public void teleport(Player player, Location location) {
        if (plugin != null) {
            plugin.teleportPlayer(Bukkit.getConsoleSender(), player, location);
        } else player.teleport(location);
    }

}
