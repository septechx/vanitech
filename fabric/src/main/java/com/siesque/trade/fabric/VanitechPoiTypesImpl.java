package com.siesque.trade.fabric;

import com.google.common.collect.ImmutableSet;
import com.siesque.trade.VanitechPoiTypes;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.world.level.block.Blocks;

@SuppressWarnings("unused")
public class VanitechPoiTypesImpl {
    public static void initFabric() {
        PointOfInterestHelper.register(
                VanitechPoiTypes.BANKER_KEY.location(),
                0, 1, ImmutableSet.copyOf(Blocks.ANVIL.getStateDefinition().getPossibleStates()));
    }
}
