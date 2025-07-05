package com.siesque.recipe_book;

import com.siesque.Vanitech;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeBookCategory;

public class VanitechRecipeBookCategories {
    public static final DeferredRegister<RecipeBookCategory> RECIPE_BOOK_CATEGORIES = DeferredRegister
            .create(Vanitech.MOD_ID, Registries.RECIPE_BOOK_CATEGORY);

    public static RegistrySupplier<RecipeBookCategory> ALLOYING;

    public static void init() {
        ALLOYING = category("alloying");

        RECIPE_BOOK_CATEGORIES.register();
    }

    private static RegistrySupplier<RecipeBookCategory> category(String name) {
        return RECIPE_BOOK_CATEGORIES.register(name, RecipeBookCategory::new);
    }
}
