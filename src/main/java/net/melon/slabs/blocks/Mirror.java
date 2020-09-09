package net.melon.slabs.blocks;

import java.util.Random;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Blocks;
import net.minecraft.block.FacingBlock;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class Mirror extends BlockWithEntity {
    public static final DirectionProperty FACING;
    public static final DirectionProperty SIDE;
    public static final VoxelShape SHAPEU;
    public static final VoxelShape SHAPED;
    public static final VoxelShape SHAPEN;
    public static final VoxelShape SHAPEE;
    public static final VoxelShape SHAPES;
    public static final VoxelShape SHAPEW;

    public Mirror() {
        super(FabricBlockSettings.copy(Blocks.OAK_PLANKS));
        this.setDefaultState((BlockState) ((BlockState) this.stateManager.getDefaultState()).with(FACING, Direction.NORTH).with(SIDE, Direction.UP));
    }

    @Override
    public BlockRenderType getRenderType(BlockState blockState) {
        return BlockRenderType.MODEL;
    }

    //This drop inventory onto ground when block broken
    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos.offset(state.get(FACING), 4));

            if (blockEntity instanceof GlassCaseEntity){
                ((GlassCaseEntity) blockEntity).deActivate(state.get(FACING));
            }
            
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }


    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return (BlockState)state.with(FACING, rotation.rotate((Direction)state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING,SIDE);
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction side = ctx.getSide();
        Direction direction = ctx.getPlayerFacing().getOpposite();

        if (side != Direction.DOWN && side != Direction.UP){
            if (side == direction){
                direction = (ctx.getHitPos().y - (double)ctx.getBlockPos().getY() >= 0.5D) ? Direction.UP : Direction.DOWN;
            }
        }

        return (BlockState) this.getDefaultState().with(FACING, direction).with(SIDE, side);
    }

    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction direction = state.get(SIDE);
        if (direction == Direction.UP){
            return SHAPEU;
        } else if (direction == Direction.DOWN){
            return SHAPED;
        } else if (direction == Direction.NORTH){
            return SHAPEN;
        } else if (direction == Direction.EAST){
            return SHAPEE;
        } else if (direction == Direction.SOUTH){
            return SHAPES;
        } else if (direction == Direction.WEST){
            return SHAPEW;
        } else {
            //failsafe
            return SHAPEU;
        }
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction direction = state.get(SIDE);
        if (direction == Direction.UP){
            return SHAPEU;
        } else if (direction == Direction.DOWN){
            return SHAPED;
        } else if (direction == Direction.NORTH){
            return SHAPEN;
        } else if (direction == Direction.EAST){
            return SHAPEE;
        } else if (direction == Direction.SOUTH){
            return SHAPES;
        } else if (direction == Direction.WEST){
            return SHAPEW;
        } else {
            //failsafe
            return SHAPEU;
        }
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockPos pedestalPos = pos.offset(state.get(FACING), 4).down();
        BlockState pedestal = world.getBlockState(pedestalPos);
        if (pedestal.isOf(MelonSlabsBlocks.SUN_PEDESTAL)){
            if(((SunPedestal)pedestal.getBlock()).getMultiblockIntact(world, pedestalPos)){
                if (((SunPedestal)pedestal.getBlock()).canActivateMultiblock(world, pedestalPos)){
                    if (world.isRaining() || world.isNight() || world.isThundering()){
                        player.sendMessage(new LiteralText("Weather conditions unideal"), true);
                    } else {
                        MirrorEntity blockEntity = ((MirrorEntity)world.getBlockEntity(pos));
                        blockEntity.activate(state.get(FACING));
                    }
                } else{
                    player.sendMessage(new LiteralText("No living potion in pedestal"), true);
                }
            } else{
                player.sendMessage(new LiteralText("Multiblock incomplete"), true);
            }
        } else{
            player.sendMessage(new LiteralText("Multiblock incomplete"), true);
        }
        
        return ActionResult.SUCCESS;
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        ((MirrorEntity)world.getBlockEntity(pos)).deActivate();
        
    }

    static {
        FACING = FacingBlock.FACING;
        SHAPEU = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D);
        SHAPED = Block.createCuboidShape(0.0D, 12.0D, 0.0D, 16.0D, 16.0D, 16.0D);
        SHAPEW = Block.createCuboidShape(12.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
        SHAPES = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 4.0D);
        SHAPEE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 4.0D, 16.0D, 16.0D);
        SHAPEN = Block.createCuboidShape(0.0D, 0.0D, 12.0D, 16.0D, 16.0D, 16.0D);
        SIDE = DirectionProperty.of("side", Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.UP, Direction.DOWN);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new MirrorEntity();
    }
}