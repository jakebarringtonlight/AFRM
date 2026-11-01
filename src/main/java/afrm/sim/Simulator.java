package afrm.sim;

import java.util.ArrayList;
import java.util.List;

import afrm.model.FuelState;
import afrm.model.FuelStatus;
import afrm.ui.Logger;

public class Simulator {
    
    public static void runSimulation(FlightSimData.FlightScenario scenario) {
        
        
        double currentFuelLevel = scenario.initialFuelKg();

        double elapsedTime = 0.0;
        Logger logger = new Logger();
        logger.logInfo("AFRM Started", elapsedTime);
        List<String> logs = new ArrayList<>();

        double remainingDistance = scenario.flightPlan().getTotalDistanceKm();
        double cruiseSpeed = scenario.aircraftModel().cruiseSpeedKmh();
        double fuelConsumptionRate = scenario.aircraftModel().fuelConsumptionRateKgPerHour();
        double fuelBurned = 0.0;
        final double tick = 0.1;

        FuelStatus fuelStatus = new FuelStatus(currentFuelLevel);
        FuelState fuelState = fuelStatus.checkFuelStatus(remainingDistance, cruiseSpeed, fuelConsumptionRate);


        while (remainingDistance > 0 && currentFuelLevel > 0) {   
            
            fuelBurned = fuelConsumptionRate * tick;
            currentFuelLevel -= fuelBurned;
            remainingDistance -= cruiseSpeed * tick;
            fuelStatus = new FuelStatus(currentFuelLevel);
            fuelState = fuelStatus.checkFuelStatus(remainingDistance, cruiseSpeed, fuelConsumptionRate);

            if (fuelState == FuelState.LOW) {
                logs.add(logger.logWarning("Fuel level is low", elapsedTime));
            } else if (fuelState == FuelState.CRITICAL) {
                logs.add(logger.logCritical("Fuel level is critical", elapsedTime));
            } else if (fuelState == FuelState.INSUFFICIENT) {
                logs.add(logger.logWarning("Insufficient fuel capacity for the flight", elapsedTime));
            } else if (fuelState == FuelState.NORMAL) {
                logs.add(logger.logInfo("Fuel level is normal", elapsedTime));
            }


            logger.updateUI(logs);

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
            elapsedTime += tick;
        }
        
    }

}
