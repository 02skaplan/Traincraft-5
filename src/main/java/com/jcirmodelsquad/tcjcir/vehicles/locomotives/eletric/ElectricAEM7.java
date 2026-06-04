package com.jcirmodelsquad.tcjcir.vehicles.locomotives.eletric;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.ElectricTrain;
import train.common.core.util.TraincraftUtil;
import train.common.library.EnumSounds;
import train.common.library.sounds.SoundRecord;

public class ElectricAEM7 extends ElectricTrain {
	@Override
	public SoundRecord getSoundRecord()
	{
		return EnumSounds.ElectricAEM7;
	}
	public ElectricAEM7(World world) {
		super(world);
		InsertTexture(0, "Toast");
		InsertTexture(1, "Toastless");
	}

	@Override
	public void updateRiderPosition() {
		TraincraftUtil.updateRider(this, 1.0, 0.0);
	}

	@Override
	public String getInventoryName() {
		return "AEM Toasty";
	}

	

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 0.7F;
	}

	
}