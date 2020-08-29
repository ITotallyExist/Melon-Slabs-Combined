package net.melon.slabs.blocks;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.melon.slabs.items.MelonSlabsItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class SunPedestalEntity extends BlockEntity implements ImplementedInventory, SidedInventory, BlockEntityClientSerializable{
    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(1, ItemStack.EMPTY);
    // private int displayEntityId;
    public SunPedestalEntity() {
        super(MelonSlabsBlocks.SUN_PEDESTAL_ENTITY);
    }

    public void scheduleTick(){
        this.getWorld().getBlockTickScheduler().schedule(pos, MelonSlabsBlocks.SUN_PEDESTAL, 1);
    }

    public boolean hasLivingPotion(){
        if (getStack(0).hasTag()){
            if (getStack(0).getTag().asString().contains("minecraft:melonslabs_living")){
                return true;
            }
        }
        return false;
    }
    
    //implementedInventory
    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        Inventories.fromTag(tag,items);
        // displayItemEntity.fromTag(tag);
        // displayEntityId = ((IntTag)tag.get("displayEntityId")).getInt();
    }

    @Override
    public void setLocation(World world, BlockPos pos) {
        this.world = world;
        this.pos = pos.toImmutable();
        //world.getBlockTickScheduler().schedule(pos, MelonSlabsBlocks.SUN_PEDESTAL, 2);
    }
 
    // @Override
    // public void Tick(){

    // }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        // removeDisplay(world, pos);
        Inventories.toTag(tag,items);
        // displayItemEntity.toTag(tag);
        // tag.putInt("displayEntityId", displayEntityId);
        return super.toTag(tag);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, Direction dir) {
        return(false);
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return false;
    }

    @Override
    public int[] getAvailableSlots(Direction dir) {
        return new int[0];
    }
    
    //these next functions make sure to update the state whenever an item is added or removed
    @Override
    public ItemStack removeStack(int slot, int count) {
        ItemStack result = Inventories.splitStack(getItems(), slot, count);
        if (!result.isEmpty()) {
            markDirty();
        }
        return result;
    }
 
    
    @Override
    public ItemStack removeStack(int slot) {
        ItemStack result = Inventories.removeStack(getItems(), slot);
        return result;
    }
 
    
    @Override
    public void setStack(int slot, ItemStack stack) {
        getItems().set(slot, stack);
        if (stack.getCount() > getMaxCountPerStack()) {
            stack.setCount(getMaxCountPerStack());
        }
    }
 
    /**
     * Clears the inventory.
     */
    @Override
    public void clear() {
        scheduleTick();

        getItems().clear();
    }

    @Override
    public void fromClientTag(CompoundTag tag) {
        // TODO Auto-generated method stub
        Inventories.fromTag(tag,items);
    }

    @Override
    public CompoundTag toClientTag(CompoundTag tag) {
        Inventories.toTag(tag,items);
        return(tag);
        // displayItemEntity.fromTag(tag)
    }
}