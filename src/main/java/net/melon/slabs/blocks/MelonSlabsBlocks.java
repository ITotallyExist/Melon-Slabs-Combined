package net.melon.slabs.blocks;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.registry.Registry;

public class MelonSlabsBlocks {
    public static final Block MELON_SLAB = new MelonSlab();
    public static final Block JILL_O_LANTERN = new JillOLantern();
    public static final Block CARVED_MELON = new CarvedMelon();
    public static final Block CARVED_MELON_SLAB = new CarvedMelonSlab();
    public static final Block JILL_O_SLAB = new JillOSlab();
    public static final Block PUMPKIN_SLAB = new PumpkinSlab();
    public static final Block CARVED_PUMPKIN_SLAB = new CarvedPumpkinSlab();
    public static final Block JACK_O_SLAB = new JackOSlab();
    public static final Block PUMPKIN_STAIRS = new PumpkinStairs();
    public static final Block CACTUS_SLAB = new CactusSlab();
    public static final Block MELON_STAIRS = new MelonStairs();
    public static final Block FRANKENMELON = new FrankenMelon();

    //ritual (tech?) blocks
    public static final Block LIGHTNING_ROD = new LightningRod();
    public static final Block LIGHTNING_COLLECTOR = new LightningCollector();
    public static final Block SUN_PEDESTAL = new SunPedestal();
    public static final Block MIRROR = new Mirror();
    public static final Block GLASS_CASE = new GlassCase();
    public static final Block REDSTONE_LASER = new RedstoneLaser();

    //block entities
    public static BlockEntityType<LightningCollectorEntity> LIGHTNING_COLLECTOR_ENTITY;
    public static BlockEntityType<SunPedestalEntity> SUN_PEDESTAL_ENTITY;
    public static BlockEntityType<MirrorEntity> MIRROR_ENTITY;
    public static BlockEntityType<GlassCaseEntity> GLASS_CASE_ENTITY;

    public static void registerBlocks(){
        Registry.register(Registry.BLOCK, "melonslabs:cactus_slab", CACTUS_SLAB);
        Registry.register(Registry.BLOCK, "melonslabs:jill_o_lantern", JILL_O_LANTERN);
        Registry.register(Registry.BLOCK, "melonslabs:carved_melon", CARVED_MELON);
        Registry.register(Registry.BLOCK, "melonslabs:melon_stairs", MELON_STAIRS);
        Registry.register(Registry.BLOCK, "melonslabs:melon_slab", MELON_SLAB);
        Registry.register(Registry.BLOCK, "melonslabs:carved_melon_slab", CARVED_MELON_SLAB);
        Registry.register(Registry.BLOCK, "melonslabs:jill_o_slab", JILL_O_SLAB);
        Registry.register(Registry.BLOCK, "melonslabs:pumpkin_stairs", PUMPKIN_STAIRS);
        Registry.register(Registry.BLOCK, "melonslabs:pumpkin_slab", PUMPKIN_SLAB);
        Registry.register(Registry.BLOCK, "melonslabs:carved_pumpkin_slab", CARVED_PUMPKIN_SLAB);
        Registry.register(Registry.BLOCK, "melonslabs:jack_o_slab", JACK_O_SLAB);
        Registry.register(Registry.BLOCK, "melonslabs:frankenmelon", FRANKENMELON);
        Registry.register(Registry.BLOCK, "melonslabs:lightning_rod", LIGHTNING_ROD);
        Registry.register(Registry.BLOCK, "melonslabs:lightning_collector", LIGHTNING_COLLECTOR);
        Registry.register(Registry.BLOCK, "melonslabs:sun_pedestal", SUN_PEDESTAL);
        Registry.register(Registry.BLOCK, "melonslabs:mirror", MIRROR);
        Registry.register(Registry.BLOCK, "melonslabs:glass_case", GLASS_CASE);
        Registry.register(Registry.BLOCK, "melonslabs:redstone_laser", REDSTONE_LASER);

        LIGHTNING_COLLECTOR_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "melonslabs:lightning_collector", BlockEntityType.Builder.create(LightningCollectorEntity::new, LIGHTNING_COLLECTOR).build(null));
        SUN_PEDESTAL_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "melonslabs:sun_pedestal", BlockEntityType.Builder.create(SunPedestalEntity::new, SUN_PEDESTAL).build(null));
        MIRROR_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "melonslabs:mirror", BlockEntityType.Builder.create(MirrorEntity::new, MIRROR).build(null));
        GLASS_CASE_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "melonslabs:glass_case", BlockEntityType.Builder.create(GlassCaseEntity::new, GLASS_CASE).build(null));
    }

    public static void putRenderLayers(){
        BlockRenderLayerMap.INSTANCE.putBlock(MelonSlabsBlocks.CACTUS_SLAB, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MelonSlabsBlocks.JACK_O_SLAB, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MelonSlabsBlocks.JILL_O_SLAB, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MelonSlabsBlocks.LIGHTNING_COLLECTOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MelonSlabsBlocks.MIRROR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MelonSlabsBlocks.GLASS_CASE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MelonSlabsBlocks.REDSTONE_LASER, RenderLayer.getCutout());
        // BlockRenderLayerMap.INSTANCE.putBlock(MelonSlabsBlocks.LIGHTNING_ROD, RenderLayer.getCutout());
    }
}