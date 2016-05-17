package zad2;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {

	Calculator calc = new Calculator();
	
	 @Before
	    public void runBeforeEveryTest() {
	        calc.currentTotal = 0;
	    }
	
	@Test
	public void checkAdding() {
		 calc.currentTotal = 7;
		 assertTrue(calc.currentTotal== calc.add(4, 3));
	}

	@Test
	public void checkSubstract() {
		calc.currentTotal = 1;
		assertTrue(calc.currentTotal == calc.sub(3, 2));
	}

	@Test
	public void checkMulti() {
		calc.currentTotal = 10;
		assertTrue(calc.currentTotal == calc.multi(5, 2));
	}

	@Test
	public void checkDivide() {
		calc.currentTotal = 2;
		assertTrue(calc.currentTotal == calc.div(4, 2));
	}

	@Test
	public void checkGreater() {
		assertEquals(calc.greater(4, 2), true);
	}

	@Test(expected = ArithmeticException.class)
	public void checkArithmeticException() {
		double a = 10 / 0;
	}
}
