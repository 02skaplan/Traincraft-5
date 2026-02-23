package train.common.entity.rollingStock.freight;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFixedFreightCar;
import train.common.enums.CargoItemFilter;

public class EntityFlatCarRails_DB extends AbstractStandardFixedFreightCar
{
	public EntityFlatCarRails_DB(World world)
	{
		super(world);
		cargoFilterCategory = CargoItemFilter.ASSEMBLED_TRAIN_TRACK;
	}

	@Override
	public void setupTextureDescription()
	{

	}

	@Override
	public String getInventoryName()
	{
		return "Flat Cart";
	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 1.84F;
	}
}