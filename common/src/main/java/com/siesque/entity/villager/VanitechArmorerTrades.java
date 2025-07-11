package com.siesque.entity.villager;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import static com.siesque.entity.villager.VanitechTrades.*;

public class VanitechArmorerTrades {
    public static Int2ObjectMap<VillagerTrades.ItemListing[]> createArmorerTrades() {
        return VanitechTrades.toIntMap(ImmutableMap.of(
                1, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(Items.COAL, 16),
                                new ItemStack(COIN, 1),
                                16,
                                2,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(COIN, 6),
                                new ItemStack(Items.IRON_HELMET),
                                12,
                                1,
                                PriceMultiplier.HIGH
                        ),
                        trade(
                                new ItemStack(COIN, 6),
                                new ItemStack(Items.IRON_CHESTPLATE),
                                12,
                                1,
                                PriceMultiplier.HIGH
                        ),
                        trade(
                                new ItemStack(COIN, 6),
                                new ItemStack(Items.IRON_LEGGINGS),
                                12,
                                1,
                                PriceMultiplier.HIGH
                        ),
                        trade(
                                new ItemStack(COIN, 6),
                                new ItemStack(Items.IRON_BOOTS),
                                12,
                                1,
                                PriceMultiplier.HIGH
                        )
                },
                2, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(Items.IRON_INGOT, 4),
                                new ItemStack(COIN, 1),
                                2,
                                10,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(COIN, 8),
                                new ItemStack(Items.BELL, 1),
                                12,
                                10,
                                PriceMultiplier.HIGH
                        ),
                        trade(
                                new ItemStack(COIN, 4),
                                new ItemStack(Items.CHAINMAIL_LEGGINGS),
                                12,
                                5,
                                PriceMultiplier.HIGH
                        ),
                        trade(
                                new ItemStack(COIN, 4),
                                new ItemStack(Items.CHAINMAIL_BOOTS),
                                12,
                                5,
                                PriceMultiplier.HIGH
                        )
                },
                3, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(Items.EMERALD, 1),
                                new ItemStack(COIN, 16),
                                12,
                                20,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(Items.DIAMOND, 1),
                                new ItemStack(COIN, 16),
                                12,
                                20,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(Items.LAVA_BUCKET, 1),
                                new ItemStack(COIN, 6),
                                12,
                                20,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(COIN, 4),
                                new ItemStack(Items.CHAINMAIL_HELMET),
                                12,
                                10,
                                PriceMultiplier.HIGH
                        ),
                        trade(
                                new ItemStack(COIN, 4),
                                new ItemStack(Items.CHAINMAIL_CHESTPLATE),
                                12,
                                10,
                                PriceMultiplier.HIGH
                        ),
                        trade(
                                new ItemStack(COIN, 2),
                                new ItemStack(Items.SHIELD),
                                12,
                                10,
                                PriceMultiplier.HIGH
                        )
                },
                4, new VillagerTrades.ItemListing[]{
                        enchantedItem(
                                Items.DIAMOND_LEGGINGS,
                                48,
                                3,
                                15,
                                PriceMultiplier.HIGH
                        ),
                        enchantedItem(
                                Items.DIAMOND_BOOTS,
                                48,
                                3,
                                15,
                                PriceMultiplier.HIGH
                        )
                },
                5, new VillagerTrades.ItemListing[]{
                        enchantedItem(
                                Items.DIAMOND_CHESTPLATE,
                                48,
                                3,
                                30,
                                PriceMultiplier.HIGH
                        ),
                        enchantedItem(
                                Items.DIAMOND_HELMET,
                                48,
                                3,
                                30,
                                PriceMultiplier.HIGH
                        )
                }
        ));
    }
}
