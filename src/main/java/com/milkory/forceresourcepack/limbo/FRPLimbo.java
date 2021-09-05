package com.milkory.forceresourcepack.limbo;

import com.milkory.forceresourcepack.common.Config;
import com.milkory.forceresourcepack.hook.MultiverseCoreHook;
import lombok.Getter;
import org.apache.commons.lang.Validate;
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

    @Getter private boolean isClosed = false;

    @Getter private final Map<Player, LimboPlayerData> playerData = new HashMap<>();
    @Getter private Location location;

    private void checkIfClosed() {
        Validate.isTrue(!isClosed, "Illegal access when limbo is closed");
    }

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
        checkIfClosed();
        LimboPlayerData data = new LimboPlayerData(player);
        data.limbo();
        this.playerData.put(player, data);
    }

    public void remove(Player player) {
        checkIfClosed();
        LimboPlayerData result = this.playerData.get(player);
        if (result != null) {
            this.playerData.remove(player);
            result.revert();
        }
    }

    public void close() {
        this.isClosed = true;
        this.playerData.forEach((key, value) -> value.revert());
    }

    public boolean has(Player player) {
        return this.playerData.containsKey(player);
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
