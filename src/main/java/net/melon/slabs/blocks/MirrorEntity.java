package net.melon.slabs.blocks;

import com.google.common.base.Preconditions;

import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.melon.slabs.entity.SunLaserEntityModel;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class MirrorEntity extends BlockEntity implements BlockEntityClientSerializable {
    public boolean active = false;
    public Direction direction = Direction.NORTH;

    // public MirrorEntity(BlockEntityType<?> type) {
    // super(type);
    // }
    public MirrorEntity() {
        super(MelonSlabsBlocks.MIRROR_ENTITY);
    }

    public void activate(Direction dir) {
        this.direction = dir;
        this.active = true;
        
        BlockEntity entity = this.world.getBlockEntity(pos.offset(dir, 4));
        if (entity instanceof GlassCaseEntity){
            ((GlassCaseEntity) entity).activate(dir);
        }

        if (!this.world.isClient()){this.sync();}

        this.world.getBlockTickScheduler().schedule(pos, MelonSlabsBlocks.MIRROR, 75);
    }

    public void deActivate(){
        this.active = false;
        BlockEntity entity = this.world.getBlockEntity(pos.offset(this.direction, 4));

        if (entity instanceof GlassCaseEntity){
            ((GlassCaseEntity) entity).deActivate(this.direction);
        }

        if (!this.world.isClient()){this.sync();}
    }

    // public void sync (){
    //     System.out.println("hi");
    //     World world = ((BlockEntity) this).getWorld();
	// 	Preconditions.checkNotNull(world); //Maintain distinct failure case from below
	// 	if (!(world instanceof ServerWorld)) throw new IllegalStateException("Cannot call sync() on the logical client! Did you check world.isClient first?");

	// 	((ServerWorld) world).getChunkManager().markForUpdate(((BlockEntity) this).getPos());
    // }
    @Override
    public void fromClientTag(CompoundTag tag) {
        active = tag.getBoolean("active");
        // TODO Auto-generated method stub

    }

    @Override
    public CompoundTag toClientTag(CompoundTag tag){
        tag.putBoolean("active", active);
        return tag;
    }
}