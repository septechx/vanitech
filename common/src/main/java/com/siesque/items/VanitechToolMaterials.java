package com.siesque.items;

import com.siesque.tags.VanitechTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ToolMaterial;

public class VanitechToolMaterials {
    public static final ToolMaterial BRONZE = new ToolMaterial(
            BlockTags.INCORRECT_FOR_IRON_TOOL,
            1024, // Durability
            6.0f, // Mining speed
            2.0f, // Attack damage
            12, // Enchantability
            VanitechTags.BRONZE_TOOL_MATERIALS
    );
}
