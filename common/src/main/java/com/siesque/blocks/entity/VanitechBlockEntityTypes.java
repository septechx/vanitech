package com.siesque.blocks.entity;

import com.siesque.Vanitech;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public class VanitechBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Vanitech.MOD_ID, net.minecraft.core.registries.Registries.BLOCK_ENTITY_TYPE);

    public static RegistrySupplier<BlockEntityType<AlloyBlastFurnaceEntity>> ALLOY_BLAST_FURNACE;

    public static void writeRegister() {
        BLOCK_ENTITIES.register();
    }

    public static <T extends BlockEntityType<?>> RegistrySupplier<T> registerBlockEntity(String name, Supplier<T> blockEntity) {
        return BLOCK_ENTITIES.register(ResourceLocation.fromNamespaceAndPath(Vanitech.MOD_ID, name), blockEntity);
    }
}
