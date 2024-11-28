package testmaven;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cg.Calculator;
import com.cg.ICalculator;

public class CalculatorTest {
	private static ICalculator calculator;
	 
    @BeforeAll
    public static void initCalculator() {
        calculator = new Calculator();
        System.out.println(" Hello ! Before All Tests");
        
    }
   

    @Test // This is a test case
    public void testSum() {
        int result = calculator.sum(3, 4);
        assertEquals(7, result);
    }
    @Test
    public void testsubtract() {
        int result = calculator.sub(5, 4);
        assertEquals(1, result);
    }
    @Test
    public void testmulti() {
        int result = calculator.multiplication(3, 4);
        assertEquals(12, result);
    }
    @Test
    public void testdiv() throws Exception {
        int result = calculator.division(10, 2);
        assertEquals(5, result);
    }
    @Test
    public void testequal() {
        boolean result = calculator.equalInteger(4, 4);
        assertEquals(true, result);
    }
    @Test
    public void testDivisionException() {
        // Expecting an Exception to be thrown when dividing by zero
        Exception exception = assertThrows(
        		                          Exception.class, () -> {
        		                        	  					    calculator.division(10, 0);
        		                          						  });
        assertEquals("deno cannot be zero", exception.getMessage());
    }
    @BeforeEach 
    public void beforeEachTest() {
        System.out.println("This is executed before each Test");
    }
}
