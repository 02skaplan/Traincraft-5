package train.common.entity.rollingStock.electric;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.ElectricTrain;
import train.common.library.EnumSounds;
import train.common.library.sounds.SoundRecord;

public class EntityLocoElectricBR_E69 extends ElectricTrain {
	@Override
	public SoundRecord getSoundRecord()
	{
		return EnumSounds.locoElectricBR_E69;
	}

	public EntityLocoElectricBR_E69(World world) {
		super(world);
	}

	@Override
	public void updateRiderPosition() {
		riddenByEntity.setPosition(posX, posY + getMountedYOffset() + riddenByEntity.getYOffset() + 0.35F, posZ);// <==
	}

	

	@Override
	public String getInventoryName() {
		return "BR E69";
	}

	

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 1.55F;
	}

	
}