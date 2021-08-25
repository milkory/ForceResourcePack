package com.milkory.forceresourcepack;

import com.milkory.forceresourcepack.common.Config;
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
        player.teleport(location);
    }

    public void remove(Player player) {
        if (this.playerToLastLocation.containsKey(player)) {
            player.teleport(this.playerToLastLocation.get(player));
            this.playerToLastLocation.remove(player);
        }
    }

    private double[] getVector(String string) {
        return Arrays.stream(string.replace(" ","").split(","))
                .mapToDouble(Double::parseDouble).toArray();
    }

}
