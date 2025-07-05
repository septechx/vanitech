package com.siesque.recipe.alloying;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;

public class AlloyingRecipeSerializer implements RecipeSerializer<AlloyingRecipe> {
    public static final MapCodec<AlloyingRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                    Ingredient.CODEC.fieldOf("primary_ingredient").forGetter(AlloyingRecipe::getPrimaryIngredient),
                    Ingredient.CODEC.fieldOf("secondary_ingredient").forGetter(AlloyingRecipe::getSecondaryIngredient),
                    ItemStack.CODEC.fieldOf("result").forGetter(AlloyingRecipe::getResult),
                    Codec.INT.optionalFieldOf("processing_time", 200).forGetter(AlloyingRecipe::getProcessingTime),
                    Codec.FLOAT.optionalFieldOf("experience", 0.0f).forGetter(AlloyingRecipe::getExperience))
            .apply(instance, AlloyingRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, AlloyingRecipe> STREAM_CODEC = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC,
            AlloyingRecipe::getPrimaryIngredient,
            Ingredient.CONTENTS_STREAM_CODEC,
            AlloyingRecipe::getSecondaryIngredient,
            ItemStack.STREAM_CODEC,
            AlloyingRecipe::getResult,
            ByteBufCodecs.INT,
            AlloyingRecipe::getProcessingTime,
            ByteBufCodecs.FLOAT,
            AlloyingRecipe::getExperience,
            AlloyingRecipe::new);

    @Override
    public @NotNull MapCodec<AlloyingRecipe> codec() {
        return CODEC;
    }

    @Override
    public @NotNull StreamCodec<RegistryFriendlyByteBuf, AlloyingRecipe> streamCodec() {
        return STREAM_CODEC;
    }
}
