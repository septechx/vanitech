package com.siesque.entity.villager;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import static com.siesque.entity.villager.VanitechTrades.*;

public class VanitechButcherTrades {
    public static Int2ObjectMap<VillagerTrades.ItemListing[]> createButcherTrades() {
        return VanitechTrades.toIntMap(ImmutableMap.of(
                1, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(Items.CHICKEN, 6),
                                new ItemStack(COIN, 1),
                                16,
                                2,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(Items.RABBIT, 2),
                                new ItemStack(COIN, 1),
                                16,
                                2,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(Items.PORKCHOP, 6),
                                new ItemStack(COIN, 1),
                                16,
                                2,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(COIN, 1),
                                new ItemStack(Items.RABBIT_STEW, 1),
                                12,
                                1,
                                PriceMultiplier.LOW
                        )
                },
                2, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(Items.COAL, 16),
                                new ItemStack(COIN, 1),
                                16,
                                2,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(COIN, 1),
                                new ItemStack(Items.COOKED_CHICKEN, 4),
                                16,
                                5,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(COIN, 1),
                                new ItemStack(Items.COOKED_PORKCHOP, 4),
                                16,
                                5,
                                PriceMultiplier.LOW
                        )
                },
                3, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(Items.BEEF, 4),
                                new ItemStack(COIN, 1),
                                16,
                                20,
                                PriceMultiplier.LOW
                        ),
                        trade(
                                new ItemStack(Items.MUTTON, 4),
                                new ItemStack(COIN, 1),
                                16,
                                20,
                                PriceMultiplier.LOW
                        )
                },
                4, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(Items.DRIED_KELP_BLOCK, 1),
                                new ItemStack(COIN, 3),
                                12,
                                30,
                                PriceMultiplier.LOW
                        )
                },
                5, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(Items.SWEET_BERRIES, 4),
                                new ItemStack(COIN, 1),
                                12,
                                30,
                                PriceMultiplier.LOW
                        )
                }
        ));
    }
}
