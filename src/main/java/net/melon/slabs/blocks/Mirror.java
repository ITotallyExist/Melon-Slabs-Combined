package net.melon.slabs.blocks;

import java.util.Random;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Blocks;
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
    public static final VoxelShape SHAPE;

    public Mirror() {
        super(FabricBlockSettings.copy(Blocks.OAK_PLANKS));
        this.setDefaultState(
                (BlockState) ((BlockState) this.stateManager.getDefaultState()).with(FACING, Direction.NORTH));
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
        builder.add(FACING);
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState) this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
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
        FACING = HorizontalFacingBlock.FACING;
        SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new MirrorEntity();
    }
}