package train.common.entity.rollingStock.freight;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.inventory.IInventory;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFixedFreightCar;

public class EntityFreightCartSmall extends AbstractStandardFixedFreightCar implements IInventory {
	public EntityFreightCartSmall(World world) {
		super(world);
	}

	@Override
	public void setupTextureDescription()
	{

	}

	
	
	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 1.45F;
	}
}