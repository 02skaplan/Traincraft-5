package com.jcirmodelsquad.tcjcir.vehicles.locomotives.diesel;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.DieselTrain;
import train.common.api.LiquidManager;
import train.common.core.util.TraincraftUtil;
import train.common.library.EnumSounds;
import train.common.library.sounds.SoundRecord;


@Deprecated
public class DieselCF7_H_Angle extends DieselTrain {
    @Override
    public SoundRecord getSoundRecord()
    {
        return EnumSounds.CF7_H_Angle;
    }

    public DieselCF7_H_Angle(World world) {
        super(world, LiquidManager.dieselFilter());

        //InsertTexture(0, "Denver SUCK MY FUCKIN BALLS cock sucker island (stupid) mofo Volkswagon 1083 deez nuts express");

    }

    @Override
    public String transportCountry()
    {
        return "US";
    }


    @Override
    public void updateRiderPosition() { TraincraftUtil.updateRider(this, 1.9, 0); }




    



    @Override
    public float getOptimalDistance(EntityMinecart cart) { return 0.95F;
    }

    @Override
    public String transportYear() {
        return "1969-1978";
    }

    @Override
    public String getInventoryName() {
        return "CF7 Anglecab (Heritage Model)";
    }

    
    

}
