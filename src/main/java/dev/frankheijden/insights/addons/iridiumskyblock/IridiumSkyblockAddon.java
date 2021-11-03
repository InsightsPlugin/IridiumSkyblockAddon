package dev.frankheijden.insights.addons.iridiumskyblock;

import com.iridium.iridiumskyblock.api.IridiumSkyblockAPI;
import com.iridium.iridiumskyblock.database.Island;
import dev.frankheijden.insights.api.addons.InsightsAddon;
import dev.frankheijden.insights.api.addons.Region;
import dev.frankheijden.insights.api.addons.SimpleCuboidRegion;
import dev.frankheijden.insights.api.objects.math.Vector3;
import org.bukkit.Location;
import org.bukkit.World;
import java.util.Optional;

public class IridiumSkyblockAddon implements InsightsAddon {

    public String getId(Island island, World world) {
        return getPluginName() + "@" + island.getId() + "-" + world.getName();
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
    public Optional<Region> getRegion(final Location location) {
        return IridiumSkyblockAPI.getInstance().getIslandViaLocation(location).map(island -> {
            var world = location.getWorld();

            Location pos1 = island.getPos1(world);
            Location pos2 = island.getPos2(world);
            return new SimpleCuboidRegion(
                    world,
                    new Vector3(pos1.getBlockX(), world.getMinHeight(), pos1.getBlockZ()),
                    new Vector3(pos2.getBlockX(), world.getMaxHeight() - 1, pos2.getBlockZ()),
                    getPluginName(),
                    getId(island, world)
            );
        });
    }
}
