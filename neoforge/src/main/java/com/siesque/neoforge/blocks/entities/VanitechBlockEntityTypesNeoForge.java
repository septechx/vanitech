package com.siesque.neoforge.blocks.entities;

import com.siesque.blocks.entity.AlloyBlastFurnaceEntity;
import com.siesque.blocks.entity.VanitechBlockEntityTypes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class VanitechBlockEntityTypesNeoForge extends VanitechBlockEntityTypes {
    public static void init() {
        ALLOY_BLAST_FURNACE = registerBlockEntity("alloy_blast_furnace", () ->
                new BlockEntityType<>(AlloyBlastFurnaceEntity::new, Blocks.BLAST_FURNACE));

        writeRegister();
    }
}
