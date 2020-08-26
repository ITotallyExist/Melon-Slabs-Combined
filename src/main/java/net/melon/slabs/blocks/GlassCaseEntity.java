package net.melon.slabs.blocks;

import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.Direction;

public class GlassCaseEntity extends BlockEntity implements BlockEntityClientSerializable {
    // public MirrorEntity(BlockEntityType<?> type) {
    // super(type);
    // }
    private boolean[] directionsActivated = { false, false, false, false };

    public GlassCaseEntity() {
        super(MelonSlabsBlocks.GLASS_CASE_ENTITY);
    }

    public void deActivate(Direction direction) {
        if (direction == Direction.NORTH) {
            directionsActivated[0] = false;
        }
        if (direction == Direction.EAST) {
            directionsActivated[1] = false;
        }
        if (direction == Direction.SOUTH) {
            directionsActivated[2] = false;
        }
        if (direction == Direction.WEST) {
            directionsActivated[3] = false;
        }

        if (!this.world.isClient()){this.sync();}
    }

    public void activate(Direction direction) {
        if (direction == Direction.NORTH) {
            directionsActivated[0] = true;
        }
        if (direction == Direction.EAST) {
            directionsActivated[1] = true;
        }
        if (direction == Direction.SOUTH) {
            directionsActivated[2] = true;
        }
        if (direction == Direction.WEST) {
            directionsActivated[3] = true;
        }

        if (!this.world.isClient()){this.sync();}
    }

    public int getSize() {
        int size = 0;

        for (int i = 0; i < 4; i++) {
            if (directionsActivated[i]) {
                size += 1;
            }
        }

        return size;
    }

    @Override
    public void fromClientTag(CompoundTag tag) {
        // TODO Auto-generated method stub
        directionsActivated[0] = tag.getBoolean("n");
        directionsActivated[1] = tag.getBoolean("e");
        directionsActivated[2] = tag.getBoolean("s");
        directionsActivated[3] = tag.getBoolean("w");

    }

    @Override
    public CompoundTag toClientTag(CompoundTag tag) {
        // TODO Auto-generated method stub
        tag.putBoolean("n", directionsActivated[0]);
        tag.putBoolean("e", directionsActivated[1]);
        tag.putBoolean("s", directionsActivated[2]);
        tag.putBoolean("w", directionsActivated[3]);
        return tag;
    }
}