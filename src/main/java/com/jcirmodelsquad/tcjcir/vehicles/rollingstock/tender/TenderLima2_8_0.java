package com.jcirmodelsquad.tcjcir.vehicles.rollingstock.tender;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.LiquidManager;
import train.common.api.Tender;

public class TenderLima2_8_0 extends Tender  {

	public TenderLima2_8_0(World world) {
		super(world,  LiquidManager.WATER_FILTER);
		
		InsertTexture(0, "Generic");
		InsertTexture(1, "CDCS");
		InsertTexture(2, "A&WRR");
		InsertTexture(3, "CRIP");
	}

	@Override
	public String getInventoryName() {
		return "Lima 2-8-0 Tender";
	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 1.3F;
	}

	@Override
	public String transportCountry()
	{
		return "US";
	}

	@Override
	public int getTankCapacity()
	{
		return 26000;
	}
}