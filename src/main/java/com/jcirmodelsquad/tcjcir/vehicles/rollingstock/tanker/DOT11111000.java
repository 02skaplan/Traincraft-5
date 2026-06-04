package com.jcirmodelsquad.tcjcir.vehicles.rollingstock.tanker;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardTankerCar;


public class DOT11111000 extends AbstractStandardTankerCar {

    public DOT11111000(World world) {
        super(world);
    }

    @Override
    public void setupTextureDescription()
    {
        InsertTexture(0, "Generic");
        InsertTexture(1, "DOW");
        InsertTexture(2, "Generic Grey");
    }

    @Override
    public String getInventoryName() {
        return "11,000 Gallon Tank car";
    }

    @Override
    public float getOptimalDistance(EntityMinecart cart) {
        return 1.9375F;
    }
}