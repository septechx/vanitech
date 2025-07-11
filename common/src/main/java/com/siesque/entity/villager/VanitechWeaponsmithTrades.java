package com.siesque.entity.villager;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import static com.siesque.entity.villager.VanitechTrades.*;

public class VanitechWeaponsmithTrades {
    public static Int2ObjectMap<VillagerTrades.ItemListing[]> createWeaponsmithTrades() {
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
                                new ItemStack(COIN, 4),
                                new ItemStack(Items.IRON_AXE, 1),
                                12,
                                1,
                                PriceMultiplier.HIGH
                        ),
                        enchantedItem(
                                Items.IRON_SWORD,
                                6,
                                3,
                                1,
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
                                new ItemStack(Items.EMERALD, 1),
                                new ItemStack(COIN, 16),
                                16,
                                15,
                                VanitechTrades.PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(Items.DIAMOND, 1),
                                new ItemStack(COIN, 16),
                                16,
                                15,
                                VanitechTrades.PriceMultiplier.LOW
                        )
                },
                4, new VillagerTrades.ItemListing[]{
                        enchantedItem(
                                Items.DIAMOND_AXE,
                                16,
                                3,
                                20,
                                PriceMultiplier.HIGH
                        )
                },
                5, new VillagerTrades.ItemListing[]{
                        enchantedItem(
                                Items.DIAMOND_SWORD,
                                14,
                                3,
                                30,
                                PriceMultiplier.HIGH
                        )
                }
        ));
    }
}
