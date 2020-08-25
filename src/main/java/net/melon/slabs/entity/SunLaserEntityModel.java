package net.melon.slabs.entity;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class SunLaserEntityModel extends EntityModel<Entity> {
    // Made with Blockbench 3.6.6

    private final ModelPart forwards;
    private final ModelPart up;

    private static float ROTATION_SPEED = 0.1f;
    private float rotation = 23.5F;

    public SunLaserEntityModel() {
		textureWidth = 16;
		textureHeight = 16;

		forwards = new ModelPart(this);
		forwards.setPivot(-0.5F, 23.5F, 0.5F);
		setRotationAngle(forwards, -0.7854F, 0.0F, -0.0436F);
        forwards.setTextureOffset(2, 0).addCuboid(-63.5F, -0.5F, -0.5F, 64.0F, 1.0F, 1.0F, 0.0F, false);

		up = new ModelPart(this);
		up.setPivot(-0.5F, 23.5F, 0.5F);
		setRotationAngle(up, 0.0F, -0.7854F, 0.0F);
		up.setTextureOffset(0, 0).addCuboid(-0.5F, -15.5F, -0.5F, 1.0F, 16.0F, 1.0F, 0.0F, false);
    }

    @Override
    public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
            //previously the render function, render code was moved to a method below
    }
    @Override
    public void render(MatrixStack matrixStack, VertexConsumer	buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
            
        forwards.render(matrixStack, buffer, packedLight, packedOverlay);
        up.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    @Override
    public void animateModel(Entity entity, float limbAngle, float limbDistance, float tickDelta) {
        rotation += ROTATION_SPEED*tickDelta;
        setRotationAngle(up, 0.0f, rotation, 0.0f);
        setRotationAngle(forwards, 0.0f, rotation, 0.0f);
    }

    public void setRotationAngle(ModelPart bone, float x, float y, float z) {
        bone.pitch = x;
        bone.yaw = y;
        bone.roll = z;
    }

    // @Override
    // public Identifier getTexture(Entity entity) {
    //     return new Identifier("melonslabs:textures/effects/sun_laser.png");
    // }

}