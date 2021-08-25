package com.milkory.forceresourcepack;

import com.google.common.base.Strings;
import com.milkory.forceresourcepack.common.Config;
import com.milkory.forceresourcepack.common.Message;
import lombok.Getter;
import org.bukkit.entity.Player;

/**
 * @author Milkory
 */
public class ResourcePackManager {

    @Getter private static final ResourcePackManager instance = new ResourcePackManager();

    @Getter private String resourcePackUrl;

    public void load() {
        this.resourcePackUrl = Config.string("resource-pack.url");
    }

    public void loadResourcePackFor(Player player) {
        if (!Strings.isNullOrEmpty(resourcePackUrl)) {
            player.setResourcePack(resourcePackUrl);
            FRPLimbo.getInstance().add(player);
            Message.sendTo(player, "sent");
        }
    }

}
