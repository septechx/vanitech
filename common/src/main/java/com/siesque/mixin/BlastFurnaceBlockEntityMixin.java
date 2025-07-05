package com.siesque.mixin;

import com.siesque.blocks.entity.AlloyBlastFurnaceEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.stats.Stats;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.BlastFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlastFurnaceBlock.class)
public abstract class BlastFurnaceBlockEntityMixin extends AbstractFurnaceBlock {
    protected BlastFurnaceBlockEntityMixin(Properties properties) {
        super(properties);
    }

    /**
     * @author septechx
     * @reason Replace the BlastFurnaceEntity with the custom AlloyBlastFurnaceEntity
     */
    @Overwrite
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new AlloyBlastFurnaceEntity(pos, state);
    }

    /**
     * @author septechx
     * @reason Replace the default furnace ticker with the custom AlloyBlastFurnaceEntity.Ticker
     */
    @Overwrite
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state,
                                                                            BlockEntityType<T> blockEntityType) {
        return new AlloyBlastFurnaceEntity.Ticker<>();
    }

    @Inject(method = "openContainer", at = @At("HEAD"), cancellable = true)
    private void handleCustomContainer(Level level, BlockPos pos, Player player, CallbackInfo ci) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof AlloyBlastFurnaceEntity) {
            player.openMenu((MenuProvider) blockEntity);
            player.awardStat(Stats.INTERACT_WITH_BLAST_FURNACE);
            ci.cancel();
        }
    }
}