package com.jcirmodelsquad.tcjcir.vehicles.rollingstock.freight;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFreightCar;
import train.common.entity.CargoManager;
import train.common.enums.LockoutGroup;

public class PFEr_40_23Reefer extends AbstractStandardFreightCar
{
    public PFEr_40_23Reefer(World world) {
        super(world);

    }
@Override
public void setupTextureDescription()
{
    InsertTexture(0, "PFE (SP First)");
    InsertTexture(1, "PFE (UP First)");
    InsertTexture(2, "Northern Pacific");
    InsertTexture(3, "DCLX");
    InsertTexture(4, "Steampunk Rail", LockoutGroup.SPR);
    InsertTexture(5, "Not the SCP Foundation", LockoutGroup.SCP);
}
    

    @Override
    public String getInventoryName() {
        return "PFE R-40-23 Ice Reefer";
    }

    @Override
    public float getOptimalDistance(EntityMinecart cart) {
        return 2.1F;
    }
    @Override
    public CargoManager setupCargoManager()
    {
        return null;
    }
}