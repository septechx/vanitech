package com.siesque.entity.villager;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import static com.siesque.entity.villager.VanitechTrades.*;

public class VanitechFarmerTrades {
    public static Int2ObjectMap<VillagerTrades.ItemListing[]> createFarmerTrades() {
        return VanitechTrades.toIntMap(ImmutableMap.of(
                1, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(Items.WHEAT, 20),
                                new ItemStack(COIN, 1),
                                16,
                                2,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(Items.POTATO, 22),
                                new ItemStack(COIN, 1),
                                16,
                                2,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(Items.CARROT, 22),
                                new ItemStack(COIN, 1),
                                16,
                                2,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(Items.BEETROOT, 15),
                                new ItemStack(COIN, 1),
                                16,
                                2,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(COIN, 1),
                                new ItemStack(Items.BREAD, 6),
                                16,
                                1,
                                PriceMultiplier.LOW
                        )
                },
                2, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(Items.PUMPKIN, 6),
                                new ItemStack(COIN, 1),
                                12,
                                10,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(COIN, 1),
                                new ItemStack(Items.PUMPKIN_PIE, 4),
                                12,
                                5,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(COIN, 1),
                                new ItemStack(Items.APPLE, 4),
                                16,
                                5,
                                PriceMultiplier.LOW
                        )
                },
                3, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(Items.MELON, 4),
                                new ItemStack(COIN, 1),
                                12,
                                20,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(COIN, 2),
                                new ItemStack(Items.COOKIE, 12),

                                12,
                                10,
                                PriceMultiplier.LOW
                        )
                },
                4, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(COIN, 2),
                                new ItemStack(Items.CAKE, 1),
                                12,
                                15,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(COIN, 2),
                                new ItemStack(Items.GLISTERING_MELON_SLICE, 3),
                                12,
                                30,
                                PriceMultiplier.LOW
                        )
                },
                5, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(COIN, 1),
                                new ItemStack(Items.GOLDEN_CARROT, 3),
                                12,
                                30,
                                PriceMultiplier.LOW
                        )
                }
        ));
    }
}
