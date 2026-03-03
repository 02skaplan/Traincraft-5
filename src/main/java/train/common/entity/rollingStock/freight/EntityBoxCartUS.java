package train.common.entity.rollingStock.freight;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFixedFreightCar;

public class EntityBoxCartUS extends AbstractStandardFixedFreightCar
{

	public EntityBoxCartUS(World world) {
		super(world);
	}

	@Override
	public void setupTextureDescription()
	{
		InsertTexture(16, "FNCC (Oldstyle)");
		InsertTexture(17, "FNCC");
		InsertTexture(18, "RBOX");
	}

	@Override
	public double getMountedYOffset() {
		return (double) height * 0.0D - 0.30000001192092896D;
	}


	

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 1.65F;
	}
}