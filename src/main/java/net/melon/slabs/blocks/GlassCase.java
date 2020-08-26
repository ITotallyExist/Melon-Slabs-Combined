package net.melon.slabs.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class GlassCase extends BlockWithEntity {

	public GlassCase() {
		super(FabricBlockSettings.copyOf(Blocks.GLASS));
	}

	@Override
	public BlockEntity createBlockEntity(BlockView world) {
		return new GlassCaseEntity();
	}
	
	@Override
    public BlockRenderType getRenderType(BlockState blockState) {
        return BlockRenderType.MODEL;
	}
	
	@Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
			BlockState pedestal = world.getBlockState(pos.down());
			if (pedestal.isOf(MelonSlabsBlocks.SUN_PEDESTAL)){
				player.sendMessage(((SunPedestal)pedestal.getBlock()).getMultiblockIntact(world, pos.down()) ? new LiteralText("Multiblock complete") : new LiteralText("Multiblock incomplete"), true);
			}
        }
        return ActionResult.SUCCESS;
    }

}