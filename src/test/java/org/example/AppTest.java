package org.example;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        String cc = "5.41E+15";
        System.out.println(new BigDecimal(cc).longValue());
        assertTrue( true );
    }
}
