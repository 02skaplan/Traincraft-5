package train.common.entity.rollingStock.passenger;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.client.render.models.ModelGS4Tail;
import train.client.render.register.TrainRenderRecord;
import train.common.Traincraft;
import train.common.api.AbstractPassengerCar;
import train.common.api.EntityRollingStock;
import train.common.api.IPassenger;
import train.common.library.Info;

public class EntityPassengerGS4_Tail extends AbstractPassengerCar {

	public EntityPassengerGS4_Tail(World world) {
		super(world);
	}

		@Override
	public void updateRiderPosition() {
		if(riddenByEntity!=null) {
			riddenByEntity.setPosition(posX, posY + getMountedYOffset() + riddenByEntity.getYOffset() + 0.17, posZ);
		}
	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 3.3F;
	}

	@Override
	public String transportCountry()
	{
		return "US";
	}

	@Override
	public void onRenderInsertRecord()
	{
		Traincraft.traincraftRegistry.RegisterRollingStockModel(new TrainRenderRecord(Info.modID,
				EntityPassengerGS4_Tail.class, new ModelGS4Tail(),
				"GS4_Tail_",
				new float[] { -0.2F, 0.025F, 0F },
				new float[] { 0F, 180F, 180F },
				new float[] {0.8f,1f,0.8f}));
	}
}