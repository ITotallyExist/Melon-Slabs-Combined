package net.melon.slabs.items;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.melon.slabs.blocks.MelonSlabsBlocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class MelonSlabsItems {
        //Item group
        private static final ItemGroup GROUP = FabricItemGroupBuilder.build(new Identifier("melonslabs", "group"), () -> new ItemStack(MelonSlabsItems.MELON_SLAB));

        //Block Items
        public static final Item JILL_O_LANTERN = new BlockItem(MelonSlabsBlocks.JILL_O_LANTERN, new Item.Settings().group(GROUP));
        public static final Item MELON_STAIRS = new BlockItem(MelonSlabsBlocks.MELON_STAIRS, new Item.Settings().group(GROUP));
        public static final Item MELON_SLAB = new BlockItem(MelonSlabsBlocks.MELON_SLAB, new Item.Settings().group(GROUP));
        public static final Item CARVED_MELON_SLAB = new BlockItem(MelonSlabsBlocks.CARVED_MELON_SLAB, new Item.Settings().group(GROUP));
        public static final Item CARVED_MELON = new BlockItem(MelonSlabsBlocks.CARVED_MELON, new Item.Settings().group(GROUP));
        public static final Item JILL_O_SLAB = new BlockItem(MelonSlabsBlocks.JILL_O_SLAB, new Item.Settings().group(GROUP));
        public static final Item CACTUS_SLAB = new BlockItem(MelonSlabsBlocks.CACTUS_SLAB, new Item.Settings().group(GROUP));
        public static final Item PUMPKIN_STAIRS = new BlockItem(MelonSlabsBlocks.PUMPKIN_STAIRS, new Item.Settings().group(GROUP));
        public static final Item PUMPKIN_SLAB = new BlockItem(MelonSlabsBlocks.PUMPKIN_SLAB, new Item.Settings().group(GROUP));
        public static final Item CARVED_PUMPKIN_SLAB = new BlockItem(MelonSlabsBlocks.CARVED_PUMPKIN_SLAB, new Item.Settings().group(GROUP));
        public static final Item JACK_O_SLAB = new BlockItem(MelonSlabsBlocks.JACK_O_SLAB, new Item.Settings().group(GROUP));
        public static final Item FRANKENMELON = new BlockItem(MelonSlabsBlocks.FRANKENMELON, new Item.Settings().group(GROUP));
        public static final Item LIGHTNING_ROD = new BlockItem(MelonSlabsBlocks.LIGHTNING_ROD, new Item.Settings().group(GROUP));
        public static final Item LIGHTNING_COLLECTOR = new BlockItem(MelonSlabsBlocks.LIGHTNING_COLLECTOR, new Item.Settings().group(GROUP));
        public static final Item SUN_PEDESTAL = new BlockItem(MelonSlabsBlocks.SUN_PEDESTAL, new Item.Settings().group(GROUP));
        public static final Item MIRROR = new BlockItem(MelonSlabsBlocks.MIRROR, new Item.Settings().group(GROUP));
        public static final Item GLASS_CASE = new BlockItem(MelonSlabsBlocks.GLASS_CASE, new Item.Settings().group(GROUP));

        //Food Items
        public static final Item PUMPKIN_SLICE = new Item(new Item.Settings().group(GROUP).food(new FoodComponent.Builder().hunger(1).saturationModifier(0.3f).snack().build()));
        public static final Item COOKED_PUMPKIN_SLICE = new Item(new Item.Settings().group(GROUP).food(new FoodComponent.Builder().hunger(4).saturationModifier(2.4f).build()));
        
        //elemental_bottles
        public static final Item LIGHTNING_BOTTLE = new LightningBottleItem(new Item.Settings().group(GROUP).rarity(Rarity.EPIC));
        public static final Item SUN_BOTTLE = new SunBottleItem(new Item.Settings().group(GROUP).rarity(Rarity.EPIC));

        //tech items
        public static final Item REDSTONE_SHARD = new RedstoneShardItem(new Item.Settings().group(GROUP));

        //register items
        public static void registerItems(){
            Registry.register(Registry.ITEM, "melonslabs:cactus_slab", CACTUS_SLAB);
            Registry.register(Registry.ITEM, "melonslabs:jill_o_lantern", JILL_O_LANTERN);
            Registry.register(Registry.ITEM, "melonslabs:carved_melon", CARVED_MELON);
            Registry.register(Registry.ITEM, "melonslabs:melon_stairs", MELON_STAIRS);
            Registry.register(Registry.ITEM, "melonslabs:melon_slab", MELON_SLAB);
            Registry.register(Registry.ITEM, "melonslabs:carved_melon_slab", CARVED_MELON_SLAB);
            Registry.register(Registry.ITEM, "melonslabs:jill_o_slab", JILL_O_SLAB);
            Registry.register(Registry.ITEM, "melonslabs:pumpkin_stairs", PUMPKIN_STAIRS);
            Registry.register(Registry.ITEM, "melonslabs:pumpkin_slab", PUMPKIN_SLAB);
            Registry.register(Registry.ITEM, "melonslabs:carved_pumpkin_slab", CARVED_PUMPKIN_SLAB);
            Registry.register(Registry.ITEM, "melonslabs:jack_o_slab", JACK_O_SLAB);
            Registry.register(Registry.ITEM, "melonslabs:pumpkin_slice", PUMPKIN_SLICE);
            Registry.register(Registry.ITEM, "melonslabs:cooked_pumpkin_slice", COOKED_PUMPKIN_SLICE);
            Registry.register(Registry.ITEM, "melonslabs:frankenmelon", FRANKENMELON);
            Registry.register(Registry.ITEM, "melonslabs:lightning_rod", LIGHTNING_ROD);
            Registry.register(Registry.ITEM, "melonslabs:lightning_collector", LIGHTNING_COLLECTOR);
            Registry.register(Registry.ITEM, "melonslabs:lightning_bottle", LIGHTNING_BOTTLE);
            Registry.register(Registry.ITEM, "melonslabs:sun_pedestal", SUN_PEDESTAL);
            Registry.register(Registry.ITEM, "melonslabs:mirror", MIRROR);
            Registry.register(Registry.ITEM, "melonslabs:glass_case", GLASS_CASE);
            Registry.register(Registry.ITEM, "melonslabs:sun_bottle", SUN_BOTTLE);
            Registry.register(Registry.ITEM, "melonslabs:redstone_shard", REDSTONE_SHARD);
        }
}