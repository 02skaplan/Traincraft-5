import com.jcirmodelsquad.tcjcir.RegisterBAPTrains;
import net.minecraft.item.Item;
import org.junit.BeforeClass;
import org.junit.Test;
import train.common.api.AbstractPassengerCar;
import train.common.library.EnumTrainType;
import train.common.library.ItemIDs;
import train.common.library.RegisterTrains;
import train.common.library.register.ITrainRecord;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class TrainRegisterTypeDataTest {

    private static LinkedHashMap<Item, ITrainRecord> register;

    private static final EnumSet<EnumTrainType> LOCOMOTIVE_TYPES = EnumSet.of(
            EnumTrainType.Steam,
            EnumTrainType.Diesel,
            EnumTrainType.Electric,
            EnumTrainType.Hydrogen
    );

    private static final EnumSet<EnumTrainType> CARGO_FREIGHT_TYPES = EnumSet.of(
            EnumTrainType.Boxcar,
            EnumTrainType.Highcube,
            EnumTrainType.HighcubeBoxcar,
            EnumTrainType.Autorack,
            EnumTrainType.Gondola,
            EnumTrainType.EquippedGondola,
            EnumTrainType.CoveredHopper,
            EnumTrainType.OpenTopHopper,
            EnumTrainType.Flatcars,
            EnumTrainType.BulkheadFlat,
            EnumTrainType.CenterbeamFlat,
            EnumTrainType.LogFlat,
            EnumTrainType.Refrigerated,
            EnumTrainType.RefrigeratedBoxcar,
            EnumTrainType.Wellcar
    );

    private static final EnumSet<EnumTrainType> TANK_FREIGHT_TYPES = EnumSet.of(
            EnumTrainType.Tankcar,
            EnumTrainType.RefrigeratedTankcar
    );

    private static final EnumSet<EnumTrainType> PASSENGER_TYPES = EnumSet.of(
            EnumTrainType.Passenger,
            EnumTrainType.Passenger_Combine,
            EnumTrainType.Passenger_CoachLounge,
            EnumTrainType.Passenger_CoachDinette,
            EnumTrainType.Passenger_Sleeper,
            EnumTrainType.Passenger_LunchCounterLounge,
            EnumTrainType.Passenger_ParlorObservation,
            EnumTrainType.Caboose
    );

    @BeforeClass
    public static void setUpRegistry() {
        /*
         * Replace RegisterBAPTrains with the real class that contains:
         *
         * public LinkedHashMap<Item, ITrainRecord> getRegister()
         *
         * This test assumes ItemIDs have already been initialized.
         * If this fails with null ItemIDs, run it after your item registration/bootstrap,
         * or extract the TrainRecord construction into a testable builder.
         */



        for (ItemIDs itemIDs : ItemIDs.values())
        {
            itemIDs.item = new Item().setUnlocalizedName(itemIDs.name());
        }

        register = new RegisterTrains().getRegister();
        register.putAll(new RegisterBAPTrains().getRegister());

        assertNotNull("Train register must not be null.", register);
        assertFalse("Train register must not be empty.", register.isEmpty());
    }

    @Test
    public void everyRegisterEntryHasValidDataForItsTrainType() {
        for (Map.Entry<Item, ITrainRecord> entry : register.entrySet())
        {
            Item item = entry.getKey();
            ITrainRecord record = entry.getValue();

            assertNotNull("Register contains a null Item key.", item);
            assertNotNull("Register contains a null ITrainRecord value for item " + item, record);

            EnumTrainType type = EnumTrainType.Other;
            if (entry.getValue().getTrainType().contains("slug"))
            {
                if (entry.getValue().getMaxSpeed() > 0)
                {
                    type = EnumTrainType.Electric;
                }
            }
            else
            {
                type = EnumTrainType.GetTrainType(entry.getValue().getTrainType());
            }

            String name = recordName(record, item);

            assertNotNull(name + " has no train type.", type);

            assertCommonRecordData(name, record);

            if (LOCOMOTIVE_TYPES.contains(type)) {
                assertLocomotiveData(name, record, type);
            } else if (type != EnumTrainType.Other){
                assertNonLocomotiveDoesNotHavePowerData(name, record);
            }

            if (type == EnumTrainType.Steam)
            {
                assertSteamData(name, record);
            }
            else if (type == EnumTrainType.Diesel || type == EnumTrainType.Hydrogen)
            {
                assertFuelPoweredLocoData(name, record, type);
            }
            else if (type == EnumTrainType.Tender)
            {
                assertTenderData(name, record);
            }

            boolean isPassenger = PASSENGER_TYPES.contains(type) || record.getEntityClass().getSuperclass() == AbstractPassengerCar.class;

            if (isPassenger)
            {
                assertPassengerData(name, record, type);
            }
            else if (CARGO_FREIGHT_TYPES.contains(type)) {
                assertCargoFreightData(name, record, type);
            }

            else if (TANK_FREIGHT_TYPES.contains(type)) {
                assertTankFreightData(name, record, type);
            }
            else if (type == EnumTrainType.MOW || type == EnumTrainType.Special || type == EnumTrainType.Other) {
                assertLooseUtilityData(name, record, type);
            }
        }
    }

    @Test
    public void everyRegisterEntryHasUniqueInternalName() {
        LinkedHashMap<String, String> seenByInternalName = new LinkedHashMap<String, String>();
        StringBuilder duplicates = new StringBuilder();

        for (Map.Entry<Item, ITrainRecord> entry : register.entrySet()) {
            Item item = entry.getKey();
            ITrainRecord record = entry.getValue();

            assertNotNull("Register contains a null Item key.", item);
            assertNotNull("Register contains a null ITrainRecord value for item " + item, record);

            String internalName = internalName(record, item);
            String owner = recordName(record, item) + " / item=" + item;

            assertNotNull(owner + " has no internal name.", internalName);
            assertFalse(owner + " has an empty internal name.", internalName.trim().isEmpty());

            /*
             * Trim before checking so accidental leading/trailing whitespace
             * does not allow two names that are functionally the same.
             */
            internalName = internalName.trim();

            if (seenByInternalName.containsKey(internalName)) {
                duplicates
                        .append("\nDuplicate internal name '")
                        .append(internalName)
                        .append("' used by both: ")
                        .append(seenByInternalName.get(internalName))
                        .append(" AND ")
                        .append(owner);
            } else {
                seenByInternalName.put(internalName, owner);
            }
        }

        if (duplicates.length() > 0) {
            fail("Duplicate train internal names were found:" + duplicates.toString());
        }
    }

    private static String internalName(ITrainRecord record, Item item) {
        Object value = readOptional(record,
                "getInternalName",
                "internalName"
        );

        if (value != null) {
            return String.valueOf(value);
        }

        fail("Could not read internal name from record "
                + recordName(record, item)
                + ". Expected getInternalName() or internalName field.");

        return null;
    }

    private static void assertCommonRecordData(String name, ITrainRecord record) {
        assertTrue(name + " must have mass >= 0.", number(record, "getMass", "mass") >= 0.0D);

    }

    private static void assertLocomotiveData(String name, ITrainRecord record, EnumTrainType type) {
        assertTrue(name + " [" + type + "] must have MHP > 0.", number(record, "getMHP", "mhp", "MHP") > 0.0D);
        assertTrue(name + " [" + type + "] must have max speed > 0.", number(record, "getMaxSpeed", "maxSpeed") > 0.0D);

        assertTrue(
                name + " [" + type + "] must have acceleration rate > 0.",
                number(record, "getAccelerationRate", "accelerationRate") > 0.0D
        );

        assertTrue(
                name + " [" + type + "] must have brake rate > 0.",
                number(record, "getBrakeRate", "brakeRate") > 0.0D
        );
    }

    private static void assertSteamData(String name, ITrainRecord record) {
        assertTrue(name + " [Steam] must have fuel consumption > 0.",
                number(record, "getFuelConsumption", "fuelConsumption") > 0.0D);

        assertTrue(name + " [Steam] must have water consumption > 0.",
                number(record, "getWaterConsumption", "waterConsumption") > 0.0D);

        assertTrue(name + " [Steam] must have heating time > 0.",
                number(record, "getHeatingTime", "heatingTime") > 0.0D);

        assertTrue(name + " [Steam] must have tank capacity > 0.",
                number(record, "getTankCapacity", "tankCapacity") > 0.0D);

        assertTrue(name + " [Steam] must have heat time > 0.",
                record.getHeatingTime() > 0.0D);
    }

    private static void assertFuelPoweredLocoData(String name, ITrainRecord record, EnumTrainType type) {
        assertTrue(name + " [" + type + "] must have fuel consumption > 0.",
                number(record, "getFuelConsumption", "fuelConsumption") > 0.0D);

        assertTrue(name + " [" + type + "] must have heating time > 0.",
                number(record, "getHeatingTime", "heatingTime") > 0.0D);

        assertTrue(name + " [" + type + "] must have tank capacity > 0.",
                number(record, "getTankCapacity", "tankCapacity") > 0.0D);
    }

    private static void assertTenderData(String name, ITrainRecord record) {
        assertTrue(name + " [Tender] must have mass > 0.",
                number(record, "getMass", "mass") > 0.0D);

        assertTrue(name + " [Tender] must have tank capacity > 0.",
                number(record, "getTankCapacity", "tankCapacity") > 0.0D);

        assertEquals(name + " [Tender] should not have MHP set.",
                0.0D, number(record, "getMHP", "mhp", "MHP"), 0.0001D);

        assertEquals(name + " [Tender] should not have max speed set.",
                0.0D, number(record, "getMaxSpeed", "maxSpeed"), 0.0001D);
    }

    private static void assertCargoFreightData(String name, ITrainRecord record, EnumTrainType type) {
        assertTrue(name + " [" + type + "] must have mass > 0.",
                number(record, "getMass", "mass") > 0.0D);

        assertTrue(name + " [" + type + "] must have cargo capacity > 0.",
                number(record, "getCargoCapacity", "cargoCapacity") > 0.0D);
    }

    private static void assertTankFreightData(String name, ITrainRecord record, EnumTrainType type) {
        assertTrue(name + " [" + type + "] must have mass > 0.",
                number(record, "getMass", "mass") > 0.0D);

        assertTrue(name + " [" + type + "] must have tank capacity > 0.",
                number(record, "getTankCapacity", "tankCapacity") > 0.0D);
    }

    private static void assertPassengerData(String name, ITrainRecord record, EnumTrainType type) {
        assertTrue(name + " [" + type + "] must have mass > 0.",
                number(record, "getMass", "mass") > 0.0D);

        /*
         * Passenger combines and baggage-style cars may optionally have cargo capacity,
         * so this intentionally does not require cargo capacity for all passenger cars.
         */
    }

    private static void assertLooseUtilityData(String name, ITrainRecord record, EnumTrainType type) {
        assertTrue(name + " [" + type + "] must have mass >= 0.",
                number(record, "getMass", "mass") >= 0.0D);

        /*
         * MOW, Special, and Other are intentionally loose because your registry uses them
         * for unusual stock where cargo/tank behavior is not guaranteed.
         */
    }

    private static void assertNonLocomotiveDoesNotHavePowerData(String name, ITrainRecord record) {
        assertEquals(name + " is not a locomotive and should not have MHP set.",
                0.0D, number(record, "getMHP", "mhp", "MHP"), 0.0001D);

        assertEquals(name + " is not a locomotive and should not have max speed set.",
                0.0D, number(record, "getMaxSpeed", "maxSpeed"), 0.0001D);
    }

    private static String recordName(ITrainRecord record, Item item) {
        Object value = readOptional(record,
                "getName",
                "getTrainName",
                "getInternalName",
                "name",
                "trainName",
                "internalName"
        );

        if (value != null) {
            return String.valueOf(value);
        }

        return String.valueOf(item);
    }

    private static EnumTrainType enumValue(Object target, String... names) {
        Object value = readRequired(target, names);

        assertTrue(
                "Expected EnumTrainType but got " + value.getClass().getName() + " from " + target,
                value instanceof EnumTrainType
        );

        return (EnumTrainType) value;
    }

    private static double number(Object target, String... names) {
        Object value = readOptional(target, names);

        if (value == null) {
            return 0.0D;
        }

        assertTrue(
                "Expected numeric value but got " + value.getClass().getName() + " from " + target,
                value instanceof Number
        );

        return ((Number) value).doubleValue();
    }

    private static String[] stringArray(Object target, String... names) {
        Object value = readRequired(target, names);

        assertTrue(
                "Expected String[] but got " + value.getClass().getName() + " from " + target,
                value instanceof String[]
        );

        return (String[]) value;
    }

    private static Object readRequired(Object target, String... names) {
        Object value = readOptional(target, names);

        if (value == null) {
            fail("Could not read any of these fields/getters from "
                    + target.getClass().getName()
                    + ": "
                    + join(names));
        }

        return value;
    }

    private static Object readOptional(Object target, String... names) {
        for (String name : names) {
            Object methodValue = tryReadMethod(target, name);
            if (methodValue != MissingValue.INSTANCE) {
                return methodValue;
            }

            Object fieldValue = tryReadField(target, name);
            if (fieldValue != MissingValue.INSTANCE) {
                return fieldValue;
            }
        }

        return null;
    }

    private static Object tryReadMethod(Object target, String methodName) {
        try {
            Method method = target.getClass().getMethod(methodName);
            method.setAccessible(true);
            return method.invoke(target);
        } catch (Exception ignored) {
            return MissingValue.INSTANCE;
        }
    }

    private static Object tryReadField(Object target, String fieldName) {
        Class<?> current = target.getClass();

        while (current != null) {
            try {
                Field field = current.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field.get(target);
            } catch (Exception ignored) {
                current = current.getSuperclass();
            }
        }

        return MissingValue.INSTANCE;
    }

    private static String join(String[] values) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            if (i > 0) {
                builder.append(", ");
            }

            builder.append(values[i]);
        }

        return builder.toString();
    }

    private enum MissingValue {
        INSTANCE
    }
}