package com.jcirmodelsquad.tcjcir.vehicles.locomotives;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractTankSlug;
import train.common.enums.LockoutGroup;
import train.common.library.EnumTrains;

public class SlugMA extends AbstractTankSlug
{
	public SlugMA(World world) {
		super(world);
		InsertTexture(0, "ANE SMA-1", LockoutGroup.ANE);
		InsertTexture(1, "ANE SMA-2", LockoutGroup.ANE);
		InsertTexture(2, "Magnolia", LockoutGroup.MAG);
		InsertTexture(3, "CSXT");
		InsertTexture(4, "ATSF");
	}

	@Override
	public String getInventoryName() {
		return "Slug";
	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 2.5F;
	}

	@Override
	public int getTankCapacity()
	{
		return 18000;
	}
}