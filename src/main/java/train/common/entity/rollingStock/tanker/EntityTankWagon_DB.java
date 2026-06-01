package train.common.entity.rollingStock.tanker;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardTankerCar;

public class EntityTankWagon_DB extends AbstractStandardTankerCar {

	public EntityTankWagon_DB(World world) {
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
		return 1.84F;
	}
}