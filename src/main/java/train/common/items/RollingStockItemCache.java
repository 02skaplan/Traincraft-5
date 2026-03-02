package train.common.items;

import train.common.api.*;
import train.common.enums.CargoItemFilter;
import train.common.library.register.ITrainRecord;

import java.util.Map;

public class RollingStockItemCache
{
    public RollingStockItemCache(ITrainRecord trainRecord, AbstractTrains train)
    {
        HasPublicSkins = trainRecord.getColors() == null || train.lockoutMap.isEmpty() || train.lockoutMap.size() != trainRecord.getColors().length;
        TransportYear = train.transportYear();
        TransportCountry = train.transportCountry();
        IsFictional = train.isFictional();
        textureDescriptionMap = train.getTextureDescriptionMap();
        isAbstractStandardFreightCar = train instanceof AbstractStandardFreightCar;
        if (isAbstractStandardFreightCar)
        {
            cargoItemFilter = ((AbstractStandardFreightCar) train).GetCargoFilterCategory();
        }
        else
        {
            cargoItemFilter = null;
        }

        maxSpeed = train instanceof Locomotive ? ((Locomotive) train).transportTopSpeed() : 0;
        WeightKg = train.weightKg();
        TankCapacity = GetTankCapacity(train);
    }

    private int GetTankCapacity(AbstractTrains abstractTrains)
    {
        if (abstractTrains instanceof Tender)
        {
            return ((Tender) abstractTrains).getTankCapacity();
        }

        if (abstractTrains instanceof LiquidTank)
        {
            return ((LiquidTank) abstractTrains).getTankCapacity();
        }

        if (abstractTrains instanceof DieselTrain)
        {
            return ((DieselTrain) abstractTrains).getTankCapacity();
        }

        if (abstractTrains instanceof SteamTrain)
        {
            return ((SteamTrain) abstractTrains).getTankCapacity();
        }

        return 0;
    }

    public final Map<Integer, String> textureDescriptionMap;

    public final boolean HasPublicSkins;
    public final boolean isAbstractStandardFreightCar;
    public final CargoItemFilter cargoItemFilter;
    public final String TransportYear;
    public final String TransportCountry;
    public final boolean IsFictional;

    public final float maxSpeed;

    public final float WeightKg;
    public int TankCapacity;
}
