
package afrm;
import afrm.model.FuelStatus;
import afrm.model.FuelState;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FuelStatusTest {

    private static final double MAX_FUEL_CAPACITY = 19000.0;

    @Test
    void sufficientFuelReturnsNormal() {
        FuelStatus fuelStatus = new FuelStatus(15000);

        assertEquals(FuelState.NORMAL,
                fuelStatus.checkFuelStatus(1000, 833.5, 2500, MAX_FUEL_CAPACITY));
    }

    @Test
    void lowFuelReturnsLow() {
        FuelStatus fuelStatus = new FuelStatus(4000);

        assertEquals(FuelState.LOW,
                fuelStatus.checkFuelStatus(100, 833.5, 2500, MAX_FUEL_CAPACITY));
    }

    @Test
    void criticalFuelReturnsCritical() {
        FuelStatus fuelStatus = new FuelStatus(1000);

        assertEquals(FuelState.CRITICAL,
                fuelStatus.checkFuelStatus(100, 833.5, 2500, MAX_FUEL_CAPACITY));
    }

    @Test
    void insufficientCapacityReturnsInsufficientCapacity() {
        FuelStatus fuelStatus = new FuelStatus(19000);

        assertEquals(FuelState.INSUFFICIENT_CAPACITY,
                fuelStatus.checkFuelStatus(8000, 833.5, 2500, MAX_FUEL_CAPACITY));
    }
}

