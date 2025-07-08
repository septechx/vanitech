package com.siesque.mixin.client;

import com.siesque.recipe_book.VanitechRecipeBookCategories;
import net.minecraft.client.gui.screens.recipebook.SearchRecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(SearchRecipeBookCategory.class)
public abstract class SearchRecipeBookCategoryMixin {
    @ModifyArgs(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screens/recipebook/SearchRecipeBookCategory;<init>(Ljava/lang/String;I[Lnet/minecraft/world/item/crafting/RecipeBookCategory;)V",
                    ordinal = 2  // Targets BLAST_FURNACE (third enum constant)
            ),
            remap = true
    )
    private static void modifyBlastFurnaceCategories(Args args) {
        RecipeBookCategory[] modified = new RecipeBookCategory[]{
                VanitechRecipeBookCategories.ALLOYING.get(),
                VanitechRecipeBookCategories.BLASTING.get()
        };

        args.set(2, modified);
    }
}
