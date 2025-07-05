package com.siesque.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.BlastFurnaceMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.FuelValues;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class AlloyBlastFurnaceEntity extends AbstractFurnaceBlockEntity {
    public AlloyBlastFurnaceEntity(BlockPos pos, BlockState blockState) {
        super(VanitechBlockEntityTypes.ALLOY_BLAST_FURNACE.get(), pos, blockState, RecipeType.BLASTING);
        System.out.println("AlloyBlastFurnaceEntity created at " + pos + "with state" + blockState);
    }

    @Override
    protected @NotNull Component getDefaultName() {
        return Component.translatable("container.blast_furnace");
    }

    @Override
    protected int getBurnDuration(FuelValues fuelValues, ItemStack stack) {
        return super.getBurnDuration(fuelValues, stack) / 2;
    }

    @Override
    protected @NotNull AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        return new BlastFurnaceMenu(containerId, inventory, this, this.dataAccess);
    }
}
