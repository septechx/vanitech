package com.siesque.items;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.List;

public class VanitechSmithingItems extends SmithingTemplateItem {
    private static final ResourceLocation EMPTY_SLOT_HELMET = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_helmet");
    private static final ResourceLocation EMPTY_SLOT_CHESTPLATE = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_chestplate");
    private static final ResourceLocation EMPTY_SLOT_LEGGINGS = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_leggings");
    private static final ResourceLocation EMPTY_SLOT_BOOTS = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_boots");
    private static final ResourceLocation EMPTY_SLOT_HOE = ResourceLocation.withDefaultNamespace("item/empty_slot_hoe");
    private static final ResourceLocation EMPTY_SLOT_AXE = ResourceLocation.withDefaultNamespace("item/empty_slot_axe");
    private static final ResourceLocation EMPTY_SLOT_SWORD = ResourceLocation.withDefaultNamespace("item/empty_slot_sword");
    private static final ResourceLocation EMPTY_SLOT_SHOVEL = ResourceLocation.withDefaultNamespace("item/empty_slot_shovel");
    private static final ResourceLocation EMPTY_SLOT_PICKAXE = ResourceLocation.withDefaultNamespace("item/empty_slot_pickaxe");

    private static final ResourceLocation EMPTY_SLOT_INGOT = ResourceLocation.withDefaultNamespace("item/empty_slot_ingot");
    private static final Component ECHO_UPGRADE =
            Component.translatable("upgrade.vanitech.echo_upgrade").withStyle(Style.EMPTY.withColor(0x034150));
    private static final Component ECHO_UPGRADE_APPLIES_TO =
            Component.translatable("item.vanitech.smithing_template.echo_upgrade.applies_to");
    private static final Component ECHO_UPGRADE_INGREDIENTS =
            Component.translatable("item.vanitech.smithing_template.echo_upgrade.ingredients");
    private static final Component ECHO_UPGRADE_BASE_SLOT_DESCRIPTION =
            Component.translatable("item.vanitech.smithing_template.echo_upgrade.base_slot_description");
    private static final Component ECHO_UPGRADE_ADDITIONS_SLOT_DESCRIPTION =
            Component.translatable("item.vanitech.smithing_template.echo_upgrade.additions_slot_description");

    public VanitechSmithingItems(Component appliesTo, Component ingredients, Component baseSlotDescription,
                                 Component additionsSlotDescription, List<ResourceLocation> baseSlotEmptyIcons,
                                 List<ResourceLocation> additionalSlotEmptyIcons, Item.Properties properties) {
        super(appliesTo, ingredients, baseSlotDescription, additionsSlotDescription, baseSlotEmptyIcons, additionalSlotEmptyIcons, properties);
    }

    public static SmithingTemplateItem createEchoUpgradeTemplate(Item.Properties properties) {
        return new SmithingTemplateItem(ECHO_UPGRADE_APPLIES_TO, ECHO_UPGRADE_INGREDIENTS,
                ECHO_UPGRADE_BASE_SLOT_DESCRIPTION, ECHO_UPGRADE_ADDITIONS_SLOT_DESCRIPTION,
                createEchoUpgradeIconList(), createUpgradeMaterialList(), properties);
    }

    private static List<ResourceLocation> createEchoUpgradeIconList() {
        return List.of(EMPTY_SLOT_HELMET, EMPTY_SLOT_SWORD, EMPTY_SLOT_CHESTPLATE, EMPTY_SLOT_PICKAXE,
                EMPTY_SLOT_LEGGINGS, EMPTY_SLOT_AXE, EMPTY_SLOT_BOOTS, EMPTY_SLOT_HOE, EMPTY_SLOT_SHOVEL);
    }

    private static List<ResourceLocation> createUpgradeMaterialList() {
        return List.of(EMPTY_SLOT_INGOT);
    }
}
