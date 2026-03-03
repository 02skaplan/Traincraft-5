package train.common.entity.rollingStock.passenger;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.client.render.models.ModelTramNY;
import train.client.render.register.TrainRenderRecord;
import train.common.Traincraft;
import train.common.api.AbstractPassengerCar;
import train.common.api.EntityRollingStock;
import train.common.api.IPassenger;
import train.common.library.Info;

public class EntityPassengerTramNY extends AbstractPassengerCar {

	public EntityPassengerTramNY(World world) {
		super(world);
	}


	@Override
	public void updateRiderPosition() {
		riddenByEntity.setPosition(posX, posY + getMountedYOffset() + riddenByEntity.getYOffset()+0.1F, posZ);
	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 2.2F;
	}

	@Override
	public void onRenderInsertRecord()
	{
		Traincraft.traincraftRegistry.RegisterRollingStockModel(new TrainRenderRecord(Info.modID,
				EntityPassengerTramNY.class, new ModelTramNY(),
				"locoTramNY_",
				new float[] { 0F, -0.44F, 0.0F },
				null,
				null));
	}
}