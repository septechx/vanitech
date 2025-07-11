package com.siesque.entity.villager.fabric;

import com.google.common.collect.ImmutableSet;
import com.siesque.VanitechFlags;
import com.siesque.entity.villager.VanitechPoiTypes;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.world.level.block.Blocks;

@SuppressWarnings("unused")
public class VanitechPoiTypesImpl {
    public static void initFabric() {
        if (VanitechFlags.enableBanker) {
            PointOfInterestHelper.register(
                    VanitechPoiTypes.BANKER_KEY.location(),
                    0, 1, ImmutableSet.copyOf(Blocks.ANVIL.getStateDefinition().getPossibleStates()));
        }
    }
}
