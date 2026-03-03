package train.common.entity.rollingStock.tender;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import train.common.api.LiquidManager;
import train.common.api.Tender;

public class EntityTenderU57 extends Tender
{
	public EntityTenderU57(World world) {
		super(world, LiquidManager.WATER_FILTER);
		
	}

	@Override
	public String getInventoryName() {
		return "U57's Tender";
	}@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 1.85F;
	}

	@Override
	public int getTankCapacity()
	{
		return 10000;
	}
}