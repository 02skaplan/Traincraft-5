package train.common.entity.rollingStock.passenger;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.client.render.models.ModelPassenger_1class_DB;
import train.client.render.register.TrainRenderRecord;
import train.common.Traincraft;
import train.common.api.AbstractPassengerCar;
import train.common.api.EntityRollingStock;
import train.common.api.IPassenger;
import train.common.library.Info;

public class EntityPassenger_1class_DB extends AbstractPassengerCar {

	public EntityPassenger_1class_DB(World world) {
		super(world);
	}

	@Override
	public void updateRiderPosition() {
		//if(this.bogie[0]!=null){
			/*double dX = this.posX - this.bogie[0].posX;
			double dZ = this.posZ - this.bogie[0].posZ;
			dX/=2;
			dZ/=2;*/
			//System.out.println(worldObj.isRemote+ " "+(posX - dX) +" " + (posZ - dZ));
			//riddenByEntity.setPosition(posX - dX, posY + getMountedYOffset() + riddenByEntity.getYOffset() + 0.2, posZ-dZ);	
		//}
		/*double rads = this.rotationYawClientReal-90 * 3.141592653589793D / 180.0D;
		double pitchRads = this.anglePitchClient * 3.141592653589793D / 180.0D;
		double distance = 1.3;
		riddenByEntity.setPosition(posX - Math.cos(rads)*distance, posY + (Math.tan(pitchRads)*-distance)+( getMountedYOffset() + riddenByEntity.getYOffset() + 0.2F), posZ - Math.sin(rads)*distance);
		*/
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
				EntityPassenger_1class_DB.class, new ModelPassenger_1class_DB(),
				"passenger_1class_DB_",
				new float[] { 0.0F, -0.44F, 0.0F },
				null,
				null));
	}
}