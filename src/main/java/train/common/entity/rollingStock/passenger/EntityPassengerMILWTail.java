package train.common.entity.rollingStock.passenger;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.client.render.models.ModelMILWTail;
import train.client.render.register.TrainRenderRecord;
import train.common.Traincraft;
import train.common.api.AbstractPassengerCar;
import train.common.api.EntityRollingStock;
import train.common.api.IPassenger;
import train.common.core.util.TraincraftUtil;
import train.common.library.Info;

public class EntityPassengerMILWTail extends AbstractPassengerCar {

	public EntityPassengerMILWTail(World world) {
		super(world);
	}

		@Override
	public void updateRiderPosition() {
		TraincraftUtil.updateRider(this, -0.45, 0);
	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 3F;
	}

	@Override
	public void onRenderInsertRecord()
	{
		Traincraft.traincraftRegistry.RegisterRollingStockModel(new TrainRenderRecord(Info.modID,
				EntityPassengerMILWTail.class, new ModelMILWTail(),
				"milw_passenger_tail",
				new float[] { 0.1F, 0.1F, 0F },
				new float[] { 0F, 180F, 180F },
				new float[] {0.9f,0.9f,0.9f}));
	}
}