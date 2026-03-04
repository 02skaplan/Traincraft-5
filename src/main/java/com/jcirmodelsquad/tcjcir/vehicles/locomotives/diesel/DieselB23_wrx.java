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

public class DieselB23_wrx extends DieselTrain {

    @Override
    public SoundRecord getSoundRecord() { return EnumSounds.DieselB23_wrx; }

    public DieselB23_wrx(World world) {
        super(world, LiquidManager.dieselFilter());

        InsertTexture(0, "WRXR Washaskan Woods RR", LockoutGroup.BIDA);
        InsertTexture(1, "WRXR Washaskan Woods RR (Extended DB)", LockoutGroup.BIDA);
    }

    @Override
    public String transportCountry()
    {
        return "US";
    }

    @Override
    public void updateRiderPosition() { TraincraftUtil.updateRider(this, 3.2, 0.25, -0.35); }

    @Override
    public float getOptimalDistance(EntityMinecart cart) { return 1.315F; }

    @Override
    public float transportMetricHorsePower()
    {
        return 2250;
    }

    @Override
    public String getInventoryName() { return "CEE B23-7 (WRX special order)"; }

    @Override
    public String transportYear() {
        return "1977-1984";
    }

    @Override
    public int getTankCapacity()
    {
        return 12000;
    }

}
