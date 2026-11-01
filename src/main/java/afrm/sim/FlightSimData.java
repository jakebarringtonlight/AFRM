package afrm.sim;

import java.util.List;

import afrm.model.AircraftModel;
import afrm.model.FlightPlan;

public class FlightSimData {

    private FlightSimData(){}

    public enum EventType {EXTRA_BURN_KG_PER_HOUR, HEADWIND_KMH}

    public record Event(double startHour, EventType type, double value){}

    public record FlightScenario(
        String scenarioID, 
        AircraftModel aircraftModel, 
        FlightPlan flightPlan, 
        double initialFuelKg, 
        List<Event> events){}

    private static final AircraftModel AIRCRAFT_MODEL = AircraftModel.defaultModel();
    
        public static final List<FlightScenario> SCENARIOS = List.of(

        new FlightScenario("normal",
            AIRCRAFT_MODEL, new FlightPlan("BRS", "JFK", 3380.0),
            17000.0, List.of()),

        new FlightScenario("headwind",
            AIRCRAFT_MODEL, new FlightPlan("BRS", "JFK", 3380.0),
            17000.0,
            List.of(new Event(0.0, EventType.HEADWIND_KMH, 200.0))),

        new FlightScenario("leak",
            AIRCRAFT_MODEL, new FlightPlan("BRS", "JFK", 3380.0),
            17000.0,
            List.of(new Event(1.5, EventType.EXTRA_BURN_KG_PER_HOUR, 2400.0))),

        new FlightScenario("tight",
            AIRCRAFT_MODEL, new FlightPlan("BRS", "MCO", 3900.0),
            18500.0, List.of(new Event(0.0, EventType.HEADWIND_KMH, 180.0))),

        new FlightScenario("infeasible",
            AIRCRAFT_MODEL, new FlightPlan("BRS", "SYD", 10600.0),
            19000.0, List.of())
    );

    public static FlightScenario getScenario(String scenarioID) {
        for (FlightScenario scenario : SCENARIOS) {
            if (scenario.scenarioID().equals(scenarioID)) {
                return scenario;
            }
        }
        return null;
    }

}
