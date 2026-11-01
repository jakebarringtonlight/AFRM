package afrm.calc;

import afrm.model.AircraftModel;
import afrm.model.FlightPlan;

public class FuelRequirements {

    private final double requiredFuel;
    private final double requiredReserveFuel;
    private final double totalFuelRequired;

    public FuelRequirements(AircraftModel aircraftModel, FlightPlan flightPlan) {
        
        double distance = flightPlan.getTotalDistanceKm();
        double consumptionRate = aircraftModel.fuelConsumptionRateKgPerHour();

        this.requiredFuel = FuelCalculator.calculateRequiredFuel(distance, consumptionRate);
        this.requiredReserveFuel = FuelCalculator.calculateRequiredReserveFuel(this.requiredFuel);
        this.totalFuelRequired = this.requiredFuel + this.requiredReserveFuel;
    }

    public double getRequiredFuel() {
        return requiredFuel;
    }

    public double getRequiredReserveFuel() {
        return requiredReserveFuel;
    }

    public double getTotalFuelRequired() {
        return totalFuelRequired;
    }

}