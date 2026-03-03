package com.jcirmodelsquad.tcjcir.vehicles.rollingstock.tanker;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardTankerCar;
import train.common.library.EnumTrains;

public class GATC10000 extends AbstractStandardTankerCar {

    public GATC10000(World world) {
        super(world, EnumTrains.gatc10000.getTankCapacity());
    }

    @Override
    public void setupTextureDescription()
    {
        InsertTexture(0, "GATC");
        InsertTexture(1, "GATC (Early)");
        InsertTexture(2, "GATC");
        InsertTexture(3, "ARR");
        InsertTexture(4, "HPCX");
        InsertTexture(5, "GATC (Union Oil)");
        InsertTexture(6, "GATC (Allied Chemical)");
    }

    @Override
    public String getInventoryName() {
        return "GATC 10,000 Gallon Tank car";
    }

    @Override
    public int getTankCapacity()
    {
        return 38000;
    }

    @Override
    public float getOptimalDistance(EntityMinecart cart) {
        return 2.1F;
    }
}