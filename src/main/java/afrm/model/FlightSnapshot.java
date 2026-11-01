package afrm.model;

public record FlightSnapshot(
        String scenarioID,
        double elapsedTime,
        double distanceToDestination,
        double fuelRemaining,
        double averageFuelConsumptionPerHour,
        String destination,
        FuelState fuelState) {}