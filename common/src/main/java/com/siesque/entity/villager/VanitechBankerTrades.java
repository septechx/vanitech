package com.siesque.entity.villager;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import static com.siesque.entity.villager.VanitechTrades.COIN;
import static com.siesque.entity.villager.VanitechTrades.trade;

public class VanitechBankerTrades {
    public static Int2ObjectMap<VillagerTrades.ItemListing[]> createBankerTrades() {
        return VanitechTrades.toIntMap(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{
                trade(
                        new ItemStack(Items.EMERALD, 1),
                        new ItemStack(COIN, 16),
                        16,
                        2,
                        VanitechTrades.PriceMultiplier.LOW
                ),
                trade(
                        new ItemStack(Items.DIAMOND, 1),
                        new ItemStack(COIN, 16),
                        16,
                        2,
                        VanitechTrades.PriceMultiplier.LOW
                )
        }));
    }
}
