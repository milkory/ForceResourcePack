package com.milkory.forceresourcepack.limbo;

import com.milkory.forceresourcepack.hook.MultiverseCoreHook;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

/**
 * @author Milkory
 */
@Getter
@ToString
@EqualsAndHashCode
public class LimboPlayerData {

    private final Player player;
    private final Location lastLocation;
    private final Inventory lastInventory = Bukkit.createInventory(null, InventoryType.PLAYER);

    public LimboPlayerData(Player player) {
        this.player = player;
        this.lastLocation = player.getLocation();
        this.lastInventory.setContents(player.getInventory().getContents());
    }

    public void limbo() {
        FRPLimbo.getInstance().teleport0(player);
        player.getInventory().clear();
    }

    public void revert() {
        MultiverseCoreHook.getHook().teleport(player, lastLocation);
        player.getInventory().setContents(lastInventory.getContents());
    }

}
