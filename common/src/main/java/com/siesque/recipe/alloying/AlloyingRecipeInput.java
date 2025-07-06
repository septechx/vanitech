package com.siesque.recipe.alloying;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import org.jetbrains.annotations.NotNull;

public record AlloyingRecipeInput(ItemStack primaryItem, ItemStack secondaryItem) implements RecipeInput {
    @Override
    public @NotNull ItemStack getItem(int slot) {
        return switch (slot) {
            case 0 -> primaryItem;
            case 1 -> secondaryItem;
            default -> throw new IllegalArgumentException("No item for index " + slot);
        };
    }

    @Override
    public int size() {
        return 2;
    }
}