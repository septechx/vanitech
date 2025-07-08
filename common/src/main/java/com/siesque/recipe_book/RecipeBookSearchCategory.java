package com.siesque.recipe_book;

import net.minecraft.client.gui.screens.recipebook.SearchRecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeBookCategory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

// Hacky class to modify the RecipeBookCategory enum
public class RecipeBookSearchCategory {
    public static void init() throws NoSuchFieldException, IllegalAccessException {
        SearchRecipeBookCategory blastFurnace = SearchRecipeBookCategory.BLAST_FURNACE;

        // Access the private 'includedCategories' field
        Field field = SearchRecipeBookCategory.class.getDeclaredField("includedCategories");
        field.setAccessible(true); // Override access checks

        // Remove 'final' modifier from the field
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        // Create a new list of categories
        List<RecipeBookCategory> newCategories = new ArrayList<>();
        newCategories.add(VanitechRecipeBookCategories.ALLOYING.get());
        newCategories.add(VanitechRecipeBookCategories.BLASTING.get());

        // Set the new categories
        field.set(blastFurnace, newCategories);
    }
}
