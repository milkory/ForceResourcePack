package com.milkory.forceresourcepack;

import com.milkory.forceresourcepack.common.Config;
import com.milkory.forceresourcepack.hook.MultiverseCoreHook;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Milkory
 */
public class FRPLimbo {

    @Getter private static final FRPLimbo instance = new FRPLimbo();

    @Getter private final Map<Player, Location> playerToLastLocation = new HashMap<>();
    @Getter private Location location;

    public void load() {
        if (Config.bool("limbo.enable")) {
            ConfigurationSection section = Config.section("limbo.location");
            double[] pos = getVector(Objects.requireNonNull(section.getString("position")));
            double[] rot = getVector(Objects.requireNonNull(section.getString("rotation")));
            this.location = new Location(Bukkit.getWorld(Objects.requireNonNull(section.getString("world"))),
                    pos[0], pos[1], pos[2], (float) rot[0], (float) rot[1]);
        }
    }

    public void add(Player player) {
        this.playerToLastLocation.put(player, player.getLocation());
        teleport0(player);
    }

    public void remove(Player player) {
        if (has(player)) {
            MultiverseCoreHook.getHook().teleport(player, this.playerToLastLocation.get(player));
            this.playerToLastLocation.remove(player);
        }
    }

    public boolean has(Player player) {
        return this.playerToLastLocation.containsKey(player);
    }

    public void teleport(Player player) {
        if (has(player)) {
            teleport0(player);
        }
    }

    public void teleport0(Player player) {
        MultiverseCoreHook.getHook().teleport(player, location);
    }

    private double[] getVector(String string) {
        return Arrays.stream(string.replace(" ", "").split(","))
                .mapToDouble(Double::parseDouble).toArray();
    }

}
