package afrm.model;

import afrm.config.Data;

public class AircraftModel {
    private final String modelName;
    private final double maxFuelCapacityKg;
    private final double emptyWeightKg;
    private final double maxTakeoffWeightKg;
    private final double fuelConsumptionRateKgPerHour;
    private final double cruiseSpeedKmh;
    private final double maxRangeKm;

    public AircraftModel(String modelName, double maxFuelCapacityKg, double emptyWeightKg,
                            double maxTakeoffWeightKg, double fuelConsumptionRateKgPerHour,
                            double cruiseSpeedKmh, double maxRangeKm) {
        this.modelName = modelName;
        this.maxFuelCapacityKg = maxFuelCapacityKg;
        this.emptyWeightKg = emptyWeightKg;
        this.maxTakeoffWeightKg = maxTakeoffWeightKg;
        this.fuelConsumptionRateKgPerHour = fuelConsumptionRateKgPerHour;
        this.cruiseSpeedKmh = cruiseSpeedKmh;
        this.maxRangeKm = maxRangeKm;
    }

    public static AircraftModel Aircraft() {
        return new AircraftModel(
            Data.MODEL_NAME,
            Data.MAX_FUEL_CAPACITY_KG,
            Data.EMPTY_WEIGHT_KG,
            Data.MAX_TAKEOFF_WEIGHT_KG,
            Data.FUEL_CONSUMPTION_RATE_KG_PER_HOUR,
            Data.CRUISE_SPEED_KMH,
            Data.MAX_RANGE_KM
        );
    }

    public String modelName() { return modelName; }
    public double maxFuelCapacityKg() { return maxFuelCapacityKg; }
    public double emptyWeightKg() { return emptyWeightKg; }
    public double maxTakeoffWeightKg() { return maxTakeoffWeightKg; }
    public double fuelConsumptionRateKgPerHour() { return fuelConsumptionRateKgPerHour; }
    public double cruiseSpeedKmh() { return cruiseSpeedKmh; }
    public double maxRangeKm() { return maxRangeKm; }
}


