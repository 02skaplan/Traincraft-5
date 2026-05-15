/*******************************************************************************
 * Copyright (c) 2012 Mrbrutal. All rights reserved.
 * 
 * @name TrainCraft
 * @author Mrbrutal
 ******************************************************************************/

package train.common.tile;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.LinkedList;

public class TileTrainDetector extends TileLockable {

	private ForgeDirection facing;
	private final LinkedList<TileTCRail> pairedTrack;
	// Use EntityMinecart instead of EntityRollingStock so we can store both EntitiesRollingStock and EntityBogies.
	private final LinkedList<EntityMinecart> activeEntities;
	boolean state = false;
	private boolean needsInit = false;
	private int[][] linkedTrackCoordinates;

	public TileTrainDetector() {
		pairedTrack = new LinkedList<>();
		activeEntities = new LinkedList<>();
	}

	public void addEntity(EntityMinecart entity) {
		activeEntities.add(entity);
		setState(true);
	}
	public void removeEntity(EntityMinecart entity) {
		activeEntities.remove(entity);
		if (activeEntities.isEmpty()) {
			setState(false);
		}
	}

	private void setState(boolean state) {
		if (state != this.state) {
			worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, worldObj.getBlock(xCoord, yCoord, zCoord));
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, blockMetadata, 3);
		}
		this.state = state;
	}

	public boolean getState() {
		return !this.activeEntities.isEmpty();
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTag) {
		super.readFromNBT(nbtTag);
		facing = ForgeDirection.getOrientation(nbtTag.getByte("Orientation"));

		// Read paired tracks from NBT.
		NBTTagList tagList = nbtTag.getTagList("PairedTracks", Constants.NBT.TAG_COMPOUND);
		linkedTrackCoordinates = new int[tagList.tagCount()][3];
		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
			int[] coordinateArray = tagCompound.getIntArray("Coordinates");
            System.arraycopy(coordinateArray, 0, linkedTrackCoordinates[i], 0, 3);
		}
		needsInit = true;
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTag) {
		super.writeToNBT(nbtTag);

		if (facing != null) {
			nbtTag.setByte("Orientation", (byte) facing.ordinal());
		}
		else {
			nbtTag.setByte("Orientation", (byte) ForgeDirection.NORTH.ordinal());
		}

		// Write paired tracks to NBT.
		NBTTagList tagList = new NBTTagList();
		NBTTagCompound tagCompound;
		for (TileTCRail pairedTrack : pairedTrack) {
			tagCompound = new NBTTagCompound();
			tagCompound.setIntArray("Coordinates", new int[]{pairedTrack.xCoord, pairedTrack.yCoord, pairedTrack.zCoord});
			tagList.appendTag(tagCompound);
		}
		nbtTag.setTag("PairedTracks", tagList);
	}

	public ForgeDirection getFacing() {
		return (facing != null ? this.facing : ForgeDirection.NORTH);
	}
	public void setFacing(ForgeDirection face) {
		this.facing = face;
	}
    public LinkedList<TileTCRail> getPairedTrack() {
        return pairedTrack;
    }
	public LinkedList<EntityMinecart> getActiveEntities() {
		return activeEntities;
	}

    @Override
    public void updateEntity() {
		if (needsInit) {
		    needsInit = false;
		    updateLinkedRails();
		}
    }

	/**
	 * <p>Track tiles cannot be added to the paired track list until before the world is fully loaded.</p>
	 * <p>This method is called after the entity is loaded to convert the coordinates stored in NBT
	 * to live references to the track tiles themselves.</p>
	 */
	private void updateLinkedRails() {
		for (int[] coordinateArray : linkedTrackCoordinates) {
			TileEntity te = worldObj.getTileEntity(coordinateArray[0], coordinateArray[1], coordinateArray[2]);
			if (te instanceof TileTCRail) {
				pairedTrack.add(((TileTCRail) te));
				((TileTCRail)te).getPairedDetectors().add(this);
			}
		}
    }
}