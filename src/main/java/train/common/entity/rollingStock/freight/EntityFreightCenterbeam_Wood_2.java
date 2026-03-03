package train.common.entity.rollingStock.freight;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFixedFreightCar;

import static train.common.enums.CargoItemFilter.WOOD_PRODUCTS;

public class EntityFreightCenterbeam_Wood_2 extends AbstractStandardFixedFreightCar
{
	public EntityFreightCenterbeam_Wood_2(World world) {
		super(world);
		cargoFilterCategory = WOOD_PRODUCTS; // Wood Products
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