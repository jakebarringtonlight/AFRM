package afrm;

import afrm.sim.FlightSimData;
import afrm.sim.FlightSimData.FlightScenario;
import afrm.sim.Simulator;

public final class AFRM {
    
    private AFRM() {}

    public static void main(String[] args) {

        for (FlightScenario scenario : FlightSimData.SCENARIOS) {
            Simulator.runSimulation(scenario);
        }
    }
}
