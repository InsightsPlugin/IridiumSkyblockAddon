package dev.frankheijden.insights.addons.iridiumskyblock;

import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.managers.IslandManager;
import dev.frankheijden.insights.api.addons.InsightsAddon;
import dev.frankheijden.insights.api.addons.Region;
import dev.frankheijden.insights.api.addons.SimpleCuboidRegion;
import dev.frankheijden.insights.api.objects.math.Vector3;
import org.bukkit.Location;
import org.bukkit.World;
import java.util.Optional;

public class IridiumSkyblockAddon implements InsightsAddon {

    public String getId(Island island, World world) {
        return getPluginName() + "@" + island.id + "-" + world.getName();
    }

    public Optional<Region> adapt(Island island, World world) {
        if (island == null) return Optional.empty();
        return Optional.of(new SimpleCuboidRegion(
                world,
                new Vector3(island.pos1.getBlockX(), 0, island.pos1.getBlockZ()),
                new Vector3(island.pos2.getBlockX(), world.getMaxHeight() - 1, island.pos2.getBlockZ()),
                getPluginName(),
                getId(island, world)
        ));
    }

    @Override
    public String getPluginName() {
        return "IridiumSkyblock";
    }

    @Override
    public String getAreaName() {
        return "island";
    }

    @Override
    public String getVersion() {
        return "{version}";
    }

    @Override
    public Optional<Region> getRegion(Location location) {
        return adapt(IslandManager.getIslandViaLocation(location), location.getWorld());
    }
}
