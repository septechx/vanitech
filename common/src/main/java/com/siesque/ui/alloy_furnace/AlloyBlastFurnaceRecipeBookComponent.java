package com.siesque.ui.alloy_furnace;

import com.siesque.recipe.alloying.AlloyingRecipeDisplay;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.recipebook.GhostSlots;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeCollection;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.context.ContextMap;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.crafting.display.FurnaceRecipeDisplay;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AlloyBlastFurnaceRecipeBookComponent extends RecipeBookComponent<AlloyBlastFurnaceMenu> {
    private static final WidgetSprites FILTER_SPRITES = new WidgetSprites(
            ResourceLocation.withDefaultNamespace("recipe_book/furnace_filter_enabled"),
            ResourceLocation.withDefaultNamespace("recipe_book/furnace_filter_disabled"),
            ResourceLocation.withDefaultNamespace("recipe_book/furnace_filter_enabled_highlighted"),
            ResourceLocation.withDefaultNamespace("recipe_book/furnace_filter_disabled_highlighted"));
    private final Component recipeFilterName;

    public AlloyBlastFurnaceRecipeBookComponent(AlloyBlastFurnaceMenu menu, Component recipeFilterName,
            List<RecipeBookComponent.TabInfo> tabInfos) {
        super(menu, tabInfos);
        this.recipeFilterName = recipeFilterName;
    }

    @Override
    protected void initFilterButtonTextures() {
        this.filterButton.initTextureValues(FILTER_SPRITES);
    }

    @Override
    protected boolean isCraftingSlot(Slot slot) {
        return switch (slot.index) {
            case 0, 1, 2, 3 -> true;
            default -> false;
        };
    }

    @Override
    protected void fillGhostRecipe(GhostSlots ghostSlots, RecipeDisplay recipeDisplay, ContextMap contextMap) {
        ghostSlots.setResult(this.menu.getResultSlot(), contextMap, recipeDisplay.result());

        if (recipeDisplay instanceof AlloyingRecipeDisplay display) {
            ghostSlots.setInput(this.menu.slots.get(0), contextMap, display.leftInput());
            ghostSlots.setInput(this.menu.slots.get(1), contextMap, display.rightInput());
            Slot slot = this.menu.slots.get(2);
            if (slot.getItem().isEmpty()) {
                ghostSlots.setInput(slot, contextMap, display.craftingStation());
            }
        }
    }

    @Override
    protected @NotNull Component getRecipeFilterName() {
        return this.recipeFilterName;
    }

    @Override
    protected void selectMatchingRecipes(RecipeCollection possibleRecipes, StackedItemContents stackedItemContents) {
        possibleRecipes.selectRecipes(stackedItemContents, arg -> arg instanceof AlloyingRecipeDisplay);
    }
}
