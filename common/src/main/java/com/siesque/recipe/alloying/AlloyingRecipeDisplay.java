package com.siesque.recipe.alloying;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.siesque.recipe.VanitechRecipeDisplayTypes;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import org.jetbrains.annotations.NotNull;

public record AlloyingRecipeDisplay(
        SlotDisplay leftInput,
        SlotDisplay rightInput,
        SlotDisplay result,
        SlotDisplay craftingStation
) implements RecipeDisplay {
    public static final MapCodec<AlloyingRecipeDisplay> MAP_CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                            SlotDisplay.CODEC.fieldOf("leftInput").forGetter(AlloyingRecipeDisplay::leftInput),
                            SlotDisplay.CODEC.fieldOf("rightInput").forGetter(AlloyingRecipeDisplay::rightInput),
                            SlotDisplay.CODEC.fieldOf("result").forGetter(AlloyingRecipeDisplay::result),
                            SlotDisplay.CODEC.fieldOf("crafting_station").forGetter(AlloyingRecipeDisplay::craftingStation)
                    )
                    .apply(instance, AlloyingRecipeDisplay::new)
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, AlloyingRecipeDisplay> STREAM_CODEC = StreamCodec.composite(
            SlotDisplay.STREAM_CODEC,
            AlloyingRecipeDisplay::rightInput,
            SlotDisplay.STREAM_CODEC,
            AlloyingRecipeDisplay::leftInput,
            SlotDisplay.STREAM_CODEC,
            AlloyingRecipeDisplay::result,
            SlotDisplay.STREAM_CODEC,
            AlloyingRecipeDisplay::craftingStation,
            AlloyingRecipeDisplay::new
    );

    @Override
    public RecipeDisplay.@NotNull Type<AlloyingRecipeDisplay> type() {
        return VanitechRecipeDisplayTypes.ALLOYING.get();
    }
}
