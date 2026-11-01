package afrm.model;

import afrm.config.Constants;

public class FuelStatus {
   
    double currentFuelLevel;

    public FuelStatus(double currentFuelLevel) {
        this.currentFuelLevel = currentFuelLevel;
    }

    public double getCurrentFuelLevel() {
        return currentFuelLevel;
    }

    public void checkFuelStatus(AircraftModel aircraftModel, FlightPlan flightPlan) {


        double distance = flightPlan.getTotalDistanceKm();
        double consumptionRate = aircraftModel.fuelConsumptionRateKgPerHour();

        double requiredFuel = afrm.calc.FuelCalculator.calculateRequiredFuel(distance, consumptionRate);
        double reserveFuel = afrm.calc.FuelCalculator.calculateRequiredReserveFuel(requiredFuel);
        double totalRequiredFuel = requiredFuel + reserveFuel;

        if (currentFuelLevel < 0.1 * requiredFuel) {
            System.out.println(Constants.LOW_FUEL_WARNING_MESSAGE);
    }

        if (currentFuelLevel < 0.05 * requiredFuel) {
            System.out.println(Constants.CRITICAL_FUEL_WARNING_MESSAGE);
        }

        if (aircraftModel.maxFuelCapacityKg() < totalRequiredFuel) {
            System.out.println(Constants.INSUFFICIENT_FUEL_CAPACITY_WARNING_MESSAGE);
        }

    }

}
