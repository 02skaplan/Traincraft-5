package train.common.entity.rollingStock.freight;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFixedFreightCar;
import train.common.enums.CargoItemFilter;

public class EntityFreightCenterbeam_Wood_1 extends AbstractStandardFixedFreightCar
{
	public EntityFreightCenterbeam_Wood_1(World world)
	{
		super(world);
		cargoFilterCategory = CargoItemFilter.WOOD_PRODUCTS; // Wood Products
	}

	@Override
	public void setupTextureDescription()
	{

	}

	

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 1.6F;
	}
}