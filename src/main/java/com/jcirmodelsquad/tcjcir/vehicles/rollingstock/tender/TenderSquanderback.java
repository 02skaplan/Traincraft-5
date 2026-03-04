package com.jcirmodelsquad.tcjcir.vehicles.rollingstock.tender;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.LiquidManager;
import train.common.api.Tender;
import train.common.enums.LockoutGroup;

public class TenderSquanderback extends Tender  {

	public TenderSquanderback(World world) {
		super(world,  LiquidManager.WATER_FILTER);
		
		InsertTexture(0, "WRX (Logo)", LockoutGroup.BIDA);
		InsertTexture(1, "WRX", LockoutGroup.BIDA);
		InsertTexture(2, "CDC&S");
	}

	@Override
	public String getInventoryName() {
		return "WRX Squanderback Tender";
	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 1.45F;
	}

	@Override
	public boolean isFictional() {
		return true;
	}

	@Override
	public String transportCountry()
	{
		return "US";
	}

	@Override
	public int getTankCapacity()
	{
		return 34000;
	}
}