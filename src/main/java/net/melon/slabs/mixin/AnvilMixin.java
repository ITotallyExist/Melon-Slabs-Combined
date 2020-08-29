package net.melon.slabs.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.melon.slabs.items.MelonSlabsItems;
import net.minecraft.block.AnvilBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneBlock;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(AnvilBlock.class)
public class AnvilMixin {
    @Inject(at = @At("HEAD"), method = "onLanding")
    public void onLanding(World world, BlockPos pos, BlockState fallingBlockState, BlockState currentStateInPos, FallingBlockEntity fallingBlockEntity, CallbackInfo info) {
        if (!world.isClient()){
            System.out.println("hi");
            if (world.getBlockState(pos.down()).getBlock() instanceof RedstoneBlock){
                System.out.println("landed on redstone block");
                world.removeBlock(pos.down(), false);
                ItemScatterer.spawn(world, pos, DefaultedList.copyOf(ItemStack.EMPTY, new ItemStack(MelonSlabsItems.REDSTONE_SHARD),new ItemStack(MelonSlabsItems.REDSTONE_SHARD),new ItemStack(MelonSlabsItems.REDSTONE_SHARD)));
            }
        }
    }
}