package train.common.entity.rollingStock.diesel;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.common.api.DieselTrain;
import train.common.api.LiquidManager;
import train.common.library.EnumHeritageTrainsLegacy;
import train.common.library.EnumSounds;
import train.common.library.sounds.SoundRecord;

public class EntityLocoDieselShunter extends DieselTrain {
	@Override
	public SoundRecord getSoundRecord()
	{
		return EnumSounds.locoDieselShunter;
	}

	public EntityLocoDieselShunter(World world) {
		super(world, EnumHeritageTrainsLegacy.locoDieselShunter.getTankCapacity(), LiquidManager.dieselFilter());
		initLoco();
	}

	public void initLoco() {
		fuelTrain = 0;
		locoInvent = new ItemStack[inventorySize];
	}



	@Override
	public void updateRiderPosition() {
		riddenByEntity.setPosition(posX, posY + getMountedYOffset() + riddenByEntity.getYOffset() + 0.4F, posZ);
	}
	
	

	

	@Override
	public void onUpdate() {
		checkInvent(locoInvent[0]);
		super.onUpdate();
	}

	
	@Override
	public String getInventoryName() {
		return "Class 08 Shunter";
	}
	

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return (0.6F);
	}

	@Override
	public int getTankCapacity()
	{
		return 8000;
	}

	
}