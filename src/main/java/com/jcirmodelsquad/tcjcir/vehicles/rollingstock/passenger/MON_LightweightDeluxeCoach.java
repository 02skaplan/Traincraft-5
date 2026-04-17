package com.jcirmodelsquad.tcjcir.vehicles.rollingstock.passenger;

import net.minecraft.world.World;
import train.common.api.AbstractPassengerCar;

public class MON_LightweightDeluxeCoach extends AbstractPassengerCar
{
	public MON_LightweightDeluxeCoach(World world) {
		super(world);
		InsertTexture(0, "MON (as built)");
		InsertTexture(1, "MON (simplified passenger scheme)");
		InsertTexture(2, "MON (34, freight scheme)");
		InsertTexture(3, "MON (high density coach 42, simplified passenger scheme)");
		InsertTexture(4, "MON (high density coach 42, freight scheme)");
		InsertTexture(5, "MON (high density coach 46)");
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
		return "(Rebuilt) 1948";
	}
}