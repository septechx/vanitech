package com.siesque.recipe;

import com.siesque.Vanitech;
import com.siesque.recipe.alloying.AlloyingRecipeDisplay;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.display.RecipeDisplay;

public class VanitechRecipeDisplayTypes {
    private static final DeferredRegister<RecipeDisplay.Type<?>> RECEIPE_DISPLAY_TYPES = DeferredRegister
            .create(Vanitech.MOD_ID, Registries.RECIPE_DISPLAY);

    public static RegistrySupplier<RecipeDisplay.Type<AlloyingRecipeDisplay>> ALLOYING;

    public static void init() {
        ALLOYING = RECEIPE_DISPLAY_TYPES.register("alloying",
                () -> new RecipeDisplay.Type<AlloyingRecipeDisplay>(AlloyingRecipeDisplay.MAP_CODEC,
                        AlloyingRecipeDisplay.STREAM_CODEC));

        RECEIPE_DISPLAY_TYPES.register();
    }
}