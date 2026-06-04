package com.jcirmodelsquad.tcjcir.vehicles.locomotives.diesel;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.DieselTrain;
import train.common.api.LiquidManager;
import train.common.core.util.TraincraftUtil;
import train.common.enums.LockoutGroup;
import train.common.library.EnumSounds;

import train.common.library.sounds.SoundRecord;


public class DieselFBFDL extends DieselTrain {
    @Override
    public SoundRecord getSoundRecord()
    {
        return EnumSounds.DieselFBFDL;
    }
    public DieselFBFDL(World world) {
        super(world, LiquidManager.dieselFilter());
        
        InsertTexture(0, "FMSR", LockoutGroup.FMSR);
        InsertTexture(1, "Blandsville & Blankerston (Early FBFDL)");
        InsertTexture(2, "Blandsville & Blankerston (Late FBFDL)");
        InsertTexture(3, "");
    }

    @Override
    public String transportCountry()
    {
        return "US";
    }

    @Override
    public boolean isFictional(){
        return true;
    }

    @Override
    public String transportYear() {
        return "1977-1978";
    }

    @Override
    public void updateRiderPosition() { TraincraftUtil.updateRider(this, 2.85, -0.08, -0.0); }

    @Override
    public float getOptimalDistance(EntityMinecart cart) { return 0.96F;
    }

    @Override
    public String getInventoryName() {
        return "CEE FB-FDL";
    }
}
