package net.melon.slabs;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.melon.slabs.blocks.BoxScreen;
import net.melon.slabs.blocks.GlassCaseEntityRenderer;
import net.melon.slabs.blocks.MelonSlabsBlocks;
import net.melon.slabs.blocks.MirrorEntityRenderer;
import net.melon.slabs.blocks.PedestalBlockEntityRenderer;

@Environment(EnvType.CLIENT)
public class MelonSlabsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        System.out.println("Client initializing");
        ScreenRegistry.register(MelonSlabs.BOX_SCREEN_HANDLER, BoxScreen::new);
        BlockEntityRendererRegistry.INSTANCE.register(MelonSlabsBlocks.SUN_PEDESTAL_ENTITY, PedestalBlockEntityRenderer::new);
        BlockEntityRendererRegistry.INSTANCE.register(MelonSlabsBlocks.MIRROR_ENTITY, MirrorEntityRenderer::new);
        BlockEntityRendererRegistry.INSTANCE.register(MelonSlabsBlocks.GLASS_CASE_ENTITY, GlassCaseEntityRenderer::new);
    }
}