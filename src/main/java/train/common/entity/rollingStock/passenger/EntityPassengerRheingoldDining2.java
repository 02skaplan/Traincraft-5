package train.common.entity.rollingStock.passenger;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.client.render.models.ModelRheingoldPassenger_Dining2;
import train.client.render.register.TrainRenderRecord;
import train.common.Traincraft;
import train.common.api.AbstractWorkCart;
import train.common.core.util.TraincraftUtil;
import train.common.library.GuiIDs;
import train.common.library.Info;

public class EntityPassengerRheingoldDining2  extends AbstractWorkCart
{
	public EntityPassengerRheingoldDining2(World world) {
		super(world);
	}

	@Override
	public void updateRiderPosition() {
		TraincraftUtil.updateRider(this, -0.1, 0);
	}

	@Override
	public String getInventoryName() {
		return "Rheingold Dining Pantograph";
	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 3.87F;
	}

	@Override
	public void onRenderInsertRecord()
	{
		Traincraft.traincraftRegistry.RegisterRollingStockModel(new TrainRenderRecord(Info.modID,
				EntityPassengerRheingoldDining2.class, new ModelRheingoldPassenger_Dining2(),
				"Rheingold_passenger_dining2_",
				new float[] { 0.05F, 0.15F, 0F },
				new float[] { 0F, 180F, 180F },
				new float[] {0.9f,1f,0.9f}));
	}
}