package com.jcirmodelsquad.tcjcir.vehicles.rollingstock.tender;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import train.common.api.LiquidManager;
import train.common.api.Tender;
import train.common.library.EnumTrains;

public class TenderDeseret extends Tender  {

    public TenderDeseret(World world) {
        super(world,  LiquidManager.WATER_FILTER);
       
    }

    @Override
    public String getInventoryName() {
        return "WP Deseret Tender";
    }

    @Override
    public String transportCountry()
    {
        return "US";
    }

    @Override
    public boolean canBeRidden() {
        return false;
    }

    @Override
    public float getOptimalDistance(EntityMinecart cart) {
        return 1.8F;
    }

    @Override
    public int getTankCapacity()
    {
        return 57000;
    }

}