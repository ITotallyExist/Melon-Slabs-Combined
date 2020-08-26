package net.melon.slabs.blocks;

import java.util.ArrayList;
import java.util.List;

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

public class MirrorEntityRenderer extends BlockEntityRenderer<MirrorEntity> {
    private static float ROTATION_SPEED = 0.3f;

    private final List<ModelPart> forwardsW = new ArrayList<ModelPart>();
    private final List<ModelPart> forwardsN = new ArrayList<ModelPart>();
    private final List<ModelPart> forwardsE = new ArrayList<ModelPart>();
    private final List<ModelPart> forwardsS = new ArrayList<ModelPart>();
    private final List<ModelPart> up = new ArrayList<ModelPart>();

    public MirrorEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);

        forwardsW.add(new ModelPart(16,16,0,0));
        forwardsW.add(new ModelPart(16,16,0,0));
        forwardsW.forEach((x -> {x.setPivot(-54.5F, 10.0F, 8.0f);}));
        forwardsW.forEach((x -> {x.pitch = -0.7854F;}));
        // forwardsW.forEach((x -> {x.roll = 0.08f;}));
        forwardsW.forEach((x -> {x.setTextureOffset(0,0).addCuboid(-0.5F, -0.5F, -0.5F, 62.5F, 1.0F, 1.0F, 0.0F, false);}));

        forwardsN.add(new ModelPart(16,16,0,0));
        forwardsN.add(new ModelPart(16,16,0,0));
        forwardsN.forEach((x -> {x.setPivot(8.0F, 10.0F, 8.0f);}));
        // forwardsN.forEach((x -> {x.pitch = -0.7854F;}));
        // forwardsN.forEach((x -> {x.pitch = 0.08f;}));
        forwardsN.forEach((x -> {x.yaw = 3.14159265f;}));
        forwardsN.forEach((x -> {x.setTextureOffset(0,0).addCuboid(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 63.5F, 0.0F, false);}));

        forwardsE.add(new ModelPart(16,16,0,0));
        forwardsE.add(new ModelPart(16,16,0,0));
        forwardsE.forEach((x -> {x.setPivot(8.5F, 10.0F, 8.0f);}));
        forwardsE.forEach((x -> {x.pitch = -0.7854F;}));
        // forwardsE.forEach((x -> {x.roll = -0.08f;}));
        forwardsE.forEach((x -> {x.setTextureOffset(0,0).addCuboid(-0.5F, -0.5F, -0.5F, 63.0F, 1.0F, 1.0F, 0.0F, false);}));

        forwardsS.add(new ModelPart(16,16,0,0));
        forwardsS.add(new ModelPart(16,16,0,0));
        forwardsS.forEach((x -> {x.setPivot(8.0F, 10.0F, 8.0f);}));
        // forwardsS.forEach((x -> {x.pitch = -0.7854F;}));
        // forwardsS.forEach((x -> {x.pitch = 0.08f;}));
        forwardsS.forEach((x -> {x.setTextureOffset(0,0).addCuboid(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 63.5F, 0.0F, false);}));

        // forwards = new ModelPart(16,16,0,0);
        // forwards.setPivot(-55.5F, 4.2F, 8.0f);
        // forwards.pitch = -0.7854F;
        // forwards.roll = 0.08f;
        // forwards.setTextureOffset(0,0).addCuboid(-0.5F, -0.5F, -0.5F, 63.5F, 1.0F, 1.0F, 0.0F, false);

        // forwards2 = new ModelPart(16,16,0,0);
        // forwards2.setPivot(-55.5F, 4.2F, 8.0f);
        // forwards2.pitch = -0.7854F;
        // forwards2.roll = 0.08f;
        // forwards2.setTextureOffset(0,0).addCuboid(-0.5F, -0.5F, -0.5F, 63.5F, 1.0F, 1.0F, 0.0F, false);

        up.add(new ModelPart(17,17,0,0));
        up.add(new ModelPart(17,17,0,0));
        up.forEach((x -> {x.setPivot(8.0F, 25.75f, 8.0f);}));
        up.forEach((x -> {x.yaw = -0.7854F;}));
        up.forEach((x -> {x.pitch = 3.14159265258979323846264f;}));
        up.forEach((x -> {x.setTextureOffset(0, 0).addCuboid(-0.5F, 0.0F, -0.5F, 1.0F, 16.0F, 1.0F, 0.0F, false);}));
    }


    @Override
    public void render(MirrorEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {  

        if (entity.active){

            float offset = ((entity.getWorld().getTime() + tickDelta) * ROTATION_SPEED)%6.28318531f;

            up.get(0).yaw = offset;
            up.get(1).yaw = -offset;

            RenderLayer renderLayer = RenderLayer.getItemEntityTranslucentCull(new Identifier("melonslabs:textures/effects/sun_laser.png"));
            VertexConsumer consumer = vertexConsumers.getBuffer(renderLayer);

            up.get(0).render(matrices, consumer, light, overlay);
            up.get(1).render(matrices, consumer, light, overlay);


            renderLayer = RenderLayer.getItemEntityTranslucentCull(new Identifier("melonslabs:textures/effects/sun_laser_2.png"));
            consumer = vertexConsumers.getBuffer(renderLayer);

            if (entity.direction == Direction.WEST){
                forwardsW.get(0).pitch = offset;
                forwardsW.get(1).pitch = -offset;
                forwardsW.get(0).render(matrices, consumer, light, overlay);
                forwardsW.get(1).render(matrices, consumer, light, overlay);
            } else if (entity.direction == Direction.NORTH){
                forwardsN.get(0).roll = offset;
                forwardsN.get(1).roll = -offset;
                forwardsN.get(0).render(matrices, consumer, light, overlay);
                forwardsN.get(1).render(matrices, consumer, light, overlay);
            } else if (entity.direction == Direction.EAST){
                forwardsE.get(0).pitch = offset;
                forwardsE.get(1).pitch = -offset;
                forwardsE.get(0).render(matrices, consumer, light, overlay);
                forwardsE.get(1).render(matrices, consumer, light, overlay);
            } else {
                forwardsS.get(0).roll = offset;
                forwardsS.get(1).roll = -offset;
                forwardsS.get(0).render(matrices, consumer, light, overlay);
                forwardsS.get(1).render(matrices, consumer, light, overlay);
            }
        }
    }

    public Identifier getTexture() {
        return new Identifier("melonslabs:textures/effects/sun_laser.png");
    }
}