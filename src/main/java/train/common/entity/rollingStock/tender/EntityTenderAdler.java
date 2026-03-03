/*******************************************************************************
 * Copyright (c) 2014 Mrbrutal. All rights reserved.
 * 
 * @name Traincraft
 * @author Mrbrutal
 ******************************************************************************/

package train.common.entity.rollingStock.tender;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import train.common.api.LiquidManager;
import train.common.api.Tender;
import train.common.library.EnumHeritageTrainsLegacy;

public class EntityTenderAdler extends Tender
{
	public EntityTenderAdler(World world)
	{
		super(world, LiquidManager.WATER_FILTER);
	}

	@Override
	public String getInventoryName() {
		return "Adler's Tender";
	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 0.55F;
	}

	@Override
	public int getTankCapacity()
	{
		return 4000;
	}
}
