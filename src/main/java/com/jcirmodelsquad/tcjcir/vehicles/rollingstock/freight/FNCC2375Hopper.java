package com.jcirmodelsquad.tcjcir.vehicles.rollingstock.freight;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFreightCar;
import train.common.entity.CargoManager;

public class FNCC2375Hopper extends AbstractStandardFreightCar
{
	public FNCC2375Hopper(World world) {
		super(world);
	}

	@Override
	public CargoManager setupCargoManager()
	{
		return null;
	}

	@Override
	public void setupTextureDescription()
	{
		InsertTexture(0, "FNCC (Plain)");
		InsertTexture(1, "FNCC (Big Block Text)");
		InsertTexture(2, "FNCC (Carbon Black)");
		InsertTexture(3, "Blandsville & Blankerston");
		InsertTexture(4, "UNCLX");
		InsertTexture(5, "UNCLX");
		InsertTexture(6, "Magnolia");
		InsertTexture(7, "RUVX");
		InsertTexture(8, "RUVX");
		InsertTexture(9, "RUVX");
		InsertTexture(10, "CBRR");
		InsertTexture(11, "DLMR");
		InsertTexture(12, "hamburger helper rayroa");
		InsertTexture(13, "AA");
		InsertTexture(14, "AA (Ferry in the Fog)");
		InsertTexture(15, "SPB");
		InsertTexture(16, "GLNT");
		InsertTexture(17, "AGW");
		InsertTexture(18, "AGW 2");
	}

	@Override
	public String getInventoryName() {
		return "FNCC 2375 Cu Foot Hopper";
	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 2.3F;
	}
}