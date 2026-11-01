
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
}
