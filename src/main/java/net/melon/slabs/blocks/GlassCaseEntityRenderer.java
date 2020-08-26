package net.melon.slabs.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

public class GlassCaseEntityRenderer extends BlockEntityRenderer<GlassCaseEntity> {
    private final List<ModelPart> cubes = new ArrayList<ModelPart>();

    public GlassCaseEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);

        cubes.add(new ModelPart(16,16,0,0));
        cubes.add(new ModelPart(16,16,0,0));
        cubes.add(new ModelPart(16,16,0,0));
        cubes.add(new ModelPart(16,16,0,0));
        cubes.forEach((x -> {x.setPivot(0, 0, 0);}));
        cubes.get(0).setTextureOffset(0,0).addCuboid(6.2375F, 8.2375F, 6.2375F, 3.525F, 3.525F, 3.525F, 0.0F, false);
        cubes.get(1).setTextureOffset(0,0).addCuboid(4.475F, 6.475F, 4.475F, 7.05F, 7.05F, 7.05F, 0.0F, false);
        cubes.get(2).setTextureOffset(0,0).addCuboid(2.7125F, 3.7125F, 2.7125F, 10.575F, 10.575F, 10.575F, 0.0F, false);
        cubes.get(3).setTextureOffset(0,0).addCuboid(0.95F, 0.95F, 0.95F, 14.1F, 14.1F, 14.1F, 0.0F, false);
    }


    @Override
    public void render(GlassCaseEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {  
        //RenderLayer renderLayer = RenderLayer.getEntityGlint();//(new Identifier("melonslabs:textures/effects/sun_laser_2.png"));\
        if (entity.getSize() != 0){
            RenderLayer renderLayer = RenderLayer.getItemEntityTranslucentCull(new Identifier("melonslabs:textures/effects/sun_laser_2.png"));
            VertexConsumer consumer = vertexConsumers.getBuffer(renderLayer);
            cubes.get(entity.getSize()-1).render(matrices, consumer, light, overlay);
        }
            // cube.render(matrices, consumer, light, overlay);
        // Render
    }

    public Identifier getTexture() {
        return new Identifier("melonslabs:textures/effects/sun_laser.png");
    }
}