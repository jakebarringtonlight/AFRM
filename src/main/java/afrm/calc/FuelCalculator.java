package afrm.calc;

import afrm.config.Constants;

public final class FuelCalculator {

    private FuelCalculator(){}

    // Calculate the flight time in hours given distance in kilometers and cruise speed in kilometers per hour.
    public static double calculateFlightTime(double distance, double cruiseSpeedKmh) {
        if (distance <= 0 || cruiseSpeedKmh <= 0) {
            return 0;
        }
        return distance / cruiseSpeedKmh;
    }
    
    // Calculate the required fuel in kilograms given distance in kilometers, cruise speed in kilometers per hour, and fuel consumption rate in kilograms per hour.
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


    // Calculate the required reserve fuel in kilograms given the required fuel in kilograms.
    public static double calculateRequiredReserveFuel(double requiredFuel) {
        if (requiredFuel <= 0) {
            return 0;
        }
        return requiredFuel * Constants.RESERVE_FRACTION;
    }
    
    // Calculate the estimated fuel consumption in kilograms given the estimated flight time and fuel consumption rate in kilograms per hour.
    public static double calculateEstimatedFuelConsumption(double flightTime, double consumptionRate) {
        if (flightTime <= 0 || consumptionRate <= 0) {
            return 0;
        }
        return flightTime * consumptionRate;
    }

    // Calculate the remaining possible flight time in hours given current fuel level in kilograms and fuel consumption rate in kilograms per hour.
    public static double calculateRemainingPossibleFlightTime(double currentFuelLevel, double consumptionRate) {
        if (currentFuelLevel <= 0 || consumptionRate <= 0) {
            return 0;
        }
        return currentFuelLevel / consumptionRate;
    }

    // Calculate the remaining range in kilometers given current fuel level in kilograms, fuel consumption rate in kilograms per hour, and cruise speed in kilometers per hour.
    public static double calculateRemainingRange(double currentFuelLevel, double consumptionRate, double cruiseSpeed) {
        if (currentFuelLevel <= 0 || consumptionRate <= 0 || cruiseSpeed <= 0) {
            return 0;
        }
        return (currentFuelLevel / consumptionRate) * cruiseSpeed;
    }

    
}
