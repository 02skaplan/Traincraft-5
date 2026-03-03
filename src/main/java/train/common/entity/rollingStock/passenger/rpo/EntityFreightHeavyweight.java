package train.common.entity.rollingStock.passenger.rpo;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFixedFreightCar;

public class EntityFreightHeavyweight extends AbstractStandardFixedFreightCar
{
	public EntityFreightHeavyweight(World world) {
		super(world);
	}

	@Override
	public void setupTextureDescription()
	{

	}

	@Override
	public double getMountedYOffset() {
		return (double) height * 0.0D - 0.30000001192092896D;
	}

	@Override
	public String getInventoryName() {
		return "Heavyweight Mailcar";
	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 3.2F;
	}
}