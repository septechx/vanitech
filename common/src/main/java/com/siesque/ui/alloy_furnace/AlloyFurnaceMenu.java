package com.siesque.ui.alloy_furnace;

import com.siesque.Vanitech;
import com.siesque.ui.VanitechMenuTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class AlloyFurnaceMenu extends AbstractContainerMenu {
    public final Container container;
    public final ContainerData data;

    public AlloyFurnaceMenu(int id, Inventory inventory) {
        this(id, inventory, new SimpleContainer(4), new SimpleContainerData(4));
    }

    public AlloyFurnaceMenu(int id, Inventory inventory, Container container, ContainerData data) {
        super(VanitechMenuTypes.ALLOY_FURNACE.get(), id);
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
        this.addSlot(new Slot(container, 2, 56, 53)); // Fuel slot
        this.addSlot(new Slot(container, 3, 116, 35)); // Result slot

        this.addDataSlots(data);
    }

    @Override
    public @NotNull ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack originalStack = slot.getItem();
            itemstack = originalStack.copy();

            if (index > 36) {
                if (!moveItemStackTo(originalStack, 36, 40, false)) {
                    return ItemStack.EMPTY;
                } else if (index < 40) {
                    if (!moveItemStackTo(originalStack, 0, 36, false)) {
                        return ItemStack.EMPTY;
                    }
                } else {
                    Vanitech.LOGGER.error("Invalid slot index: {}", index);
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
}
