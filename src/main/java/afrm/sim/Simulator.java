package afrm.sim;

import java.util.ArrayList;
import java.util.List;

import afrm.model.AircraftModel;
import afrm.model.FlightPlan;
import afrm.model.FlightSnapshot;
import afrm.model.FuelState;
import afrm.model.FuelStatus;
import afrm.sim.FlightSimData.EventType;
import afrm.sim.FlightSimData.FlightScenario;
import afrm.ui.Logger;

public class Simulator {
    
    public static void runSimulation(FlightSimData.FlightScenario scenario) {
        
        Logger logger = new Logger();
        
        AircraftModel aircraftModel = scenario.aircraftModel();
        FlightPlan flightPlan = scenario.flightPlan();
        String destination = flightPlan.getDestinationAirport();
        String departure = flightPlan.getDepartureAirport();

        double cruiseSpeed = aircraftModel.cruiseSpeedKmh();
        double fuelConsumptionRate = aircraftModel.fuelConsumptionRateKgPerHour();
        
        double currentFuelLevel = scenario.initialFuelKg();
        double remainingDistance = flightPlan.getTotalDistanceKm();
        double elapsedTime = 0.0;
        double fuelBurned = 0.0;
        FuelState lastFuelState = null;


        logger.initialiseUI();
        logger.logInfo("AFRM Started : " + departure + " to " + destination + ". Scenario: " + scenario.scenarioID(), elapsedTime);
        List<String> logs = new ArrayList<>();
       
        double tick = 0.1;

        FuelStatus fuelStatus = new FuelStatus(currentFuelLevel);

        if (currentFuelLevel < 0.1 * afrm.calc.FuelCalculator.calculateRequiredFuel(remainingDistance, fuelConsumptionRate)) {
            logger.logWarning("Initial fuel level is low", elapsedTime);
        } else if (currentFuelLevel < 0.05 * afrm.calc.FuelCalculator.calculateRequiredFuel(remainingDistance, fuelConsumptionRate)) {
            logger.logCritical("Initial fuel level is critical", elapsedTime);
        } else if (currentFuelLevel < afrm.calc.FuelCalculator.calculateRequiredFuel(remainingDistance, fuelConsumptionRate)) {
            logger.logCritical("Initial fuel level is insufficient for the flight", elapsedTime);
        } else {
            logger.logInfo("Initial fuel level is sufficient for the flight", elapsedTime);
        }

        FuelState fuelState = fuelStatus.checkFuelStatus(remainingDistance, cruiseSpeed, fuelConsumptionRate);


        while (remainingDistance > 0 && currentFuelLevel > 0) {   
            
            double actualSpeed = cruiseSpeed - eventValue(scenario, EventType.HEADWIND_KMH, elapsedTime);
            double actualFuelConsumptionRate = fuelConsumptionRate + eventValue(scenario, EventType.EXTRA_BURN_KG_PER_HOUR, elapsedTime);

            fuelStatus = new FuelStatus(currentFuelLevel);
            fuelState = fuelStatus.checkFuelStatus(remainingDistance, cruiseSpeed, actualFuelConsumptionRate);

            fuelBurned = actualFuelConsumptionRate * tick;
            currentFuelLevel -= fuelBurned;
            remainingDistance -= actualSpeed * tick;
            fuelStatus = new FuelStatus(currentFuelLevel);
            fuelState = fuelStatus.checkFuelStatus(remainingDistance, cruiseSpeed, actualFuelConsumptionRate);

            if (fuelState == FuelState.LOW) {
                logs.add(logger.logWarning("Fuel level is low", elapsedTime));
            } else if (fuelState == FuelState.CRITICAL) {
                logs.add(logger.logCritical("Fuel level is critical", elapsedTime));
            } else if (fuelState == FuelState.INSUFFICIENT_CAPACITY) {
                logs.add(logger.logWarning("Insufficient fuel capacity for the flight", elapsedTime));
            } else if (fuelState == FuelState.NORMAL) {
                logs.add(logger.logInfo("Fuel level is normal", elapsedTime));
            }

            double averageFuelConsumption = elapsedTime > 0 ? fuelBurned / elapsedTime : fuelConsumptionRate;
            logger.updateUI(new FlightSnapshot(elapsedTime, remainingDistance, currentFuelLevel,
                    averageFuelConsumption, destination, fuelState));

            double flightTime = Math.min(1.0 / 60.0, remainingDistance / cruiseSpeed);
            double fuelUsed = fuelConsumptionRate * flightTime;
            if (fuelUsed > currentFuelLevel) {
                fuelUsed = currentFuelLevel;
                flightTime = fuelUsed / fuelConsumptionRate;
            }
            currentFuelLevel = Math.max(0.0, currentFuelLevel - fuelUsed);
            fuelBurned += fuelUsed;
            remainingDistance = Math.max(0.0, remainingDistance - cruiseSpeed * flightTime);
            elapsedTime += flightTime;



            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
            elapsedTime += tick;
        }
        
    }

    private static double eventValue(FlightScenario scenario, EventType type, double elapsedTime) {
        double value = 0.0;
        for (FlightSimData.Event event : scenario.events()) {
            if (event.type() == type && event.startHour() <= elapsedTime) {
                value = event.value();
            }
        }
        return value;
    }
}
