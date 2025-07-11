package com.siesque.entity.villager;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import static com.siesque.entity.villager.VanitechTrades.*;

public class VanitechToolsmithTrades {
    public static Int2ObjectMap<VillagerTrades.ItemListing[]> createToolsmithTrades() {
        return VanitechTrades.toIntMap(ImmutableMap.of(
                1, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(COIN, 2),
                                new ItemStack(Items.STONE_PICKAXE, 1),
                                12,
                                1,
                                PriceMultiplier.HIGH
                        ),
                        trade(
                                new ItemStack(COIN, 2),
                                new ItemStack(Items.STONE_AXE, 1),
                                12,
                                1,
                                PriceMultiplier.HIGH
                        ),
                        trade(
                                new ItemStack(COIN, 2),
                                new ItemStack(Items.STONE_SHOVEL, 1),
                                12,
                                1,
                                PriceMultiplier.HIGH
                        ),
                        trade(
                                new ItemStack(COIN, 2),
                                new ItemStack(Items.STONE_HOE, 1),
                                12,
                                1,
                                PriceMultiplier.HIGH
                        ),
                        trade(
                                new ItemStack(Items.COAL, 16),
                                new ItemStack(COIN, 1),
                                16,
                                2,
                                PriceMultiplier.LOW
                        )
                },
                2, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(Items.IRON_INGOT, 4),
                                new ItemStack(COIN, 1),
                                12,
                                10,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(COIN, 8),
                                new ItemStack(Items.BELL, 1),
                                12,
                                5,
                                PriceMultiplier.HIGH
                        )
                },
                3, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(Items.FLINT, 16),
                                new ItemStack(COIN, 1),
                                12,
                                20,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(COIN, 6),
                                new ItemStack(Items.DIAMOND_HOE),
                                3,
                                10,
                                PriceMultiplier.HIGH
                        ),
                        enchantedItem(
                                Items.IRON_AXE,
                                8,
                                3,
                                10,
                                PriceMultiplier.HIGH
                        ),
                        enchantedItem(
                                Items.IRON_PICKAXE,
                                8,
                                3,
                                10,
                                PriceMultiplier.HIGH
                        ),
                        enchantedItem(
                                Items.IRON_SHOVEL,
                                8,
                                3,
                                10,
                                PriceMultiplier.HIGH
                        )
                },
                4, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(Items.EMERALD, 1),
                                new ItemStack(COIN, 16),
                                12,
                                30,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(Items.DIAMOND, 1),
                                new ItemStack(COIN, 16),
                                12,
                                30,
                                PriceMultiplier.LOW
                        ),
                        enchantedItem(
                                Items.DIAMOND_AXE,
                                16,
                                3,
                                15,
                                PriceMultiplier.HIGH
                        ),
                        enchantedItem(
                                Items.DIAMOND_SHOVEL,
                                16,
                                3,
                                15,
                                PriceMultiplier.HIGH
                        )
                },
                5, new VillagerTrades.ItemListing[]{
                        enchantedItem(
                                Items.DIAMOND_PICKAXE,
                                18,
                                3,
                                30,
                                PriceMultiplier.HIGH
                        )
                }
        ));
    }
}
