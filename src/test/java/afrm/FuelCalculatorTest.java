
package afrm;

import afrm.calc.FuelCalculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FuelCalculatorTest {

    @Test
    void calculateFlightTimeReturnsCorrectValue() {
        double result = FuelCalculator.calculateFlightTime(1000, 500);
        assertEquals(2.0, result);
    }

    @Test
    void calculateRequiredReserveFuelReturnsFivePercent() {
        double result = FuelCalculator.calculateRequiredReserveFuel(10000);
        assertEquals(500, result);
    }

    @Test
    void calculateRemainingRangeReturnsCorrectValue() {
        double result = FuelCalculator.calculateRemainingRange(5000, 2500, 500);
        assertEquals(1000, result);
    }

    @Test
    void calculateFlightTimeReturnsZeroForInvalidInputs() {
        assertEquals(0, FuelCalculator.calculateFlightTime(0, 500));
        assertEquals(0, FuelCalculator.calculateFlightTime(1000, 0));
        assertEquals(0, FuelCalculator.calculateFlightTime(-1000, 500));
    }

    @Test
    void calculateRequiredFuelAppliesMultiplier() {
        double result = FuelCalculator.calculateRequiredFuel(1000, 500, 1000);
        assertEquals(2000, result);
    }

    @Test
    void calculateRemainingRangeReturnsZeroWithNoFuel() {
        double result = FuelCalculator.calculateRemainingRange(0, 2500, 500);
        assertEquals(0, result);
    }

    @Test
    void calculateRemainingPossibleFlightTimeReturnsZeroWithNoFuel() {
        double result = FuelCalculator.calculateRemainingPossibleFlightTime(0, 2500);
        assertEquals(0, result);
    }
}
