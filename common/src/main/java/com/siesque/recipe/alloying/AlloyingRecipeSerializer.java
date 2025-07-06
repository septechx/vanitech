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
                    Ingredient.CODEC.fieldOf("primary_ingredient").forGetter(AlloyingRecipe::primaryIngredient),
                    Ingredient.CODEC.fieldOf("secondary_ingredient").forGetter(AlloyingRecipe::secondaryIngredient),
                    ItemStack.CODEC.fieldOf("result").forGetter(AlloyingRecipe::result),
                    Codec.INT.optionalFieldOf("processing_time", 200).forGetter(AlloyingRecipe::processingTime),
                    Codec.FLOAT.optionalFieldOf("experience", 0.0f).forGetter(AlloyingRecipe::experience))
            .apply(instance, AlloyingRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, AlloyingRecipe> STREAM_CODEC = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC,
            AlloyingRecipe::primaryIngredient,
            Ingredient.CONTENTS_STREAM_CODEC,
            AlloyingRecipe::secondaryIngredient,
            ItemStack.STREAM_CODEC,
            AlloyingRecipe::result,
            ByteBufCodecs.INT,
            AlloyingRecipe::processingTime,
            ByteBufCodecs.FLOAT,
            AlloyingRecipe::experience,
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
