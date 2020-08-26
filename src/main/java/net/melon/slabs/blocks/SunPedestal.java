package net.melon.slabs.blocks;

import java.util.Random;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.melon.slabs.entity.DisplayItemEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class SunPedestal extends BlockWithEntity{
    public static final DirectionProperty FACING;
    private static final VoxelShape BASE_SHAPE;
    private static final VoxelShape X_STEP_SHAPE;
    private static final VoxelShape X_STEM_SHAPE;
    private static final VoxelShape Z_STEP_SHAPE;
    private static final VoxelShape Z_STEM_SHAPE;
    private static final VoxelShape TOP_SHAPE;
    private static final VoxelShape X_AXIS_SHAPE;
    private static final VoxelShape Z_AXIS_SHAPE;

    public SunPedestal() {
        super(FabricBlockSettings.copy(Blocks.ANVIL));
        this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.NORTH));
    }

    @Override
    public BlockEntity createBlockEntity(BlockView blockView) {
        return new SunPedestalEntity();
    }
    @Override
    public BlockRenderType getRenderType(BlockState blockState) {
        return BlockRenderType.MODEL;
    }

    //This drop inventory onto ground when block broken
    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof SunPedestalEntity) {
                ItemScatterer.spawn(world, pos, (SunPedestalEntity)blockEntity);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        //((SunPedestalEntity)world.getBlockEntity(pos)).displayInventory(world, pos);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        ItemStack itemStack = player.getStackInHand(hand);
        if (!world.isClient) {

            if (blockEntity instanceof SunPedestalEntity){
                if (itemStack.isEmpty() && !((SunPedestalEntity)blockEntity).getStack(0).isEmpty()){
                    //pick up item
                    player.setStackInHand(hand, ((SunPedestalEntity)blockEntity).removeStack(0));
                    world.getBlockTickScheduler().schedule(pos, this, 1);
                    return ActionResult.SUCCESS;
                }

                if (((SunPedestalEntity)blockEntity).getStack(0).isEmpty()){
                    ((SunPedestalEntity)blockEntity).setStack(0, itemStack);
                    player.setStackInHand(hand, ItemStack.EMPTY);
                    world.getBlockTickScheduler().schedule(pos, this, 1);
                    return ActionResult.SUCCESS;
                }
            }
            return ActionResult.FAIL;
        } else{
            if (blockEntity instanceof SunPedestalEntity && blockEntity.getWorld().isClient){
                if (itemStack.isEmpty() && !((SunPedestalEntity)blockEntity).getStack(0).isEmpty()){
                    ((SunPedestalEntity)blockEntity).removeStack(0);
                    return ActionResult.SUCCESS;
                }

                if (((SunPedestalEntity)blockEntity).getStack(0).isEmpty()){
                    ((SunPedestalEntity)blockEntity).setStack(0, itemStack);
                    return ActionResult.SUCCESS;
                }
            }
            return ActionResult.FAIL;
        }
    }

    public boolean getMultiblockIntact(World world, BlockPos pos){
        if (!world.isSkyVisible(pos.up(2))){return false;}

        if (!world.getBlockState(pos.up()).isOf(MelonSlabsBlocks.GLASS_CASE)){return false;}


        //north arm
        if (!world.isAir(pos.up().north())){return false;}
        if (!world.isAir(pos.up().north(2))){return false;}
        if (!world.isAir(pos.up().north(3))){return false;}
        BlockState northMirror = world.getBlockState(pos.up().north(4));
        if (!northMirror.isOf(MelonSlabsBlocks.MIRROR)){return false;}
        if (northMirror.get(FACING) != Direction.SOUTH){return false;}
        if (!world.isSkyVisible(pos.up().north(4))){return false;}

        //east arm
        if (!world.isAir(pos.up().east())){return false;}
        if (!world.isAir(pos.up().east(2))){return false;}
        if (!world.isAir(pos.up().east(3))){return false;}
        BlockState eastMirror = world.getBlockState(pos.up().east(4));
        if (!eastMirror.isOf(MelonSlabsBlocks.MIRROR)){return false;}
        if (eastMirror.get(FACING) != Direction.WEST){return false;}
        if (!world.isSkyVisible(pos.up().east(4))){return false;}

        //south arm
        if (!world.isAir(pos.up().south())){return false;}
        if (!world.isAir(pos.up().south(2))){return false;}
        if (!world.isAir(pos.up().south(3))){return false;}
        BlockState southMirror = world.getBlockState(pos.up().south(4));
        if (!southMirror.isOf(MelonSlabsBlocks.MIRROR)){return false;}
        if (southMirror.get(FACING) != Direction.NORTH){return false;}
        if (!world.isSkyVisible(pos.up().south(4))){return false;}

        //west arm
        if (!world.isAir(pos.up().west())){return false;}
        if (!world.isAir(pos.up().west(2))){return false;}
        if (!world.isAir(pos.up().west(3))){return false;}
        BlockState westMirror = world.getBlockState(pos.up().west(4));
        if (!westMirror.isOf(MelonSlabsBlocks.MIRROR)){return false;}
        if (westMirror.get(FACING) != Direction.EAST){return false;}
        if (!world.isSkyVisible(pos.up().west(4))){return false;}

        return true;
    }

    public int getMultiblockActive(World world, BlockPos pos){
        BlockEntity entity = world.getBlockEntity(pos.up());

        if (entity instanceof GlassCaseEntity){
            return ((GlassCaseEntity)entity).getSize();
        }

        return 0;
    }

    public boolean canActivateMultiblock(World world, BlockPos pos){
        ItemStack item = ((SunPedestalEntity) world.getBlockEntity(pos)).getStack(0);

        if (((SunPedestalEntity) world.getBlockEntity(pos)).hasLivingPotion() && this.getMultiblockIntact(world, pos)){
            return true;
        }
        
        return false;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(FACING, ctx.getPlayerFacing().rotateYClockwise());
    }

    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction direction = (Direction)state.get(FACING);
        return direction.getAxis() == Direction.Axis.X ? X_AXIS_SHAPE : Z_AXIS_SHAPE;
    }

    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction direction = (Direction)state.get(FACING);
        return direction.getAxis() == Direction.Axis.X ? X_AXIS_SHAPE : Z_AXIS_SHAPE;
    }

    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return (BlockState)state.with(FACING, rotation.rotate((Direction)state.get(FACING)));
    }

    static {
        FACING = HorizontalFacingBlock.FACING;
        BASE_SHAPE = Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 4.0D, 14.0D);
        X_STEP_SHAPE = Block.createCuboidShape(3.0D, 4.0D, 4.0D, 13.0D, 5.0D, 12.0D);
        X_STEM_SHAPE = Block.createCuboidShape(4.0D, 5.0D, 6.0D, 12.0D, 10.0D, 10.0D);
        Z_STEP_SHAPE = Block.createCuboidShape(4.0D, 4.0D, 3.0D, 12.0D, 5.0D, 13.0D);
        Z_STEM_SHAPE = Block.createCuboidShape(6.0D, 5.0D, 4.0D, 10.0D, 10.0D, 12.0D);
        TOP_SHAPE = Block.createCuboidShape(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D);
        X_AXIS_SHAPE = VoxelShapes.union(BASE_SHAPE, X_STEP_SHAPE, X_STEM_SHAPE, TOP_SHAPE);
        Z_AXIS_SHAPE = VoxelShapes.union(BASE_SHAPE, Z_STEP_SHAPE, Z_STEM_SHAPE, TOP_SHAPE);
    }
}