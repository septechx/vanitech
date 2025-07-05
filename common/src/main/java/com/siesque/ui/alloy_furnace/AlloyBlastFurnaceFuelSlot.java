package com.siesque.ui.alloy_furnace;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class AlloyBlastFurnaceFuelSlot extends Slot {
    private final AlloyBlastFurnaceMenu menu;

    public AlloyBlastFurnaceFuelSlot(AlloyBlastFurnaceMenu menu, Container container, int slot, int xPosition,
            int yPosition) {
        super(container, slot, xPosition, yPosition);
        this.menu = menu;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return this.isFuel(stack) || isBucket(stack);
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return isBucket(stack) ? 1 : super.getMaxStackSize(stack);
    }

    private boolean isFuel(ItemStack stack) {
        return menu.isFuel(stack);
    }

    public static boolean isBucket(ItemStack stack) {
        return stack.is(Items.BUCKET);
    }
}