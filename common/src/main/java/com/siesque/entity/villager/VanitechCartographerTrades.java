package com.siesque.entity.villager;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.tags.StructureTags;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.saveddata.maps.MapDecorationTypes;

import static com.siesque.entity.villager.VanitechTrades.*;

public class VanitechCartographerTrades {
    public static Int2ObjectMap<VillagerTrades.ItemListing[]> createCartographerTrades() {
        return VanitechTrades.toIntMap(ImmutableMap.of(
                1, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(Items.PAPER, 12),
                                new ItemStack(COIN, 1),
                                12,
                                2,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(COIN, 2),
                                new ItemStack(Items.MAP, 1),
                                12,
                                1,
                                PriceMultiplier.LOW
                        )
                },
                2, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(COIN, 3),
                                new ItemStack(Items.COMPASS, 1),
                                12,
                                5,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(Items.GLASS_PANE, 8),
                                new ItemStack(COIN, 1),
                                16,
                                10,
                                PriceMultiplier.LOW
                        )
                },
                3, new VillagerTrades.ItemListing[]{
                        treasureMap(
                                24,
                                StructureTags.ON_OCEAN_EXPLORER_MAPS,
                                "filled_map.monument",
                                MapDecorationTypes.OCEAN_MONUMENT,
                                12,
                                10
                        ),
                        treasureMap(
                                22,
                                StructureTags.ON_TRIAL_CHAMBERS_MAPS,
                                "filled_map.trial_chambers",
                                MapDecorationTypes.TRIAL_CHAMBERS,
                                12,
                                10
                        )
                },
                4, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(COIN, 2),
                                new ItemStack(Items.ITEM_FRAME, 1),
                                12,
                                15,
                                PriceMultiplier.LOW
                        )
                },
                5, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(COIN, 28),
                                new ItemStack(Items.GLOBE_BANNER_PATTERN, 1),
                                12,
                                30,
                                PriceMultiplier.LOW
                        ),
                        treasureMap(
                                32,
                                StructureTags.ON_WOODLAND_EXPLORER_MAPS,
                                "filled_map.mansion",
                                MapDecorationTypes.WOODLAND_MANSION,
                                12,
                                30
                        )
                }
        ));
    }
}
