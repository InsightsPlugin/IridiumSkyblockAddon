package net.frankheijden.insights.addons.iridiumskyblock;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import net.frankheijden.insights.entities.Area;
import net.frankheijden.insights.entities.CacheAssistant;
import net.frankheijden.insights.entities.CuboidSelection;
import org.bukkit.Location;

import java.util.Collections;

public class IridiumSkyblockAddon extends CacheAssistant {

    public IridiumSkyblockAddon() {
        super("IridiumSkyblock", "IridiumSkyblock", "island", "1.1.0");
    }

    public String getId(Island island) {
        return getPluginName() + "@" + island.getId();
    }

    public Area adapt(Island island) {
        if (island == null) return null;
        Location min = island.getPos1().clone();
        min.setY(0);
        Location max = island.getPos2().clone();
        max.setY(max.getWorld().getMaxHeight() - 1);
        return Area.from(this, getId(island), Collections.singletonList(new CuboidSelection(min, max)));
    }

    @Override
    public Area getArea(Location location) {
        return adapt(IridiumSkyblock.getIslandManager().getIslandViaLocation(location));
    }
}
