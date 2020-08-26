package net.melon.slabs.blocks;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class PedestalBlockEntityRenderer extends BlockEntityRenderer<SunPedestalEntity> {
    private static ItemStack stack = new ItemStack(Items.JUKEBOX, 1);

    public PedestalBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }
    
    @Override
    public void render(SunPedestalEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();
        // Calculate the current offset in the y value
        double offset = Math.sin((blockEntity.getWorld().getTime() + tickDelta) / 8.0) / 8.0;
        // Move the item
        matrices.translate(0.5, 1.4 + offset, 0.5);
 
        // Rotate the item
        matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion((blockEntity.getWorld().getTime() + tickDelta) * 4));

        int lightAbove = WorldRenderer.getLightmapCoordinates(blockEntity.getWorld(), blockEntity.getPos().up());
        // MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformation.Mode.GROUND, lightAbove, overlay, matrices, vertexConsumers);
        MinecraftClient.getInstance().getItemRenderer().renderItem(blockEntity.getStack(0), ModelTransformation.Mode.GROUND, lightAbove, overlay, matrices, vertexConsumers);
 
        // Mandatory call after GL calls
        matrices.pop();
    }
}