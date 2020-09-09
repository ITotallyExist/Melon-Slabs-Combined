package net.melon.slabs.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FacingBlock;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.datafixer.fix.ChunkPalettedStorageFix.Facing;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class RedstoneLaser extends Block{
    public static final DirectionProperty FACING;

    public static final VoxelShape SHAPEU;
    public static final VoxelShape SHAPEU1;
    public static final VoxelShape SHAPEU2;
    public static final VoxelShape SHAPEU3;
    public static final VoxelShape SHAPEU4;
    public static final VoxelShape SHAPEU5;
    public static final VoxelShape SHAPEU6;
    public static final VoxelShape SHAPEU7;
    public static final VoxelShape SHAPEU8;
    public static final VoxelShape SHAPEU9;
    public static final VoxelShape SHAPEU10;

    public static final VoxelShape SHAPED;
    public static final VoxelShape SHAPED1;
    public static final VoxelShape SHAPED2;
    public static final VoxelShape SHAPED3;
    public static final VoxelShape SHAPED4;
    public static final VoxelShape SHAPED5;
    public static final VoxelShape SHAPED6;
    public static final VoxelShape SHAPED7;
    public static final VoxelShape SHAPED8;
    public static final VoxelShape SHAPED9;
    public static final VoxelShape SHAPED10;

    public static final VoxelShape SHAPEN;
    public static final VoxelShape SHAPEN1;
    public static final VoxelShape SHAPEN2;
    public static final VoxelShape SHAPEN3;
    public static final VoxelShape SHAPEN4;
    public static final VoxelShape SHAPEN5;
    public static final VoxelShape SHAPEN6;
    public static final VoxelShape SHAPEN7;
    public static final VoxelShape SHAPEN8;
    public static final VoxelShape SHAPEN9;
    public static final VoxelShape SHAPEN10;

    public static final VoxelShape SHAPEE;
    public static final VoxelShape SHAPEE1;
    public static final VoxelShape SHAPEE2;
    public static final VoxelShape SHAPEE3;
    public static final VoxelShape SHAPEE4;
    public static final VoxelShape SHAPEE5;
    public static final VoxelShape SHAPEE6;
    public static final VoxelShape SHAPEE7;
    public static final VoxelShape SHAPEE8;
    public static final VoxelShape SHAPEE9;
    public static final VoxelShape SHAPEE10;

    public static final VoxelShape SHAPES;
    public static final VoxelShape SHAPES1;
    public static final VoxelShape SHAPES2;
    public static final VoxelShape SHAPES3;
    public static final VoxelShape SHAPES4;
    public static final VoxelShape SHAPES5;
    public static final VoxelShape SHAPES6;
    public static final VoxelShape SHAPES7;
    public static final VoxelShape SHAPES8;
    public static final VoxelShape SHAPES9;
    public static final VoxelShape SHAPES10;

    public static final VoxelShape SHAPEW;
    public static final VoxelShape SHAPEW1;
    public static final VoxelShape SHAPEW2;
    public static final VoxelShape SHAPEW3;
    public static final VoxelShape SHAPEW4;
    public static final VoxelShape SHAPEW5;
    public static final VoxelShape SHAPEW6;
    public static final VoxelShape SHAPEW7;
    public static final VoxelShape SHAPEW8;
    public static final VoxelShape SHAPEW9;
    public static final VoxelShape SHAPEW10;

    public RedstoneLaser() {
        super(FabricBlockSettings.copy(Blocks.SMOOTH_STONE));
        this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.UP));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction direction = ctx.getPlayerFacing().getOpposite();
        return (BlockState)this.getDefaultState().with(FACING, ctx.getSide());
    }

    @Override
    public BlockRenderType getRenderType(BlockState blockState) {
        return BlockRenderType.MODEL;
    }
    
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction direction = state.get(FACING);
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
        Direction direction = state.get(FACING);
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

    static{
        FACING = FacingBlock.FACING;
        SHAPEU1 = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D);
        SHAPEU2 = Block.createCuboidShape(4.0D, 3.0D, 4.0D, 12.0D, 6.0D, 12.0D);
        SHAPEU3 = Block.createCuboidShape(7.5D, 6.0D, 5.75D, 8.5D, 11.5D, 6.75D);
        SHAPEU4 = Block.createCuboidShape(7.5D, 6.0D, 9.25D, 8.5D, 11.5D, 10.25D);
        SHAPEU5 = Block.createCuboidShape(5.75D, 6.0D, 7.5D, 6.75D, 11.5D, 8.5D);
        SHAPEU6 = Block.createCuboidShape(9.25D, 6.0D, 7.5D, 10.25D, 11.5D, 8.5D);
        SHAPEU7 = Block.createCuboidShape(7.0D, 6.0D, 7.0D, 9.0D, 16.0D, 9.0D);
        SHAPEU8 = Block.createCuboidShape(6.5D, 7.0D, 6.5D, 9.5D, 15.0D, 9.5D);
        SHAPEU9 = Block.createCuboidShape(6.0D, 8.0D, 6.0D, 10.0D, 14.0D, 10.0D);
        SHAPEU10 = Block.createCuboidShape(5.5D, 10.0D, 5.5D, 10.5D, 12.0D, 10.5D);
        SHAPEU = VoxelShapes.union(SHAPEU1, SHAPEU2, SHAPEU3, SHAPEU4, SHAPEU5, SHAPEU6, SHAPEU7, SHAPEU8, SHAPEU9, SHAPEU10);

        SHAPED1 = Block.createCuboidShape(0.0D, 13.0D, 0.0D, 16.0D, 16.0D, 16.0D);
        SHAPED2 = Block.createCuboidShape(4.0D, 10.0D, 4.0D, 12.0D, 13.0D, 12.0D);
        SHAPED3 = Block.createCuboidShape(7.5D, 4.5D, 5.75D, 8.5D, 10.0D, 6.75D);
        SHAPED4 = Block.createCuboidShape(7.5D, 4.5D, 9.25D, 8.5D, 10.0D, 10.25D);
        SHAPED5 = Block.createCuboidShape(5.75D, 4.5D, 7.5D, 6.75D, 10.0D, 8.5D);
        SHAPED6 = Block.createCuboidShape(9.25D, 4.5D, 7.5D, 10.25D, 10.0D, 8.5D);
        SHAPED7 = Block.createCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 10.0D, 9.0D);
        SHAPED8 = Block.createCuboidShape(6.5D, 1.0D, 6.5D, 9.5D, 9.0D, 9.5D);
        SHAPED9 = Block.createCuboidShape(6.0D, 2.0D, 6.0D, 10.0D, 8.0D, 10.0D);
        SHAPED10 = Block.createCuboidShape(5.5D, 4.0D, 5.5D, 10.5D, 6.0D, 10.5D);
        SHAPED = VoxelShapes.union(SHAPED1, SHAPED2, SHAPED3, SHAPED4, SHAPED5, SHAPED6, SHAPED7, SHAPED8, SHAPED9, SHAPED10);

        SHAPEN1 = Block.createCuboidShape(0.0D, 0.0D, 16.0D, 16.0D, 16.0D, 13.0D);
        SHAPEN2 = Block.createCuboidShape(4.0D, 4.0D, 13.0D, 12.0D, 12.0D, 10.0D);
        SHAPEN3 = Block.createCuboidShape(7.5D, 5.75D, 10.0D, 8.5D, 6.75D, 4.5D);
        SHAPEN4 = Block.createCuboidShape(7.5D, 9.25D, 10.0D, 8.5D, 10.25D, 4.5D);
        SHAPEN5 = Block.createCuboidShape(5.75D, 7.5D, 10.0D, 6.75D, 8.5D, 4.5D);
        SHAPEN6 = Block.createCuboidShape(9.25D, 7.5D, 10.0D, 10.25D, 8.5D, 4.5D);
        SHAPEN7 = Block.createCuboidShape(7.0D, 7.0D, 10.0D, 9.0D, 9.0D, 0.0D);
        SHAPEN8 = Block.createCuboidShape(6.5D, 6.5D, 9.0D, 9.5D, 9.5D, 1.0D);
        SHAPEN9 = Block.createCuboidShape(6.0D, 6.0D, 8.0D, 10.0D, 10.0D, 2.0D);
        SHAPEN10 = Block.createCuboidShape(5.5D, 5.5D, 6.0D, 10.5D, 10.5D, 4.0D);
        SHAPEN = VoxelShapes.union(SHAPEN1, SHAPEN2, SHAPEN3, SHAPEN4, SHAPEN5, SHAPEN6, SHAPEN7, SHAPEN8, SHAPEN9, SHAPEN10);

        SHAPEE1 = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 3.0D, 16.0D, 16.0D);
        SHAPEE2 = Block.createCuboidShape(3.0D, 4.0D, 4.0D, 6.0D, 12.0D, 12.0D);
        SHAPEE3 = Block.createCuboidShape(6.0D, 7.5D, 5.75D, 11.5D, 8.5D, 6.75D);
        SHAPEE4 = Block.createCuboidShape(6.0D, 7.5D, 9.25D, 11.5D, 8.5D, 10.25D);
        SHAPEE5 = Block.createCuboidShape(6.0D, 5.75D, 7.5D, 11.5D, 6.75D, 8.5D);
        SHAPEE6 = Block.createCuboidShape(6.0D, 9.25D, 7.5D, 11.5D, 10.25D, 8.5D);
        SHAPEE7 = Block.createCuboidShape(6.0D, 7.0D, 7.0D, 16.0D, 9.0D, 9.0D);
        SHAPEE8 = Block.createCuboidShape(7.0D, 6.5D, 6.5D, 15.0D, 9.5D, 9.5D);
        SHAPEE9 = Block.createCuboidShape(8.0D, 6.0D, 6.0D, 14.0D, 10.0D, 10.0D);
        SHAPEE10 = Block.createCuboidShape(10.0D, 5.5D, 5.5D, 12.0D, 10.5D, 10.5D);
        SHAPEE = VoxelShapes.union(SHAPEE1, SHAPEE2, SHAPEE3, SHAPEE4, SHAPEE5, SHAPEE6, SHAPEE7, SHAPEE8, SHAPEE9, SHAPEE10);

        SHAPES1 = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 3.0D);
        SHAPES2 = Block.createCuboidShape(4.0D, 4.0D, 3.0D, 12.0D, 12.0D, 6.0D);
        SHAPES3 = Block.createCuboidShape(7.5D, 5.75D, 6.0D, 8.5D, 6.75D, 11.5D);
        SHAPES4 = Block.createCuboidShape(7.5D, 9.25D, 6.0D, 8.5D, 10.25D, 11.5D);
        SHAPES5 = Block.createCuboidShape(5.75D, 7.5D, 6.0D, 6.75D, 8.5D, 11.5D);
        SHAPES6 = Block.createCuboidShape(9.25D, 7.5D, 6.0D, 10.25D, 8.5D, 11.5D);
        SHAPES7 = Block.createCuboidShape(7.0D, 7.0D, 6.0D, 9.0D, 9.0D, 16.0D);
        SHAPES8 = Block.createCuboidShape(6.5D, 6.5D, 7.0D, 9.5D, 9.5D, 15.0D);
        SHAPES9 = Block.createCuboidShape(6.0D, 6.0D, 8.0D, 10.0D, 10.0D, 14.0D);
        SHAPES10 = Block.createCuboidShape(5.5D, 5.5D, 10.0D, 10.5D, 10.5D, 12.0D);
        SHAPES = VoxelShapes.union(SHAPES1, SHAPES2, SHAPES3, SHAPES4, SHAPES5, SHAPES6, SHAPES7, SHAPES8, SHAPES9, SHAPES10);

        SHAPEW1 = Block.createCuboidShape(13.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
        SHAPEW2 = Block.createCuboidShape(10.0D, 4.0D, 4.0D, 13.0D, 12.0D, 12.0D);
        SHAPEW3 = Block.createCuboidShape(10.0D, 7.5D, 5.75D, 4.5D, 8.5D, 6.75D);
        SHAPEW4 = Block.createCuboidShape(10.0D, 7.5D, 9.25D, 4.5D, 8.5D, 10.25D);
        SHAPEW5 = Block.createCuboidShape(10.0D, 5.75D, 7.5D, 4.5D, 6.75D, 8.5D);
        SHAPEW6 = Block.createCuboidShape(10.0D, 9.25D, 7.5D, 4.5D, 10.25D, 8.5D);
        SHAPEW7 = Block.createCuboidShape(10.0D, 7.0D, 7.0D, 0.0D, 9.0D, 9.0D);
        SHAPEW8 = Block.createCuboidShape(9.0D, 6.5D, 6.5D, 1.0D, 9.5D, 9.5D);
        SHAPEW9 = Block.createCuboidShape(8.0D, 6.0D, 6.0D, 2.0D, 10.0D, 10.0D);
        SHAPEW10 = Block.createCuboidShape(6.0D, 5.5D, 5.5D, 4.0D, 10.5D, 10.5D);
        SHAPEW = VoxelShapes.union(SHAPEW1, SHAPEW2, SHAPEW3, SHAPEW4, SHAPEW5, SHAPEW6, SHAPEW7, SHAPEW8, SHAPEW9, SHAPEW10);
    }
}