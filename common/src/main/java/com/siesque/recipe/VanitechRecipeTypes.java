package com.siesque.recipe;

import com.siesque.Vanitech;
import com.siesque.recipe.alloying.AlloyingRecipe;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

public class VanitechRecipeTypes {
    private static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Vanitech.MOD_ID,
            Registries.RECIPE_TYPE);

    public static RegistrySupplier<RecipeType<AlloyingRecipe>> ALLOYING;

    public static void init() {
        ALLOYING = recipe("alloying", AlloyingRecipe.class);

        RECIPE_TYPES.register();
    }

    private static <T extends Recipe<?>> RegistrySupplier<RecipeType<T>> recipe(String name, Class<T> recipeClass) {
        return RECIPE_TYPES.register(name, () -> new RecipeType<T>() {
            @Override
            public String toString() {
                return name;
            }
        });
    }
}
