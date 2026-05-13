package train.common.library;

public enum EnumTrainType
{
    Steam("Steam", "L"),
    Diesel("Diesel", "L"),
    Electric("Electric", "L"),
    Hydrogen("Hydrogen", "L"),

    Boxcar("Boxcar", "B"),
    Highcube("Highcube", "X"),
    HighcubeBoxcar("Highcube Boxcar", "X"),
    Autorack("Autorack", "X"),
    Gondola("Gondola", "G"),
    EquippedGondola("EquippedGondola", "E"),
    Tankcar("Tankcar", "T"),
    CoveredHopper("Covered Hopper", "C"),
    OpenTopHopper("Open Top Hopper", "H"),
    Flatcars("Flatcar", "F"),
    BulkheadFlat("Bulkhead Flat", "F"),
    CenterbeamFlat("Centerbeam Flat", "F"),
    LogFlat("Log Flat", "F"),
    Stockcar("Livestock Car", "S"),
    Refrigerated("Refrigerated Car", "R"),
    RefrigeratedBoxcar("Refrigerated Boxcar", "R"),
    RefrigeratedTankcar("Refrigerated Tankcar", "R"),

    MOW("M.O.W", "M"),

    Passenger("Passenger", "N"),
    Passenger_Combine("Passenger/Combine", "N"),
    Passenger_CoachLounge("Coach-Lounge", "N"),
    Passenger_CoachDinette("Coach-Dinette", "N"),
    Passenger_Sleeper("Sleeper", "N"),
    Passenger_LunchCounterLounge("Lunch Counter-Lounge", "N"),
    Passenger_ParlorObservation("Parlor-Observation", "N"),


    Caboose("Caboose", "N"),
    Wellcar("Wellcar", "Q"),
    Special("Special", "O"),
    Tender("Tender", "0"),
    Other("Other", "O");

    public final String TrainType;
    public final String TrainTypeCode;

    EnumTrainType(String trainType, String trainTypeCode)
    {
        TrainType = trainType;
        TrainTypeCode = trainTypeCode;
    }

    public static String GetTrainTypeCode(String text)
    {
        for (EnumTrainType trainType : EnumTrainType.values())
        {
            if (trainType.TrainType.equalsIgnoreCase(text))
            {
                return trainType.TrainTypeCode;
            }
        }
        return Other.TrainTypeCode;
    }

    public static EnumTrainType GetTrainType(String text)
    {
        for (EnumTrainType trainType : EnumTrainType.values())
        {
            if (trainType.TrainType.equalsIgnoreCase(text))
            {
                return trainType;
            }
        }
        return Other;
    }
}


