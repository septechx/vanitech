package com.siesque.entity.villager;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import static com.siesque.entity.villager.VanitechTrades.*;

public class VanitechLibrarianTrades {
    public static Int2ObjectMap<VillagerTrades.ItemListing[]> createLibrarianTrades() {
        return VanitechTrades.toIntMap(ImmutableMap.of(
                1, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(COIN, 8),
                                new ItemStack(Items.BOOKSHELF, 1),
                                12,
                                1,
                                PriceMultiplier.LOW
                        ),
                        enchantBook(1)
                },
                2, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(COIN, 2),
                                new ItemStack(Items.LANTERN, 1),
                                16,
                                5,
                                PriceMultiplier.LOW
                        ),
                        enchantBook(5)
                },
                3, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(COIN, 1),
                                new ItemStack(Items.GLASS, 2),
                                12,
                                10,
                                PriceMultiplier.LOW
                        ),
                        enchantBook(10)
                },
                4, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(Items.WRITABLE_BOOK, 1),
                                new ItemStack(COIN, 2),
                                12,
                                30,
                                PriceMultiplier.LOW
                        ),
                        enchantBook(15)
                },
                5, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(COIN, 16),
                                new ItemStack(Items.NAME_TAG, 1),
                                12,
                                30,
                                PriceMultiplier.LOW
                        ),
                        enchantBook(20)
                }
        ));
    }
}
