package train.common.entity.rollingStock.diesel;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.common.api.DieselTrain;
import train.common.api.LiquidManager;
import train.common.library.EnumHeritageTrainsLegacy;
import train.common.library.EnumSounds;
import train.common.library.sounds.SoundRecord;

public class EntityLocoDieselChME3 extends DieselTrain
{
	@Override
	public SoundRecord getSoundRecord()
	{
		return EnumSounds.locoDieselChME3;
	}

	public EntityLocoDieselChME3(World world) {
		super(world, EnumHeritageTrainsLegacy.locoDieselChME3.getTankCapacity(), LiquidManager.dieselFilter());
		initLoco();
	}

	public void initLoco() {
		fuelTrain = 0;
		locoInvent = new ItemStack[inventorySize];
	}



	@Override
	public void updateRiderPosition() {
		riddenByEntity.setPosition(posX, posY + getMountedYOffset() + riddenByEntity.getYOffset() + 0.35F, posZ);
	}



	

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (worldObj.isRemote) {
			return;
		}
		checkInvent(locoInvent[0]);
	}

	

	@Override
	public String getInventoryName() {
		return "ChME3";
	}

	

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return (1.5F);
	}

	@Override
	public int getTankCapacity()
	{
		return 5000;
	}

	
}