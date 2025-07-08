package com.siesque.ui.alloy_furnace;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.navigation.ScreenPosition;
import net.minecraft.client.gui.screens.inventory.AbstractRecipeBookScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AlloyBlastFurnaceScreen extends AbstractRecipeBookScreen<AlloyBlastFurnaceMenu> {
    private static final ResourceLocation BACKGROUND = ResourceLocation.fromNamespaceAndPath("vanitech",
            "textures/gui/alloy_furnace.png");
    private static final ResourceLocation LIT_PROGRESS_SPRITE = ResourceLocation.fromNamespaceAndPath("vanitech",
            "lit_progress");
    private static final ResourceLocation BURN_PROGRESS_SPRITE = ResourceLocation.fromNamespaceAndPath("vanitech",
            "burn_progress");

    public AlloyBlastFurnaceScreen(AlloyBlastFurnaceMenu menu, Inventory playerInventory, Component title,
                                   Component recipeFilterName,
                                   List<RecipeBookComponent.TabInfo> tabInfos) {
        super(menu, new AlloyBlastFurnaceRecipeBookComponent(menu, recipeFilterName, tabInfos), playerInventory, title);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        int x = this.leftPos;
        int y = this.topPos;
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND,
                x, y, 0, 0, this.imageWidth, this.imageHeight, 176, 166);

        if (this.menu.isLit()) {
            int l = Mth.ceil(this.menu.getLitProgress() * 13.0F) + 1;
            guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, LIT_PROGRESS_SPRITE,
                    14, 14, 0, 14 - l, x + 56, y + 36 + 14 - l, 14, l);
        }

        int l = Mth.ceil(this.menu.getBurnProgress() * 24.0F);
        guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, BURN_PROGRESS_SPRITE,
                24, 16, 0, 0, x + 79, y + 34, l, 16);
    }

    @Override
    protected @NotNull ScreenPosition getRecipeBookButtonPosition() {
        return new ScreenPosition(this.leftPos + 20, this.height / 2 - 49);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        renderTooltip(guiGraphics, mouseX, mouseY);
        // renderSlotIndices(guiGraphics);
    }

    private void renderSlotIndices(GuiGraphics guiGraphics) {
        for (var slot : this.getMenu().slots) {
            int idx = slot.index;
            int x = this.leftPos + slot.x - 16;
            int y = this.topPos + slot.y - 16;
            guiGraphics.drawString(
                    this.font,
                    Integer.toString(idx),
                    x + 18,
                    y + 18,
                    0xFFFFFF);
        }
    }
}