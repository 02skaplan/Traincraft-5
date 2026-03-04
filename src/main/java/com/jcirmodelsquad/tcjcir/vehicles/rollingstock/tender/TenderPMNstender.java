package com.jcirmodelsquad.tcjcir.vehicles.rollingstock.tender;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.LiquidManager;
import train.common.api.Tender;

public class TenderPMNstender extends Tender  {

    public TenderPMNstender(World world) {
        super(world,  LiquidManager.WATER_FILTER);
       
        InsertTexture(0, "Pere Marquette");
        InsertTexture(1, "C&O Early");
        InsertTexture(2, "C&O Late");
        InsertTexture(3, "Polar Express");
    }

    @Override
    public String getInventoryName() {
        return "PM N Series Tender";
    }

    @Override
    public boolean canBeRidden() {
        return false;
    }

    @Override
    public float getOptimalDistance(EntityMinecart cart) {
        return 2.165F;
    }

    @Override
    public String transportCountry()
    {
        return "US";
    }

    @Override
    public int getTankCapacity()
    {
        return 83000;
    }
}