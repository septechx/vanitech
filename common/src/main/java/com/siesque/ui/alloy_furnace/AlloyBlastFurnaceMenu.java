package com.siesque.ui.alloy_furnace;

import com.siesque.ui.VanitechMenuTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class AlloyBlastFurnaceMenu extends AbstractContainerMenu {
    public final Container container;
    public final ContainerData data;
    protected Level level;

    public AlloyBlastFurnaceMenu(int id, Inventory inventory) {
        this(id, inventory, new SimpleContainer(4), new SimpleContainerData(4));
        this.level = inventory.player.level();
    }

    public AlloyBlastFurnaceMenu(int id, Inventory inventory, Container container, ContainerData data) {
        super(VanitechMenuTypes.ALLOY_BLAST_FURNACE.get(), id);
        checkContainerSize(container, 4);
        checkContainerDataCount(data, 4);

        this.container = container;
        this.data = data;

        // Player inventory
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(inventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }

        // Player hotbar
        for (int col = 0; col < 9; ++col) {
            this.addSlot(new Slot(inventory, col, 8 + col * 18, 142));
        }

        this.addSlot(new Slot(container, 0, 45, 18)); // Primary ingredient slot
        this.addSlot(new Slot(container, 1, 66, 18)); // Secondary ingredient slot
        this.addSlot(new AlloyBlastFurnaceFuelSlot(this, container, 2, 56, 53)); // Fuel slot
        this.addSlot(new FurnaceResultSlot(inventory.player, container, 3, 116, 35)); // Result slot
        this.addDataSlots(data);
    }

    @Override
    public @NotNull ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack originalStack = slot.getItem();
            itemstack = originalStack.copy();

            // Handle furnace slots (36-39)
            if (index >= 36) {
                if (!moveItemStackTo(originalStack, 0, 36, false)) {
                    return ItemStack.EMPTY;
                }
            } else {
                // Handle player inventory slots (0-35)
                if (index >= 0 && index < 36) {
                    // Try to move to ingredient slots first (36, 37)
                    if (!moveItemStackTo(originalStack, 36, 38, false)) {
                        // If that fails, try fuel slot (38)
                        if (!moveItemStackTo(originalStack, 38, 39, false)) {
                            return ItemStack.EMPTY;
                        }
                    }
                }
            }

            if (originalStack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            slot.onTake(player, originalStack);
        }

        return itemstack;
    }

    @Override
    public boolean stillValid(Player player) {
        return container.stillValid(player);
    }

    public boolean isLit() {
        return data.get(0) > 0;
    }

    public float getBurnProgress() {
        int i = this.data.get(2);
        int j = this.data.get(3);
        return j != 0 && i != 0 ? Mth.clamp((float) i / j, 0.0F, 1.0F) : 0.0F;
    }

    public float getLitProgress() {
        int i = this.data.get(1);
        if (i == 0) {
            i = 200;
        }

        return Mth.clamp((float) this.data.get(0) / i, 0.0F, 1.0F);
    }

    public boolean isFuel(ItemStack stack) {
        return this.level.fuelValues().isFuel(stack);
    }
}