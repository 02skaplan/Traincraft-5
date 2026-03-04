package com.jcirmodelsquad.tcjcir.vehicles.locomotives;

import com.jcirmodelsquad.tcjcir.models.detailkits.ModelRotaryPlowCoupler;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractRotarySnowPlow;
import train.common.core.util.TraincraftUtil;
import train.common.entity.CargoManager;
import train.common.entity.CargoSpecification;
import train.common.enums.LockoutGroup;
import train.common.library.EnumSounds;
import train.common.library.sounds.SoundRecord;


public class RotaryPlow1 extends AbstractRotarySnowPlow {
    @Override
    public SoundRecord getSoundRecord()
    {
        return EnumSounds.ROTARYPLOW;
    }
    public RotaryPlow1(World world) {
        super(world);
        InsertTexture(0, "Generic Black (With Wings)");
        InsertTexture(1, "SPR", LockoutGroup.SPR);

        //cargomanger for detachable front coupler
        setCargoManager(new CargoManager(new CargoSpecification[][] {
                { new CargoSpecification(ModelRotaryPlowCoupler.class, "trains/RotaryPlow_Coupler_Black",
                        "Front Coupler Attachment (Black)", 0, 3, 0)},
        }));
    }

    @Override
    public float transportMetricHorsePower()
    {
        return 1750;
    }

    @Override
    public void updateRiderPosition() { TraincraftUtil.updateRider(this, 1.9, 0.45, -0.25); }

    @Override
    public String transportYear() {
        return "1960s-";
    }

    @Override
    public boolean isFictional(){
        return true;
    }

    @Override
    public String getInventoryName() {
        return "Rotary Snowplow";
    }

    @Override
    public float getOptimalDistance(EntityMinecart cart) {
        return 1.3F;
    }
}
