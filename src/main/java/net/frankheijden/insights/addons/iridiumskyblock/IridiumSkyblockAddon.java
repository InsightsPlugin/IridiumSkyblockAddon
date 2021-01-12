package net.frankheijden.insights.addons.iridiumskyblock;

import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.managers.IslandManager;
import net.frankheijden.insights.entities.Area;
import net.frankheijden.insights.entities.CacheAssistant;
import net.frankheijden.insights.entities.CuboidSelection;
import org.bukkit.Location;

import java.util.Collections;

public class IridiumSkyblockAddon extends CacheAssistant {

    public IridiumSkyblockAddon() {
        super("IridiumSkyblock", "IridiumSkyblock", "island", "1.1.1");
    }

    public String getId(Island island) {
        return getPluginName() + "@" + island.id;
    }

    public Area adapt(Island island) {
        if (island == null) return null;
        Location min = island.pos1.clone();
        min.setY(0);
        Location max = island.pos2.clone();
        max.setY(max.getWorld().getMaxHeight() - 1);
        return Area.from(this, getId(island), Collections.singletonList(new CuboidSelection(min, max)));
    }

    @Override
    public Area getArea(Location location) {
        return adapt(IslandManager.getIslandViaLocation(location));
    }
}
