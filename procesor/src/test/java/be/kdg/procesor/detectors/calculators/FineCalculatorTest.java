package be.kdg.procesor.detectors.calculators;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Testing the FineCalculator
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FineCalculatorTest {

    @Autowired
    private FineCalculator fineCalculator;

    @Test
    public void calculate() {
        double fine = fineCalculator.calculate();
        assertEquals(250, fine, 1e-15);
    }

    @Test
    public void calculateSpeeding() {
        double fine = fineCalculator.calculate(100, 50);
        double expect = (100 - 50) * 2;
        assertEquals(expect, fine, 1e-15);
    }
}