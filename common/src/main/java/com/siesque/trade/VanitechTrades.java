package com.siesque.trade;

import com.siesque.items.VanitechItems;
import dev.architectury.event.events.common.LifecycleEvent;
import dev.architectury.platform.Platform;
import dev.architectury.registry.level.entity.trade.SimpleTrade;
import dev.architectury.registry.level.entity.trade.TradeRegistry;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;

import java.util.Optional;

public class VanitechTrades {
    private static VillagerTrades.ItemListing[] BANKER_TRADES;

    public static void init() {
        // Get around there not being a deferred register for VillagerTrades
        if (Platform.isNeoForge() && !VanitechItems.EMERALD_COIN.isPresent()) {
            LifecycleEvent.SETUP.register(VanitechTrades::init);
            return;
        }

        BANKER_TRADES = new VillagerTrades.ItemListing[]{
                trade(
                        new ItemStack(Items.EMERALD, 1),
                        new ItemStack(VanitechItems.EMERALD_COIN.get(), 16),
                        16,
                        2),
                trade(
                        new ItemStack(Items.DIAMOND, 1),
                        new ItemStack(VanitechItems.EMERALD_COIN.get(), 16),
                        16,
                        2)
        };

        TradeRegistry.registerVillagerTrade(VanitechVillagerProfessions.BANKER.getKey(), 1, BANKER_TRADES);
    }

    private static VillagerTrades.ItemListing trade(ItemStack price, ItemStack product, int maxTrades,
                                                    int experiencePoints) {
        return new SimpleTrade(
                new ItemCost(price.getItem()),
                Optional.empty(),
                product,
                maxTrades,
                experiencePoints,
                0.05f);
    }
}
