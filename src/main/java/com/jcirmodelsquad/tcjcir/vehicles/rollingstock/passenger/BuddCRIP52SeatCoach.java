package com.jcirmodelsquad.tcjcir.vehicles.rollingstock.passenger;

import net.minecraft.world.World;
import train.common.api.AbstractPassengerCar;

public class BuddCRIP52SeatCoach extends AbstractPassengerCar
{

	public BuddCRIP52SeatCoach(World world)
	{
		super(world);
	}

	

	@Override
	public double getAdditionalYOffset()
	{
		return 0.55F;
	}

	@Override
	public float getOptimalLinkingDistance()
	{
		return 3.73F;
	}

	@Override
	public String transportYear() {
		return "1939-1940";
	}
}