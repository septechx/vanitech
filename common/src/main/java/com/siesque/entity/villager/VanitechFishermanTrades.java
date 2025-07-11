package com.siesque.entity.villager;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import static com.siesque.entity.villager.VanitechTrades.*;

public class VanitechFishermanTrades {
    public static Int2ObjectMap<VillagerTrades.ItemListing[]> createFishermanTrades() {
        return VanitechTrades.toIntMap(ImmutableMap.of(
                1, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(Items.STRING, 16),
                                new ItemStack(COIN, 1),
                                16,
                                2,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(Items.COAL, 16),
                                new ItemStack(COIN, 1),
                                16,
                                2,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(COIN, 3),
                                new ItemStack(Items.COD_BUCKET, 1),
                                16,
                                1,
                                PriceMultiplier.LOW
                        ),
                        multiItemTrade(
                                new ItemStack(Items.COD, 8),
                                new ItemStack(Items.COOKED_COD, 8),
                                new ItemStack(COIN, 1),
                                16,
                                1,
                                PriceMultiplier.LOW
                        )
                },
                2, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(Items.COD, 12),
                                new ItemStack(COIN, 1),
                                16,
                                10,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(COIN, 2),
                                new ItemStack(Items.CAMPFIRE, 1),
                                12,
                                5,
                                PriceMultiplier.LOW
                        ),
                        multiItemTrade(
                                new ItemStack(Items.SALMON, 8),
                                new ItemStack(Items.COOKED_SALMON, 8),
                                new ItemStack(COIN, 1),
                                16,
                                5,
                                PriceMultiplier.LOW
                        )
                },
                3, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(Items.SALMON, 12),
                                new ItemStack(COIN, 1),
                                16,
                                20,
                                PriceMultiplier.LOW
                        ),
                        enchantedItem(
                                Items.FISHING_ROD,
                                8,
                                3,
                                10,
                                PriceMultiplier.HIGH
                        )
                },
                4, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(Items.TROPICAL_FISH, 3),
                                new ItemStack(COIN, 1),
                                12,
                                30,
                                PriceMultiplier.LOW
                        )
                },
                5, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(Items.PUFFERFISH, 2),
                                new ItemStack(COIN, 1),
                                12,
                                30,
                                PriceMultiplier.LOW
                        )
                }
        ));
    }
}
