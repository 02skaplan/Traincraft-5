package com.jcirmodelsquad.tcjcir.vehicles.rollingstock.freight;

import com.jcirmodelsquad.tcjcir.models.loads.ModelAAR70TonHopperLoad;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFreightCar;
import train.common.entity.CargoManager;
import train.common.entity.CargoSpecification;
import train.common.enums.LockoutGroup;

public class AAR70TonHopper extends AbstractStandardFreightCar
{
    public AAR70TonHopper(World world) {
        super(world);
    }

    @Override
    public CargoManager setupCargoManager()
    {
        return new CargoManager(new CargoSpecification[][] {
                { new CargoSpecification(ModelAAR70TonHopperLoad.class,
                        "loads/AAR_70Ton_hopper_load_coal", "Coal", 0, 3.05, 0) },
                { new CargoSpecification(ModelAAR70TonHopperLoad.class,
                        "loads/AAR_70Ton_hopper_load_gravel", "Gravel", 0, 3.05, 0) }
        });
    }

    @Override
    public void setupTextureDescription()
    {
        InsertTexture(0, "NKP");
        InsertTexture(1, "GCM", LockoutGroup.GCM);
        InsertTexture(2, "AGW");
        InsertTexture(3, "TNO As-Delivered");
        InsertTexture(4, "TNO Later");
    }


    @Override
    public float getOptimalDistance(EntityMinecart cart) {
        return 2.275F;
    }

}
