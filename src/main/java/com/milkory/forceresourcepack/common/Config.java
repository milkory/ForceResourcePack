package com.milkory.forceresourcepack.common;

import com.milkory.forceresourcepack.ForceResourcePack;
import lombok.Getter;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

/**
 * @author Milkory
 */
public class Config {

    private static final ForceResourcePack plugin = ForceResourcePack.getInstance();

    @Getter private static Configuration config;

    public static void load() {
        config = loadConfig("config.yml");
    }

    public static YamlConfiguration loadConfig(String path) {
        File file = plugin.saveResource(path);
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        YamlConfiguration defaults = YamlConfiguration.loadConfiguration(plugin.getResourceReader(path));
        config.setDefaults(defaults);
        return config;
    }

    public static boolean bool(String path) {
        return config.getBoolean(path);
    }

    public static String string(String path) {
        return config.getString(path);
    }

    public static ConfigurationSection section(String path) {
        return config.getConfigurationSection(path);
    }

}
