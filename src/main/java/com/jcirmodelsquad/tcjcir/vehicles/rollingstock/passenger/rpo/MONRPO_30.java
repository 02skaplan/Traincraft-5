package com.jcirmodelsquad.tcjcir.vehicles.rollingstock.passenger.rpo;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFixedFreightCar;

public class MONRPO_30 extends AbstractStandardFixedFreightCar
{
	public MONRPO_30(World world) {
		super(world);
	}

	

	@Override
	public void setupTextureDescription()
	{
		InsertTexture(0, "MON (as built)");
		InsertTexture(1, "MON");
		InsertTexture(2, "MON (simplified passenger scheme)");
		InsertTexture(3, "MON (freight scheme)");
	}

	@Override
	public String getInventoryName() {
		return "Monon RPO (30' mail section)";
	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 3.97F;
	}

	@Override
	public String transportYear() {
		return "(Rebuilt) 1947";
	}
}