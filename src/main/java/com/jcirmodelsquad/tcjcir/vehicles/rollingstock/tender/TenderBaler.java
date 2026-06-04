package com.jcirmodelsquad.tcjcir.vehicles.rollingstock.tender;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.LiquidManager;
import train.common.api.Tender;

public class TenderBaler extends Tender  {

    public TenderBaler(World world) {
        super(world,  LiquidManager.WATER_FILTER);
       
    }

    @Override
    public String getInventoryName() {
        return "WCP Baler Tender";
    }

    @Override
    public boolean isFictional() {
        return true;
    }

    @Override
    public boolean canBeRidden() {
        return false;
    }

    @Override
    public float getOptimalDistance(EntityMinecart cart) {
        return 1.7F;
    }

    @Override
    public String transportCountry()
    {
        return "US";
    }
}