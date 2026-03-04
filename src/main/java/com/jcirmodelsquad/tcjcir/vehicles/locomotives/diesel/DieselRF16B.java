package com.jcirmodelsquad.tcjcir.vehicles.locomotives.diesel;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.DieselTrain;
import train.common.api.LiquidManager;
import train.common.core.util.TraincraftUtil;
import train.common.enums.LockoutGroup;
import train.common.library.EnumSounds;
import train.common.library.EnumTrains;
import train.common.library.sounds.SoundRecord;


public class DieselRF16B extends DieselTrain {
    @Override
    public SoundRecord getSoundRecord()
    {
        return EnumSounds.DieselRF16B;
    }
    public DieselRF16B(World world) {
        super(world, LiquidManager.dieselFilter());

        InsertTexture(0, "Pennsylvania (1 Stipe)");
        InsertTexture(1, "Baltimore & Ohio");
        InsertTexture(2, "Baltimore & Ohio (Late)");
        InsertTexture(3, "New York Central");
        InsertTexture(4, "New York Central (Late)");
        InsertTexture(5, "Monongahela");
        InsertTexture(6, "Blandsville & Blankerston");
        InsertTexture(7, "Galesburg Clinton & Midland", train.common.enums.LockoutGroup.GCM); 
        InsertTexture(8, "Steampunk Rail", LockoutGroup.SPR);
    }

    @Override
    public float transportMetricHorsePower()
    {
        return 1600;
    }

    @Override
    public String transportCountry()
    {
        return "US";
    }


    @Override
    public void updateRiderPosition() { TraincraftUtil.updateRider(this, 3.15, -0.08, -0.47); }
    

    



    

    @Override
    public float getOptimalDistance(EntityMinecart cart) { return 1.08F;
    }



    @Override
    public String getInventoryName() {
        return "BLH RF-16B";
    }

    @Override
    public int getTankCapacity()
    {
        return 15000;
    }

    


}
