package train.common.entity.rollingStock.steam;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.LiquidManager;
import train.common.api.SteamTrain;
import train.common.library.EnumSounds;
import train.common.library.sounds.SoundRecord;

public class EntityLocoSteamHeavy extends SteamTrain {
	@Override
	public SoundRecord getSoundRecord()
	{
		return EnumSounds.locoHeavySteamHeavy;
	}
	public EntityLocoSteamHeavy(World world) {
		super(world, 5000/*change value here to EnumTrains..getTankCapacity()*/, LiquidManager.WATER_FILTER);
		
	}

	@Override
	public void updateRiderPosition() {
		riddenByEntity.setPosition(posX, posY + getMountedYOffset() + riddenByEntity.getYOffset() + 0.2F, posZ);
	}



	

	@Override
	public String getInventoryName() {
		return "Heavy Steam Locomotive";
	}

	

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 1F;
	}

	@Override
	public int getTankCapacity()
	{
		return 10000;
	}
}