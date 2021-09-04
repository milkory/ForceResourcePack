package com.milkory.forceresourcepack;

import com.milkory.forceresourcepack.common.Config;
import com.milkory.forceresourcepack.common.Message;
import com.milkory.forceresourcepack.hook.AuthMeHook;
import com.milkory.forceresourcepack.hook.MultiverseCoreHook;
import com.milkory.forceresourcepack.limbo.FRPLimbo;
import com.milkory.forceresourcepack.listener.AuthMeListener;
import com.milkory.forceresourcepack.listener.CommonListener;
import com.milkory.forceresourcepack.listener.LimboListener;
import com.milkory.forceresourcepack.listener.VanillaListener;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * @author Milkory
 */
public class ForceResourcePack extends JavaPlugin {

    @Getter private static ForceResourcePack instance;

    public ForceResourcePack() {
        instance = this;
    }

    @Override public void onLoad() {
        Config.load();
        Message.load();
        ResourcePackManager.getInstance().load();
    }

    public void load() {
        onLoad();
        FRPLimbo.getInstance().load();
    }

    @Override public void onEnable() {
        MultiverseCoreHook.getHook().hook();
        FRPLimbo.getInstance().load();

        if (Config.bool("setting.after-auth") && AuthMeHook.getHook().hook()) {
            Bukkit.getPluginManager().registerEvents(new AuthMeListener(), this);
        } else Bukkit.getPluginManager().registerEvents(new VanillaListener(), this);

        Bukkit.getPluginManager().registerEvents(new CommonListener(), this);
        Bukkit.getPluginManager().registerEvents(new LimboListener(), this);
    }

    @Override public void onDisable() {
        FRPLimbo.getInstance().close();
    }

    public InputStreamReader getResourceReader(String file) {
        return new InputStreamReader(Objects.requireNonNull(this.getResource(file)));
    }

    public File saveResource(String path) {
        File file = new File(getDataFolder(), path);
        if (!file.exists()) saveResource(path, false);
        return file;
    }

    @Override public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            switch (args[0].toLowerCase()) {
                case "reload":
                    if (sender.hasPermission("force-resource-pack.command.reload")) {
                        load();
                        sender.sendMessage(ChatColor.GREEN + "Reloaded successfully!");
                        return true;
                    }
                    break;
                case "push":
                    if (sender.hasPermission("force-resource-pack.command.push")) {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            ResourcePackManager.getInstance().loadResourcePackFor(player);
                        }
                        sender.sendMessage(ChatColor.GREEN + "Pushed successfully!");
                        return true;
                    }
                    break;
            }
        }
        sender.sendMessage(ChatColor.RED + "Unknown command or lack permission.");
        return true;
    }

}
