package train.common.entity.rollingStock.electric;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.ElectricTrain;
import train.common.core.util.TraincraftUtil;
import train.common.library.EnumSounds;
import train.common.library.sounds.SoundRecord;

public class EntityLocoElectricE10_DB extends ElectricTrain {
	@Override
	public SoundRecord getSoundRecord()
	{
		return EnumSounds.locoElectricE10_DB;
	}

	public EntityLocoElectricE10_DB(World world) {
		super(world);
	}

	

	@Override
	public void updateRiderPosition() {
		TraincraftUtil.updateRider(this,  3.75, 0.05);
	}

	
	@Override
	public String getInventoryName() {
		return "E10 (DB)";
	}

	

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 1F;
	}
	
}