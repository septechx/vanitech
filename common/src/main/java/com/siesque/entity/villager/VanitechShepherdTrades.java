package com.siesque.entity.villager;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import static com.siesque.entity.villager.VanitechTrades.*;

public class VanitechShepherdTrades {
    private static final Item[] WOOL_COLORS = {
            Items.WHITE_WOOL, Items.ORANGE_WOOL, Items.MAGENTA_WOOL, Items.LIGHT_BLUE_WOOL,
            Items.YELLOW_WOOL, Items.LIME_WOOL, Items.PINK_WOOL, Items.GRAY_WOOL,
            Items.LIGHT_GRAY_WOOL, Items.CYAN_WOOL, Items.PURPLE_WOOL, Items.BLUE_WOOL,
            Items.BROWN_WOOL, Items.GREEN_WOOL, Items.RED_WOOL, Items.BLACK_WOOL
    };

    private static final Item[] DYE_COLORS = {
            Items.WHITE_DYE, Items.ORANGE_DYE, Items.MAGENTA_DYE, Items.LIGHT_BLUE_DYE,
            Items.YELLOW_DYE, Items.LIME_DYE, Items.PINK_DYE, Items.GRAY_DYE,
            Items.LIGHT_GRAY_DYE, Items.CYAN_DYE, Items.PURPLE_DYE, Items.BLUE_DYE,
            Items.BROWN_DYE, Items.GREEN_DYE, Items.RED_DYE, Items.BLACK_DYE
    };

    private static final Item[] CARPET_COLORS = {
            Items.WHITE_CARPET, Items.ORANGE_CARPET, Items.MAGENTA_CARPET, Items.LIGHT_BLUE_CARPET,
            Items.YELLOW_CARPET, Items.LIME_CARPET, Items.PINK_CARPET, Items.GRAY_CARPET,
            Items.LIGHT_GRAY_CARPET, Items.CYAN_CARPET, Items.PURPLE_CARPET, Items.BLUE_CARPET,
            Items.BROWN_CARPET, Items.GREEN_CARPET, Items.RED_CARPET, Items.BLACK_CARPET
    };

    private static final Item[] BED_COLORS = {
            Items.WHITE_BED, Items.ORANGE_BED, Items.MAGENTA_BED, Items.LIGHT_BLUE_BED,
            Items.YELLOW_BED, Items.LIME_BED, Items.PINK_BED, Items.GRAY_BED,
            Items.LIGHT_GRAY_BED, Items.CYAN_BED, Items.PURPLE_BED, Items.BLUE_BED,
            Items.BROWN_BED, Items.GREEN_BED, Items.RED_BED, Items.BLACK_BED
    };

    private static final Item[] BANNER_COLORS = {
            Items.WHITE_BANNER, Items.ORANGE_BANNER, Items.MAGENTA_BANNER, Items.LIGHT_BLUE_BANNER,
            Items.YELLOW_BANNER, Items.LIME_BANNER, Items.PINK_BANNER, Items.GRAY_BANNER,
            Items.LIGHT_GRAY_BANNER, Items.CYAN_BANNER, Items.PURPLE_BANNER, Items.BLUE_BANNER,
            Items.BROWN_BANNER, Items.GREEN_BANNER, Items.RED_BANNER, Items.BLACK_BANNER
    };

    public static Int2ObjectMap<VillagerTrades.ItemListing[]> createShepherdTrades() {
        return VanitechTrades.toIntMap(ImmutableMap.of(
                1, createWoolTrades(),
                2, createDyeTrades(),
                3, createCarpetTrades(),
                4, createBedTrades(),
                5, createBannerTrades()));
    }

    private static VillagerTrades.ItemListing[] createWoolTrades() {
        VillagerTrades.ItemListing[] trades = new VillagerTrades.ItemListing[WOOL_COLORS.length * 2];
        int index = 0;

        for (Item wool : WOOL_COLORS) {
            trades[index++] = wool(wool);
        }

        for (Item wool : WOOL_COLORS) {
            trades[index++] = sellWool(wool);
        }

        return trades;
    }

    private static VillagerTrades.ItemListing[] createDyeTrades() {
        VillagerTrades.ItemListing[] trades = new VillagerTrades.ItemListing[DYE_COLORS.length * 2];
        int index = 0;

        for (Item dye : DYE_COLORS) {
            trades[index++] = dye(dye);
        }

        for (Item dye : DYE_COLORS) {
            trades[index++] = sellDye(dye);
        }

        return trades;
    }

    private static VillagerTrades.ItemListing[] createCarpetTrades() {
        VillagerTrades.ItemListing[] trades = new VillagerTrades.ItemListing[CARPET_COLORS.length * 2];
        int index = 0;

        for (Item carpet : CARPET_COLORS) {
            trades[index++] = carpet(carpet);
        }

        for (Item carpet : CARPET_COLORS) {
            trades[index++] = sellCarpet(carpet);
        }

        return trades;
    }

    private static VillagerTrades.ItemListing[] createBedTrades() {
        VillagerTrades.ItemListing[] trades = new VillagerTrades.ItemListing[BED_COLORS.length + 1];
        int index = 0;

        for (Item bed : BED_COLORS) {
            trades[index++] = bed(bed);
        }

        trades[index] = trade(
                new ItemStack(COIN, 1),
                new ItemStack(Items.PAINTING, 4),
                12,
                15,
                PriceMultiplier.LOW);

        return trades;
    }

    private static VillagerTrades.ItemListing[] createBannerTrades() {
        VillagerTrades.ItemListing[] trades = new VillagerTrades.ItemListing[BANNER_COLORS.length];

        for (int i = 0; i < BANNER_COLORS.length; i++) {
            trades[i] = banner(BANNER_COLORS[i]);
        }

        return trades;
    }

    private static VillagerTrades.ItemListing wool(Item item) {
        return trade(
                new ItemStack(COIN, 1),
                new ItemStack(item, 12),
                16,
                2,
                PriceMultiplier.LOW);
    }

    private static VillagerTrades.ItemListing dye(Item item) {
        return trade(
                new ItemStack(COIN, 1),
                new ItemStack(item, 12),
                16,
                10,
                PriceMultiplier.LOW);
    }

    private static VillagerTrades.ItemListing carpet(Item item) {
        return trade(
                new ItemStack(COIN, 1),
                new ItemStack(item, 12),
                16,
                5,
                PriceMultiplier.LOW);
    }

    private static VillagerTrades.ItemListing bed(Item item) {
        return trade(
                new ItemStack(item, 1),
                new ItemStack(COIN, 3),
                12,
                15,
                PriceMultiplier.LOW);
    }

    private static VillagerTrades.ItemListing banner(Item item) {
        return trade(
                new ItemStack(COIN, 1),
                new ItemStack(item, 3),
                12,
                30,
                PriceMultiplier.LOW);
    }

    private static VillagerTrades.ItemListing sellWool(Item item) {
        return trade(
                new ItemStack(item, 16),
                new ItemStack(COIN, 1),
                16,
                2,
                PriceMultiplier.LOW);
    }

    private static VillagerTrades.ItemListing sellDye(Item item) {
        return trade(
                new ItemStack(item, 16),
                new ItemStack(COIN, 1),
                16,
                10,
                PriceMultiplier.LOW);
    }

    private static VillagerTrades.ItemListing sellCarpet(Item item) {
        return trade(
                new ItemStack(item, 16),
                new ItemStack(COIN, 1),
                16,
                5,
                PriceMultiplier.LOW);
    }
}
