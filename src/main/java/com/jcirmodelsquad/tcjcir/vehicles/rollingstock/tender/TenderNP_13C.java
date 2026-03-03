package com.jcirmodelsquad.tcjcir.vehicles.rollingstock.tender;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import train.common.api.LiquidManager;
import train.common.api.Tender;
import train.common.library.EnumTrains;

public class TenderNP_13C extends Tender  {

	public TenderNP_13C(World world) {
		super(world,  LiquidManager.WATER_FILTER);
		
	}
	
	@Override
	public String getInventoryName() {
		return "NP 13C Tender";
	}

	@Override
	public String transportCountry()
	{
		return "US";
	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 1.3F;
	}

	@Override
	public int getTankCapacity()
	{
		return 31000;
	}
}