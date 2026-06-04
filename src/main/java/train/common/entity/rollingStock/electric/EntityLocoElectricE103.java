package train.common.entity.rollingStock.electric;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.ElectricTrain;
import train.common.core.util.TraincraftUtil;
import train.common.library.EnumSounds;
import train.common.library.sounds.SoundRecord;

public class EntityLocoElectricE103 extends ElectricTrain {
	@Override
	public SoundRecord getSoundRecord()
	{
		return EnumSounds.locoElectricE103;
	}

	public EntityLocoElectricE103(World world) {
		super(world);
	}



	@Override
	public void updateRiderPosition() {
		TraincraftUtil.updateRider(this,  3.65, 0.05);
	}

	
	@Override
	public String getInventoryName() {
		return "E103";
	}

	

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 0.995F;
	}
	
}