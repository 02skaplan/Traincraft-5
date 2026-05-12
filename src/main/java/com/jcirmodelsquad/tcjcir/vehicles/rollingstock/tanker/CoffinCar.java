package com.jcirmodelsquad.tcjcir.vehicles.rollingstock.tanker;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardTankerCar;


public class CoffinCar extends AbstractStandardTankerCar {


    public CoffinCar(World world) {
        super(world);
    }

    @Override
    public void setupTextureDescription()
    {

    }

    @Override
    public String getInventoryName() {
        return "Early Coffin Car";
    }

    @Override
    public float getOptimalDistance(EntityMinecart cart) {
        return 2.4F;
    }

    @Override
    public int getTankCapacity()
    {
        return 16000;
    }
}