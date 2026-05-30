package com.jcirmodelsquad.tcjcir.vehicles.locomotives.diesel;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.DieselTrain;
import train.common.api.LiquidManager;
import train.common.core.util.TraincraftUtil;
import train.common.library.EnumSounds;
import train.common.library.sounds.SoundRecord;


public class DieselSD70M extends DieselTrain {
    @Override
    public SoundRecord getSoundRecord()
    {
        return EnumSounds.DieselSD70M;
    }
    public DieselSD70M(World world) {
        super(world, LiquidManager.dieselFilter());
        
        InsertTexture(0, "Blandsville & Blankerston");
        InsertTexture(1, "");
        InsertTexture(2, "");

    }

    @Override
    public float transportMetricHorsePower()
    {
        return 4000;
    }

    @Override
    public String transportCountry()
    {
        return "US";
    }
        
    @Override
    public void updateRiderPosition() { TraincraftUtil.updateRider(this, 4.15, 0.3, -0.35); }

    @Override
    public float getOptimalDistance(EntityMinecart cart) { return 1.45F; }

    @Override
    public String transportYear() {
        return "1992-2007";
    }

    @Override
    public String getInventoryName() {
        return "EMD SD70M";
    }

    @Override
    public int getTankCapacity()
    {
        return 20000;
    }

}
