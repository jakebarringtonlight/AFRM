package afrm.model;

public class FlightPlan {

    private final String departureAirport;
    private final String destinationAirport;
    private final double totalDistanceKm;

    // Method to create a FlightPlan.
    public FlightPlan(String departureAirport, String destinationAirport, double totalDistanceKm) {
        this.departureAirport = departureAirport;
        this.destinationAirport = destinationAirport;
        this.totalDistanceKm = totalDistanceKm;
    }

    // Getter methods for FlightPlan properties.
    public String getDepartureAirport() { return departureAirport; }
    public String getDestinationAirport() { return destinationAirport; }
    public double getTotalDistanceKm() { return totalDistanceKm; }
}