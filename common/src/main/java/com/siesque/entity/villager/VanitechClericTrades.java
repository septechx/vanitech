package com.siesque.entity.villager;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import static com.siesque.entity.villager.VanitechTrades.*;

public class VanitechClericTrades {
    public static Int2ObjectMap<VillagerTrades.ItemListing[]> createClericTrades() {
        return VanitechTrades.toIntMap(ImmutableMap.of(
                1, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(Items.ROTTEN_FLESH, 16),
                                new ItemStack(COIN, 1),
                                16,
                                2,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(COIN, 1),
                                new ItemStack(Items.REDSTONE, 4),
                                12,
                                1,
                                PriceMultiplier.LOW
                        )
                },
                2, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(Items.GOLD_INGOT, 2),
                                new ItemStack(COIN, 1),
                                12,
                                10,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(COIN, 1),
                                new ItemStack(Items.LAPIS_LAZULI, 4),
                                12,
                                5,
                                PriceMultiplier.LOW
                        )
                },
                3, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(Items.RABBIT_FOOT, 1),
                                new ItemStack(COIN, 6),
                                12,
                                20,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(COIN, 1),
                                new ItemStack(Items.GLOWSTONE, 1),
                                12,
                                10,
                                PriceMultiplier.LOW
                        )
                },
                4, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(Items.TURTLE_SCUTE, 1),
                                new ItemStack(COIN, 2),
                                12,
                                30,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(Items.GLASS_BOTTLE, 6),
                                new ItemStack(COIN, 1),
                                12,
                                30,
                                PriceMultiplier.LOW
                        )
                },
                5, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(COIN, 6),
                                new ItemStack(Items.ENDER_PEARL, 1),
                                12,
                                15,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(COIN, 3),
                                new ItemStack(Items.EXPERIENCE_BOTTLE, 2),
                                12,
                                30,
                                PriceMultiplier.LOW
                        )
                }
        ));
    }
}
