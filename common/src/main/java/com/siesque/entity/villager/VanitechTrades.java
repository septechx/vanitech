package com.siesque.entity.villager;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.siesque.VanitechFlags;
import com.siesque.items.VanitechItems;
import dev.architectury.event.events.common.LifecycleEvent;
import dev.architectury.platform.Platform;
import dev.architectury.registry.level.entity.trade.SimpleTrade;
import dev.architectury.registry.level.entity.trade.TradeRegistry;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.saveddata.maps.MapDecorationType;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class VanitechTrades {
    public static final Item COIN = VanitechItems.EMERALD_COIN.get();

    private static Int2ObjectMap<VillagerTrades.ItemListing[]> BANKER_TRADES;
    private static Int2ObjectMap<VillagerTrades.ItemListing[]> TOOLSMITH_TRADES;
    private static Int2ObjectMap<VillagerTrades.ItemListing[]> WEAPONSMITH_TRADES;
    private static Int2ObjectMap<VillagerTrades.ItemListing[]> SHEPHERD_TRADES;
    private static Int2ObjectMap<VillagerTrades.ItemListing[]> MASON_TRADES;
    private static Int2ObjectMap<VillagerTrades.ItemListing[]> LIBRARIAN_TRADES;
    private static Int2ObjectMap<VillagerTrades.ItemListing[]> LEATHERWORKER_TRADES;
    private static Int2ObjectMap<VillagerTrades.ItemListing[]> FLETCHER_TRADES;
    private static Int2ObjectMap<VillagerTrades.ItemListing[]> FISHERMAN_TRADES;
    private static Int2ObjectMap<VillagerTrades.ItemListing[]> FARMER_TRADES;
    private static Int2ObjectMap<VillagerTrades.ItemListing[]> CLERIC_TRADES;
    private static Int2ObjectMap<VillagerTrades.ItemListing[]> CARTOGRAPHER_TRADES;
    private static Int2ObjectMap<VillagerTrades.ItemListing[]> BUCHER_TRADES;
    private static Int2ObjectMap<VillagerTrades.ItemListing[]> ARMORER_TRADES;

    public static void init() {
        // Get around there not being a deferred register for VillagerTrades
        if (Platform.isNeoForge() && !VanitechItems.EMERALD_COIN.isPresent()) {
            LifecycleEvent.SETUP.register(VanitechTrades::init);
            return;
        }

        removeTrades(VillagerProfession.TOOLSMITH);
        removeTrades(VillagerProfession.WEAPONSMITH);
        removeTrades(VillagerProfession.SHEPHERD);
        removeTrades(VillagerProfession.MASON);
        removeTrades(VillagerProfession.LIBRARIAN);
        removeTrades(VillagerProfession.LEATHERWORKER);
        removeTrades(VillagerProfession.FLETCHER);
        removeTrades(VillagerProfession.FISHERMAN);
        removeTrades(VillagerProfession.FARMER);
        removeTrades(VillagerProfession.CLERIC);
        removeTrades(VillagerProfession.CARTOGRAPHER);
        removeTrades(VillagerProfession.BUTCHER);
        removeTrades(VillagerProfession.ARMORER);

        BANKER_TRADES = VanitechBankerTrades.createBankerTrades();
        TOOLSMITH_TRADES = VanitechToolsmithTrades.createToolsmithTrades();
        WEAPONSMITH_TRADES = VanitechWeaponsmithTrades.createWeaponsmithTrades();
        SHEPHERD_TRADES = VanitechShepherdTrades.createShepherdTrades();
        MASON_TRADES = VanitechMasonTrades.createMasonTrades();
        LIBRARIAN_TRADES = VanitechLibrarianTrades.createLibrarianTrades();
        LEATHERWORKER_TRADES = VanitechLeatherworkerTrades.createLeatherworkerTrades();
        FLETCHER_TRADES = VanitechFletcherTrades.createFletcherTrades();
        FISHERMAN_TRADES = VanitechFishermanTrades.createFishermanTrades();
        FARMER_TRADES = VanitechFarmerTrades.createFarmerTrades();
        CLERIC_TRADES = VanitechClericTrades.createClericTrades();
        CARTOGRAPHER_TRADES = VanitechCartographerTrades.createCartographerTrades();
        BUCHER_TRADES = VanitechButcherTrades.createButcherTrades();
        ARMORER_TRADES = VanitechArmorerTrades.createArmorerTrades();

        if (VanitechFlags.enableBanker) registerTrades(VanitechVillagerProfessions.BANKER.getKey(), BANKER_TRADES);
        registerTrades(VillagerProfession.TOOLSMITH, TOOLSMITH_TRADES);
        registerTrades(VillagerProfession.WEAPONSMITH, WEAPONSMITH_TRADES);
        registerTrades(VillagerProfession.SHEPHERD, SHEPHERD_TRADES);
        registerTrades(VillagerProfession.MASON, MASON_TRADES);
        registerTrades(VillagerProfession.LIBRARIAN, LIBRARIAN_TRADES);
        registerTrades(VillagerProfession.LEATHERWORKER, LEATHERWORKER_TRADES);
        registerTrades(VillagerProfession.FLETCHER, FLETCHER_TRADES);
        registerTrades(VillagerProfession.FISHERMAN, FISHERMAN_TRADES);
        registerTrades(VillagerProfession.FARMER, FARMER_TRADES);
        registerTrades(VillagerProfession.CLERIC, CLERIC_TRADES);
        registerTrades(VillagerProfession.CARTOGRAPHER, CARTOGRAPHER_TRADES);
        registerTrades(VillagerProfession.BUTCHER, BUCHER_TRADES);
        registerTrades(VillagerProfession.ARMORER, ARMORER_TRADES);
    }

    private static void registerTrades(ResourceKey<VillagerProfession> profession, Int2ObjectMap<VillagerTrades.ItemListing[]> trades) {
        for (var entry : trades.int2ObjectEntrySet()) {
            TradeRegistry.registerVillagerTrade(profession, entry.getIntKey(), entry.getValue());
        }
    }

    private static void removeTrades(ResourceKey<VillagerProfession> profession) {
        VillagerTrades.TRADES.remove(profession);
    }

    protected static Int2ObjectMap<VillagerTrades.ItemListing[]> toIntMap(ImmutableMap<Integer, VillagerTrades.ItemListing[]> map) {
        return new Int2ObjectOpenHashMap<>(map);
    }

    protected static VillagerTrades.ItemListing trade(ItemStack price, ItemStack product, int maxTrades,
                                                      int experiencePoints, PriceMultiplier multiplier) {
        return new SimpleTrade(
                new ItemCost(price.getItem(), price.getCount()),
                Optional.empty(),
                product,
                maxTrades,
                experiencePoints,
                multiplier.value());
    }

    protected static VillagerTrades.ItemListing multiItemTrade(ItemStack price, ItemStack secondaryPrice, ItemStack product, int maxTrades,
                                                               int experiencePoints, PriceMultiplier multiplier) {
        return new SimpleTrade(
                new ItemCost(price.getItem(), price.getCount()),
                Optional.of(new ItemCost(secondaryPrice.getItem())),
                product,
                maxTrades,
                experiencePoints,
                multiplier.value()
        );
    }

    protected static VillagerTrades.ItemListing enchantedItem(Item item, int baseCoinCost, int maxTrades, int experiencePoints,
                                                              PriceMultiplier multiplier) {
        return new EnchantedItemForEmeralds(item, baseCoinCost, maxTrades, experiencePoints, multiplier);
    }

    protected static VillagerTrades.ItemListing enchantBook(int villagerXp) {
        return new EnchantBookForEmeralds(villagerXp, EnchantmentTags.TRADEABLE);
    }

    protected static VillagerTrades.ItemListing dyedArmor(Item item, int value) {
        return new DyedArmorForEmeralds(item, value);
    }

    protected static VillagerTrades.ItemListing tippedArrow(Item fromItem, int fromCount, Item toItem, int toCount, int emeraldCost, int maxUses, int villagerXp) {
        return new TippedArrowForItemsAndEmeralds(fromItem, fromCount, toItem, toCount, emeraldCost, maxUses, villagerXp);
    }

    protected static VillagerTrades.ItemListing treasureMap(int emeraldCost, TagKey<Structure> destination, String displayName, Holder<MapDecorationType> destinationType, int maxUses, int villagerXp) {
        return new TreasureMapForEmeralds(emeraldCost, destination, displayName, destinationType, maxUses, villagerXp);
    }

    public enum PriceMultiplier {
        HIGH(),
        LOW();

        PriceMultiplier() {
        }

        public float value() {
            return switch (this) {
                case HIGH -> 0.20f;
                case LOW -> 0.05f;
            };
        }
    }

    // From Minecraft's VillagerTrades.EnchantedItemForEmeralds to avoid writing an access widener
    public static final class EnchantedItemForEmeralds implements VillagerTrades.ItemListing {
        private final ItemStack itemStack;
        private final int baseEmeraldCost;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public EnchantedItemForEmeralds(Item item, int baseEmeraldCost, int maxUses, int villagerXp, PriceMultiplier multiplier) {
            this(item, baseEmeraldCost, maxUses, villagerXp, multiplier.value());
        }

        public EnchantedItemForEmeralds(Item item, int baseEmeraldCost, int maxUses, int villagerXp, float priceMultiplier) {
            this.itemStack = new ItemStack(item);
            this.baseEmeraldCost = baseEmeraldCost;
            this.maxUses = maxUses;
            this.villagerXp = villagerXp;
            this.priceMultiplier = priceMultiplier;
        }

        public MerchantOffer getOffer(Entity trader, RandomSource random) {
            int i = 5 + random.nextInt(15);
            RegistryAccess registryAccess = trader.level().registryAccess();
            Optional<HolderSet.Named<Enchantment>> optional = registryAccess.lookupOrThrow(Registries.ENCHANTMENT).get(EnchantmentTags.ON_TRADED_EQUIPMENT);
            ItemStack itemStack = EnchantmentHelper.enchantItem(random, new ItemStack(this.itemStack.getItem()), i, registryAccess, optional);
            int j = Math.min(this.baseEmeraldCost + i, 64);
            ItemCost itemCost = new ItemCost(COIN, j);
            return new MerchantOffer(itemCost, itemStack, this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

    // From Minecraft's VillagerTrades.EnchantedBookForEmeralds to avoid writing an access widener
    public static final class EnchantBookForEmeralds implements VillagerTrades.ItemListing {
        private final int villagerXp;
        private final TagKey<Enchantment> tradeableEnchantments;
        private final int minLevel;
        private final int maxLevel;

        public EnchantBookForEmeralds(int villagerXp, TagKey<Enchantment> tradeableEnchantments) {
            this(villagerXp, 0, Integer.MAX_VALUE, tradeableEnchantments);
        }

        public EnchantBookForEmeralds(int villagerXp, int minLevel, int maxLevel, TagKey<Enchantment> tradeableEnchantments) {
            this.minLevel = minLevel;
            this.maxLevel = maxLevel;
            this.villagerXp = villagerXp;
            this.tradeableEnchantments = tradeableEnchantments;
        }

        public MerchantOffer getOffer(Entity trader, RandomSource random) {
            Optional<Holder<Enchantment>> optional = trader.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getRandomElementOf(this.tradeableEnchantments, random);
            int l;
            ItemStack itemStack;
            if (optional.isPresent()) {
                Holder<Enchantment> holder = (Holder) optional.get();
                Enchantment enchantment = (Enchantment) holder.value();
                int i = Math.max(enchantment.getMinLevel(), this.minLevel);
                int j = Math.min(enchantment.getMaxLevel(), this.maxLevel);
                int k = Mth.nextInt(random, i, j);
                itemStack = EnchantmentHelper.createBook(new EnchantmentInstance(holder, k));
                l = 2 + random.nextInt(5 + k * 10) + 3 * k;
                if (holder.is(EnchantmentTags.DOUBLE_TRADE_PRICE)) {
                    l *= 2;
                }

                // Multiply by 2 to match the original price scaling (should be 16, but we only have 2 slots)
                l *= 2;

                if (l > 128) {
                    l = 128;
                }
            } else {
                l = 1;
                itemStack = new ItemStack(Items.BOOK);
            }

            if (l > 64) {
                return new MerchantOffer(
                        new ItemCost(COIN, 64),
                        // Sadly we have to use the second slot for emeralds instead of a book :(
                        Optional.of(new ItemCost(COIN, l - 64)),
                        itemStack,
                        12,
                        this.villagerXp,
                        0.2F
                );
            }

            return new MerchantOffer(
                    new ItemCost(COIN, l),
                    Optional.of(new ItemCost(Items.BOOK)),
                    itemStack,
                    12,
                    this.villagerXp,
                    0.2F
            );
        }
    }

    // From Minecraft's VillagerTrades.TippedArrowForItemsAndEmeralds to avoid writing an access widener
    static class TippedArrowForItemsAndEmeralds implements VillagerTrades.ItemListing {
        private final ItemStack toItem;
        private final int toCount;
        private final int emeraldCost;
        private final int maxUses;
        private final int villagerXp;
        private final Item fromItem;
        private final int fromCount;
        private final float priceMultiplier;

        public TippedArrowForItemsAndEmeralds(Item fromItem, int fromCount, Item toItem, int toCount, int emeraldCost, int maxUses, int villagerXp) {
            this.toItem = new ItemStack(toItem);
            this.emeraldCost = emeraldCost;
            this.maxUses = maxUses;
            this.villagerXp = villagerXp;
            this.fromItem = fromItem;
            this.fromCount = fromCount;
            this.toCount = toCount;
            this.priceMultiplier = 0.05F;
        }

        public MerchantOffer getOffer(Entity trader, RandomSource random) {
            ItemCost itemCost = new ItemCost(COIN, this.emeraldCost);
            List<Holder<Potion>> list = (List) BuiltInRegistries.POTION.listElements().filter((reference) -> !((Potion) reference.value()).getEffects().isEmpty() && trader.level().potionBrewing().isBrewablePotion(reference)).collect(Collectors.toList());
            Holder<Potion> holder = (Holder) Util.getRandom(list, random);
            ItemStack itemStack = new ItemStack(this.toItem.getItem(), this.toCount);
            itemStack.set(DataComponents.POTION_CONTENTS, new PotionContents(holder));
            return new MerchantOffer(itemCost, Optional.of(new ItemCost(this.fromItem, this.fromCount)), itemStack, this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

    // From Minecraft's VillagerTrades.DyedArmorForEmeralds to avoid writing an access widener
    static class DyedArmorForEmeralds implements VillagerTrades.ItemListing {
        private final Item item;
        private final int value;
        private final int maxUses;
        private final int villagerXp;

        public DyedArmorForEmeralds(Item item, int value) {
            this(item, value, 12, 1);
        }

        public DyedArmorForEmeralds(Item item, int value, int maxUses, int villagerXp) {
            this.item = item;
            this.value = value;
            this.maxUses = maxUses;
            this.villagerXp = villagerXp;
        }

        private static DyeItem getRandomDye(RandomSource random) {
            return DyeItem.byColor(DyeColor.byId(random.nextInt(16)));
        }

        public MerchantOffer getOffer(Entity trader, RandomSource random) {
            ItemCost itemCost = new ItemCost(COIN, this.value);
            ItemStack itemStack = new ItemStack(this.item);
            if (itemStack.is(ItemTags.DYEABLE)) {
                List<DyeItem> list = Lists.newArrayList();
                list.add(getRandomDye(random));
                if (random.nextFloat() > 0.7F) {
                    list.add(getRandomDye(random));
                }

                if (random.nextFloat() > 0.8F) {
                    list.add(getRandomDye(random));
                }

                itemStack = DyedItemColor.applyDyes(itemStack, list);
            }

            return new MerchantOffer(itemCost, itemStack, this.maxUses, this.villagerXp, 0.2F);
        }
    }

    // From Minecraft's VillagerTrades.TreasureMapForEmeralds to avoid writing an access widener
    static class TreasureMapForEmeralds implements VillagerTrades.ItemListing {
        private final int emeraldCost;
        private final TagKey<Structure> destination;
        private final String displayName;
        private final Holder<MapDecorationType> destinationType;
        private final int maxUses;
        private final int villagerXp;

        public TreasureMapForEmeralds(int emeraldCost, TagKey<Structure> destination, String displayName, Holder<MapDecorationType> destinationType, int maxUses, int villagerXp) {
            this.emeraldCost = emeraldCost;
            this.destination = destination;
            this.displayName = displayName;
            this.destinationType = destinationType;
            this.maxUses = maxUses;
            this.villagerXp = villagerXp;
        }

        @Nullable
        public MerchantOffer getOffer(Entity trader, RandomSource random) {
            Level var4 = trader.level();
            if (var4 instanceof ServerLevel serverLevel) {
                BlockPos blockPos = serverLevel.findNearestMapStructure(this.destination, trader.blockPosition(), 100, true);
                if (blockPos != null) {
                    ItemStack itemStack = MapItem.create(serverLevel, blockPos.getX(), blockPos.getZ(), (byte) 2, true, true);
                    MapItem.renderBiomePreviewMap(serverLevel, itemStack);
                    MapItemSavedData.addTargetDecoration(itemStack, blockPos, "+", this.destinationType);
                    itemStack.set(DataComponents.ITEM_NAME, Component.translatable(this.displayName));
                    return new MerchantOffer(new ItemCost(COIN, this.emeraldCost), Optional.of(new ItemCost(Items.COMPASS)), itemStack, this.maxUses, this.villagerXp, 0.2F);
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }
    }
}
