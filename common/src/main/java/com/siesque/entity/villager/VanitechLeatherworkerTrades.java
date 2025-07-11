package com.siesque.entity.villager;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import static com.siesque.entity.villager.VanitechTrades.*;

public class VanitechLeatherworkerTrades {
    public static Int2ObjectMap<VillagerTrades.ItemListing[]> createLeatherworkerTrades() {
        return VanitechTrades.toIntMap(ImmutableMap.of(
                1, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(Items.LEATHER, 6),
                                new ItemStack(COIN, 2),
                                16,
                                2,
                                PriceMultiplier.LOW
                        ),
                        dyedArmor(Items.LEATHER_LEGGINGS, 4),
                        dyedArmor(Items.LEATHER_CHESTPLATE, 4),

                },
                2, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(Items.FLINT, 16),
                                new ItemStack(COIN, 1),
                                12,
                                10,
                                PriceMultiplier.LOW
                        ),
                        dyedArmor(Items.LEATHER_HELMET, 3),
                        dyedArmor(Items.LEATHER_BOOTS, 3),
                },
                3, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(Items.RABBIT_HIDE, 1),
                                new ItemStack(COIN, 1),
                                12,
                                20,
                                PriceMultiplier.LOW
                        ),
                        dyedArmor(Items.LEATHER_HORSE_ARMOR, 4),
                },
                4, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(Items.TURTLE_SCUTE, 1),
                                new ItemStack(COIN, 2),
                                12,
                                30,
                                PriceMultiplier.LOW
                        )
                },
                5, new VillagerTrades.ItemListing[]{
                        trade(
                                new ItemStack(COIN, 6),
                                new ItemStack(Items.SADDLE),
                                12,
                                30,
                                PriceMultiplier.HIGH
                        )
                }
        ));
    }
}
