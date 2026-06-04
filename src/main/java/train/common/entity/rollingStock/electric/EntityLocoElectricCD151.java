package train.common.entity.rollingStock.electric;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.ElectricTrain;
import train.common.core.util.TraincraftUtil;
import train.common.library.EnumSounds;
import train.common.library.sounds.SoundRecord;

public class EntityLocoElectricCD151 extends ElectricTrain {
	@Override
	public SoundRecord getSoundRecord()
	{
		return EnumSounds.locoDieselCD151;
	}

	public EntityLocoElectricCD151(World world) {
		super(world);
	}

	

	@Override
	public void updateRiderPosition() {
		TraincraftUtil.updateRider(this,  3.75, 0.3);
	}


	@Override
	public String getInventoryName() {
		return "CD 151";
	}

	

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 0.475F;
	}
	
}
