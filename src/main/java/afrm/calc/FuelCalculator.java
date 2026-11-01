package afrm.calc;

import afrm.config.Constants;

public final class FuelCalculator {

    private FuelCalculator(){}


    public static double calculateFlightTime(double distance, double cruiseSpeedKmh) {
        return distance / cruiseSpeedKmh;
    }

    public static double calculateRequiredFuel(double distance, double consumtptionRate){

        return distance * consumtptionRate * Constants.FUEL_REQ_MULTIPLIER;
    }
    
    public static double calculateRequiredReserveFuel(double requiredFuel) {
        return requiredFuel * Constants.RESERVE_FRACTION;
    }

    public static double calculateEstimatedFuelConsumption(double distance, double consumptionRate) {
        return distance * consumptionRate;
    }

    public static double calculateRemainingPossibleFlightTime(double currentFuelLevel, double consumptionRate) {
        return currentFuelLevel / consumptionRate;
    }

    public static double calculateRemainingRange(double currentFuelLevel, double consumptionRate, double cruiseSpeed) {
        return (currentFuelLevel / consumptionRate) * cruiseSpeed;
    }

    
}
