package train.common.api.interfaces;

import train.common.library.register.ITrainRecord;

public interface IMultiPartPlacementTransport
{
    boolean isMainPart();

    ITrainRecord subTransportModelPiece();

    float getSpawnOffset();

    boolean flipEntityOnSpawn();
}