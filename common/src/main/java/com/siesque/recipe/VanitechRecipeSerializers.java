package com.siesque.recipe;

import com.siesque.Vanitech;
import com.siesque.recipe.alloying.AlloyingRecipe;
import com.siesque.recipe.alloying.AlloyingRecipeSerializer;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;

import java.util.function.Supplier;

public class VanitechRecipeSerializers {
    private static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister
            .create(Vanitech.MOD_ID, Registries.RECIPE_SERIALIZER);

    public static RegistrySupplier<RecipeSerializer<AlloyingRecipe>> ALLOYING;

    public static void init() {
        ALLOYING = registerSerializer("alloying", AlloyingRecipeSerializer::new);

        RECIPE_SERIALIZERS.register();
    }

    private static <T extends RecipeSerializer<?>> RegistrySupplier<T> registerSerializer(String name,
            Supplier<T> serializer) {
        return RECIPE_SERIALIZERS.register(name, serializer);
    }
}