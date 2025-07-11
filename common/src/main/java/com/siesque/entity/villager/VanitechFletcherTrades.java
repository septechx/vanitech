package com.siesque.entity.villager;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import static com.siesque.entity.villager.VanitechTrades.*;

public class VanitechFletcherTrades {
    public static Int2ObjectMap<VillagerTrades.ItemListing[]> createFletcherTrades() {
        return VanitechTrades.toIntMap(ImmutableMap.of(
                1, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(Items.STICK, 32),
                                new ItemStack(COIN, 1),
                                16,
                                2,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(COIN, 1),
                                new ItemStack(Items.ARROW, 16),
                                12,
                                1,
                                PriceMultiplier.LOW
                        ),
                        multiItemTrade(
                                new ItemStack(Items.GRAVEL, 10),
                                new ItemStack(COIN, 1),
                                new ItemStack(Items.FLINT, 10),
                                12,
                                1,
                                PriceMultiplier.LOW
                        )
                },
                2, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(Items.FLINT, 16),
                                new ItemStack(COIN, 1),
                                12,
                                10,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(COIN, 3),
                                new ItemStack(Items.BOW),
                                12,
                                5,
                                PriceMultiplier.LOW
                        )
                },
                3, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(Items.STRING, 16),
                                new ItemStack(COIN, 1),
                                16,
                                20,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(COIN, 6),
                                new ItemStack(Items.CROSSBOW),
                                12,
                                10,
                                PriceMultiplier.LOW
                        )
                },
                4, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(Items.FEATHER, 24),
                                new ItemStack(COIN, 1),
                                16,
                                30,
                                PriceMultiplier.LOW
                        ),
                        enchantedItem(
                                Items.BOW,
                                8,
                                3,
                                15,
                                PriceMultiplier.LOW
                        )
                },
                5, new VillagerTrades.ItemListing[]{
                        enchantedItem(
                                Items.CROSSBOW,
                                8,
                                3,
                                15,
                                PriceMultiplier.LOW
                        ),
                        tippedArrow(
                                Items.ARROW,
                                4,
                                Items.TIPPED_ARROW,
                                4,
                                1,
                                12,
                                30
                        )
                }
        ));
    }
}
