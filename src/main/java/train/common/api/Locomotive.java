package train.common.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jcirmodelsquad.tcjcir.extras.packets.RemoteControlKeyPacket;
import com.jcirmodelsquad.tcjcir.features.autotrain.AutoTrain2Handler;
import com.jcirmodelsquad.tcjcir.vehicles.locomotives.PCH100H;
import com.jcirmodelsquad.tcjcir.vehicles.locomotives.PCH120Commute;
import com.jcirmodelsquad.tcjcir.vehicles.locomotives.PCH130Commute2;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.world.World;
import org.apache.commons.lang3.RandomStringUtils;
import org.lwjgl.input.Keyboard;
import train.client.core.handlers.TCKeyHandler;
import train.common.Traincraft;
import train.common.adminbook.ServerLogger;
import train.common.core.HandleMaxAttachedCarts;
import train.common.core.handlers.ConfigHandler;
import train.common.core.network.PacketKeyPress;
import train.common.core.network.PacketParkingBrake;
import train.common.core.network.PacketSlotsFilled;
import train.common.entity.rollingStock.*;
import train.common.items.ItemATOCard;
import train.common.items.ItemRemoteController;
import train.common.items.ItemRemoteControllerModule;
import train.common.items.ItemWirelessTransmitter;
import train.common.library.EnumSounds;
import train.common.library.Info;
import train.common.mtc.MTCMessage;
import train.common.mtc.tile.TileInstructionRadio;
import train.common.mtc.network.*;
import train.common.mtc.vbc.VBCTracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Locomotive extends EntityRollingStock implements IInventory, WirelessTransmitter {
    public boolean lampOn;
    public boolean dothelightthing;
    public boolean bellPressed;
    public int inventorySize;
    protected ItemStack locoInvent[];
    private int soundPosition = 0;
    public boolean parkingBrake = false;
    private int whistleDelay = 0;
    private int bellCount = 0;
    private int blowUpDelay = 0;
    private String lastRider = "";
    private Entity lastEntityRider;
    public int numCargoSlots;
    public int numCargoSlots1;
    public int numCargoSlots2;
    private boolean hasDrowned = false;
    protected boolean canCheckInvent = true;
    private int slotsFilled = 0;
    private int fuelUpdateTicks = 0;
    public boolean isLocoTurnedOn = false;
    public boolean forwardPressed = false;
    private boolean backwardPressed = false;
    public boolean brakePressed = false;
    public TileEntity[] blocksToCheck;

    //Minecraft Train Control
    public int speedLimit, nextSpeedLimit, trainLevel, mtcStatus, mtcType, atoStatus = 0;
    public Vec3 stopPoint3 = Vec3.createVectorHelper(0, 0, 0);
    public double distanceFromStopPoint = 0.0;

    public Vec3 stationStop3 = Vec3.createVectorHelper(0, 0, 0);
    public double distanceFromStationStop = 0.0;


    public Vec3 speedChange3 = Vec3.createVectorHelper(0, 0, 0);
    public Double distanceFromSpeedChange = 0.0;

    public boolean isDriverOverspeed = false;
    public boolean overspeedBrakingInProgress = false;
    public Boolean mtcOverridePressed = false;
    public Boolean overspeedOveridePressed = false;
    public boolean enforceSpeedLimits = true;

    public String serverUUID = "";
    public String trainID = "";
    public String currentSignalBlock = "";
    public boolean speedGoingDown = false;

    public boolean stationStop = false;
    public String connectingUUID = "";

    public boolean isConnected = false;
    public boolean isConnecting = false;
    public int connectionAttempts = 0;
    public boolean atoAllowed = true;
    public int blinkMode = 0; // 0 = Off | 1 = Commander | 2 = Amazon Prime
    //public static int lightsOn = 0;
    /**
     * state of the loco
     */
    private String locoState = "";
    /**
     * false if linked carts have no effect on the velocity of this cart. Use
     * carefully, if you link two carts that can't be adjusted, it will behave
     * as if they are not linked.
     */
    protected boolean canBeAdjusted = false;

    /**
     * These variables are used to display changes in the GUI
     */
    public int currentNumCartsPulled = 0;
    public double currentMassPulled = 0;
    public double currentSpeedSlowDown = 0;
    public double currentAccelSlowDown = 0;
    public double currentBrakeSlowDown = 0;
    public double currentFuelConsumptionChange = 0;

    /**
     * used internally inside each loco to set the fuel consumption
     */
    protected int fuelRate;
    /**
     * This is for the "can pull" feature It is used to avoid conflict with
     * isCartLockDown @see EntityRollingStock line 422 This is set in @see
     * TrainsOnClick
     */
    public boolean canBePulled = false;
    //ETI Type Beat type beat.
    public String operatorID = ""; //Example: PR for PeachRail, or TXCN for Texas Central
    public String trainName = ""; //May not be used very often, but just in case, include it.
    public String trainNumber = "";
    public ArrayList<String> stations = new ArrayList<String>();


    public Locomotive(World world) {
        super(world);
        setFuelConsumption(0);
        inventorySize = numCargoSlots + numCargoSlots2 + numCargoSlots1;
        dataWatcher.addObject(2, 0);
        this.setDefaultMass(0);
        this.setCustomSpeed(getMaxSpeed());
        dataWatcher.addObject(3, destination);
        dataWatcher.addObject(5, trainID);
        dataWatcher.addObject(22, locoState);
        dataWatcher.addObject(24, fuelTrain);
        dataWatcher.addObject(25, (int) convertSpeed(Math.sqrt(Math.abs(motionX * motionX) + Math.abs(motionZ * motionZ))));//convertSpeed((Math.abs(this.motionX) + Math.abs(this.motionZ))
        dataWatcher.addObject(26, castToString(currentNumCartsPulled));
        dataWatcher.addObject(27, castToString(currentMassPulled));
        dataWatcher.addObject(28, castToString(Math.round(currentSpeedSlowDown)));
        dataWatcher.addObject(29, castToString(currentAccelSlowDown));
        dataWatcher.addObject(30, castToString(currentBrakeSlowDown));
        //dataWatcher.addObject(31, castToString(currentFuelConsumptionChange));
        dataWatcher.addObject(15, (float) Math.round((getCustomSpeed() * 3.6f)));
        //dataWatcher.addObject(32, lineWaypoints);
        setAccel(0);
        setBrake(0);
        this.entityCollisionReduction = 0.99F;
        if (this instanceof SteamTrain) isLocoTurnedOn = true;
        char[] chars = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
        StringBuilder sb = new StringBuilder(5);
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        trainID = sb.toString();
        if (!serverUUID.equals("")) {
            attemptConnection(serverUUID);
        }
    }

    /**
     * this is basically NBT for entity spawn, to keep data between client and server in sync because some data is not automatically shared.
     */
    @Override
    public void readSpawnData(ByteBuf additionalData) {
        super.readSpawnData(additionalData);
        isLocoTurnedOn = additionalData.readBoolean();
        parkingBrake = additionalData.readBoolean();
    }

    @Override
    public void writeSpawnData(ByteBuf buffer) {
        super.writeSpawnData(buffer);
        buffer.writeBoolean(isLocoTurnedOn);
        buffer.writeBoolean(parkingBrake);
    }

    private String castToString(double str) {
        return "" + str;
    }

    @Override
    public boolean isPoweredCart() {
        return true;
    }

    @Override
    public boolean canBeRidden() {
        return true;
    }

    /**
     * To disable linking altogether, return false here.
     *
     * @return True if this cart is linkable.
     */
    @Override
    public boolean isLinkable() {
        return false;
    }

    /**
     * Returns true if this cart is a storage cart Some carts may have
     * inventories but not be storage carts and some carts without inventories
     * may be storage carts.
     *
     * @return True if this cart should be classified as a storage cart.
     */
    @Override
    public boolean isStorageCart() {
        return false;
    }

    protected int getCurrentMaxSpeed() {
        return (dataWatcher.getWatchableObjectInt(2));
    }

    protected void setCurrentMaxSpeed(int maxSpeed) {
        if (!worldObj.isRemote) {
            dataWatcher.updateObject(2, maxSpeed);
        }
    }

    /**
     * set the max speed in km/h if the param is 0 then the default speed is
     * used
     * <p>
     * //@param speed //this is for making documentation of some sort via javadoc, shouldn't be relevant to the operation of the mod
     */
    public void setCustomSpeed(double m) {
        if (m != 0) {
            setCurrentMaxSpeed((int) m);
            return;
        }
        setCurrentMaxSpeed((int) this.getMaxSpeed());
    }

    /**
     * returns the absolute maximum speed of the given locomotive (speed in
     * km/h) divided by 3.6 to get ms
     *
     * @return double
     */
    public float getMaxSpeed() {
        if (trainSpec != null) {
            if (currentMassPulled > 1) {
                float power = (float) currentMassPulled / (((float) trainSpec.getMHP()) * 0.37f);
                if (power > 1) {
                    return trainSpec.getMaxSpeed() / (power);
                }
            }
            return trainSpec.getMaxSpeed();
        }
        return 50;
    }

    /**
     * returns the current maximum speed of the given locomotive (speed in km/h)
     * divided by 3.6 to get ms
     *
     * @return double
     */
    public float getCustomSpeed() {
        return getCurrentMaxSpeed() / 3.6f;
    }

    @Override
    public boolean canOverheat() {
        return getOverheatTime() > 0;
    }

    @Override
    public int getOverheatTime() {
        if (trainSpec != null) {
            return trainSpec.getHeatingTime();
        }
        return 0;
    }

    @Override
    public void limitSpeedOnTCRail() {
        if (!canBePulled) {
            maxSpeed = SpeedHandler.handleSpeed(getMaxSpeed(), maxSpeed, this);
            //System.out.println(maxSpeed);
            if (this.speedLimiter != 0 && speedWasSet) {
                //maxSpeed *= this.speedLimiter;
                adjustSpeed(maxSpeed, speedLimiter);
            }
            if (motionX < -maxSpeed) {
                motionX = -maxSpeed;
            }
            if (motionX > maxSpeed) {
                motionX = maxSpeed;
            }
            if (motionZ < -maxSpeed) {
                motionZ = -maxSpeed;
            }
            if (motionZ > maxSpeed) {
                motionZ = maxSpeed;
            }
        }
    }

    /**
     * set the fuel consumption rate for each loco if i is 0 then default
     * consumption is used
     * <p>
     * //@param i //this is for making documentation of some sort via javadoc, shouldn't be relevant to the operation of the mod
     *
     * @return
     */
    public int setFuelConsumption(int c) {
        if (c != 0) {
            return fuelRate = c;
        }
        if (trainSpec != null) {
            return fuelRate = trainSpec.getFuelConsumption();
        }
        return 0;

    }

    /**
     * returns the fuel consumption rate for each loco
     *
     * @return int
     */
    public int getFuelConsumption() {
        return fuelRate == 0 ? trainSpec.getFuelConsumption() : fuelRate;
    }

    /**
     * Return the power of the loco, used for cart pulling
     *
     * @see HandleMaxAttachedCarts for calculations
     */
    public int getPower() {
        if (trainSpec != null) {
            return trainSpec.getMHP();
        }
        return 0;
    }

    /**
     * Set acceleration rate if rate = 0, default value is used
     *
     * @param rate
     */
    public double setAccel(double rate) {
        if (rate != 0) {
            return accelerate = rate;
        } else {
            if (trainSpec != null) {
                return accelerate = trainSpec.getAccelerationRate();
            }
            return 0.45;
        }
    }

    /**
     * Set brake rate if rate = 0, default value is used
     *
     * @param rate
     */
    public double setBrake(double rate) {
        if (rate != 0) {
            return brake = rate;
        } else {
            if (trainSpec != null) {
                return brake = trainSpec.getBrakeRate();
            }
            return 0.98;
        }
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setBoolean("canBeAdjusted", canBeAdjusted);
        nbttagcompound.setBoolean("canBePulled", canBePulled);
        nbttagcompound.setInteger("overheatLevel", getOverheatLevel());
        nbttagcompound.setString("lastRider", lastRider);
        nbttagcompound.setString("destination", destination);
        nbttagcompound.setBoolean("parkingBrake", parkingBrake);
        if (!(this instanceof SteamTrain)) {
            nbttagcompound.setBoolean("isLocoTurnedOn", isLocoTurnedOn);
        }
        nbttagcompound.setString("trainID", trainID);
        nbttagcompound.setInteger("speedLimit", speedLimit);
        nbttagcompound.setInteger("trainLevel", trainLevel);
        nbttagcompound.setInteger("mtcStatus", mtcStatus);
        nbttagcompound.setInteger("mtcType", mtcType);
        nbttagcompound.setInteger("atoStatus", atoStatus);
        nbttagcompound.setDouble("xFromStop", stopPoint3.xCoord);
        nbttagcompound.setDouble("yFromStop", stopPoint3.yCoord);
        nbttagcompound.setDouble("zFromStop", stopPoint3.zCoord);
        nbttagcompound.setDouble("xFromStationStop", stationStop3.xCoord);
        nbttagcompound.setDouble("yFromStationStop", stationStop3.yCoord);
        nbttagcompound.setDouble("zFromStationStop", stationStop3.zCoord);
        nbttagcompound.setInteger("nextSpeedLimit", nextSpeedLimit);
        nbttagcompound.setDouble("xSpeedChange", speedChange3.xCoord);
        nbttagcompound.setDouble("ySpeedChange", speedChange3.yCoord);
        nbttagcompound.setDouble("zSpeedChange", speedChange3.zCoord);
        nbttagcompound.setBoolean("mtcOverridePressed", mtcOverridePressed);
        nbttagcompound.setBoolean("overspeedOverridePressed", overspeedOveridePressed);
        nbttagcompound.setString("serverUUID", serverUUID);
        nbttagcompound.setString("currentSignalBlock", currentSignalBlock);
        nbttagcompound.setBoolean("isConnected", isConnected);
        nbttagcompound.setBoolean("stationStop", stationStop);
        nbttagcompound.setBoolean("lampOn", lampOn);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound ntc) {
        super.readEntityFromNBT(ntc);
        canBeAdjusted = ntc.getBoolean("canBeAdjusted");
        canBePulled = ntc.getBoolean("canBePulled");
        setOverheatLevel(ntc.getInteger("overheatLevel"));
        lastRider = ntc.getString("lastRider");
        destination = ntc.getString("destination");
        this.parkingBrake = ntc.getBoolean("parkingBrake");
        if (!(this instanceof SteamTrain)) {
            isLocoTurnedOn = ntc.getBoolean("isLocoTurnedOn");
        }
        trainID = ntc.getString("trainID");
        speedLimit = ntc.getInteger("speedLimit");
        trainLevel = ntc.getInteger("trainLevel");
        mtcStatus = ntc.getInteger("mtcStatus");
        mtcType = ntc.getInteger("mtcType");
        atoStatus = ntc.getInteger("atoStatus");

        stopPoint3 = Vec3.createVectorHelper(ntc.getDouble("xFromStop"), ntc.getDouble("yFromStop"), ntc.getDouble("zFromStop"));
        stationStop3 = Vec3.createVectorHelper(ntc.getDouble("xFromStationStop"), ntc.getDouble("yFromStationStop"), ntc.getDouble("zFromStationStop"));
        speedChange3 = Vec3.createVectorHelper(ntc.getDouble("xSpeedChange"), ntc.getDouble("ySpeedChange"), ntc.getDouble("zSpeedChange"));


        mtcOverridePressed = ntc.getBoolean("mtcOverridePressed");
        overspeedOveridePressed = ntc.getBoolean("overspeedOverridePressed");
        serverUUID = ntc.getString("serverUUID");
        currentSignalBlock = ntc.getString("currentSignalBlock");
        isConnected = ntc.getBoolean("isConnected");
        stationStop = ntc.getBoolean("stationStop");
        dataWatcher.updateObject(5, trainID);
    }

    /**
     * Returns true if this entity should push and be pushed by other entities
     * when colliding.
     */
    @Override
    public boolean canBePushed() {
        return false;
    }

    public void setCanBeAdjusted(boolean canBeAdj) {
        this.canBeAdjusted = canBeAdj;
    }

    /**
     * gets packet from server and distribute for GUI handles motion
     *
     * @param i
     */
    @Override
    public void keyHandlerFromPacket(int i) {
        if (this.getTrainLockedFromPacket()) {
            if (this.riddenByEntity instanceof EntityPlayer
                    && !((EntityPlayer) this.riddenByEntity).getDisplayName().toLowerCase()
                    .equals(this.getTrainOwner().toLowerCase())) {
                return;
            }
        }
        pressKey(i);
        if (i == 8 && ConfigHandler.SOUNDS) {
            soundHorn();
        }
        if (i == 4) {
            forwardPressed = true;
        }
        if (i == 5) {
            backwardPressed = true;
        }
        if (i == 12) {
            brakePressed = true;
        }
        if (i == 13) {
            forwardPressed = false;
        }
        if (i == 14) {
            backwardPressed = false;
        }
        if (i == 15) {
            brakePressed = false;
        }
        if (i == 16) {
            if (mtcStatus != 0 && this.mtcType == 2) {
                if (!(this instanceof SteamTrain && !ConfigHandler.ALLOW_ATO_ON_STEAMERS)) {
                    if (atoStatus == 1) {
                        atoStatus = 0;
                    } else {
                        atoStatus = 1;
                    }
                }
            }
        }

        if (i == 17) {

            if (mtcOverridePressed) {
                mtcOverridePressed = false;
            } else {
                mtcOverridePressed = true;
                this.mtcStatus = 0;
                this.speedLimit = 0;
                this.nextSpeedLimit = 0;
                this.trainLevel = 0;
                disconnectFromServer();
            }


        }
        if (i == 18) {
            if (mtcStatus != 0) {
                if (overspeedOveridePressed) {
                    overspeedOveridePressed = false;
                } else {
                    overspeedOveridePressed = true;
                }
            }
        }
        if (i == 19) {
            /*if (lampOn == false) {//if lampon is EQUAL TO false
                lampOn = true;// make lampon EQUAL true
            } else if (lampOn == true) {//if lampon is EQUAL TO true
                lampOn = false; //make lampon EQUAL false
            }*/
            lampOn = !lampOn;
            if (lampOn) {
                this.dothelightthing = true;
            }
            if (!lampOn) {
                dothelightthing = false;
            }

            if (lampOn) {
                System.out.println(lampOn + " loco.java");
            }
            if (!lampOn) {
                System.out.println(lampOn + " loco.java");
            }
        }

        /*if (i == 48){
            soundBell();
        }*/

        if (i == 20) {
            cycleThroughBeacons();
        }

        if (i == 10) {//BELLPRESSED NEESD TO BE TRUEE
            bellPressed = !bellPressed;
            if (bellPressed) {
                soundBell3();
            }
        }
    }

    /**
     * All this is used in GUI only
     *
     * @return
     */
    public String getCurrentNumCartsPulled() {
        return (this.dataWatcher.getWatchableObjectString(26));
    }

    public String getCurrentMassPulled() {
        return (this.dataWatcher.getWatchableObjectString(27));
    }

    public String getCurrentSpeedSlowDown() {
        return (this.dataWatcher.getWatchableObjectString(28));
    }

    public String getCurrentAccelSlowDown() {
        return (this.dataWatcher.getWatchableObjectString(29));
    }

    public String getCurrentBrakeSlowDown() {
        return (this.dataWatcher.getWatchableObjectString(30));
    }

    public String getCurrentFuelConsumptionChange() {
        return (this.dataWatcher.getWatchableObjectString(31));
    }

    public Float getCustomSpeedGUI() {
        return (this.dataWatcher.getWatchableObjectFloat(15));
    }

    public String getDestinationGUI() {
        if (worldObj.isRemote) {
            return (this.dataWatcher.getWatchableObjectString(3));
        }
        return destination;
    }

    private double convertSpeed(double speed) {
        //System.out.println("X "+motionX +" Z "+motionZ);
        if (ConfigHandler.REAL_TRAIN_SPEED) {
            speed *= 2;// applying ratio
        } else {
            speed *= 6;
        }
        speed *= 36;
        //speed *= 10;// convert in ms
        //speed *= 6;// applying ratio
        //speed *= 3.6;// convert in km/h
        return speed;
    }

    /*public void soundBell() {
        worldObj.playSoundAtEntity(this, Info.resourceLocation + ":" + "sounds/bell/test.ogg", 1F, 1.0F);
    }*/
    public void soundBell2AndaHalf() {
        worldObj.playSoundAtEntity(this, Info.resourceLocation + ":" + "bell_test", 1.0F, 1.0F);

    }

    public void soundBell3() {
        for (EnumSounds sounds : EnumSounds.values()) {
            if (sounds.getEntityClass() != null && !sounds.getHornString().equals("") && sounds.getEntityClass().equals(this.getClass()) && !sounds.getBellString().equals("")) {

                worldObj.playSoundAtEntity(this, Info.resourceLocation + ":" + sounds.getBellString(), 1f, 1F);
                bellCount = sounds.getBellLength();//default 15 for bronze bell
                //System.out.println(bellCount);

            }
        }
    }

    public void soundHorn() {
        for (EnumSounds sounds : EnumSounds.values()) {
            if (sounds.getEntityClass() != null && !sounds.getHornString().equals("") && sounds.getEntityClass().equals(this.getClass()) && whistleDelay == 0) {
                worldObj.playSoundAtEntity(this, Info.resourceLocation + ":" + sounds.getHornString(), sounds.getHornVolume(), 1.0F);
                whistleDelay = 65;
            }
        }
        List entities = worldObj.getEntitiesWithinAABB(EntityAnimal.class, AxisAlignedBB.getBoundingBox(
                this.posX - 20, this.posY - 5, this.posZ - 20,
                this.posX + 20, this.posY + 5, this.posZ + 20));

        for (Object e : entities) {
            if (e instanceof EntityAnimal) {
                ((EntityAnimal) e).setTarget(this);
                ((EntityAnimal) e).getNavigator().setPath(null, 0);
            }
        }
    }

    @Override
    public void onUpdate() {
        if (trainID.equals("") && !worldObj.isRemote && ticksExisted % 40 == 0) {
            trainID = RandomStringUtils.randomAlphanumeric(5);
            dataWatcher.updateObject(5, trainID);

        }

        if (worldObj.isRemote && ticksExisted % 2 == 0 && !Minecraft.getMinecraft().ingameGUI.getChatGUI().getChatOpen()) {
            if (Keyboard.isKeyDown(FMLClientHandler.instance().getClient().gameSettings.keyBindForward.getKeyCode())
                    && !forwardPressed) {
                Traincraft.keyChannel.sendToServer(new PacketKeyPress(4));
                forwardPressed = true;
            } else if (!Keyboard
                    .isKeyDown(FMLClientHandler.instance().getClient().gameSettings.keyBindForward.getKeyCode())
                    && forwardPressed) {
                Traincraft.keyChannel.sendToServer(new PacketKeyPress(13));
                forwardPressed = false;
            }
            if (Keyboard.isKeyDown(FMLClientHandler.instance().getClient().gameSettings.keyBindBack.getKeyCode())
                    && !backwardPressed) {
                Traincraft.keyChannel.sendToServer(new PacketKeyPress(5));
                backwardPressed = true;
            } else if (!Keyboard
                    .isKeyDown(FMLClientHandler.instance().getClient().gameSettings.keyBindBack.getKeyCode())
                    && backwardPressed) {
                Traincraft.keyChannel.sendToServer(new PacketKeyPress(14));
                backwardPressed = false;
            }
            if (Keyboard.isKeyDown(FMLClientHandler.instance().getClient().gameSettings.keyBindJump.getKeyCode())
                    && !brakePressed) {
                Traincraft.keyChannel.sendToServer(new PacketKeyPress(12));
                brakePressed = true;
            } else if (!Keyboard
                    .isKeyDown(FMLClientHandler.instance().getClient().gameSettings.keyBindJump.getKeyCode())
                    && brakePressed) {
                Traincraft.keyChannel.sendToServer(new PacketKeyPress(15));
                brakePressed = false;
            }

            Item currentItem = new Item();
            if (Minecraft.getMinecraft().thePlayer != null && Minecraft.getMinecraft().thePlayer.inventory.getCurrentItem() != null) {
                currentItem = Minecraft.getMinecraft().thePlayer.inventory.getCurrentItem().getItem();
            }
            boolean hasController = currentItem instanceof ItemRemoteController;
            boolean isConnected = false;

            if (currentItem != null && hasController) {
                isConnected = ((ItemRemoteController) currentItem).attachedLocomotive != null;
            }
            //1: Forward
            //2: Backwards
            //3: Toggle Brake
            //4: Horn

            if (Minecraft.getMinecraft().thePlayer != null && Vec3.createVectorHelper(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY, Minecraft.getMinecraft().thePlayer.posZ).distanceTo(Vec3.createVectorHelper(this.posX, posY, posZ)) < 200) {
                if (TCKeyHandler.remoteControlForward.getIsKeyPressed() && hasController && isConnected && ((ItemRemoteController) currentItem).attachedLocomotive == this) {
                    Traincraft.remoteControlKey.sendToServer(new RemoteControlKeyPacket(this.getEntityId(), 1));
                }

                if (TCKeyHandler.remoteControlBackwards.getIsKeyPressed() && hasController && isConnected && ((ItemRemoteController) currentItem).attachedLocomotive == this) {
                    Traincraft.remoteControlKey.sendToServer(new RemoteControlKeyPacket(this.getEntityId(), 2));
                }

                if (TCKeyHandler.remoteControlBrake.getIsKeyPressed() && hasController && isConnected && ((ItemRemoteController) currentItem).attachedLocomotive == this) {
                    Traincraft.remoteControlKey.sendToServer(new RemoteControlKeyPacket(this.getEntityId(), 3));
                }

                if (TCKeyHandler.remoteControlHorn.getIsKeyPressed() && hasController && isConnected && ((ItemRemoteController) currentItem).attachedLocomotive == this) {
                    Traincraft.remoteControlKey.sendToServer(new RemoteControlKeyPacket(this.getEntityId(), 4));
                }
            }

        }

        // if (worldObj.isRemote) {
        // if (updateTicks % 50 == 0) {
        // Traincraft.brakeChannel
        // .sendToServer(new PacketParkingBrake(parkingBrake, this.getEntityId()));
        // Traincraft.ignitionChannel.sendToServer(new PacketSetLocoTurnedOn(isLocoTurnedOn));//
        // sending to client
        // updateTicks=0;
        // }
        // }
        if (!worldObj.isRemote) {
            if (parkingBrake) {
                motionX = 0.0;
                motionZ = 0.0;
            }
            if (this.riddenByEntity instanceof EntityLivingBase) {
                //EntityLivingBase entity = (EntityLivingBase) this.riddenByEntity;
                if (forwardPressed || backwardPressed) {
                    if (getFuel() > 0 && this.isLocoTurnedOn() && rand.nextInt(4) == 0 && !worldObj.isRemote) {
                        if (this.getTrainLockedFromPacket() && !((EntityPlayer) this.riddenByEntity).getDisplayName()
                                .toLowerCase().equals(this.getTrainOwner().toLowerCase())) {
                            return;
                        }
                        if (riddenByEntity instanceof EntityPlayer) {
                            int dir = MathHelper
                                    .floor_double((((EntityPlayer) riddenByEntity).rotationYaw * 4F) / 360F + 0.5D) & 3;
                            if (dir == 2) {
                                if (forwardPressed) {
                                    motionZ -= 0.0075 * this.accelerate;
                                } else {
                                    motionZ += 0.0075 * this.accelerate;
                                }
                            } else if (dir == 0) {
                                if (forwardPressed) {
                                    motionZ += 0.0075 * this.accelerate;
                                } else {
                                    motionZ -= 0.0075 * this.accelerate;
                                }
                            } else if (dir == 1) {
                                if (forwardPressed) {
                                    motionX -= 0.0075 * this.accelerate;
                                } else {
                                    motionX += 0.0075 * this.accelerate;
                                }
                            } else if (dir == 3) {
                                if (forwardPressed) {
                                    motionX += 0.0075 * this.accelerate;
                                } else {
                                    motionX -= 0.0075 * this.accelerate;
                                }
                            }
                        }
                    }
                } else if (brakePressed) {
                    motionX *= brake;
                    motionZ *= brake;
                }
            }


            if (updateTicks % 20 == 0) HandleMaxAttachedCarts.PullPhysic(this);
            if (updateTicks % 15 == 0) VBCTracking.getInstance().updateFromRS(Vec3.createVectorHelper(Math.floor(posX), Math.floor(posY), Math.floor(posZ)));

            /**
             * Can't use datawatcher here. Locomotives use them all already
             * Check inventory The packet never arrives if it is sent when the
             * entity reads its NBT (player hasn't been initialised probably)
             */
            if (updateTicks % 200 == 0) {
                this.slotsFilled = 0;
                for (int i = 0; i < getSizeInventory(); i++) {
                    ItemStack itemstack = getStackInSlot(i);
                    if (itemstack != null) {
                        slotsFilled++;
                    }
                }

                Traincraft.slotschannel.sendToAllAround(new PacketSlotsFilled(this, slotsFilled), new TargetPoint(this.worldObj.provider.dimensionId, this.posX, this.posY, this.posZ, 150.0D));
            }
            /**
             * Fuel consumption
             */
            //if (this instanceof DieselTrain) consumption /= 5;
            if (fuelUpdateTicks >= 100) {
                fuelUpdateTicks = 0;
                updateFuelTrain(this.getFuelConsumption());
            }
            fuelUpdateTicks++;

        }
        if (whistleDelay > 0) {
            whistleDelay--;
        }
        if (this.riddenByEntity instanceof EntityPlayer) {
            this.lastRider = ((EntityPlayer) this.riddenByEntity).getDisplayName();
            this.lastEntityRider = (this.riddenByEntity);
        }

       /* if (!this.worldObj.isRemote && this.getParkingBrakeFromPacket() && !getState().equals("broken")) {
            motionX *= 0.0;
            motionZ *= 0.0;
        }*/

        //public void soundBell2() {
            /*for (EnumSounds sounds : EnumSounds.values()) {
                if (sounds.getEntityClass() != null && !sounds.getBellString().equals("")&& sounds.getEntityClass().equals(this.getClass()) && bellDelay == 0) {
                    if (bellPressed) {
                        bellCount++;
                        if (bellCount == 10) {
                            worldObj.playSoundAtEntity(this, Info.resourceLocation + ":" + sounds.getBellString(), 1.0F, 1.0F);//first float is volume
                            //bellDelay = 1;
                            bellCount = 0;
                        }
                        //break;

                    }
                }//THIS SHOULD WORK BUT BELLPRESSED IS NEVER SET TO TRUE TO MAKE IT WORK IN THE FIRST PLACE
            }//FIND OUT HOW TO GET BELLPRESSED TO TRUE
        //}
*/
        if (ConfigHandler.SOUNDS) {
            for (EnumSounds sounds : EnumSounds.values()) {
                if (sounds.getEntityClass() != null && !sounds.getHornString().equals("") && sounds.getEntityClass().equals(this.getClass()) && whistleDelay == 0 && !sounds.getBellString().equals("")) {
                    if (getFuel() > 0 && this.isLocoTurnedOn()) {
                        double speed = Math.sqrt(motionX * motionX + motionZ * motionZ);
                        if (speed > -0.001D && speed < 0.01D && soundPosition == 0) {
                            worldObj.playSoundAtEntity(this, Info.resourceLocation + ":" + sounds.getIdleString(), sounds.getIdleVolume(), 1F);
                            soundPosition = sounds.getIdleSoundLenght();//soundPosition is probably where IN the sound it is currently playing, eg 1 sec int osoudn file
                        }
                        if (sounds.getSoundChangeWithSpeed() && !sounds.getHornString().equals("") && sounds.getEntityClass().equals(this.getClass()) && whistleDelay == 0 && !sounds.getBellString().equals("")) {
                            if (speed > 0.01D && speed < 0.06D && soundPosition == 0) {
                                worldObj.playSoundAtEntity(this, Info.resourceLocation + ":" + sounds.getRunString(), sounds.getRunVolume(), 0.1F);
                                soundPosition = sounds.getRunSoundLenght();
                            } else if (speed > 0.06D && speed < 0.2D && soundPosition == 0) {
                                worldObj.playSoundAtEntity(this, Info.resourceLocation + ":" + sounds.getRunString(), sounds.getRunVolume(), 0.4F);
                                soundPosition = sounds.getRunSoundLenght() / 2;
                            } else if (speed > 0.2D && soundPosition == 0) {
                                worldObj.playSoundAtEntity(this, Info.resourceLocation + ":" + sounds.getRunString(), sounds.getRunVolume(), 0.5F);
                                soundPosition = sounds.getRunSoundLenght() / 3;
                            }
                        } else {
                            if (speed > 0.01D && soundPosition == 0) {
                                worldObj.playSoundAtEntity(this, Info.resourceLocation + ":" + sounds.getRunString(), sounds.getRunVolume(), 1F);
                                soundPosition = sounds.getRunSoundLenght();
                            }
                        }
                        if (soundPosition > 0) {
                            soundPosition--;
                        }
                    }
                    break;
                }
            }
        }

        for (EnumSounds sounds : EnumSounds.values()) {
            if (sounds.getEntityClass() != null && !sounds.getHornString().equals("") && sounds.getEntityClass().equals(this.getClass()) && !sounds.getBellString().equals("")) {
                if (bellPressed) {

                    if (bellCount == 0) {
                        soundBell3();
                    }

                    if (bellCount > 0) {
                        bellCount--;
                    }
                } else {
                    bellCount = 0;
                }
                break;
            }
        }

        /*
        * for (EnumSounds sounds : EnumSounds.values()) {
            if (sounds.getEntityClass() != null && !sounds.getHornString().equals("")&& sounds.getEntityClass().equals(this.getClass()) && !sounds.getBellString().equals("")) {
                if (bellPressed) {

                    if (bellCount == 0) {
                        worldObj.playSoundAtEntity(this, Info.resourceLocation + ":" + sounds.getBellString(), 1f, 1F);// 2nd float is pitch, first float is volue
                        bellCount = 15;//default 15 for bronze bell
                        System.out.println(bellCount);
                    }
                    if (bellCount > 0) {
                        bellCount--;
                    }
                }
                else{
                    bellCount = 0;
                }
                break;
            }
        }

        * */

        if (getState().equals("cold") && !canBePulled) {
            this.extinguish();
            if (getCurrentMaxSpeed() >= (getMaxSpeed() * 0.6)) {
                motionX *= 0.0;
                motionZ *= 0.0;
            }
        }
        if (getState().equals("warm")) {
            this.extinguish();
            if (getCurrentMaxSpeed() >= (getMaxSpeed() * 0.7)) {
                motionX *= 0.94;
                motionZ *= 0.94;
            }
        }
        if (getState().equals("hot")) {
            this.extinguish();
        }
        //if (getState().equals("very hot")) {}
        if (getState().equals("too hot")) {
            motionX *= 0.95;
            motionZ *= 0.95;
            worldObj.spawnParticle("largesmoke", posX, posY + 0.3, posZ, 0.0D, 0.0D, 0.0D);
        }
        if (getState().equals("broken")) {
            setFire(8);
            this.setCustomSpeed(0);// set speed to normal
            this.setAccel(0.000001);// simulate a break down
            this.setBrake(1);
            this.motionX *= 0.97;// slowly slows down
            this.motionZ *= 0.97;
            worldObj.spawnParticle("largesmoke", posX, posY + 0.3, posZ, 0.0D, 0.0D, 0.0D);
            worldObj.spawnParticle("largesmoke", posX, posY + 0.3, posZ, 0.0D, 0.0D, 0.0D);
            blowUpDelay++;
            if (blowUpDelay > 80) {
                if (!worldObj.isRemote) {
                    //worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 0.5F, true);
                    worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 0.5F, false);
                    this.setDead();
                }
                if (!worldObj.isRemote && FMLCommonHandler.instance().getMinecraftServerInstance() != null && this.lastEntityRider instanceof EntityPlayer) {
                    FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().sendChatMsg(new ChatComponentText(((EntityPlayer) this.lastEntityRider).getDisplayName() + " blew " + this.getTrainOwner() + "'s locomotive"));
                    FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().sendChatMsg(new ChatComponentText(((EntityPlayer) this.lastEntityRider).getDisplayName() + " blew " + this.getTrainOwner() + "'s locomotive"));
                }
            }
        }
        //Todo: Better packets
        //Minecraft Train Control things.
        if (!worldObj.isRemote) {
            boolean autoTrainOn = false;
            try {
                AutoTrain2Handler handlerField = (AutoTrain2Handler) getClass().getField("autoTrainHandler").get(this);
                autoTrainOn = handlerField.autoTrainActivated;

            } catch (IllegalAccessException | NoSuchFieldException e) {

            }
            if (mtcStatus == 1 | mtcStatus == 2) {
                if ((mtcType == 2 || mtcType == 3) && !isConnected) {
                    //Send updates every few seconds
                    if (this.ticksExisted % 20 == 0) {
                        sendMTCStatusUpdate();
                    }
                }
                if ((mtcType == 2 || mtcType == 3) && !trainIsWMTCSupported()) {
                    //Seems like the MTC card has been removed suddenly. Terminate connections.
                    disconnectFromServer();
                    serverUUID = "";
                    mtcStatus = 0;
                    Traincraft.mtcChannel.sendToAllAround(new PacketMTC(getEntityId(), mtcStatus, 2), new NetworkRegistry.TargetPoint(this.worldObj.provider.dimensionId, this.posX, this.posY, this.posZ, 150.0D));
                }
                    isDriverOverspeed = getSpeed() > speedLimit && speedLimit != 0 && enforceSpeedLimits;
                    if (isDriverOverspeed && (ticksExisted % 40 == 0) && atoStatus != 1 && this.riddenByEntity != null) {
                        //Todo Play Sound on Client
                        //Traincraft.playSoundOnClientChannel.sendTo(new PacketPlaySoundOnClient(7, "tc:mtc_overspeed"), (EntityPlayerMP) this.riddenByEntity);
                    }
                    if (isDriverOverspeed && ticksExisted % 120 == 0 && !overspeedBrakingInProgress && !overspeedOveridePressed && atoStatus != 1) {
                        //Start braking.
                        overspeedBrakingInProgress = true;
                    }

                    if (isDriverOverspeed && ticksExisted % 120 == 0 && !overspeedBrakingInProgress && !overspeedOveridePressed && atoStatus != 1) {
                        overspeedBrakingInProgress = true;
                    }

                    if (overspeedBrakingInProgress && atoStatus != 1) {
                        if (getSpeed() < speedLimit) {
                            //Stop overspeed braking.
                            overspeedBrakingInProgress = false;
                            isDriverOverspeed = false;
                        } else {
                            slow(speedLimit);
                        }
                    }

                    distanceFromStopPoint = stopPoint3.distanceTo(Vec3.createVectorHelper(this.posX, this.posY, this.posZ));
                    distanceFromSpeedChange = speedChange3.distanceTo(Vec3.createVectorHelper(this.posX, this.posY, this.posZ));


                    if (distanceFromSpeedChange < this.speedLimit && !(distanceFromSpeedChange < this.nextSpeedLimit)) {
                        speedLimit = (int) Math.round(distanceFromSpeedChange);
                        speedGoingDown = true;

                        if (distanceFromSpeedChange <= 5) {
                            speedChange3 = Vec3.createVectorHelper(0, 0, 0);
                            speedLimit = nextSpeedLimit;
                            nextSpeedLimit = 0;
                            speedGoingDown = false;

                            Traincraft.mtcChannel.sendToAllAround(new PacketSpeedLimit(getEntityId(), speedLimit, nextSpeedLimit, 0, 0, 0),
                                    new TargetPoint(this.worldObj.provider.dimensionId, this.posX, this.posY, this.posZ, 150.0D));
                        }

                    }

                    if (distanceFromStopPoint >= 40 && distanceFromStopPoint < this.speedLimit && !(this.stopPoint3.xCoord == 0.0)) {
                        this.speedLimit = (int) Math.round(distanceFromStopPoint);
                        speedGoingDown = true;
                    } else if (distanceFromStopPoint >= 10 && distanceFromStopPoint < this.speedLimit && !(this.stopPoint3.xCoord == 0.0) && (mtcType == 2 || mtcType == 3)) {
                        this.speedLimit = (int) Math.round(distanceFromStopPoint);
                        speedGoingDown = true;
                    }
                    Traincraft.mtcChannel.sendToAllAround(new PacketSpeedLimit(getEntityId(), speedLimit, nextSpeedLimit),
                            new TargetPoint(this.worldObj.provider.dimensionId, this.posX, this.posY, this.posZ, 150.0D));

				/*if (distanceFromStopPoint < this.getSpeed() && !(distanceFromStopPoint < nextSpeedLimit)  && !(this instanceof EntityLocoElectricPeachDriverlessMetro)) {
					speedLimit = (int) Math.round(distanceFromStopPoint);
					Traincraft.itsChannel.sendToAllAround(new PacketSetSpeed(this.speedLimit, (int) this.posX, (int) this.posY, (int) this.posZ, getEntityId()), new TargetPoint(this.worldObj.provider.dimensionId, this.posX, this.posY, this.posZ, 150.0D) );
				}*/


                    //For Automatic Train Operation

                    if (this.atoStatus == 1 && trainIsATOSupported()) {
                        distanceFromStationStop = stationStop3.distanceTo(Vec3.createVectorHelper(this.posX, this.posY, this.posZ));
                        if (this.parkingBrake) {
                            this.parkingBrake = false;
                            //Accelerate to the speed limit
                        }
                        if (!(distanceFromStopPoint < this.getSpeed()) && (!(distanceFromSpeedChange < this.getSpeed())) && !(distanceFromStationStop < this.getSpeed() + 5)) {
                            accel(this.speedLimit);
                        }


                        //Todo: ATO

                if (this.atoStatus == 1) {

                }
                if (distanceFromStationStop < this.getSpeed()) {
                    stop(stationStop3);
                }

                if (distanceFromSpeedChange < this.getSpeed() && !(this.getSpeed() == this.nextSpeedLimit)) {
                    //Slow it down to the next speed limit
                    slow(this.nextSpeedLimit);
                }

                if (isDriverOverspeed) {
                    //The ATO system is speeding somehow, slow it down
                    slow(this.speedLimit);
                }
                if (this.distanceFromStopPoint < 2 || this.distanceFromStationStop < 2 && !stationStop) {
                    this.parkingBrake = true;
                }

                if (this.distanceFromStationStop < 2 && !stationStop) {
                    //Disengage ATO.

                    this.isBraking = true;
                    this.stationStop3 = Vec3.createVectorHelper(0,0,0);

                    this.atoStatus = 0;
                    this.stationStop = true;

                    Traincraft.mtcChannel.sendToAllAround(new PacketATO(this.getEntityId(), 0), new NetworkRegistry.TargetPoint(this.worldObj.provider.dimensionId, this.posX, this.posY, this.posZ, 150.0D));
                    Traincraft.mtcChannel.sendToAllAround(new PacketStopPoint(this.getEntityId(), 0.0, 0.0, 0.0, 1), new NetworkRegistry.TargetPoint(this.worldObj.provider.dimensionId, this.posX, this.posY, this.posZ, 150.0D));
                    Traincraft.brakeChannel.sendToAllAround(new PacketParkingBrake(true, this.getEntityId()),
                            new NetworkRegistry.TargetPoint(this.worldObj.provider.dimensionId, this.posX, this.posY, this.posZ, 150.0D));
                    stationStopComplete();

                }


                }

                    }
                }


        super.onUpdate();
        if (!worldObj.isRemote) {
            //System.out.println(motionX +" "+motionZ);
            dataWatcher.updateObject(25, (int) convertSpeed(Math.sqrt(motionX * motionX + motionZ * motionZ)));
            dataWatcher.updateObject(24, fuelTrain);
            dataWatcher.updateObject(20, overheatLevel);
            dataWatcher.updateObject(22, locoState);
            dataWatcher.updateObject(3, destination);
            dataWatcher.updateObject(5, trainID);
            dataWatcher.updateObject(26, (castToString(currentNumCartsPulled)));
            dataWatcher.updateObject(27, (castToString((currentMassPulled)) + " tons"));
            dataWatcher.updateObject(28, ((int) currentSpeedSlowDown) + " km/h");
            dataWatcher.updateObject(29, (castToString((double) (Math.round(currentAccelSlowDown * 1000)) / 1000)));
            dataWatcher.updateObject(30, (castToString((double) (Math.round(currentBrakeSlowDown * 1000)) / 1000)));
            dataWatcher.updateObject(31, ("1c/" + castToString((int) (currentFuelConsumptionChange)) + " per tick"));
            dataWatcher.updateObject(15, getMaxSpeed());
            //System.out.println();
            if (this.worldObj.handleMaterialAcceleration(this.boundingBox.expand(0.0D, -0.2000000059604645D, 0.0D).contract(0.001D, 0.001D, 0.001D), Material.water, this) && this.updateTicks % 4 == 0) {
                if (!hasDrowned && !worldObj.isRemote && FMLCommonHandler.instance().getMinecraftServerInstance() != null && this.lastEntityRider instanceof EntityPlayer) {
                    FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().sendChatMsg(new ChatComponentText(((EntityPlayer) this.lastEntityRider).getDisplayName() + " drowned " + this.getTrainOwner() + "'s locomotive"));
                    FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().sendChatMsg(new ChatComponentText(((EntityPlayer) this.lastEntityRider).getDisplayName() + " drowned " + this.getTrainOwner() + "'s locomotive"));
                }
                //this.attackEntityFrom(DamageSource.generic, 100);
                this.setCustomSpeed(0);// set speed to normal
                this.setAccel(0.000001);// simulate a break down
                this.setBrake(1);
                this.motionX *= 0.97;// slowly slows down
                this.motionZ *= 0.97;
                this.fuelTrain = 0;
                this.hasDrowned = true;
                this.canCheckInvent = false;
                blowUpDelay++;
                if (blowUpDelay > 20) {
                    this.attackEntityFrom(DamageSource.drown, 100);
                }
            }/*
             * else{ this.canCheckInvent=true; this.hasDrowned=false; }
             */
        }
    }


    @Override
    protected void applyDragAndPushForces() {
        motionX *= getDragAir();
        motionY *= 0.0D;
        motionZ *= getDragAir();
    }

    /**
     * Carts should return their drag factor here
     *
     * @return The drag rate.
     */
    @Override
    public double getDragAir() {
        return 1D;
    }

    /**
     * Added for SMP
     *
     * @return true if on, false if off
     */
    public boolean getParkingBrakeFromPacket() {
        return parkingBrake;
    }

    /**
     * Added for SMP
     *
     * @param set set 0 if parking break is false, 1 if true
     */
    public void setParkingBrakeFromPacket(boolean set) {
        parkingBrake = set;
    }

    /**
     * added for SMP, used by the HUD
     *
     * @return
     */
    public double getSpeed() {
        return dataWatcher.getWatchableObjectInt(25);
    }

    /**
     * added for SMP, used by the HUD
     *
     * @return
     */
    @Override
    public int getOverheatLevel() {
        return (this.dataWatcher.getWatchableObjectInt(20));
    }

    /**
     * returns the state of the loco state is the consequence of overheating
     *
     * @return cold warm hot very hot too hot broken
     */
    public String getState() {
        return (this.dataWatcher.getWatchableObjectString(22));
    }

    /**
     * set the state of the loco
     *
     * @param state cold warm hot very hot too hot broken
     */
    public void setState(String state) {
        locoState = state;
        this.dataWatcher.updateObject(22, state);
    }

    /**
     * added for SMP, used by the HUD
     *
     * @return
     */
    public int getFuel() {
        if (worldObj.isRemote) {
            return (this.dataWatcher.getWatchableObjectInt(24));
        }
        return fuelTrain;
    }

    /**
     * Is it fuelled? used in GUI
     */
    public boolean getIsFuelled() {
        if (worldObj.isRemote) {
            return (this.dataWatcher.getWatchableObjectInt(24)) > 0;
        }
        return (this.fuelTrain > 0);
    }

    /**
     * Used for the gui
     */
    public int getFuelDiv(int i) {
        if (worldObj.isRemote) {
            return ((this.dataWatcher.getWatchableObjectInt(24) * i) / 1200);
        }
        return (this.fuelTrain * i) / 1200;
    }

    /**
     * This code applies fuel consumption.
     *
     * @param consumption
     */
    protected void updateFuelTrain(int consumption) {
        if (fuelTrain < 0 && !canBePulled) {
            motionX *= 0.8;
            motionZ *= 0.8;
        } else {
            if (this.isLocoTurnedOn()) {
                fuelTrain -= consumption;
                if (fuelTrain < 0) fuelTrain = 0;
            }
        }
    }

    public void setLocoTurnedOnFromPacket(boolean set) {
        isLocoTurnedOn = set;
    }

    public boolean isLocoTurnedOn() {
        return isLocoTurnedOn;
    }

    // private int placeInSpecialInvent(ItemStack itemstack1, int i, boolean doAdd) {
    // if (locoInvent[i] == null) {
    // if (doAdd) locoInvent[i] = itemstack1;
    // return itemstack1.stackSize;
    // }
    // else if (locoInvent[i] != null && locoInvent[i] == itemstack1 && itemstack1.isStackable() &&
    // (!itemstack1.getHasSubtypes() || locoInvent[i].getItemDamage() == itemstack1.getItemDamage())
    // && ItemStack.areItemStackTagsEqual(locoInvent[i], itemstack1)) {
    //
    // int var9 = locoInvent[i].stackSize + itemstack1.stackSize;
    // if (var9 <= itemstack1.getMaxStackSize()) {
    // if (doAdd) locoInvent[i].stackSize = var9;
    // return var9;
    // }
    // else if (locoInvent[i].stackSize < itemstack1.getMaxStackSize()) {
    // if (doAdd) locoInvent[i].stackSize = locoInvent[i].getMaxStackSize();
    // return Math.abs(locoInvent[i].getMaxStackSize() - locoInvent[i].stackSize -
    // itemstack1.stackSize);
    //
    // }
    // }
    // return itemstack1.stackSize;
    //
    // }


    //TODO Fix ISided Inventory buildcraft support
	/*
	/**
	 * Offers an ItemStack for addition to the inventory.
	 *
	 * @param stack
	 *            ItemStack offered for addition. Do not manipulate this!
	 * @param doAdd
	 *            If false no actual addition should take place.
	 * @param from
	 *            Orientation the ItemStack is offered from.
	 * @return Amount of items used from the passed stack.
	 */
	/*
	@Override
	public int addItem(ItemStack stack, boolean doAdd, ForgeDirection from) {
		if (stack == null) { return 0; }
		//FuelHandler.steamFuelLast(itemstack) > 0 || LiquidManager.getInstance().isDieselLocoFuel(itemstack)||(itemstack.getItem().shiftedIndex==Item.redstone.shiftedIndex) || (itemstack.getItem() instanceof IElectricItem)
		//LiquidManager.getInstance().isContainer(itemstack1)&&loco instanceof SteamTrain
		if (this instanceof SteamTrain) {
			//System.out.println("is fuel? "+(FuelHandler.steamFuelLast(stack) > 0) + "return "+placeInSpecialInvent(stack,0,false));
			if (FuelHandler.steamFuelLast(stack) > 0) return placeInSpecialInvent(stack, 0, doAdd);
			if (LiquidManager.getInstance().isContainer(stack)) return placeInSpecialInvent(stack, 1, doAdd);
		}
		if (this instanceof DieselTrain) {
			//System.out.println("is diesel? "+(LiquidManager.getInstance().isDieselLocoFuel(stack)) + "return "+placeInSpecialInvent(stack,0,false));
			if (LiquidManager.getInstance().isDieselLocoFuel(stack)) return placeInSpecialInvent(stack, 0, doAdd);
		}
		if (this instanceof ElectricTrain) {
			if ((stack.getItem() == Item.itemRegistry.getObject("redstone")) || (stack.getItem() instanceof IElectricItem)) return placeInSpecialInvent(stack, 0, doAdd);
		}
		return 0;

	}
	*/

    //  Quoted out as it doesn't seem to have any use nor to be called at all.
    //	/**
    //	 * Requests items to be extracted from the inventory
    //	 *
    //	 * @param doRemove
    //	 *            If false no actual extraction may occur.
    //	 * @param from
    //	 *            Orientation the ItemStack is requested from.
    //	 * @param maxItemCount
    //	 *            Maximum amount of items to extract (spread over all returned
    //	 *            item stacks)
    //	 * @return Array of item stacks extracted from the inventory
    //	 */
    //	@Override
    //	public ItemStack[] extractItem(boolean doRemove, ForgeDirection from, int maxItemCount) {
    //		return null;
    //	}

    @Override
    public boolean attackEntityFrom(DamageSource damagesource, float i) {
        if (worldObj.isRemote) {
            return true;
        }
        if (worldObj.isRemote) {

            if (Minecraft.getMinecraft().thePlayer != null) {
                for (int i2 = 0; i2 < Minecraft.getMinecraft().thePlayer.inventory.getSizeInventory(); i2++) {
                    if (Minecraft.getMinecraft().thePlayer.inventory.getStackInSlot(i2) != null && Minecraft.getMinecraft().thePlayer.inventory.getStackInSlot(i2).getItem() instanceof ItemRemoteController && ((ItemRemoteController) Minecraft.getMinecraft().thePlayer.inventory.getStackInSlot(i2).getItem()).attachedLocomotive == this) {
                        ((ItemRemoteController) Minecraft.getMinecraft().thePlayer.inventory.getStackInSlot(i2).getItem()).attachedLocomotive = null;
                        break;
                    }
                }
            }
            return true;

        }
        if (canBeDestroyedByPlayer(damagesource)) return true;
        super.attackEntityFrom(damagesource, i);
        setRollingDirection(-getRollingDirection());
        setRollingAmplitude(10);
        setBeenAttacked();
        setDamage(getDamage() + i * 10);
        if (getDamage() > 40) {
            if (riddenByEntity != null) {
                riddenByEntity.mountEntity(this);
            }
            this.setDead();
            disconnectFromServer();
            ServerLogger.deleteWagon(this);

            ArrayList<Vec3> positions = new ArrayList<>();
            positions.add(Vec3.createVectorHelper(Math.floor(posX), Math.floor(posY), Math.floor(posZ)));


            //VBCTracking.getInstance().updateFromRS(positions);

            if (damagesource.getEntity() instanceof EntityPlayer) {
                dropCartAsItem(((EntityPlayer) damagesource.getEntity()).capabilities.isCreativeMode);
            } else {
                dropCartAsItem(false);
            }
        }
        return true;
    }

    @Override
    public void dropCartAsItem(boolean isCreative) {
        if (!itemdropped) {
            super.dropCartAsItem(isCreative);
            for (ItemStack stack : locoInvent) {
                if (stack != null) {
                    entityDropItem(stack, 0);
                }
            }
        }
    }

    /**
     * RC routing integration
     */
    @Override
    public boolean setDestination(ItemStack ticket) {
        if (ticket != null) {
            destination = getTicketDestination(ticket);
            return true;
        }
        return false;
    }

    /* IInventory implements */
    @Override
    public ItemStack getStackInSlot(int i) {
        return locoInvent[i];
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int par1) {
        if (this.locoInvent[par1] != null) {
            ItemStack var2 = this.locoInvent[par1];
            this.locoInvent[par1] = null;
            return var2;
        } else {
            return null;
        }
    }

    @Override
    public ItemStack decrStackSize(int i, int j) {
        if (locoInvent[i] != null) {
            if (locoInvent[i].stackSize <= j) {
                ItemStack itemstack = locoInvent[i];
                locoInvent[i] = null;
                return itemstack;
            }
            ItemStack itemstack1 = locoInvent[i].splitStack(j);
            if (locoInvent[i].stackSize == 0) {
                locoInvent[i] = null;
            }
            return itemstack1;

        } else {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack) {
        locoInvent[i] = itemstack;
        if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
            itemstack.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public void openInventory() {
    }

    @Override
    public void closeInventory() {
    }

    @Override
    public void markDirty() {

        if (!worldObj.isRemote) {

            this.slotsFilled = 0;

            for (int i = 0; i < getSizeInventory(); i++) {

                ItemStack itemstack = getStackInSlot(i);

                if (itemstack != null) {

                    slotsFilled++;
                }
            }

            Traincraft.slotschannel.sendToAllAround(new PacketSlotsFilled(this, slotsFilled), new TargetPoint(this.worldObj.provider.dimensionId, this.posX, this.posY, this.posZ, 150.0D));
        }
    }

    public int getAmmountOfCargo() {
        return slotsFilled;
    }

    public void recieveSlotsFilled(int amount) {
        this.slotsFilled = amount;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public ItemStack[] getInventory() {
        return locoInvent;
    }


    /**
     * For MTC's Automatic Train Operation system
     */
    public void accel(Integer desiredSpeed) {
        if (this.worldObj != null) {

            if (this.getSpeed() != desiredSpeed) {
                if ((int) this.getSpeed() <= this.speedLimit) {
                    if (this.riddenByEntity == null) {

                        double rotation = this.serverRealRotation;
                        if (rotation == 90.0) {

                            this.motionX -= 0.0020 * this.accelerate;


                        } else if (rotation == -90.0) {

                            this.motionX += 0.0020 * this.accelerate;

                        } else if (rotation == 0.0) {

                            this.motionZ += 0.0020 * this.accelerate;

                        } else if (rotation == -180.0) {

                            this.motionZ -= 0.0020 * this.accelerate;
                        } else {

                        }

                    } else {
                        int dir = MathHelper
                                .floor_double((((EntityPlayer) riddenByEntity).rotationYaw * 4F) / 360F + 0.5D) & 3;
                        if (dir == 2) {

                            this.motionZ -= 0.0020 * this.accelerate;


                        } else if (dir == 0) {

                            this.motionZ += 0.0020 * this.accelerate;

                        } else if (dir == 1) {

                            this.motionX -= 0.0020 * this.accelerate;

                        } else if (dir == 3) {

                            this.motionX += 0.0020 * this.accelerate;

                        }

                    }
                }

            }
        }
    }

    public void slow(Integer desiredSpeed) {
        if (this.getSpeed() >= desiredSpeed) {
            motionX *= brake;
            motionZ *= brake;
        }
    }

    public void stop(Vec3 signalPosition) {
        double currentDistance = Math.copySign(Vec3.createVectorHelper(this.posX, this.posY, this.posZ).distanceTo(signalPosition), 1.0D);
        double originalDistance;
        originalDistance = currentDistance;
        double slowPercentage = 3D;
        if (1.0D - currentDistance != 0.0D && originalDistance != 0.0D) {
            slowPercentage = currentDistance / this.getSpeed();
        }
       // System.out.println(slowPercentage);
        this.motionX *= slowPercentage;
        this.motionZ *= slowPercentage;

    }

    @Override
    public void receiveMessage(MTCMessage message) {
        JsonParser parser = new JsonParser();

        JsonObject thing = parser.parse(message.message.toString()).getAsJsonObject();
        //System.out.println("Got one!");
        //Todo: W-MTC
       /* if (message != null) {
            if (thing.get("funct").getAsString().equals("startlevel2")) {
                //That's actually really great, now let's get where it sent from owo
                //	System.out.println("Connected!");
                serverUUID = message.UUIDFrom;
                mtcType = 2;
                mtcStatus = thing.get("mtcStatus").getAsInt();
                isConnected = true;
                isConnecting = false;
                Traincraft.mscChannel.sendToAllAround(new PacketMTC(getEntityId(), mtcStatus, 2), new NetworkRegistry.TargetPoint(this.worldObj.provider.dimensionId, this.posX, this.posY, this.posZ, 150.0D));
                speedLimit = thing.get("speedLimit").getAsInt();
                nextSpeedLimit = thing.get("nextSpeedLimit").getAsInt();
                Traincraft.itsChannel.sendToAllAround(new PacketSetSpeed(speedLimit, 0, 0, 0, getEntityId()), new NetworkRegistry.TargetPoint(this.worldObj.provider.dimensionId, this.posX, this.posY, this.posZ, 150.0D));
                if (nextSpeedLimit != 0) {
                    xSpeedLimitChange = thing.get("nextSpeedLimitChangeX").getAsDouble();
                    ySpeedLimitChange = thing.get("nextSpeedLimitChangeY").getAsDouble();
                    zSpeedLimitChange = thing.get("nextSpeedLimitChangeZ").getAsDouble();
                }

            } else if (thing.get("funct").getAsString().equals("response")) {
                mtcType = 2;
                this.mtcStatus = thing.get("mtcStatus").getAsInt();
                Traincraft.mscChannel.sendToAllAround(new PacketMTC(getEntityId(), mtcStatus, 2), new NetworkRegistry.TargetPoint(this.worldObj.provider.dimensionId, this.posX, this.posY, this.posZ, 150.0D));
                nextSpeedLimit = thing.get("nextSpeedLimit").getAsInt();
                if (!speedGoingDown && xFromStopPoint == 0.0) {
                    speedLimit = thing.get("speedLimit").getAsInt();
                    Traincraft.itsChannel.sendToAllAround(new PacketSetSpeed(speedLimit, 0, 0, 0, getEntityId()), new NetworkRegistry.TargetPoint(this.worldObj.provider.dimensionId, this.posX, this.posY, this.posZ, 150.0D));
                }
                if (thing.get("speedChange").getAsBoolean()) {
                    xSpeedLimitChange = thing.get("nextSpeedLimitChangeX").getAsDouble();
                    ySpeedLimitChange = thing.get("nextSpeedLimitChangeY").getAsDouble();
                    zSpeedLimitChange = thing.get("nextSpeedLimitChangeZ").getAsDouble();
                    Traincraft.itnsChannel.sendToAllAround(new PacketNextSpeed( nextSpeedLimit, 0,0,0, xSpeedLimitChange, ySpeedLimitChange, zSpeedLimitChange, this.getEntityId()), new NetworkRegistry.TargetPoint(this.worldObj.provider.dimensionId, this.posX, this.posY, this.posZ, 150.0D));
                }

                if (thing.get("endSoon").getAsBoolean()) {
                    if (!(stationStop)) {
                        xFromStopPoint = thing.get("xStopPoint").getAsDouble();
                        yFromStopPoint = thing.get("yStopPoint").getAsDouble();
                        zFromStopPoint = thing.get("zStopPoint").getAsDouble();
                        Traincraft.atoSetStopPoint.sendToAllAround(new PacketATOSetStopPoint(this.getEntityId(), xFromStopPoint, yFromStopPoint, zFromStopPoint, xStationStop, yStationStop, zStationStop), new NetworkRegistry.TargetPoint(this.worldObj.provider.dimensionId, this.posX, this.posY, this.posZ, 150.0D));
                    }
                }
                if (thing.get("stationStopSoon").getAsBoolean() && !stationStop) {
                    xStationStop = thing.get("xStationStop").getAsDouble();
                    yStationStop = thing.get("yStationStop").getAsDouble();
                    zStationStop = thing.get("zStationStop").getAsDouble();


                    Traincraft.atoSetStopPoint.sendToAllAround(new PacketATOSetStopPoint(this.getEntityId(), xFromStopPoint, yFromStopPoint, zFromStopPoint, xStationStop, yStationStop, zStationStop), new NetworkRegistry.TargetPoint(this.worldObj.provider.dimensionId, this.posX, this.posY, this.posZ, 150.0D));
                } if (thing.get("atoStatus") != null) {
                    this.atoStatus = thing.get("atoStatus").getAsInt();
                    Traincraft.atoChannel.sendToAllAround(new PacketATO(this.getEntityId(), thing.get("atoStatus").getAsInt()),new NetworkRegistry.TargetPoint(this.worldObj.provider.dimensionId, this.posX, this.posY, this.posZ, 150.0D));
                }
               

            }
        }*/
    }

    @Override
    public void sendMessage(MTCMessage message) {
        //	System.out.println("Sendmessage..");
        if (Loader.isModLoaded("ComputerCraft") || Loader.isModLoaded("OpenComputers")) {
            AxisAlignedBB targetBox = AxisAlignedBB.getBoundingBox(this.posX, this.posY, this.posZ, this.posX + 2000, this.posY + 2000, this.posZ + 2000);
            List<TileEntity> allTEs = worldObj.loadedTileEntityList;
            for (TileEntity te : allTEs) {

                if (te instanceof TileInstructionRadio) {

                    TileInstructionRadio teP = (TileInstructionRadio) te;

                    if (teP.uniqueID.equals(message.UUIDTo)) {

                        //System.out.println(message.message);
                        teP.receiveMessage(message);
                    }

                }

            }
        }


    }

    public void attemptConnection(String theServerUUID) {
        //Oh, that's great! We just got the servers UUID. Now let's try connecting to it.
        if (theServerUUID != null && !serverUUID.equals(theServerUUID) && !canBePulled) {
            //	System.out.println("Oh, that's great! We just got the servers UUID. Now let's try connecting to it.");
            JsonObject sendTo = new JsonObject();
            sendTo.addProperty("funct", "attemptconnection");
            sendTo.addProperty("entityID", this.getEntityId());
            sendTo.addProperty("trainType", this.trainLevel);
            //	System.out.println(sendTo.toString());
            sendMessage(new MTCMessage(this.trainID, theServerUUID, sendTo.toString(), 0));
        }
    }

    public void disconnectFromServer() {
        JsonObject sendTo = new JsonObject();
        sendTo.addProperty("funct", "disconnect");
        sendMessage(new MTCMessage(this.trainID, serverUUID, sendTo.toString(), 0));
        this.mtcType = 1;
        this.serverUUID = "";
        isConnected = false;
    }

    public void remoteControlFromPacket(int key) {
        System.out.println("glrlr");
        System.out.println(this.serverRealRotation);
        switch (key) {
            case 1: {
                double rotation = this.serverRealRotation;
                if (rotation < 90.0 && rotation > 0 || rotation == 90.0) {

                    this.motionX -= 0.0015 * this.accelerate;


                } else if (rotation == -90.0) {

                    this.motionX += 0.0015 * this.accelerate;

                } else if (rotation < -90.00 && rotation > -180) {

                    this.motionZ -= 0.0015 * this.accelerate;
                } else if (rotation == 0) {
                    this.motionZ += 0.0015 * this.accelerate;
                } else if (rotation < 180.0 && rotation > 90.0 || rotation == 180) {
                    this.motionZ -= 0.0015 * this.accelerate;
                } else if (rotation > -180 && rotation < -90 || rotation == -180) {
                    this.motionZ -= 0.0015 * this.accelerate;
                }


                break;
            }

            case 2: {
                double rotation = this.serverRealRotation;
                if (rotation < 90.0 && rotation > 0 || rotation == 90.0) {

                    this.motionX += 0.0015 * this.accelerate;


                } else if (rotation == -90.0) {

                    this.motionX -= 0.0015 * this.accelerate;

                } else if (rotation < -90.00 && rotation > -180) {

                    this.motionZ += 0.0015 * this.accelerate;
                } else if (rotation == 0) {
                    this.motionZ -= 0.0015 * this.accelerate;
                } else if (rotation < 180.0 && rotation > 90.0 || rotation == 180) {
                    this.motionZ += 0.0015 * this.accelerate;
                } else if (rotation > -180 && rotation < -90 || rotation == -180) {
                    this.motionZ += 0.0015 * this.accelerate;
                }

                break;
            }

            case 3: {
                this.parkingBrake = !this.parkingBrake;
                break;
            }

            case 4: {
                soundHorn();
                break;
            }
        }
    }

    public void stationStopComplete() {
    }

    public void sendMTCStatusUpdate() {
        JsonObject sendingObj = new JsonObject();
        sendingObj.addProperty("funct", "update");
        sendingObj.addProperty("signalBlock", this.currentSignalBlock);
        sendingObj.addProperty("trainLevel", this.trainLevel);
        sendingObj.addProperty("trainName", this.getTrainName());
        sendingObj.addProperty("destination", this.getDestinationGUI());
        sendingObj.addProperty("posX", this.posX);
        sendingObj.addProperty("posY", this.posY);
        sendingObj.addProperty("posZ", this.posZ);
        sendingObj.addProperty("entityID", this.getEntityId());
        sendingObj.addProperty("atoStatus", this.atoStatus);
        if (this.ridingEntity != null && this.ridingEntity instanceof EntityPlayer) {
            sendingObj.addProperty("driverName", ((EntityPlayer) ridingEntity).getDisplayName());
        } else {
            sendingObj.addProperty("driverName", "Nobody");
        }
        sendingObj.addProperty("currentSpeed", (int) Math.abs(this.getSpeed()));
        sendingObj.addProperty("speedOverrideActivated", overspeedOveridePressed);
        sendMessage(new MTCMessage(this.trainID, this.serverUUID, sendingObj.toString(), 1));
    }

    public boolean trainIsWMTCSupported() {
        boolean support = false;
        int whichOneToCheck = 0;
        if (this instanceof SteamTrain) whichOneToCheck = 2;
        if (!(this instanceof SteamTrain)) whichOneToCheck = 1;
        if (this.getInventory()[whichOneToCheck] != null) {
            // System.out.println(this.getInventory()[whichOneToCheck].getItem().getClass().getName());
            if (this.getInventory()[whichOneToCheck].getItem() instanceof ItemWirelessTransmitter) {
                support = true;
            } else {
                support = false;
            }
        }
        return this instanceof EntityLocoDieselSD40 || this instanceof EntityLocoElectricBP4 || this instanceof EntityLocoDieselClass66 || this instanceof EntityLocoElectricBR185 || this instanceof EntityLocoElectricCD151 || this instanceof EntityLocoDieselDD35A || this instanceof EntityLocoElectricICE1 || this instanceof EntityLocoElectricHighSpeedZeroED || this instanceof EntityLocoElectricE103 || this instanceof EntityLocoDieselV60_DB || this instanceof EntityLocoDieselCD742 || this instanceof EntityLocoElectricVL10 || this instanceof EntityLocoElectricTramNY || this instanceof EntityLocoDieselIC4_DSB_MG || this instanceof EntityLocoDieselSD70 || this instanceof PCH120Commute || support;


    }

    public Boolean trainIsATOSupported() {
        boolean support = false;
        int whichOneToCheck = 0;
        if (this instanceof SteamTrain) whichOneToCheck = 3;
        if (!(this instanceof SteamTrain)) whichOneToCheck = 2;
        if (this.getInventory()[whichOneToCheck] != null) {
            // System.out.println(this.getInventory()[whichOneToCheck].getItem().getClass().getName());
            if (this.getInventory()[whichOneToCheck].getItem() instanceof ItemATOCard) {
                support = true;
            } else {
                support = false;
            }
        }
        return this instanceof EntityLocoElectricHighSpeedZeroED || this instanceof EntityLocoElectricTramNY || this instanceof EntityLocoElectricICE1 || this instanceof EntityLocoDieselIC4_DSB_MG || this instanceof PCH120Commute || this instanceof PCH100H || support;

    }


    public boolean trainIsRemoteControlSupported() {
        for (ItemStack item : this.getInventory()) {
            if (item != null && item.getItem() != null && item.getItem() instanceof ItemRemoteControllerModule) {
                return true;
            }
        }
        return false;
    }

    public int supportedBlinkModes() {
        return 0;
        //0 = None at all | 1 = Commander | 2 = Prime | 3 = All
    }

    public void cycleThroughBeacons() {
        switch (blinkMode) {
            case 0: {
                if (supportedBlinkModes() == 0) {
                    blinkMode = 0;
                } else {
                    blinkMode = 1;
                }
                break;
            }
            case 1: {
                if (supportedBlinkModes() == 1) {
                    blinkMode = 0;
                } else {
                    blinkMode = 2;
                }
                break;
            }
            case 2: {
                blinkMode = 0;
            }
        }
    }

    // public int blinkMode = 0; // 0 = Off | 1 = Commander | 2 = Amazon Prime
}
