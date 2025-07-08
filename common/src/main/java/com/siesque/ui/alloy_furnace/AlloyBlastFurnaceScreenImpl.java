package com.siesque.ui.alloy_furnace;

import com.siesque.items.VanitechItems;
import com.siesque.recipe_book.VanitechRecipeBookCategories;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.SearchRecipeBookCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Items;

import java.util.Arrays;

public final class AlloyBlastFurnaceScreenImpl {
    public static AlloyBlastFurnaceScreen screen(AlloyBlastFurnaceMenu menu, Inventory inventory, Component title) {
        return new AlloyBlastFurnaceScreen(
                menu,
                inventory,
                title,
                Component.literal("Showing Blastable"),
                Arrays.asList(
                        new RecipeBookComponent.TabInfo(SearchRecipeBookCategory.BLAST_FURNACE),
                        new RecipeBookComponent.TabInfo(
                                VanitechItems.BRONZE_INGOT
                                        .get(),
                                VanitechRecipeBookCategories.ALLOYING
                                        .get()
                        ),
                        new RecipeBookComponent.TabInfo(
                                Items.IRON_INGOT,
                                VanitechRecipeBookCategories.BLASTING.get()
                        )
                ));
    }
}
