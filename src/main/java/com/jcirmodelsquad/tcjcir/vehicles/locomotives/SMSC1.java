package com.jcirmodelsquad.tcjcir.vehicles.locomotives;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractTankSlug;
import train.common.enums.LockoutGroup;
import train.common.library.EnumTrains;

public class SMSC1 extends AbstractTankSlug
{
    public SMSC1(World world)
    {
        super(world);
        InsertTexture(0, "FNCC S99 (KIT-L)", LockoutGroup.FNCC);
    }

    @Override
    public String getInventoryName() {
        return "Slug Motor System, C-C, Type 1";
    }

    @Override
    public float getOptimalDistance(EntityMinecart cart) {
        return 3.0F;
    }

    @Override
    public boolean isFictional(){
        return true;
    }

    @Override
    public int getTankCapacity()
    {
        return 16000;
    }
}