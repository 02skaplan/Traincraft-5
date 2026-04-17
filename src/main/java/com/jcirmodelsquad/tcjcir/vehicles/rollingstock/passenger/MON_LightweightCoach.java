package com.jcirmodelsquad.tcjcir.vehicles.rollingstock.passenger;

import net.minecraft.world.World;
import train.common.api.AbstractPassengerCar;

public class MON_LightweightCoach extends AbstractPassengerCar
{
	public MON_LightweightCoach(World world) {
		super(world);
		InsertTexture(0, "MON (21-23, as built)");
		InsertTexture(1, "MON (24-26, as built)");
		InsertTexture(2, "MON (21-23)");
		InsertTexture(3, "MON (24-26)");
		InsertTexture(4, "MON (21-23, simplified passenger scheme)");
		InsertTexture(5, "MON (24-26, simplified passenger scheme)");
		InsertTexture(6, "MON (25, freight scheme)");
		InsertTexture(7, "MON (26, freight scheme)");
		InsertTexture(8, "MON (high density coach 43, simplified passenger scheme)");
		InsertTexture(9, "MON (high density coach 43, freight scheme, early)");
		InsertTexture(10, "MON (high density coach 43, freight scheme, late)");
		InsertTexture(11, "MON (high density coach 44)");
		InsertTexture(12, "MON (high density coach 45)");
	}

	@Override
	public double getAdditionalYOffset()
	{
		return -0.1F;
	}

	@Override
	public float getOptimalLinkingDistance()
	{
		return 3.97F;
	}

	@Override
	public String transportCountry()
	{
		return "US";
	}

	@Override
	public String transportYear() {
		return "(Rebuilt) 1947";
	}
}