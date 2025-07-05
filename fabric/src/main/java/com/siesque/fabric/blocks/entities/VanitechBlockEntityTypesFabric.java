package com.siesque.fabric.blocks.entities;

import com.siesque.blocks.entity.AlloyBlastFurnaceEntity;
import com.siesque.blocks.entity.VanitechBlockEntityTypes;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.world.level.block.Blocks;

public class VanitechBlockEntityTypesFabric extends VanitechBlockEntityTypes {
    public static void init() {
        ALLOY_BLAST_FURNACE = registerBlockEntity("alloy_blast_furnace",
                () -> FabricBlockEntityTypeBuilder.create(AlloyBlastFurnaceEntity::new, Blocks.BLAST_FURNACE).build());

        writeRegister();
    }
}
