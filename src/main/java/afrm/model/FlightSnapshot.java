package afrm.model;

// Record of in flight values during simulation for UI.
public record FlightSnapshot(
        String scenarioID,
        double elapsedTime,
        double distanceToDestination,
        double fuelRemaining,
        double averageFuelConsumptionPerHour,
        String destination,
        FuelState fuelState) {}