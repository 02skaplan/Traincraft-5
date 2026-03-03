package train.common.entity.rollingStock.diesel;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.common.api.DieselTrain;
import train.common.api.LiquidManager;
import train.common.core.util.TraincraftUtil;
import train.common.library.EnumHeritageTrainsLegacy;
import train.common.library.EnumSounds;
import train.common.library.sounds.SoundRecord;

public class EntityLocoDieselClass66 extends DieselTrain {
	@Override
	public SoundRecord getSoundRecord()
	{
		return EnumSounds.locoDieselClass66;
	}
	public EntityLocoDieselClass66(World world) {
		super(world, EnumHeritageTrainsLegacy.locoDieselEWSClass66.getTankCapacity(), LiquidManager.dieselFilter());
	}

	@Override
	public void updateRiderPosition() {
		TraincraftUtil.updateRider(this,  5.6, 0.1);
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
		return "Class 66";
	}

	

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return (1F);
	}

	@Override
	public int getTankCapacity()
	{
		return 6400;
	}

	
}