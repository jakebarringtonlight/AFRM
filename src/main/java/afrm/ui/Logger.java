package afrm.ui;

import afrm.model.FlightSnapshot;
import afrm.model.FuelState;

public class Logger {

    private static final String RESET = "\033[0m";
    private static final String RED = "\033[31m";
    private static final String YELLOW = "\033[33m";
    private static final String GREEN = "\033[32m";

    public Logger() {}
    
    public void initialiseUI() {
        System.out.print("\033[2J\033[H");

        for (int i = 0; i < 10; i++) {
            System.out.println();
        }

        System.out.print("\033[12;1H");
        System.out.println("LOGS:");
        System.out.flush();
    }


    public void updateUI(FlightSnapshot snapshot) {
        System.out.print("\0337");
        System.out.print("\033[1;1H");

        overwriteValue("========================================");
        overwriteValue("..................AFRM..................");
        overwriteValue("Scenario: " + snapshot.scenarioID());
        overwriteValue("Fuel Status: " + colorText(snapshot.fuelState()) + snapshot.fuelState() + RESET);
        overwriteValue("Elapsed Flight Time: " + timeFormat(snapshot.elapsedTime()));
        overwriteValue(String.format("Distance To Destination: %.2f km", snapshot.distanceToDestination()));
        overwriteValue(String.format("Fuel Remaining: %.2f kg", snapshot.fuelRemaining()));
        overwriteValue(String.format("Average Fuel Consumption: %.2f kg/h", snapshot.averageFuelConsumptionPerHour()));
        overwriteValue("Destination Airport: " + snapshot.destination());
        overwriteValue("========================================");
        
        System.out.print("\0338");
        System.out.flush();
    }
    
    public String logInfo(String info, double elapsedTime){
        
        return "Elapsed Time: " + timeFormat(elapsedTime) + ". INFO: " + info;
    }

    public String logWarning(String warning, double elapsedTime){
        
        return "Elapsed Time: " + timeFormat(elapsedTime) + ". WARNING: " + warning;
    }

    public String logCritical(String critical, double elapsedTime){
        
        return "Elapsed Time: " + timeFormat(elapsedTime) + ". CRITICAL: " + critical;
    }

    public void printLog(String log){
        System.out.println(log);
        System.out.flush();
    }

        private static void overwriteValue(String text) {
        System.out.println(text + "\033[K");
    }

    private static String colorText(FuelState fuelState) {
        switch (fuelState) {
            case NORMAL -> {
                return GREEN;
            }
            case LOW -> {
                return YELLOW;
            }
            case CRITICAL, INSUFFICIENT_CAPACITY -> {
                return RED;
            }
            default -> {
                return RESET;
            }
        }
    }

    public static String timeFormat(double elapsedTime) {
        int hours = (int) elapsedTime;
        int minutes = (int) ((elapsedTime - hours) * 60);
        return String.format("%02d:%02d", hours, minutes);
    }

}
