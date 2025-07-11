package com.siesque.entity.villager;

import com.google.common.collect.ImmutableSet;
import com.siesque.Vanitech;
import com.siesque.VanitechFlags;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.platform.Platform;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.Blocks;

public class VanitechPoiTypes {
    private static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(Vanitech.MOD_ID,
            Registries.POINT_OF_INTEREST_TYPE);

    public static RegistrySupplier<PoiType> BANKER;
    public static ResourceKey<PoiType> BANKER_KEY = ResourceKey.create(Registries.POINT_OF_INTEREST_TYPE,
            ResourceLocation.fromNamespaceAndPath(Vanitech.MOD_ID, "banker_poi"));

    public static void init() {
        if (Platform.isFabric()) {
            initFabric();
        } else {
            initArchitectury();
        }
    }

    public static void initArchitectury() {
        if (VanitechFlags.enableBanker) {
            BANKER = POI_TYPES.register(BANKER_KEY.location(),
                    () -> new PoiType(ImmutableSet.copyOf(Blocks.ANVIL.getStateDefinition().getPossibleStates()),
                            1, 1));
        }

        POI_TYPES.register();

    }

    @ExpectPlatform
    public static void initFabric() {
        throw new AssertionError();
    }
}