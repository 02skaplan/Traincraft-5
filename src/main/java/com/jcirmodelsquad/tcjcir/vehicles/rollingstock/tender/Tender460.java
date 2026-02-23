package com.jcirmodelsquad.tcjcir.vehicles.rollingstock.tender;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import train.common.api.LiquidManager;
import train.common.api.Tender;
import train.common.library.EnumTrains;

public class Tender460 extends Tender  {

	public Tender460(World world) {
		super(world,  LiquidManager.WATER_FILTER);
		
		InsertTexture(0, "Blank");
		InsertTexture(1, "CDC&S");
		InsertTexture(2, "RI");
		InsertTexture(3, "%Lemo");
		InsertTexture(4, "GCM", train.common.enums.LockoutGroup.GCM); 
	}
	
	@Override
	public String getInventoryName() {
		return "ALCo 4-6-0 Tender";
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
}