package train.common.entity.rollingStock.tanker;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardTankerCar;

public class EntityTankLava extends AbstractStandardTankerCar {

	public EntityTankLava(World world) {
		super(world);
		isHighTemperature = true;
	}

	@Override
	public void setupTextureDescription()
	{

	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		checkInvent(cargoItems[0]);
		if (worldObj.isRemote)
			return;
		if (getAmount() > 0)
			setColor(getColorFromString("Full"));
		if (getAmount() <= 0)
			setColor(getColorFromString("Empty"));
	}
	
	@Override
	public String getInventoryName() {
		return "Lava Tank cart";
	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 1.85F;
	}
}