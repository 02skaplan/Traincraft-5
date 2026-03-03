package train.common.entity.rollingStock.tanker;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardTankerCar;

public class EntityTankWagon2 extends AbstractStandardTankerCar {

	public EntityTankWagon2(World world) {
		super(world);
	}

	@Override
	public void setupTextureDescription()
	{

	}

	@Override
	public String getInventoryName() {
		return "Tank cart";
	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 1.54F;
	}

	@Override
	public int getTankCapacity()
	{
		return 40000;
	}
}