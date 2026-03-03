package com.jcirmodelsquad.tcjcir.vehicles.rollingstock.tender;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import train.common.api.LiquidManager;
import train.common.api.Tender;
import train.common.library.EnumTrains;

public class TenderShay3Truck extends Tender  {

	public TenderShay3Truck(World world) {
		super(world,  LiquidManager.WATER_FILTER);
		
	}

	@Override
	public String getInventoryName() {
		return "Class 3-PC-13 3-Truck Shay Tender";
	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 0.7F;
	}

	@Override
	public String transportCountry()
	{
		return "US";
	}

	@Override
	public int getTankCapacity()
	{
		return 10000;
	}
}