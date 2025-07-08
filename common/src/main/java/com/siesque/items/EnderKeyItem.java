package com.siesque.items;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EndPortalFrameBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import org.jetbrains.annotations.NotNull;

public class EnderKeyItem extends Item {
    public EnderKeyItem(Item.Properties properties) {
        super(properties);
    }

    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        BlockState blockState = level.getBlockState(blockPos);
        if (blockState.is(Blocks.END_PORTAL_FRAME) && !(Boolean) blockState.getValue(EndPortalFrameBlock.HAS_EYE)) {
            if (!level.isClientSide) {
                BlockState newBlockState = (BlockState) blockState.setValue(EndPortalFrameBlock.HAS_EYE, true);
                Block.pushEntitiesUp(blockState, newBlockState, level, blockPos);
                level.setBlock(blockPos, newBlockState, 2);
                level.updateNeighbourForOutputSignal(blockPos, Blocks.END_PORTAL_FRAME);
                context.getItemInHand().shrink(1);
                level.levelEvent(1503, blockPos, 0);
                BlockPattern.BlockPatternMatch blockPatternMatch = EndPortalFrameBlock.getOrCreatePortalShape().find(level, blockPos);
                if (blockPatternMatch != null) {
                    BlockPos blockPos2 = blockPatternMatch.getFrontTopLeft().offset(-3, 0, -3);

                    for (int i = 0; i < 3; ++i) {
                        for (int j = 0; j < 3; ++j) {
                            BlockPos blockPos3 = blockPos2.offset(i, 0, j);
                            level.destroyBlock(blockPos3, true, (Entity) null);
                            level.setBlock(blockPos3, Blocks.END_PORTAL.defaultBlockState(), 2);
                        }
                    }

                    level.globalLevelEvent(1038, blockPos2.offset(1, 0, 1), 0);
                }

            }
            return InteractionResult.SUCCESS;
        } else {
            return InteractionResult.PASS;
        }
    }
}

