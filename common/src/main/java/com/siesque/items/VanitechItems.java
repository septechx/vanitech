package com.siesque.items;

import com.siesque.Vanitech;
import com.siesque.blocks.VanitechBlocks;
import com.siesque.items.equipment.VanitechArmorMaterials;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class VanitechItems {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Vanitech.MOD_ID, Registries.ITEM);

    public static RegistrySupplier<Item> BRONZE_INGOT;
    public static RegistrySupplier<Item> BRONZE_NUGGET;
    public static RegistrySupplier<Item> EMERALD_COIN;

    public static RegistrySupplier<Item> BRONZE_BLOCK;

    public static RegistrySupplier<Item> BRONZE_SWORD;
    public static RegistrySupplier<Item> BRONZE_PICKAXE;
    public static RegistrySupplier<Item> BRONZE_AXE;
    public static RegistrySupplier<Item> BRONZE_SHOVEL;
    public static RegistrySupplier<Item> BRONZE_HOE;

    public static RegistrySupplier<Item> BRONZE_HELMET;
    public static RegistrySupplier<Item> BRONZE_CHESTPLATE;
    public static RegistrySupplier<Item> BRONZE_LEGGINGS;
    public static RegistrySupplier<Item> BRONZE_BOOTS;

    public static RegistrySupplier<Item> ENDER_KEY;

    public static void init() {
        BRONZE_INGOT = item("bronze_ingot", CreativeModeTabs.INGREDIENTS);
        BRONZE_NUGGET = item("bronze_nugget", CreativeModeTabs.INGREDIENTS);
        EMERALD_COIN = item("emerald_coin", CreativeModeTabs.INGREDIENTS);

        BRONZE_BLOCK = block("bronze_block", VanitechBlocks.BRONZE_BLOCK, CreativeModeTabs.BUILDING_BLOCKS);

        BRONZE_SWORD = sword("bronze_sword", VanitechToolMaterials.BRONZE, 4.5f, -2.4f);
        BRONZE_PICKAXE = pickaxe("bronze_pickaxe", VanitechToolMaterials.BRONZE, 2.5f, -2.8f);
        BRONZE_AXE = axe("bronze_axe", VanitechToolMaterials.BRONZE, 5.5f, -3.0f);
        BRONZE_SHOVEL = shovel("bronze_shovel", VanitechToolMaterials.BRONZE, 2.0f, -3.0f);
        BRONZE_HOE = hoe("bronze_hoe", VanitechToolMaterials.BRONZE, 0.5f, -2.0f);

        BRONZE_HELMET = armor("bronze_helmet", VanitechArmorMaterials.BRONZE_ARMOR_MATERIAL, ArmorType.HELMET);
        BRONZE_CHESTPLATE = armor("bronze_chestplate", VanitechArmorMaterials.BRONZE_ARMOR_MATERIAL, ArmorType.CHESTPLATE);
        BRONZE_LEGGINGS = armor("bronze_leggings", VanitechArmorMaterials.BRONZE_ARMOR_MATERIAL, ArmorType.LEGGINGS);
        BRONZE_BOOTS = armor("bronze_boots", VanitechArmorMaterials.BRONZE_ARMOR_MATERIAL, ArmorType.BOOTS);

        ENDER_KEY = custom("ender_key", EnderKeyItem.class, CreativeModeTabs.TOOLS_AND_UTILITIES, Rarity.RARE);

        ITEMS.register();
    }

    public static RegistrySupplier<Item> custom(String name, Class<? extends Item> itemClass,
                                                ResourceKey<CreativeModeTab> tab, Rarity rarity) {
        return registerItem(name, () -> {
            try {
                return itemClass.getConstructor(Item.Properties.class).newInstance(baseProperties(name).arch$tab(tab).rarity(rarity));
            } catch (Exception e) {
                Vanitech.LOGGER.error("Failed to create item: {}", name);
                throw new RuntimeException(e);
            }
        });
    }

    public static RegistrySupplier<Item> item(String name, ResourceKey<CreativeModeTab> tab) {
        return registerItem(name, () -> new Item(baseProperties(name).arch$tab(tab)));
    }

    public static RegistrySupplier<Item> block(String name, Supplier<Block> block,
                                               ResourceKey<CreativeModeTab> tab) {
        return registerItem(name, () -> new BlockItem(block.get(), baseProperties(name).arch$tab(tab)));
    }

    public static RegistrySupplier<Item> armor(String name, ArmorMaterial material,
                                               ArmorType armorType) {
        return registerItem(name, () -> new Item(baseProperties(name)
                .humanoidArmor(material, armorType)
                .arch$tab(CreativeModeTabs.COMBAT)));
    }

    public static RegistrySupplier<Item> pickaxe(String name, ToolMaterial material, float attackDamage,
                                                 float attackSpeed) {
        return registerItem(name, () -> new Item(baseProperties(name)
                .pickaxe(material, attackDamage, attackSpeed)
                .arch$tab(CreativeModeTabs.TOOLS_AND_UTILITIES)));
    }

    public static RegistrySupplier<Item> sword(String name, ToolMaterial material, float attackDamage,
                                               float attackSpeed) {
        return registerItem(name, () -> new Item(baseProperties(name)
                .sword(material, attackDamage, attackSpeed)
                .arch$tab(CreativeModeTabs.COMBAT)));
    }

    public static RegistrySupplier<Item> axe(String name, ToolMaterial material, float attackDamage,
                                             float attackSpeed) {
        return registerItem(name, () -> new Item(baseProperties(name)
                .axe(material, attackDamage, attackSpeed)
                .arch$tab(CreativeModeTabs.TOOLS_AND_UTILITIES)));
    }

    public static RegistrySupplier<Item> shovel(String name, ToolMaterial material, float attackDamage,
                                                float attackSpeed) {
        return registerItem(name, () -> new Item(baseProperties(name)
                .shovel(material, attackDamage, attackSpeed)
                .arch$tab(CreativeModeTabs.TOOLS_AND_UTILITIES)));
    }

    public static RegistrySupplier<Item> hoe(String name, ToolMaterial material, float attackDamage,
                                             float attackSpeed) {
        return registerItem(name, () -> new Item(baseProperties(name)
                .hoe(material, attackDamage, attackSpeed)
                .arch$tab(CreativeModeTabs.TOOLS_AND_UTILITIES)));
    }

    public static RegistrySupplier<Item> registerItem(String name, Supplier<Item> item) {
        return ITEMS.register(ResourceLocation.fromNamespaceAndPath(Vanitech.MOD_ID, name), item);
    }

    public static Item.Properties baseProperties(String name) {
        return new Item.Properties().setId(
                ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Vanitech.MOD_ID, name)));
    }
}
