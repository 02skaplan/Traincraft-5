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

public class DieselB23 extends DieselTrain {

    @Override
    public SoundRecord getSoundRecord() { return EnumSounds.DieselB23; }

    public DieselB23(World world) {
        super(world, LiquidManager.dieselFilter());
        
        InsertTexture(0, "Franklin Industrial Minerals (FIMX Early)");
        InsertTexture(1, "Southern Pacific (Early)");
        InsertTexture(2, "Southern Pacific (Late)");
        InsertTexture(3, "FNCC (KIT-L, 1st Order)", LockoutGroup.FNCC);
        InsertTexture(4, "FNCC (KIT-L, 2nd Order)", LockoutGroup.FNCC);
        InsertTexture(5, "Blandsville & Blankerston");
        InsertTexture(6, "Western Pacific");
        InsertTexture(7, "Western Pacific (Post 90s)");
        InsertTexture(8, "CSXT (YN1)");
        InsertTexture(9, "Conrail");
        InsertTexture(10, "Norfolk Southern");
        InsertTexture(11, "Union Pacific (CCRCL)");
        InsertTexture(12, "Staff Storage Mountain Co.");
        InsertTexture(13, "USSC");
        InsertTexture(14, "CSXT (YN2)");
        InsertTexture(15, "Union Pacific");
        InsertTexture(16, "Providence & Worcester");
        InsertTexture(17, "Fox Union Rail Resources (FURRX)");
        InsertTexture(18, "Camas Prairie Railnet");
        InsertTexture(19, "Finger Lakes");
        InsertTexture(20, "Finger Lakes (Ex Camas Prairie)");
        InsertTexture(21, "AOK");
        InsertTexture(22, "ADT (Ex UP)", LockoutGroup.ADT);
        InsertTexture(23, "ADT (Ex NS)", LockoutGroup.ADT);
        InsertTexture(24, "ADT", LockoutGroup.ADT);
        InsertTexture(25, "BNSF H1 (Ex ATSF)");
        InsertTexture(26, "Atlas & Red Sands Railroad", LockoutGroup.BIDA);
        InsertTexture(27, "Magnolia", LockoutGroup.MAG);
        InsertTexture(28, "CNRC 1901 & 1903", LockoutGroup.CNRC);
        InsertTexture(29, "CNRC 1902", LockoutGroup.CNRC);
        InsertTexture(30, "CSXT (Stealth)");
        InsertTexture(31, "CSXT (Bluedown)");
        InsertTexture(32, "Seaboard");
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
    public String getInventoryName() { return "GE B23-7"; }

    @Override
    public String transportYear() {
        return "1977-1984";
    }

    @Override
    public int getTankCapacity()
    {
        return 20000;
    }

}
