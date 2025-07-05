package com.siesque.recipe.alloying;

import com.siesque.recipe.VanitechRecipeSerializers;
import com.siesque.recipe.VanitechRecipeTypes;
import com.siesque.recipe_book.VanitechRecipeBookCategories;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import java.util.List;

public class AlloyingRecipe implements Recipe<AlloyingRecipeInput> {
    private final Ingredient primaryIngredient;
    private final Ingredient secondaryIngredient;
    private final ItemStack result;
    private final int processingTime;
    private final float experience;

    public AlloyingRecipe(Ingredient primaryIngredient, Ingredient secondaryIngredient, ItemStack result,
                          int processingTime, float experience) {
        this.primaryIngredient = primaryIngredient;
        this.secondaryIngredient = secondaryIngredient;
        this.result = result;
        this.processingTime = processingTime;
        this.experience = experience;
    }

    @Override
    public boolean matches(AlloyingRecipeInput input, Level level) {
        return (primaryIngredient.test(input.primaryItem()) && secondaryIngredient.test(input.secondaryItem())) ||
                (secondaryIngredient.test(input.primaryItem()) && primaryIngredient.test(input.secondaryItem()));
    }

    @Override
    public ItemStack assemble(AlloyingRecipeInput input, HolderLookup.Provider registries) {
        return result.copy();
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public RecipeType<AlloyingRecipe> getType() {
        return VanitechRecipeTypes.ALLOYING.get();
    }

    @Override
    public RecipeSerializer<AlloyingRecipe> getSerializer() {
        return VanitechRecipeSerializers.ALLOYING.get();
    }

    public Ingredient getPrimaryIngredient() {
        return primaryIngredient;
    }

    public Ingredient getSecondaryIngredient() {
        return secondaryIngredient;
    }

    public ItemStack getResult() {
        return result;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public float getExperience() {
        return experience;
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.create(List.of(primaryIngredient, secondaryIngredient));
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return VanitechRecipeBookCategories.ALLOYING.get();
    }
}
