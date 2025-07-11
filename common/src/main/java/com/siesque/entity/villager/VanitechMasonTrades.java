package com.siesque.entity.villager;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import static com.siesque.entity.villager.VanitechTrades.*;

public class VanitechMasonTrades {
    private static final Item[] TERRACOTA_COLORS = {
            Items.WHITE_TERRACOTTA, Items.ORANGE_TERRACOTTA, Items.MAGENTA_TERRACOTTA, Items.LIGHT_BLUE_TERRACOTTA,
            Items.YELLOW_TERRACOTTA, Items.LIME_TERRACOTTA, Items.PINK_TERRACOTTA, Items.GRAY_TERRACOTTA,
            Items.LIGHT_GRAY_TERRACOTTA, Items.CYAN_TERRACOTTA, Items.PURPLE_TERRACOTTA, Items.BLUE_TERRACOTTA,
            Items.BROWN_TERRACOTTA, Items.GREEN_TERRACOTTA, Items.RED_TERRACOTTA, Items.BLACK_TERRACOTTA
    };

    private static final Item[] GLAZED_TERRACOTA_COLORS = {
            Items.WHITE_GLAZED_TERRACOTTA, Items.ORANGE_GLAZED_TERRACOTTA, Items.MAGENTA_GLAZED_TERRACOTTA,
            Items.LIGHT_BLUE_GLAZED_TERRACOTTA, Items.YELLOW_GLAZED_TERRACOTTA, Items.LIME_GLAZED_TERRACOTTA,
            Items.PINK_GLAZED_TERRACOTTA, Items.GRAY_GLAZED_TERRACOTTA, Items.LIGHT_GRAY_GLAZED_TERRACOTTA,
            Items.CYAN_GLAZED_TERRACOTTA, Items.PURPLE_GLAZED_TERRACOTTA, Items.BLUE_GLAZED_TERRACOTTA,
            Items.BROWN_GLAZED_TERRACOTTA, Items.GREEN_GLAZED_TERRACOTTA, Items.RED_GLAZED_TERRACOTTA,
            Items.BLACK_GLAZED_TERRACOTTA
    };

    public static Int2ObjectMap<VillagerTrades.ItemListing[]> createMasonTrades() {
        return VanitechTrades.toIntMap(ImmutableMap.of(
                1, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(Items.CLAY_BALL, 16),
                                new ItemStack(COIN, 1),
                                16,
                                2,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(COIN, 1),
                                new ItemStack(Items.BRICK, 16),
                                16,
                                1,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(Items.QUARTZ, 18),
                                new ItemStack(COIN, 1),
                                12,
                                1,
                                PriceMultiplier.LOW
                        )
                },
                2, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(Items.GRANITE, 16),
                                new ItemStack(COIN, 1),
                                16,
                                20,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(Items.DIORITE, 16),
                                new ItemStack(COIN, 1),
                                16,
                                20,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(Items.ANDESITE, 16),
                                new ItemStack(COIN, 1),
                                16,
                                20,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(Items.STONE, 22),
                                new ItemStack(COIN, 1),
                                16,
                                10,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(COIN, 1),
                                new ItemStack(Items.POLISHED_GRANITE, 16),
                                16,
                                10,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(COIN, 1),
                                new ItemStack(Items.POLISHED_DIORITE, 16),
                                16,
                                10,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(COIN, 1),
                                new ItemStack(Items.POLISHED_ANDESITE, 16),
                                16,
                                10,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(COIN, 1),
                                new ItemStack(Items.DRIPSTONE_BLOCK, 16),
                                16,
                                10,
                                PriceMultiplier.LOW
                        )
                },
                3, createLevel3Trades(),
                4, createLevel4Trades(),
                5, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(COIN, 1),
                                new ItemStack(Items.QUARTZ_BLOCK, 4),
                                12,
                                30,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(COIN, 1),
                                new ItemStack(Items.QUARTZ_PILLAR, 4),
                                12,
                                30,
                                PriceMultiplier.LOW
                        ),
                }
        ));
    }

    private static VillagerTrades.ItemListing[] createLevel3Trades() {
        VillagerTrades.ItemListing[] trades = new VillagerTrades.ItemListing[GLAZED_TERRACOTA_COLORS.length * 2];
        int index = 0;

        for (Item terracotta : TERRACOTA_COLORS) {
            trades[index++] = trade(
                    new ItemStack(terracotta, 16),
                    new ItemStack(COIN, 1),
                    12,
                    15,
                    PriceMultiplier.LOW
            );
        }

        for (Item glazedTerracotta : GLAZED_TERRACOTA_COLORS) {
            trades[index++] = trade(
                    new ItemStack(glazedTerracotta, 16),
                    new ItemStack(COIN, 1),
                    12,
                    15,
                    PriceMultiplier.LOW
            );
        }

        return trades;
    }

    private static VillagerTrades.ItemListing[] createLevel4Trades() {
        VillagerTrades.ItemListing[] trades = new VillagerTrades.ItemListing[GLAZED_TERRACOTA_COLORS.length * 2];
        int index = 0;

        for (Item terracotta : TERRACOTA_COLORS) {
            trades[index++] = trade(
                    new ItemStack(COIN, 1),
                    new ItemStack(terracotta, 16),
                    12,
                    20,
                    PriceMultiplier.LOW
            );
        }

        for (Item glazedTerracotta : GLAZED_TERRACOTA_COLORS) {
            trades[index++] = trade(
                    new ItemStack(COIN, 1),
                    new ItemStack(glazedTerracotta, 16),
                    12,
                    20,
                    PriceMultiplier.LOW
            );
        }

        return trades;
    }
}
