package com.jcirmodelsquad.tcjcir.vehicles.rollingstock.tender;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import train.common.api.LiquidManager;
import train.common.api.Tender;
import train.common.library.EnumTrains;

public class EntityTenderNS3700 extends Tender{

	public EntityTenderNS3700(World world) {
		super(world,  LiquidManager.WATER_FILTER);

		InsertTexture(0, "NS 3737");
	}

	@Override
	public String getInventoryName() {
		return "NS 3700 Tender";
	}

	@Override
	public boolean canBeRidden() {
		return false;
	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 1.1F;
	}

}