package afrm.calc;

import afrm.config.Constants;

public final class FuelCalculator {

    private FuelCalculator(){}


    public static double calculateFlightTime(double distance, double cruiseSpeedKmh) {
        if (distance <= 0 || cruiseSpeedKmh <= 0) {
            return 0;
        }
        return distance / cruiseSpeedKmh;
    }

    public static double calculateRequiredFuel(
        double distance,
        double cruiseSpeed,
        double consumptionRate) {

    if (distance <= 0 || cruiseSpeed <= 0 || consumptionRate <= 0) {
        return 0;
    }

    double flightTime = distance / cruiseSpeed;

    return flightTime * consumptionRate;
}
    
    public static double calculateRequiredReserveFuel(double requiredFuel) {
        if (requiredFuel <= 0) {
            return 0;
        }
        return requiredFuel * Constants.RESERVE_FRACTION;
    }
    
    public static double calculateEstimatedFuelConsumption(double distance, double consumptionRate) {
        if (distance <= 0 || consumptionRate <= 0) {
            return 0;
        }
        return distance * consumptionRate;
    }

    public static double calculateRemainingPossibleFlightTime(double currentFuelLevel, double consumptionRate) {
        if (currentFuelLevel <= 0 || consumptionRate <= 0) {
            return 0;
        }
        return currentFuelLevel / consumptionRate;
    }

    public static double calculateRemainingRange(double currentFuelLevel, double consumptionRate, double cruiseSpeed) {
        if (currentFuelLevel <= 0 || consumptionRate <= 0 || cruiseSpeed <= 0) {
            return 0;
        }
        return (currentFuelLevel / consumptionRate) * cruiseSpeed;
    }

    
}
