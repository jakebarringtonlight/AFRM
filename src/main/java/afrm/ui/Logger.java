package afrm.ui;
import java.util.List;

public class Logger {
    
    public void updateUI(List<String> logs){

        System.out.print("\033[H\033[2J");
        System.out.flush();
        cockpitDisplay(logs);
    }

    public String logInfo(String info, String elapsedTime){
        
        return "INFO: " + info + ". Elapsed time: " + elapsedTime;
    }

    public String logWarning(String warning, String elapsedTime){
        
        return "WARNING: " + warning + ". Elapsed time: " + elapsedTime;
    }

    public String logCritical(String critical, String elapsedTime){
        
        return "CRITICAL: " + critical + ". Elapsed time: " + elapsedTime;
    }

    public void cockpitDisplay(List<String> logs){
        
        System.out.println("========================================");
        System.out.println("..................AFRM..................");
        System.out.println("Fuel Status: ");
        System.out.println("Elapsed Flight Time: ");
        System.out.println("Distance To Destination: ");
        System.out.println("Fuel Remaining: ");
        System.out.println("Average Fuel Consumption: ");
        System.out.println("Destination Airport: ");
        System.out.println("ETA: ");
        System.out.println("========================================");
        System.out.println("LOGS: ");
        printLogs(logs);
    }

    public void printLogs(List<String> logs){

        for (String log : logs){
            System.out.println(log);
        }
        
    }

}
