package com.siesque.blocks;

import com.siesque.Vanitech;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class VanitechBlocks {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Vanitech.MOD_ID, Registries.BLOCK);

    public static RegistrySupplier<Block> BRONZE_BLOCK;

    public static void init() {
        BRONZE_BLOCK = blockWithTool("bronze_block", SoundType.IRON, 5.5f);

        BLOCKS.register();
    }

    public static RegistrySupplier<Block> blockWithTool(String name, SoundType soundType, float strength) {
        return registerBlock(name, () -> new Block(
                baseProperties(name).sound(soundType).strength(strength).requiresCorrectToolForDrops()));
    }

    public static RegistrySupplier<Block> block(String name, SoundType soundType, float strength) {
        return registerBlock(name, () -> new Block(baseProperties(name).sound(soundType).strength(strength)));
    }

    public static RegistrySupplier<Block> registerBlock(String name, Supplier<Block> block) {
        return BLOCKS.register(ResourceLocation.fromNamespaceAndPath(Vanitech.MOD_ID, name), block);
    }

    public static BlockBehaviour.Properties baseProperties(String name) {
        return BlockBehaviour.Properties.of().setId(
                ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Vanitech.MOD_ID, name)));
    }
}
