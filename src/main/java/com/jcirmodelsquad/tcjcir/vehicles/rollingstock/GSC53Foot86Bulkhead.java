package com.jcirmodelsquad.tcjcir.vehicles.rollingstock;

import com.jcirmodelsquad.tcjcir.models.loads.ModelWrappedWoodOptimized_bulkhead_slice_For53Ft;
import com.jcirmodelsquad.tcjcir.models.loads.Modelpipeload1;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import train.common.Traincraft;
import train.common.api.Freight;
import train.common.entity.CargoManager;
import train.common.entity.CargoSpecification;
import train.common.library.GuiIDs;

public class GSC53Foot86Bulkhead extends Freight implements IInventory {
    public int freightInventorySize;
    public int numFreightSlots;
    public GSC53Foot86Bulkhead(World world) {
        super(world);
        initFreightCart();
        textureDescriptionMap.put(0, "CGW/CNW");
        textureDescriptionMap.put(1, "BN");
        textureDescriptionMap.put(2, "CB&Q");
        textureDescriptionMap.put(3, "CRIP");
        textureDescriptionMap.put(4, "DRGW");
        textureDescriptionMap.put(5, "GN");
        textureDescriptionMap.put(6, "NP");
        textureDescriptionMap.put(7, "Blandsville & Blankerston");
        textureDescriptionMap.put(8, "UP");
        setCargoManager(new CargoManager(new CargoSpecification[][] {

                { new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,//washaska wood
                        "loads/WrappedWood3_Washaska_Straps", "Wrapped Lumber (Washaska)", 0, 2.5, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/WrappedWood3_Washaska_Straps", "Wrapped Lumber (Washaska)",
                                    new CargoSpecification.RenderParameters().setOffset(0, 2.185, 0)
                                ),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/WrappedWood3_Washaska_Straps", "Wrapped Lumber (Washaska)",
                                    new CargoSpecification.RenderParameters().setOffset(0, 1.87, 0)
                                ),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/WrappedWood3_Washaska_Straps", "Wrapped Lumber (Washaska)",
                                    new CargoSpecification.RenderParameters().setOffset(0, 1.555, 0)
                                ),
                },

                { new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,//washaska wood 2
                        "loads/WrappedWood3_Washaska_Straps2", "Wrapped Lumber (Washaska)", 0, 2.5, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/WrappedWood3_Washaska_Straps2", "Wrapped Lumber (Washaska)", 0, 2.185, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/WrappedWood3_Washaska_Straps2", "Wrapped Lumber (Washaska)", 0, 1.87, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/WrappedWood3_Washaska_Straps2", "Wrapped Lumber (Washaska)", 0, 1.555, 0),},

                { new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,//tolko wood
                        "loads/WrappedWood3_Tolko_Straps", "Wrapped Lumber (Tolko)", 0, 2.5, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/WrappedWood3_Tolko_Straps", "Wrapped Lumber (Tolko)", 0, 2.185, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/WrappedWood3_Tolko_Straps", "Wrapped Lumber (Tolko)", 0, 1.87, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/WrappedWood3_Tolko_Straps", "Wrapped Lumber (Tolko)", 0, 1.555, 0),},

                { new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,//canfor wood
                        "loads/WrappedWood3_Canfor_Straps", "Wrapped Lumber (Canfor)", 0, 2.5, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/WrappedWood3_Canfor_Straps", "Wrapped Lumber (Canfor)", 0, 2.185, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/WrappedWood3_Canfor_Straps", "Wrapped Lumber (Canfor)", 0, 1.87, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/WrappedWood3_Canfor_Straps", "Wrapped Lumber (Canfor)", 0, 1.555, 0),},

                { new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,//blank wood
                        "loads/WrappedWood3_Blank_Straps", "Wrapped Lumber", 0, 2.5, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/WrappedWood3_Blank_Straps", "Wrapped Lumber", 0, 2.185, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/WrappedWood3_Blank_Straps", "Wrapped Lumber", 0, 1.87, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/WrappedWood3_Blank_Straps", "Wrapped Lumber", 0, 1.555, 0),},

                { new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,//foggy woob
                        "loads/WrappedWood3_FRLC_Straps", "Wrapped Lumber (Foggy River)", 0, 2.5, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/WrappedWood3_FRLC_Straps", "Wrapped Lumber (Foggy River)", 0, 2.185, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/WrappedWood3_FRLC_Straps", "Wrapped Lumber (Foggy River)", 0, 1.87, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/WrappedWood3_FRLC_Straps", "Wrapped Lumber (Foggy River)", 0, 1.555, 0),},

                { new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,//Thick & Thin
                        "loads/WrappedWood3_Thick_n_Thin_Straps", "Wrapped Lumber (Thick & Thin)", 0, 2.5, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/WrappedWood3_Thick_n_Thin_Straps", "Wrapped Lumber (Thick & Thin)", 0, 2.185, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/WrappedWood3_Thick_n_Thin_Straps", "Wrapped Lumber (Thick & Thin)", 0, 1.87, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/WrappedWood3_Thick_n_Thin_Straps", "Wrapped Lumber (Thick & Thin)", 0, 1.555, 0),},

                { new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,//Oak
                        "loads/UnwrappedWood_oak", "Unwrapped Lumber (Oak)", 0, 2.5, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/UnwrappedWood_oak", "Unwrapped Lumber (Oak)", 0, 2.185, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/UnwrappedWood_oak", "Unwrapped Lumber (Oak)", 0, 1.87, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/UnwrappedWood_oak", "Unwrapped Lumber (Oak)", 0, 1.555, 0),},

                { new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,//Spruce
                        "loads/UnwrappedWood_spruce", "Unwrapped Lumber (Spruce)", 0, 2.5, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/UnwrappedWood_spruce", "Unwrapped Lumber (Spruce)", 0, 2.185, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/UnwrappedWood_spruce", "Unwrapped Lumber (Spruce)", 0, 1.87, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/UnwrappedWood_spruce", "Unwrapped Lumber (Spruce)", 0, 1.555, 0),},

                { new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,//Birch
                        "loads/UnwrappedWood_birch", "Unwrapped Lumber (Birch)", 0, 2.5, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/UnwrappedWood_birch", "Unwrapped Lumber (Birch)", 0, 2.185, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/UnwrappedWood_birch", "Unwrapped Lumber (Birch)", 0, 1.87, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/UnwrappedWood_birch", "Unwrapped Lumber (Birch)", 0, 1.555, 0),},

                { new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,//Jungle
                        "loads/UnwrappedWood_jungle", "Unwrapped Lumber (Jungle)", 0, 2.5, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/UnwrappedWood_jungle", "Unwrapped Lumber (Jungle)", 0, 2.185, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/UnwrappedWood_jungle", "Unwrapped Lumber (Jungle)", 0, 1.87, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/UnwrappedWood_jungle", "Unwrapped Lumber (Jungle)", 0, 1.555, 0),},

                { new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,//Acacia
                        "loads/UnwrappedWood_acacia", "Unwrapped Lumber (Acacia)", 0, 2.5, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/UnwrappedWood_acacia", "Unwrapped Lumber (Acacia)", 0, 2.185, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/UnwrappedWood_acacia", "Unwrapped Lumber (Acacia)", 0, 1.87, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/UnwrappedWood_acacia", "Unwrapped Lumber (Acacia)", 0, 1.555, 0),},

                { new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,//Dark Oak
                        "loads/UnwrappedWood_darkoak", "Unwrapped Lumber (Dark Oak)", 0, 2.5, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/UnwrappedWood_darkoak", "Unwrapped Lumber (Dark Oak)", 0, 2.185, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/UnwrappedWood_darkoak", "Unwrapped Lumber (Dark Oak)", 0, 1.87, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/UnwrappedWood_darkoak", "Unwrapped Lumber (Dark Oak)", 0, 1.555, 0),},

                { new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,//Pine
                        "loads/UnwrappedWood_pine", "Unwrapped Lumber (Pine)", 0, 2.5, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/UnwrappedWood_pine", "Unwrapped Lumber (Pine)", 0, 2.185, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/UnwrappedWood_pine", "Unwrapped Lumber (Pine)", 0, 1.87, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/UnwrappedWood_pine", "Unwrapped Lumber (Pine)", 0, 1.555, 0),},

                { new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,//Redwood
                        "loads/UnwrappedWood_redwood", "Unwrapped Lumber (Redwood)", 0, 2.5, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/UnwrappedWood_redwood", "Unwrapped Lumber (Redwood)", 0, 2.185, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/UnwrappedWood_redwood", "Unwrapped Lumber (Redwood)", 0, 1.87, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/UnwrappedWood_redwood", "Unwrapped Lumber (Redwood)", 0, 1.555, 0),},

                { new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,//Fir
                        "loads/UnwrappedWood_fir", "Unwrapped Lumber (Fir)", 0, 2.5, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/UnwrappedWood_fir", "Unwrapped Lumber (Fir)", 0, 2.185, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/UnwrappedWood_fir", "Unwrapped Lumber (Fir)", 0, 1.87, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_bulkhead_slice_For53Ft.class,
                                "loads/UnwrappedWood_fir", "Unwrapped Lumber (Fir)", 0, 1.555, 0),},

                { new CargoSpecification(Modelpipeload1.class,
                        "loads/pipe_falling_sfx_1", "Pipes", 0, 2.5, 0) },
        }));
    }

    public GSC53Foot86Bulkhead(World world, double d, double d1, double d2){
        this(world);
        setPosition(d, d1 + yOffset, d2);
        motionX = 0.0D;
        motionY = 0.0D;
        motionZ = 0.0D;
        prevPosX = d;
        prevPosY = d1;
        prevPosZ = d2;
        initFreightCart();
    }

    public void initFreightCart() {
        numFreightSlots = 5;
        freightInventorySize = trainSpec.getCargoCapacity();
        cargoItems = new ItemStack[freightInventorySize];
    }
    @Override
    public void setDead() {
        super.setDead();
        isDead = true;
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
        super.writeEntityToNBT(nbttagcompound);
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < cargoItems.length; i++) {
            if (cargoItems[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                cargoItems[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        nbttagcompound.setTag("Items", nbttaglist);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
        super.readEntityFromNBT(nbttagcompound);

        NBTTagList nbttaglist = nbttagcompound.getTagList("Items", Constants.NBT.TAG_COMPOUND);
        cargoItems = new ItemStack[getSizeInventory()];
        for (int i = 0; i < nbttaglist.tagCount(); i++) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 0xff;
            if (j >= 0 && j < cargoItems.length) {
                cargoItems[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
    }

    @Override
    public String getInventoryName() {
        return "GSC 53' Bulkhead Flatcar (8\' 6\" Bulkhead)";
    }

    @Override
    public int getSizeInventory() {
        return freightInventorySize;
    }

    @Override
    public boolean interactFirst(EntityPlayer entityplayer) {
        if ((super.interactFirst(entityplayer))) {
            return true;
        }
        entityplayer.openGui(Traincraft.instance, GuiIDs.FREIGHT, worldObj, this.getEntityId(), -1, (int) this.posZ);
        return true;
    }

    @Override
    public boolean isStorageCart() {
        return true;
    }

    @Override
    public float getOptimalDistance(EntityMinecart cart) {
        return 2.6F;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        return true;
    }
}