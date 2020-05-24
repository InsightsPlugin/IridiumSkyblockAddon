package net.frankheijden.insights.addons.iridiumskyblock;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import net.frankheijden.insights.entities.CacheAssistant;
import net.frankheijden.insights.entities.Selection;
import org.bukkit.Location;

public class IridiumSkyblockAddon extends CacheAssistant {

    public IridiumSkyblockAddon() {
        super("IridiumSkyblock", "IridiumSkyblock", "island", "1.0.0");
    }

    public Selection adapt(Island island) {
        if (island == null) return null;
        return new Selection(island.getPos1(), island.getPos2());
    }

    @Override
    public Selection getSelection(Location location) {
        return adapt(IridiumSkyblock.getIslandManager().getIslandViaLocation(location));
    }
}
