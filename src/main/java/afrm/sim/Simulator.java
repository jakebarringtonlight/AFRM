package afrm.sim;

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
        

        // Initialising all the variables from the constants and FlightScenario.
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
        double fuelConsumed = 0.0;
        FuelState lastFuelState = null;

        logger.initialiseUI();
        logger.printLog(logger.logInfo("AFRM Started : " + departure + " to " + destination + ". Scenario: " + scenario.scenarioID(), elapsedTime));
    
        FuelStatus fuelStatus = new FuelStatus(currentFuelLevel);


        // Check to ensure fuel level is valid and sufficient to make it to destination.
        if (currentFuelLevel > aircraftModel.maxFuelCapacityKg()
        || !fuelStatus.isFuelSufficient(aircraftModel, flightPlan)) {
        logger.updateUI(new FlightSnapshot(scenario.scenarioID(), elapsedTime, remainingDistance, currentFuelLevel,
                fuelConsumptionRate, destination, FuelState.INSUFFICIENT_CAPACITY));
        logger.printLog(logger.logCritical("Insufficient fuel capacity for the flight plan", elapsedTime));
        return;
        }

        FuelState fuelState = fuelStatus.checkFuelStatus(remainingDistance, cruiseSpeed, fuelConsumptionRate, aircraftModel.maxFuelCapacityKg());

        // Loop to simulate the time passed in the flight. 
        while (remainingDistance > 0 && currentFuelLevel > 0) {   
            
            double actualSpeed = Math.max(1.0, cruiseSpeed - eventValue(scenario, EventType.HEADWIND_KMH, elapsedTime));
            double actualFuelConsumptionRate = fuelConsumptionRate + eventValue(scenario, EventType.EXTRA_BURN_KG_PER_HOUR, elapsedTime);

            fuelStatus = new FuelStatus(currentFuelLevel);
            fuelState = fuelStatus.checkFuelStatus(remainingDistance, actualSpeed, actualFuelConsumptionRate, aircraftModel.maxFuelCapacityKg());

            // If the fuelState changes then a new log is printed.
            if (fuelState != lastFuelState) {

            if (fuelState == FuelState.LOW) {
                logger.printLog(logger.logWarning("Fuel level is low", elapsedTime));

            } else if (fuelState == FuelState.CRITICAL) {
                logger.printLog(logger.logCritical("Fuel level is critical", elapsedTime));

            } else if (fuelState == FuelState.NORMAL) {
                logger.printLog(logger.logInfo("Fuel level is normal", elapsedTime));

            } else if (fuelState == FuelState.INSUFFICIENT_CAPACITY) {
                logger.printLog(logger.logCritical("Insufficient fuel to complete flight", elapsedTime));
            }
            }
            lastFuelState = fuelState;

            // Simulating the 'real time' flight value updates.
            double averageFuelConsumption = elapsedTime > 0 ? fuelConsumed / elapsedTime : fuelConsumptionRate;
            logger.updateUI(new FlightSnapshot(scenario.scenarioID(), elapsedTime, remainingDistance, currentFuelLevel,
                    averageFuelConsumption, destination, fuelState));

            double flightTime = Math.min(1.0 / 60.0, remainingDistance / actualSpeed);
            double fuelUsed = actualFuelConsumptionRate * flightTime;
            if (fuelUsed > currentFuelLevel) {
                fuelUsed = currentFuelLevel;
                flightTime = fuelUsed / actualFuelConsumptionRate;
            }
            currentFuelLevel = Math.max(0.0, currentFuelLevel - fuelUsed);
            fuelConsumed += fuelUsed;
            remainingDistance = Math.max(0.0, remainingDistance - actualSpeed * flightTime);
            if (remainingDistance < 0.001) {
            remainingDistance = 0.0;
            }
            elapsedTime += flightTime;


            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        if (currentFuelLevel <= 0 && remainingDistance > 0) {
            fuelState = FuelState.CRITICAL;
            }

        double finalAverageConsumption = elapsedTime > 0
                ? fuelConsumed / elapsedTime
                : fuelConsumptionRate;
            
        //Final UI Values.
        logger.updateUI(new FlightSnapshot(
                scenario.scenarioID(),
                elapsedTime,
                remainingDistance,
                currentFuelLevel,
                finalAverageConsumption,
                destination,
                fuelState
        ));
    
    }

    // Method to simulate the 'real time' flight value updates.
    private static double eventValue(FlightScenario scenario, EventType type, double elapsedTime) {
        double value = 0.0;
        for (FlightSimData.Event event : scenario.events()) {
            if (event.type() == type && event.startHour() <= elapsedTime) {
                value += event.value();
            }
        }
        return value;
    }
}