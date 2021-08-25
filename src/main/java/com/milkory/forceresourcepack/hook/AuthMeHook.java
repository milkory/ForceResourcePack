package com.milkory.forceresourcepack.hook;

import com.milkory.forceresourcepack.common.Logger;
import fr.xephi.authme.AuthMe;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Nullable;

/**
 * @author Milkory
 */
public class AuthMeHook {

    @Getter private static final AuthMeHook hook = new AuthMeHook();

    @Getter @Nullable private AuthMe plugin;

    public boolean hook() {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("Authme");
        if (plugin == null) return false;
        this.plugin = (AuthMe) plugin;
        Logger.info("Hooked into Authme.");
        return true;
    }

}
