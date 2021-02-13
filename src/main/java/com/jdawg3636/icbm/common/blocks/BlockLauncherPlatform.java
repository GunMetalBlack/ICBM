package com.jdawg3636.icbm.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockLauncherPlatform extends Block {

    /**
     * Properties for Positioning Within Multiblock
     */
    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;

    /**
     * Constructor - Sets Default State for Multiblock Positioning Properties
     */
    public BlockLauncherPlatform() {
        super(Block.Properties.create(Material.IRON));
        this.setDefaultState(this.stateContainer.getBaseState().with(HALF, DoubleBlockHalf.LOWER).with(FACING, Direction.NORTH));
    }

    /**
     * Add Properties to BlockState
     */
    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HALF);
        builder.add(FACING);
    }

    /**
     * Using to prevent placement in invalid locations (ex. near world height), return null BlockState if invalid
     */
    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        int multiblockHeight = 3;
        BlockPos blockpos = context.getPos();
        return blockpos.getY() <= context.getWorld().getHeight()-multiblockHeight && context.getWorld().getBlockState(blockpos.up()).isReplaceable(context) ? super.getStateForPlacement(context).with(FACING, context.getPlacementHorizontalFacing().getOpposite()) : null;
    }

    /**
     * Multiblock Placement Routine
     *
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        int xOffsetBase = 0;
        int zOffsetBase = 0;
        if (state.get(FACING).getAxis().compareTo(Direction.Axis.X) == 0) zOffsetBase = 1; else xOffsetBase = 1;
        // Flag values copied from net.minecraft.block.DoublePlantBlock
        worldIn.setBlockState(pos.add(+xOffsetBase, 0, +zOffsetBase), this.getDefaultState().with(HALF, DoubleBlockHalf.UPPER).with(FACING, state.get(FACING)), 3);
        worldIn.setBlockState(pos.add(+xOffsetBase, 1, +zOffsetBase), this.getDefaultState().with(HALF, DoubleBlockHalf.UPPER).with(FACING, state.get(FACING)), 3);
        worldIn.setBlockState(pos.add(+xOffsetBase, 2, +zOffsetBase), this.getDefaultState().with(HALF, DoubleBlockHalf.UPPER).with(FACING, state.get(FACING)), 3);
        worldIn.setBlockState(pos.add(-xOffsetBase, 0, -zOffsetBase), this.getDefaultState().with(HALF, DoubleBlockHalf.UPPER).with(FACING, state.get(FACING)), 3);
        worldIn.setBlockState(pos.add(-xOffsetBase, 1, -zOffsetBase), this.getDefaultState().with(HALF, DoubleBlockHalf.UPPER).with(FACING, state.get(FACING)), 3);
        worldIn.setBlockState(pos.add(-xOffsetBase, 2, -zOffsetBase), this.getDefaultState().with(HALF, DoubleBlockHalf.UPPER).with(FACING, state.get(FACING)), 3);
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        //TODO temp bypass, need to implement
        if (true || state.get(HALF) != DoubleBlockHalf.UPPER) {
            return super.isValidPosition(state, worldIn, pos);
        } else {
            BlockState blockstate = worldIn.getBlockState(pos.down());
            if (state.getBlock() != this) return super.isValidPosition(state, worldIn, pos); //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
            return blockstate.isIn(this) && blockstate.get(HALF) == DoubleBlockHalf.LOWER;
        }
    }

    /**
     * Initiates Multiblock Destruction Propagation
     *
     * Called before the Block is set to air in the world. Called regardless of if the player's tool can actually collect
     * this block
     */
    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!worldIn.isRemote) {
            if (player.isCreative()) {
                removeBottomHalf(worldIn, pos, state, player);
            } else {
                spawnDrops(state, worldIn, pos, (TileEntity)null, player, player.getHeldItemMainhand());
            }
        }

        super.onBlockHarvested(worldIn, pos, state, player);
    }

    /**
     * Multiblock Destruction Impl Util
     *
     * Only called if broken in Creative Mode, otherwise uses spawnDrops method
     */
    protected static void removeBottomHalf(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        DoubleBlockHalf doubleblockhalf = state.get(HALF);
        if (doubleblockhalf == DoubleBlockHalf.UPPER) {
            BlockPos blockpos = pos.down();
            BlockState blockstate = world.getBlockState(blockpos);
            if (blockstate.getBlock() == state.getBlock() && blockstate.get(HALF) == DoubleBlockHalf.LOWER) {
                world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 35);
                world.playEvent(player, 2001, blockpos, Block.getStateId(blockstate));
            }
        }

    }

    /**
     * Currently using this to propagate multiblock destruction. This might be a bad idea.
     *
     * Update the provided state given the provided neighbor facing and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
     * returns its solidified counterpart.
     * Note that this method should ideally consider only the specific face passed in.
     */
    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        //todo temp bypass
        if(1==1) return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        DoubleBlockHalf doubleblockhalf = stateIn.get(HALF);
        if (facing.getAxis() != Direction.Axis.Y || doubleblockhalf == DoubleBlockHalf.LOWER != (facing == Direction.UP) || facingState.isIn(this) && facingState.get(HALF) != doubleblockhalf) {
            return doubleblockhalf == DoubleBlockHalf.LOWER && facing == Direction.DOWN && !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        } else {
            return Blocks.AIR.getDefaultState();
        }
    }

    /**
     * TODO Implement Custom Drop Routine
     *
     * Spawns the block's drops in the world. By the time this is called the Block has possibly been set to air via
     * Block.removedByPlayer
     */
    @Override
    public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
        super.harvestBlock(worldIn, player, pos, Blocks.AIR.getDefaultState(), te, stack);
    }

    /**
     * Override to take into account the {@link this.FACING} property
     *
     * Copied from net.minecraft.block.AnvilBlock
     */
    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    /**
     * Override to Block Interaction with Pistons
     */
    @Override
    public PushReaction getPushReaction(BlockState state) {
        return PushReaction.BLOCK;
    }

}
