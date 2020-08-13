package net.melon.slabs;

import net.minecraft.block.SlabBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.Random;

import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.fluid.Fluids;
import net.fabricmc.api.EnvType;



public class JillOSlab extends SlabBlock{
    public static final DirectionProperty FACING;
    private static final ParticleEffect PARTICLE;
    public boolean scheduled = false;

    public JillOSlab() {
        super(FabricBlockSettings.copy(Blocks.JACK_O_LANTERN));
        this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.NORTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(TYPE, FACING, WATERLOGGED);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        BlockState blockState = ctx.getWorld().getBlockState(blockPos);
        if (blockState.isOf(this)) {
            return (BlockState)((BlockState)blockState.with(TYPE, SlabType.DOUBLE)).with(WATERLOGGED, false);
        } else if (blockState.isOf(MelonSlabs.CARVED_MELON_SLAB_BLOCK)){
            return (BlockState)((BlockState)MelonSlabs.JILL_O_SLAB_BLOCK.getDefaultState().with(FACING, blockState.get(FACING)).with(TYPE, SlabType.DOUBLE)).with(WATERLOGGED, false);
        } else {
            FluidState fluidState = ctx.getWorld().getFluidState(blockPos);
            BlockState blockState2 = (BlockState)((BlockState)this.getDefaultState().with(TYPE, SlabType.BOTTOM)).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER).with(FACING, ctx.getPlayerFacing().getOpposite());
            Direction direction = ctx.getSide();
            return direction != Direction.DOWN && (direction == Direction.UP || ctx.getHitPos().y - (double)blockPos.getY() <= 0.5D) ? blockState2 : (BlockState)blockState2.with(TYPE, SlabType.TOP).with(FACING, ctx.getPlayerFacing().getOpposite());
        }
    }

    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        ItemStack itemStack = context.getStack();
        SlabType slabType = (SlabType)state.get(TYPE);
        if (slabType != SlabType.DOUBLE && (itemStack.getItem() == this.asItem() || itemStack.getItem() == MelonSlabs.CARVED_MELON_SLAB.asItem())) {
           if (context.canReplaceExisting()) {
              boolean bl = context.getHitPos().y - (double)context.getBlockPos().getY() > 0.5D;
              Direction direction = context.getSide();
              if (slabType == SlabType.BOTTOM) {
                 return direction == Direction.UP || bl && direction.getAxis().isHorizontal();
              } else {
                 return direction == Direction.DOWN || !bl && direction.getAxis().isHorizontal();
              }
           } else {
              return true;
           }
        } else {
           return false;
        }
    }

    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (!scheduled){
            world.getBlockTickScheduler().schedule(pos, this, 1);
            scheduled = false;
        }
    }

    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        this.update(state, world, pos);
        super.neighborUpdate(state, world, pos, block, fromPos, notify);
        if (!scheduled){
            scheduled = true;
            world.getBlockTickScheduler().schedule(pos, this, 1);
        }
    }

    public void update (BlockState state, World world, BlockPos pos){
        if (state.get(WATERLOGGED)){
            world.setBlockState(pos, MelonSlabs.CARVED_MELON_SLAB_BLOCK.getDefaultState().with(FACING, state.get(FACING)).with(WATERLOGGED, true).with(TYPE,state.get(TYPE)));

            Direction direction2 = state.get(TYPE) == SlabType.BOTTOM ? Direction.UP : Direction.DOWN;

            ItemEntity itemEntity = new ItemEntity(world, (double)pos.getX() + 0.5D + (double)direction2.getOffsetX() * 0.65D, (double)pos.getY() + 0.1D, (double)pos.getZ() + 0.5D + (double)direction2.getOffsetZ() * 0.65D, new ItemStack(Items.TORCH, 1));
            itemEntity.setVelocity(0.05D * (double)direction2.getOffsetX() + world.random.nextDouble() * 0.02D, 0.05D, 0.05D * (double)direction2.getOffsetZ() + world.random.nextDouble() * 0.02D);
            world.spawnEntity(itemEntity);
        }
    }

    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(TYPE) != SlabType.DOUBLE){
            double d = (double)pos.getX() + 0.5D;
            double e = (double)pos.getY() + (state.get(TYPE) == SlabType.BOTTOM ? 0.8D : 0.5D);
            double f = (double)pos.getZ() + 0.5D;
            world.addParticle(ParticleTypes.SMOKE, d, e, f, 0.0D, 0.0D, 0.0D);
            world.addParticle(PARTICLE, d, e, f, 0.0D, 0.0D, 0.0D);
        }
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(TYPE) != SlabType.DOUBLE){
            world.getBlockTickScheduler().schedule(pos, this, 1);
        } else{
            scheduled = false;
        }
        update(state, world, pos);
    }

    static{
        FACING = HorizontalFacingBlock.FACING;
        PARTICLE = ParticleTypes.FLAME;
    }
}