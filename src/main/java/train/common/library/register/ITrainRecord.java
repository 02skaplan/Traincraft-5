package train.common.library.register;

import net.minecraft.item.Item;

public interface ITrainRecord
{
    String getInternalName();

    Item getItem();

    String getTrainType();

    int getMHP();

    int getMaxSpeed();

    double getMass();

    int getFuelConsumption();

    @Deprecated // Will be removed later please override the getWaterConsumption() in the entity for the stock
    int getWaterConsumption();

    int getHeatingTime();

    double getAccelerationRate();

    double getBrakeRate();

    /*
    OVERRIDE THE GET TANK CAPACITY METHOD IN THE ENTITY CLASS
     */
    @Deprecated
    int getTankCapacity();

    int[] getColors();

    double getBogieLocoPosition();

    Class getEntityClass();

    int getGuiRenderScale();

    String[] getAdditionalTooltip();

    int getCargoCapacity();

    String name();

}
