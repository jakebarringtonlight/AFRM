package afrm.model;

public class FuelStatus {
   
    double currentFuelLevel;

    public FuelStatus(double currentFuelLevel) {
        this.currentFuelLevel = currentFuelLevel;
    }

    public double getCurrentFuelLevel() {
        return currentFuelLevel;
    }

    public boolean isFuelSufficient(AircraftModel aircraftModel, FlightPlan flightPlan) {
        double distance = flightPlan.getTotalDistanceKm();
        double cruiseSpeed = aircraftModel.cruiseSpeedKmh();
        double consumptionRate = aircraftModel.fuelConsumptionRateKgPerHour();
        double requiredFuel = afrm.calc.FuelCalculator.calculateRequiredFuel(distance, cruiseSpeed, consumptionRate);
        double reserveFuel = afrm.calc.FuelCalculator.calculateRequiredReserveFuel(requiredFuel);
        double totalRequiredFuel = requiredFuel + reserveFuel;

        return currentFuelLevel >= totalRequiredFuel;
    }

    public FuelState checkFuelStatus(double remainingDistance, double cruiseSpeed, double fuelConsumptionRate, double maxFuelCapacity) {

        double remainingRequiredFuel = afrm.calc.FuelCalculator.calculateRequiredFuel(remainingDistance, cruiseSpeed, fuelConsumptionRate);
        double reserveFuel = afrm.calc.FuelCalculator.calculateRequiredReserveFuel(remainingRequiredFuel);
        double totalRequiredFuel = remainingRequiredFuel + reserveFuel;
        
        
        if (maxFuelCapacity < remainingRequiredFuel) {
         
            return FuelState.INSUFFICIENT_CAPACITY;
        }

        if (currentFuelLevel < remainingRequiredFuel) {
            return FuelState.CRITICAL;
        }

        if (currentFuelLevel < 0.1 * maxFuelCapacity) {
                return FuelState.CRITICAL;
        }

        if (currentFuelLevel < remainingRequiredFuel + reserveFuel) {
            return FuelState.LOW;
        }

        if (currentFuelLevel < 0.25 * maxFuelCapacity) {
            return FuelState.LOW;
        }

        
        if (currentFuelLevel >= totalRequiredFuel) {
            return FuelState.NORMAL;
        }
        
        return FuelState.NORMAL;
    }    

}
