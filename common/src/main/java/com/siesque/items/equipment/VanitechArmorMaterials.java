package com.siesque.items.equipment;

import com.siesque.Vanitech;
import com.siesque.tags.VanitechTags;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

import java.util.Map;

public class VanitechArmorMaterials {
    public static final ResourceKey<EquipmentAsset> BRONZE_ARMOR_MATERIAL_KEY = ResourceKey.create(
            EquipmentAssets.ROOT_ID, ResourceLocation.fromNamespaceAndPath(Vanitech.MOD_ID, "bronze")
    );
    public static final ResourceKey<EquipmentAsset> ECHO_ARMOR_MATERIAL_KEY = ResourceKey.create(
            EquipmentAssets.ROOT_ID, ResourceLocation.fromNamespaceAndPath(Vanitech.MOD_ID, "echo")
    );

    public static final ArmorMaterial BRONZE_ARMOR_MATERIAL = new ArmorMaterial(
            24, // Durability
            Map.of(
                    ArmorType.HELMET, 3,
                    ArmorType.CHESTPLATE, 8,
                    ArmorType.LEGGINGS, 6,
                    ArmorType.BOOTS, 3
            ),
            12, // Enchantability
            SoundEvents.ARMOR_EQUIP_IRON,
            2.0f, // Toughness
            0.05f, // Knockback resistance
            VanitechTags.REPAIRS_BRONZE_ARMOR,
            BRONZE_ARMOR_MATERIAL_KEY
    );

    public static final ArmorMaterial ECHO_ARMOR_MATERIAL = new ArmorMaterial(
            150,
            Map.of(
                    ArmorType.HELMET, 3,
                    ArmorType.CHESTPLATE, 8,
                    ArmorType.LEGGINGS, 6,
                    ArmorType.BOOTS, 3
            ),
            18,
            SoundEvents.ARMOR_EQUIP_NETHERITE,
            5.0f,
            0.20f,
            VanitechTags.REPAIRS_ECHO_ARMOR,
            ECHO_ARMOR_MATERIAL_KEY
    );
}
