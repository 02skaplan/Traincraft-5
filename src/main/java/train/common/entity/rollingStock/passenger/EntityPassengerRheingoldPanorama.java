package train.common.entity.rollingStock.passenger;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.client.render.models.ModelRheingoldPassenger_Panorama;
import train.client.render.register.TrainRenderRecord;
import train.common.Traincraft;
import train.common.api.AbstractPassengerCar;
import train.common.api.EntityRollingStock;
import train.common.api.IPassenger;
import train.common.core.util.TraincraftUtil;
import train.common.library.Info;

public class EntityPassengerRheingoldPanorama extends AbstractPassengerCar
{

	public EntityPassengerRheingoldPanorama(World world) {
		super(world);
	}

	@Override
	public void updateRiderPosition() {
		TraincraftUtil.updateRider(this,-0.5, 0.4);
	}


	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 3.95F;
	}

	@Override
	public void onRenderInsertRecord()
	{
		Traincraft.traincraftRegistry.RegisterRollingStockModel(new TrainRenderRecord(Info.modID,
				EntityPassengerRheingoldPanorama.class, new ModelRheingoldPassenger_Panorama(),
				"Rheingold_passenger_panorama_",
				new float[] { -0.1F, 0.15F, 0.1F },
				new float[] { 0F, 180F, 180F },
				new float[] {0.9f,1f,0.9f}));
	}
}