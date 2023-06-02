package com.jcirmodelsquad.tcjcir.vehicles.locomotives;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jcirmodelsquad.tcjcir.features.autotrain.DriveScript2;
import com.jcirmodelsquad.tcjcir.features.autotrain.Station;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import org.apache.commons.lang3.StringUtils;
import train.common.Traincraft;
import train.common.api.ElectricTrain;
import train.common.api.EntityRollingStock;
import train.common.library.GuiIDs;
import train.common.mtc.MTCMessage;
import train.common.mtc.network.PacketATO;


import java.util.ArrayList;
import java.util.List;

public class PCH120Commute extends ElectricTrain {

    DriveScript2 script2 = new DriveScript2();

    public PCH120Commute(World world) {
        super(world);
    }


    public PCH120Commute(World world, double d, double d1, double d2) {
        this(world);
        setPosition(d, d1 + yOffset, d2);
        motionX = 0.0D;
        motionY = 0.0D;
        motionZ = 0.0D;
        prevPosX = d;
        prevPosY = d1;
        prevPosZ = d2;
    }

    @Override
    public void updateRiderPosition() {
        if (riddenByEntity == null) {
            return;
        }
        double pitchRads = this.anglePitchClient * Math.PI / 180.0D;
        double distance = 2.75;
        double yOffset = 0.2;
        float rotationCos1 = (float) Math.cos(Math.toRadians(this.renderYaw + 90));
        float rotationSin1 = (float) Math.sin(Math.toRadians((this.renderYaw + 90)));
        if (side.isServer()) {
            rotationCos1 = (float) Math.cos(Math.toRadians(this.serverRealRotation + 90));
            rotationSin1 = (float) Math.sin(Math.toRadians((this.serverRealRotation + 90)));
            anglePitchClient = serverRealPitch * 60;
        }
        float pitch = (float) (posY + ((Math.tan(pitchRads) * distance) + getMountedYOffset())
                + riddenByEntity.getYOffset() + yOffset);
        float pitch1 = (float) (posY + getMountedYOffset() + riddenByEntity.getYOffset() + yOffset);
        double bogieX1 = (this.posX + (rotationCos1 * distance));
        double bogieZ1 = (this.posZ + (rotationSin1 * distance));
        //System.out.println(rotationCos1+" "+rotationSin1);
        if (anglePitchClient > 20 && rotationCos1 == 1) {
            bogieX1 -= pitchRads * 2;
            pitch -= pitchRads * 1.2;
        }
        if (anglePitchClient > 20 && rotationSin1 == 1) {
            bogieZ1 -= pitchRads * 2;
            pitch -= pitchRads * 1.2;
        }
        if (pitchRads == 0.0) {
            riddenByEntity.setPosition(bogieX1, pitch1, bogieZ1);
        }
        if (pitchRads > -1.01 && pitchRads < 1.01) {
            riddenByEntity.setPosition(bogieX1, pitch, bogieZ1);
        }
    }

    @Override
    public void setDead() {
        super.setDead();
        isDead = true;
    }

    @Override
    public void pressKey(int i) {
        if (i == 7 && riddenByEntity != null && riddenByEntity instanceof EntityPlayer) {
            ((EntityPlayer) riddenByEntity).openGui(Traincraft.instance, GuiIDs.LOCO, worldObj, (int) this.posX, (int) this.posY, (int) this.posZ);
        }
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
        super.writeEntityToNBT(nbttagcompound);

        nbttagcompound.setShort("fuelTrain", (short) fuelTrain);
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < locoInvent.length; i++) {
            if (locoInvent[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                locoInvent[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
        super.readEntityFromNBT(nbttagcompound);

        fuelTrain = nbttagcompound.getShort("fuelTrain");
        NBTTagList nbttaglist = nbttagcompound.getTagList("Items", Constants.NBT.TAG_COMPOUND);
        locoInvent = new ItemStack[getSizeInventory()];
        for (int i = 0; i < nbttaglist.tagCount(); i++) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 0xff;
            if (j >= 0 && j < locoInvent.length) {
                locoInvent[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
    }

    @Override
    public int getSizeInventory() {
        return inventorySize;
    }

    @Override
    public String getInventoryName() {
        return "PCH-120 Commute";
    }


    @Override
    public boolean interactFirst(EntityPlayer entityplayer) {
        playerEntity = entityplayer;
        if ((super.interactFirst(entityplayer))) {
            return false;
        }
        if (!worldObj.isRemote) {
            if (riddenByEntity != null && (riddenByEntity instanceof EntityPlayer) && riddenByEntity != entityplayer) {
                return true;
            }
            entityplayer.mountEntity(this);
        }
        return true;
    }

    public AxisAlignedBB getDetectionBoundingBox() {
        if (boundingBox == null) {
            // boundingBox = AxisAlignedBB.getBoundingBox(posX, posY, posZ, );
        }
        return boundingBox;
    }

    @Override
    public float getOptimalDistance(EntityMinecart cart) {
        return 0.8F;
    }

    @Override
    public boolean canBeAdjusted(EntityMinecart cart) {
        return canBeAdjusted;
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        return true;
    }

    @Override
    public void setLocoTurnedOnFromPacket(boolean set) {
        super.setLocoTurnedOnFromPacket(set);
        script2.start(this);
    }


    @Override
    public void stationStopComplete() {
        super.stationStopComplete();
        script2.stationStop(this);
       /* theCurrentStation = nextStation;
        theTimetable.remove(nextStation);
        atoStatus = 0;
        this.stationStop3 = Vec3.createVectorHelper(0,0,0);*/
       // Traincraft.atoSetStopPoint.sendToAllAround(new PacketATOSetStopPoint(this.getEntityId(), xFromStopPoint, yFromStopPoint, zFromStopPoint, xStationStop, yStationStop, zStationStop), new NetworkRegistry.TargetPoint(this.worldObj.provider.dimensionId, this.posX, this.posY, this.posZ, 150.0D));
    }

}