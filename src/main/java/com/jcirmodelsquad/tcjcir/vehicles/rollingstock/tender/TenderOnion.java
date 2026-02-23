package com.jcirmodelsquad.tcjcir.vehicles.rollingstock.tender;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import train.common.api.LiquidManager;
import train.common.api.Tender;
import train.common.library.EnumTrains;

public class TenderOnion extends Tender  {

	public TenderOnion(World world) {
		super(world,  LiquidManager.WATER_FILTER);
		
	}

	@Override
	public String getInventoryName() {
		return "Onion's Tender";
	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 1.0F;
	}

	@Override
	public String transportCountry()
	{
		return "Moon";
	}
}