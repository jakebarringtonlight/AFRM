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
        double consumptionRate = aircraftModel.fuelConsumptionRateKgPerHour();
        double requiredFuel = afrm.calc.FuelCalculator.calculateRequiredFuel(distance, consumptionRate);
        double reserveFuel = afrm.calc.FuelCalculator.calculateRequiredReserveFuel(requiredFuel);
        double totalRequiredFuel = requiredFuel + reserveFuel;

        return currentFuelLevel >= totalRequiredFuel;
    }

    public FuelState checkFuelStatus(double remainingDistance, double cruiseSpeed, double fuelConsumptionRate) {

        double currentFuelLevel = this.currentFuelLevel;
        double distance = remainingDistance;
        double consumptionRate = fuelConsumptionRate;
        double requiredFuel = afrm.calc.FuelCalculator.calculateRequiredFuel(distance, consumptionRate);
        double reserveFuel = afrm.calc.FuelCalculator.calculateRequiredReserveFuel(requiredFuel);
        double totalRequiredFuel = requiredFuel + reserveFuel;
        
        if (currentFuelLevel < 0.1 * requiredFuel) {
            return FuelState.LOW;    
        }

        if (currentFuelLevel < 0.05 * requiredFuel) {
            return FuelState.CRITICAL;}

        if (currentFuelLevel < requiredFuel) {
         
            return FuelState.INSUFFICIENT;
        }
        if (currentFuelLevel >= totalRequiredFuel) {
            return FuelState.NORMAL;
        }
        return null;
    }    

}
