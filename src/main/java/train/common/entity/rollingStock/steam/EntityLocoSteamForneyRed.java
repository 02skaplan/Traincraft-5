package train.common.entity.rollingStock.steam;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.locomotive.AbstractSteamTankEngine;
import train.common.library.EnumSounds;
import train.common.library.sounds.SoundRecord;

public class EntityLocoSteamForneyRed extends AbstractSteamTankEngine {

	@Override
	public SoundRecord getSoundRecord()
	{
		return EnumSounds.locoSteamForneyRed;
	}
	public EntityLocoSteamForneyRed(World world) {
		super(world);
	}

	@Override
	public void updateRiderPosition() {
		riddenByEntity.setPosition(posX, posY + getMountedYOffset() + riddenByEntity.getYOffset() + 0.45, posZ);// default
	}

	@Override
	public String getInventoryName() {
		return "Forney";
	}
	
	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 1.5F;
	}

	@Override
	public float transportMetricHorsePower()
	{
		return 600;
	}

	@Override
	public int getTankCapacity()
	{
		return 8000;
	}
}