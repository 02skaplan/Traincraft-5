package train.common.entity.rollingStock.passenger;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.client.render.register.TrainRenderRecord;
import train.common.Traincraft;
import train.common.api.AbstractPassengerCar;
import train.common.api.EntityRollingStock;
import train.common.api.IPassenger;
import train.common.library.Info;

public class EntityPassenger_2class_DB extends AbstractPassengerCar {

	public EntityPassenger_2class_DB(World world) {
		super(world);
	}

	@Override
	public void updateRiderPosition() {
		riddenByEntity.setPosition(posX, posY + getMountedYOffset() + riddenByEntity.getYOffset() + 0.2, posZ);
	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 1.9F;
	}

	@Override
	public void onRenderInsertRecord()
	{
		Traincraft.traincraftRegistry.RegisterRollingStockModel(new TrainRenderRecord(Info.modID,
				EntityPassenger_2class_DB.class, new train.client.render.models.ModelPassenger_2class_DB(),
				"passenger_2class_DB_",
				new float[] { 0.0F, -0.44F, 0.0F },
				null,
				null));
	}
}