package train.common.entity.rollingStock.freight;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFixedFreightCar;
import train.common.enums.CargoItemFilter;

public class EntityFreightIceWagon extends AbstractStandardFixedFreightCar
{
	public EntityFreightIceWagon(World world) {
		super(world);
		cargoFilterCategory = CargoItemFilter.ICE_MATERIAL;
	}

	@Override
	public void setupTextureDescription()
	{

	}

	

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 0.75F;
	}

}