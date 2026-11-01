package afrm.model;

public class FlightPlan {

    private final String departureAirport;
    private final String destinationAirport;
    private final double totalDistanceKm;

    public FlightPlan(String departureAirport, String destinationAirport, double totalDistanceKm) {
        this.departureAirport = departureAirport;
        this.destinationAirport = destinationAirport;
        this.totalDistanceKm = totalDistanceKm;
    }

    public String getDepartureAirport() { return departureAirport; }
    public String getDestinationAirport() { return destinationAirport; }
    public double getTotalDistanceKm() { return totalDistanceKm; }
}