package com.siesque.blocks.entity;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.siesque.Vanitech;
import com.siesque.recipe.VanitechRecipeTypes;
import com.siesque.recipe.alloying.AlloyingRecipe;
import com.siesque.recipe.alloying.AlloyingRecipeInput;
import com.siesque.ui.alloy_furnace.AlloyBlastFurnaceMenu;
import it.unimi.dsi.fastutil.objects.Reference2IntMap.Entry;
import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeCraftingHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class AlloyBlastFurnaceEntity extends BaseContainerBlockEntity
        implements WorldlyContainer, RecipeCraftingHolder, StackedContentsCompatible {
    public static final int INVENTORY_SIZE = 4;
    public static final int SLOT_PRIMARY_INGREDIENT = 0;
    public static final int SLOT_SECONDARY_INGREDIENT = 1;
    public static final int SLOT_FUEL = 2;
    public static final int SLOT_RESULT = 3;
    public static final int BURN_TIME_STANDARD = 200;
    public static final int BURN_COOL_SPEED = 2;

    private static final Codec<Map<ResourceKey<Recipe<?>>, Integer>> RECIPES_USED_CODEC = Codec
            .unboundedMap(Recipe.KEY_CODEC, Codec.INT);

    private static final short DEFAULT_COOKING_TIMER = 0;
    private static final short DEFAULT_COOKING_TOTAL_TIME = 0;
    private static final short DEFAULT_LIT_TIME_REMAINING = 0;
    private static final short DEFAULT_LIT_TOTAL_TIME = 0;

    private static final int[] SLOTS_FOR_UP = new int[]{0, 1};
    private static final int[] SLOTS_FOR_DOWN = new int[]{3, 2};
    private static final int[] SLOTS_FOR_SIDES = new int[]{2};

    private final Reference2IntOpenHashMap<ResourceKey<Recipe<?>>> recipesUsed = new Reference2IntOpenHashMap<>();
    private final RecipeManager.CachedCheck<AlloyingRecipeInput, AlloyingRecipe> quickCheck;
    int litTimeRemaining;
    int litTotalTime;
    int cookingTimer;
    int cookingTotalTime;
    protected final ContainerData data = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> AlloyBlastFurnaceEntity.this.litTimeRemaining;
                case 1 -> AlloyBlastFurnaceEntity.this.litTotalTime;
                case 2 -> AlloyBlastFurnaceEntity.this.cookingTimer;
                case 3 -> AlloyBlastFurnaceEntity.this.cookingTotalTime;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0:
                    AlloyBlastFurnaceEntity.this.litTimeRemaining = value;
                    break;
                case 1:
                    AlloyBlastFurnaceEntity.this.litTotalTime = value;
                    break;
                case 2:
                    AlloyBlastFurnaceEntity.this.cookingTimer = value;
                    break;
                case 3:
                    AlloyBlastFurnaceEntity.this.cookingTotalTime = value;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    };
    private NonNullList<ItemStack> items;

    public AlloyBlastFurnaceEntity(BlockPos pos, BlockState blockState) {
        super(VanitechBlockEntityTypes.ALLOY_BLAST_FURNACE.get(), pos, blockState);
        this.items = NonNullList.withSize(INVENTORY_SIZE, ItemStack.EMPTY);
        this.quickCheck = RecipeManager.createCheck(VanitechRecipeTypes.ALLOYING.get());
        Vanitech.LOGGER.debug("AlloyBlastFurnaceEntity created at {} with state {}", pos, blockState);
    }

    public static void serverTick(ServerLevel level, BlockPos pos, BlockState state, AlloyBlastFurnaceEntity furnace) {
        boolean bl = furnace.isLit();
        boolean bl2 = false;
        if (furnace.isLit()) {
            furnace.litTimeRemaining--;
        }

        ItemStack fuelStack = furnace.items.get(SLOT_FUEL);
        ItemStack primaryStack = furnace.items.get(SLOT_PRIMARY_INGREDIENT);
        ItemStack secondaryStack = furnace.items.get(SLOT_SECONDARY_INGREDIENT);
        boolean bl3 = !primaryStack.isEmpty() && !secondaryStack.isEmpty();
        boolean bl4 = !fuelStack.isEmpty();

        if (furnace.isLit() || bl4 && bl3) {
            AlloyingRecipeInput recipeInput = new AlloyingRecipeInput(primaryStack, secondaryStack);
            RecipeHolder<AlloyingRecipe> recipeHolder;
            if (bl3) {
                recipeHolder = furnace.quickCheck.getRecipeFor(recipeInput, level).orElse(null);
            } else {
                recipeHolder = null;
            }

            int i = furnace.getMaxStackSize();
            if (!furnace.isLit() && canBurn(level.registryAccess(), recipeHolder, recipeInput, furnace.items, i)) {
                furnace.litTimeRemaining = furnace.getBurnDuration(level, fuelStack);
                furnace.litTotalTime = furnace.litTimeRemaining;
                if (furnace.isLit()) {
                    bl2 = true;
                    if (bl4) {
                        Item item = fuelStack.getItem();
                        fuelStack.shrink(1);
                        if (fuelStack.isEmpty()) {
                            furnace.items.set(SLOT_FUEL, item.getCraftingRemainder());
                        }
                    }
                }
            }

            if (furnace.isLit() && canBurn(level.registryAccess(), recipeHolder, recipeInput, furnace.items, i)) {
                furnace.cookingTimer++;
                if (furnace.cookingTimer == furnace.cookingTotalTime) {
                    furnace.cookingTimer = 0;
                    furnace.cookingTotalTime = getTotalCookTime(level, furnace);
                    if (burn(level.registryAccess(), recipeHolder, recipeInput, furnace.items, i)) {
                        furnace.setRecipeUsed(recipeHolder);
                    }

                    bl2 = true;
                }
            } else {
                furnace.cookingTimer = 0;
            }
        } else if (!furnace.isLit() && furnace.cookingTimer > 0) {
            furnace.cookingTimer = Mth.clamp(furnace.cookingTimer - BURN_COOL_SPEED, 0, furnace.cookingTotalTime);
        }

        if (bl != furnace.isLit()) {
            bl2 = true;
            state = state.setValue(AbstractFurnaceBlock.LIT, furnace.isLit());
            level.setBlock(pos, state, 3);
        }

        if (bl2) {
            setChanged(level, pos, state);
        }
    }

    private static boolean canBurn(
            RegistryAccess registryAccess,
            @Nullable RecipeHolder<AlloyingRecipe> recipe,
            AlloyingRecipeInput recipeInput,
            NonNullList<ItemStack> items,
            int maxStackSize) {
        if (!items.get(SLOT_PRIMARY_INGREDIENT).isEmpty() && !items.get(SLOT_SECONDARY_INGREDIENT).isEmpty()
                && recipe != null) {
            ItemStack itemStack = recipe.value().assemble(recipeInput, registryAccess);
            if (itemStack.isEmpty()) {
                return false;
            } else {
                ItemStack resultStack = items.get(SLOT_RESULT);
                if (resultStack.isEmpty()) {
                    return true;
                } else if (!ItemStack.isSameItemSameComponents(resultStack, itemStack)) {
                    return false;
                } else {
                    return resultStack.getCount() < maxStackSize
                            && resultStack.getCount() < resultStack.getMaxStackSize() ||
                            resultStack.getCount() < itemStack.getMaxStackSize();
                }
            }
        } else {
            return false;
        }
    }

    private static boolean burn(
            RegistryAccess registryAccess,
            @Nullable RecipeHolder<AlloyingRecipe> recipe,
            AlloyingRecipeInput recipeInput,
            NonNullList<ItemStack> items,
            int maxStackSize) {
        if (recipe != null && canBurn(registryAccess, recipe, recipeInput, items, maxStackSize)) {
            ItemStack primaryStack = items.get(SLOT_PRIMARY_INGREDIENT);
            ItemStack secondaryStack = items.get(SLOT_SECONDARY_INGREDIENT);
            ItemStack resultStack = recipe.value().assemble(recipeInput, registryAccess);
            ItemStack currentResult = items.get(SLOT_RESULT);

            if (currentResult.isEmpty()) {
                items.set(SLOT_RESULT, resultStack.copy());
            } else if (ItemStack.isSameItemSameComponents(currentResult, resultStack)) {
                currentResult.grow(resultStack.getCount());
            }

            primaryStack.shrink(1);
            secondaryStack.shrink(1);
            return true;
        } else {
            return false;
        }
    }

    private static int getTotalCookTime(ServerLevel level, AlloyBlastFurnaceEntity furnace) {
        ItemStack primaryStack = furnace.getItem(SLOT_PRIMARY_INGREDIENT);
        ItemStack secondaryStack = furnace.getItem(SLOT_SECONDARY_INGREDIENT);
        if (!primaryStack.isEmpty() && !secondaryStack.isEmpty()) {
            AlloyingRecipeInput recipeInput = new AlloyingRecipeInput(primaryStack, secondaryStack);
            return furnace.quickCheck
                    .getRecipeFor(recipeInput, level)
                    .map(recipeHolder -> recipeHolder.value().processingTime())
                    .orElse(BURN_TIME_STANDARD);
        }
        return BURN_TIME_STANDARD;
    }

    private static void createExperience(ServerLevel level, Vec3 popVec, int recipeIndex, float experience) {
        int i = Mth.floor(recipeIndex * experience);
        float f = Mth.frac(recipeIndex * experience);
        if (f != 0.0F && Math.random() < f) {
            i++;
        }

        ExperienceOrb.award(level, popVec, i);
    }

    private boolean isLit() {
        return this.litTimeRemaining > 0;
    }

    @Override
    protected void loadAdditional(ValueInput valueInput) {
        super.loadAdditional(valueInput);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(valueInput, this.items);
        this.cookingTimer = valueInput.getShortOr("cooking_time_spent", DEFAULT_COOKING_TIMER);
        this.cookingTotalTime = valueInput.getShortOr("cooking_total_time", DEFAULT_COOKING_TOTAL_TIME);
        this.litTimeRemaining = valueInput.getShortOr("lit_time_remaining", DEFAULT_LIT_TIME_REMAINING);
        this.litTotalTime = valueInput.getShortOr("lit_total_time", DEFAULT_LIT_TOTAL_TIME);
        this.recipesUsed.clear();
        this.recipesUsed.putAll(valueInput
                .read("RecipesUsed", RECIPES_USED_CODEC).orElse(Map.of()));
    }

    @Override
    protected void saveAdditional(ValueOutput valueOutput) {
        super.saveAdditional(valueOutput);
        valueOutput.putShort("cooking_time_spent", (short) this.cookingTimer);
        valueOutput.putShort("cooking_total_time", (short) this.cookingTotalTime);
        valueOutput.putShort("lit_time_remaining", (short) this.litTimeRemaining);
        valueOutput.putShort("lit_total_time", (short) this.litTotalTime);
        ContainerHelper.saveAllItems(valueOutput, this.items);
        valueOutput.store("RecipesUsed", RECIPES_USED_CODEC, this.recipesUsed);
    }

    protected int getBurnDuration(Level level, ItemStack stack) {
        return level.fuelValues().burnDuration(stack);
    }

    @Override
    public int @NotNull [] getSlotsForFace(Direction side) {
        if (side == Direction.DOWN) {
            return SLOTS_FOR_DOWN;
        } else {
            return side == Direction.UP ? SLOTS_FOR_UP : SLOTS_FOR_SIDES;
        }
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack itemStack, @Nullable Direction direction) {
        return this.canPlaceItem(index, itemStack);
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        return direction != Direction.DOWN || index != SLOT_FUEL || stack.is(Items.WATER_BUCKET)
                || stack.is(Items.BUCKET);
    }

    @Override
    protected @NotNull Component getDefaultName() {
        return Component.translatable("container.blast_furnace");
    }

    @Override
    protected @NotNull NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }

    @Override
    protected @NotNull AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        return new AlloyBlastFurnaceMenu(containerId, inventory, this, this.data);
    }

    @Override
    public int getContainerSize() {
        return INVENTORY_SIZE;
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        ItemStack itemStack = this.items.get(slot);
        boolean bl = !stack.isEmpty() && ItemStack.isSameItemSameComponents(itemStack, stack);
        this.items.set(slot, stack);
        stack.limitSize(this.getMaxStackSize(stack));
        if ((slot == SLOT_PRIMARY_INGREDIENT || slot == SLOT_SECONDARY_INGREDIENT) && !bl
                && this.level instanceof ServerLevel serverLevel) {
            this.cookingTotalTime = getTotalCookTime(serverLevel, this);
            this.cookingTimer = 0;
            this.setChanged();
        }
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack stack) {
        if (slot == SLOT_RESULT) {
            return false;
        } else if (slot == SLOT_FUEL) {
            ItemStack itemStack = this.items.get(SLOT_FUEL);
            assert this.level != null;
            return this.level.fuelValues().isFuel(stack) || stack.is(Items.BUCKET) && !itemStack.is(Items.BUCKET);
        } else {
            return true; // Allow any item in ingredient slots
        }
    }

    @Nullable
    @Override
    public RecipeHolder<?> getRecipeUsed() {
        return null;
    }

    @Override
    public void setRecipeUsed(@Nullable RecipeHolder<?> recipe) {
        if (recipe != null) {
            ResourceKey<Recipe<?>> resourceKey = recipe.id();
            this.recipesUsed.addTo(resourceKey, 1);
        }
    }

    @Override
    public void awardUsedRecipes(Player player, List<ItemStack> items) {
    }

    public void awardUsedRecipesAndPopExperience(ServerPlayer player) {
        List<RecipeHolder<?>> list = this.getRecipesToAwardAndPopExperience(player.level(), player.position());
        player.awardRecipes(list);

        for (RecipeHolder<?> recipeHolder : list) {
            if (recipeHolder != null) {
                player.triggerRecipeCrafted(recipeHolder, this.items);
            }
        }

        this.recipesUsed.clear();
    }

    public List<RecipeHolder<?>> getRecipesToAwardAndPopExperience(ServerLevel level, Vec3 popVec) {
        List<RecipeHolder<?>> list = Lists.newArrayList();

        for (Entry<ResourceKey<Recipe<?>>> entry : this.recipesUsed.reference2IntEntrySet()) {
            level.recipeAccess().byKey(entry.getKey()).ifPresent(recipeHolder -> {
                list.add(recipeHolder);
                createExperience(level, popVec, entry.getIntValue(),
                        ((AlloyingRecipe) recipeHolder.value()).experience());
            });
        }

        return list;
    }

    @Override
    public void fillStackedContents(StackedItemContents stackedItemContents) {
        for (ItemStack itemStack : this.items) {
            stackedItemContents.accountStack(itemStack);
        }
    }

    @Override
    public void preRemoveSideEffects(BlockPos pos, BlockState state) {
        super.preRemoveSideEffects(pos, state);
        if (this.level instanceof ServerLevel serverLevel) {
            this.getRecipesToAwardAndPopExperience(serverLevel, Vec3.atCenterOf(pos));
        }
    }

    public static class Ticker<T extends BlockEntity> implements BlockEntityTicker<T> {
        @Override
        public void tick(Level level, BlockPos blockPos, BlockState blockState, T blockEntity) {
            if (level instanceof ServerLevel serverLevel
                    && blockEntity instanceof AlloyBlastFurnaceEntity alloyFurnace) {
                serverTick(serverLevel, blockPos, blockState, alloyFurnace);
            }
        }
    }
}