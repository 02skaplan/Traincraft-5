package com.jcirmodelsquad.tcjcir.vehicles.rollingstock.passenger;

import net.minecraft.world.World;
import train.common.api.AbstractPassengerCar;
import train.common.enums.LockoutGroup;

public class ACF_SP_98seatcoach extends AbstractPassengerCar
{
	public ACF_SP_98seatcoach(World world) {
		super(world);
		InsertTexture(0, "SP As-Delivered");
		InsertTexture(1, "SP Modified w/ AC");
		InsertTexture(2, "SP Sunset Scheme");
		InsertTexture(3, "SP Two-Tone Grey");
		InsertTexture(4, "NEP As-Delivered", LockoutGroup.CUBED);
		InsertTexture(5, "NEP As-Modified", LockoutGroup.CUBED);
		InsertTexture(6, "NEP As-Modernized", LockoutGroup.CUBED);
		InsertTexture(7, "Blank As-Delivered");
		InsertTexture(8, "Blank As-Modified");
		InsertTexture(9, "DLMR (Early)", LockoutGroup.DLMR);
		InsertTexture(10, "DLMR (Later)", LockoutGroup.DLMR);
	}



	@Override
	public double getAdditionalYOffset()
	{
		return -0.1F;
	}

	@Override
	public float getOptimalLinkingDistance()
	{
		return 3.70F;
	}
}