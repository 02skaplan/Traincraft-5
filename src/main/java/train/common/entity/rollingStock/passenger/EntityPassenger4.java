package train.common.entity.rollingStock.passenger;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.common.api.AbstractPassengerCar;
import train.common.api.EntityRollingStock;
import train.common.api.IPassenger;

public class EntityPassenger4 extends AbstractPassengerCar {

	public EntityPassenger4(World world) {
		super(world);
	}
	@Override
	public void updateRiderPosition() {
		riddenByEntity.setPosition(posX, posY + getMountedYOffset() + riddenByEntity.getYOffset(), posZ);
	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 1.5F;
	}
}