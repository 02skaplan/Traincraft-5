package com.jcirmodelsquad.tcjcir.vehicles.rollingstock.tanker;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardTankerCar;


public class DOT11120600 extends AbstractStandardTankerCar {

    public DOT11120600(World world) {
        super(world);
    }

    @Override
    public void setupTextureDescription()
    {
        InsertTexture(0, "Generic");
        InsertTexture(1, "PROX");
        InsertTexture(2, "CCOX/ECYX");
        InsertTexture(3, "CCOX/ECYX");
        InsertTexture(4, "AMPX");
    }

    @Override
    public String getInventoryName() {
        return "20,600 Gallon Tank car";
    }

    @Override
    public int getTankCapacity()
    {
        return 78000;
    }

    @Override
    public float getOptimalDistance(EntityMinecart cart) {
        return 2.125F;
    }

}