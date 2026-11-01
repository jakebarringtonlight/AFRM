package afrm.model;

public record FlightSnapshot(
        double elapsedTime,
        double distanceToDestination,
        double fuelRemaining,
        double averageFuelConsumptionPerHour,
        String destination,
        FuelState fuelState) {}