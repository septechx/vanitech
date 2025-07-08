package com.siesque.recipe.alloying;

import com.siesque.recipe.VanitechRecipeSerializers;
import com.siesque.recipe.VanitechRecipeTypes;
import com.siesque.recipe_book.VanitechRecipeBookCategories;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public record AlloyingRecipe(Ingredient primaryIngredient, Ingredient secondaryIngredient, ItemStack result,
                             int processingTime, float experience,
                             boolean blasting) implements Recipe<AlloyingRecipeInput> {

    @Override
    public boolean matches(AlloyingRecipeInput input, Level level) {
        return (primaryIngredient.test(input.primaryItem()) && secondaryIngredient.test(input.secondaryItem())) ||
                (secondaryIngredient.test(input.primaryItem()) && primaryIngredient.test(input.secondaryItem()));
    }

    @Override
    public @NotNull ItemStack assemble(AlloyingRecipeInput input, HolderLookup.Provider registries) {
        return result.copy();
    }

    @Override
    public @NotNull RecipeType<AlloyingRecipe> getType() {
        return VanitechRecipeTypes.ALLOYING.get();
    }

    @Override
    public @NotNull RecipeSerializer<AlloyingRecipe> getSerializer() {
        return VanitechRecipeSerializers.ALLOYING.get();
    }

    @Override
    public @NotNull PlacementInfo placementInfo() {
        return PlacementInfo.create(List.of(primaryIngredient, secondaryIngredient));
    }

    @Override
    public @NotNull RecipeBookCategory recipeBookCategory() {
        if (blasting) {
            return VanitechRecipeBookCategories.BLASTING.get();
        }
        return VanitechRecipeBookCategories.ALLOYING.get();
    }

    @Override
    public @NotNull List<RecipeDisplay> display() {
        return List.of(
                new AlloyingRecipeDisplay(
                        this.primaryIngredient.display(),
                        this.secondaryIngredient.display(),
                        new SlotDisplay.ItemStackSlotDisplay(this.result),
                        new SlotDisplay.ItemSlotDisplay(Items.GRASS_BLOCK)));
    }
}
