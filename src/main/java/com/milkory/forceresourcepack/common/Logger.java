package com.milkory.forceresourcepack.common;

import com.milkory.forceresourcepack.ForceResourcePack;

/**
 * @author Milkory
 */
public class Logger {

    private static final ForceResourcePack plugin = ForceResourcePack.getInstance();

    public static void info(String msg) {
        plugin.getLogger().info(msg);
    }

    public static void warning(String msg) {
        plugin.getLogger().warning(msg);
    }

    public static void fine(String msg) {
        plugin.getLogger().fine(msg);
    }


}
