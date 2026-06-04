package com.jcirmodelsquad.tcjcir.vehicles.locomotives.eletric;
//package train.common.entity.rollingStock;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.ElectricTrain;
import train.common.library.EnumSounds;
import train.common.library.sounds.SoundRecord;

public class Thanos extends ElectricTrain {
    @Override
    public SoundRecord getSoundRecord()
    {
        return EnumSounds.Thanos;
    }
    public Thanos(World world) {
        super(world);
    }

    @Override
    public void updateRiderPosition() {
        if(riddenByEntity==null){return;}
        riddenByEntity.setPosition(posX, posY + getMountedYOffset() + riddenByEntity.getYOffset() + 0.10F, posZ);
    }




    @Override
    public String getInventoryName() {
        return "Thanos Cart";
    }

    
    @Override
    public float getOptimalDistance(EntityMinecart cart) {
        return 0.53F;
    }

    @Override
    public boolean isFictional()
    {
        return true;
    }
}