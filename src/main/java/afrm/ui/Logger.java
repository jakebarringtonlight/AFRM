package afrm.ui;
import java.util.List;

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
         System.out.println("========================================");
        System.out.println("LOGS:");
        System.out.flush();
    }


    public void updateUI(FlightSnapshot snapshot) {
        System.out.print("\0337");
        System.out.print("\033[H"); 

        overwriteValue("========================================");
        overwriteValue("..................AFRM..................");
        overwriteValue("Fuel Status: " + colorText(snapshot.fuelState()) + snapshot.fuelState() + RESET);
        overwriteValue("Elapsed Flight Time: " + timeFormat(snapshot.elapsedTime()));
        overwriteValue("Distance To Destination: " + snapshot.distanceToDestination() + " km");
        overwriteValue("Fuel Remaining: " + snapshot.fuelRemaining() + " kg");
        overwriteValue("Average Fuel Consumption: " + snapshot.averageFuelConsumptionPerHour() + " kg/h");
        overwriteValue("Destination Airport: " + snapshot.destination());
        overwriteValue("========================================");
        
        System.out.print("\0338");
        System.out.flush();
    }
    
    public String logInfo(String info, double elapsedTime){
        
        return "INFO: " + info + ". Elapsed time: " + elapsedTime;
    }

    public String logWarning(String warning, double elapsedTime){
        
        return "WARNING: " + warning + ". Elapsed time: " + elapsedTime;
    }

    public String logCritical(String critical, double elapsedTime){
        
        return "CRITICAL: " + critical + ". Elapsed time: " + elapsedTime;
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
