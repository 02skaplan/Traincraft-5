package com.jcirmodelsquad.tcjcir.vehicles.rollingstock.freight;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFreightCar;
import train.common.entity.CargoManager;

public class SP_B50_AAR extends AbstractStandardFreightCar
{
    public SP_B50_AAR(World world) {
        super(world);
    }

    

    @Override
    public CargoManager setupCargoManager()
    {
        return null;
    }

    @Override
    public void setupTextureDescription()
    {
        InsertTexture(0, "B-50-29 SP As-Delivered");
        InsertTexture(1, "B-50-28 TNO As-Delivered");
        InsertTexture(2, "B-50-32 SSW As-Delivered");
        InsertTexture(3, "B-50-32 SSW Gothic Font");
        InsertTexture(4, "B-50-29 SP Gothic Font");
        InsertTexture(5, "B-50-28 TNO Gothic Font");
        InsertTexture(6, "B-50-28 Blank");
        InsertTexture(7, "Copy-Ord E&ARR AD (Ord #1)");
        InsertTexture(8, "Copy-Ord E&ARR AD (Ord #2)");
        InsertTexture(9, "Copy-Ord ROLX (early)");
        InsertTexture(10, "Copy-Ord FMSR");
        InsertTexture(11, "B-50-28RB SP");
        InsertTexture(12, "B-50-29RB SP");
        InsertTexture(13, "B-50-29RB Blank");
        InsertTexture(14, "B-50-28RB Magnolia Acres");
        InsertTexture(15, "B-50-28RB ROLX (late)");
        InsertTexture(16, "B-50-28RB UNCLX");
        InsertTexture(17, "Copy-Rebuild FNCC");
        InsertTexture(18, "Copy-Rebuild FNCC (w/ herald)");
        InsertTexture(19, "Copy-Rebuild LRR");

    }

    @Override
    public String getInventoryName() {
        return "SP B-50-28 /-29 /-31 Boxcar";
    }

    @Override
    public float getOptimalDistance(EntityMinecart cart) {
        return 2.1F;
    }
}