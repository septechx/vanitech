package com.siesque.tags;

import com.siesque.Vanitech;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class VanitechTags {
    public static final TagKey<Item> BRONZE_TOOL_MATERIALS = item("bronze_tool_materials");
    public static final TagKey<Item> REPAIRS_BRONZE_ARMOR = item("repairs_bronze_armor");

    public static TagKey<Item> item(String name) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Vanitech.MOD_ID, name));
    }
}
