package zad1;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

public class CalculatorTest {

	Calculator calc = new Calculator();

	@Test
	public void checkAdding() {
		assertEquals(7, calc.add(4, 3));

	}
	@Ignore
	@Test(expected = IllegalArgumentException.class) //ma wystapic blad
	public void testNegativeNumbersShouldThrowException() {
		calc.add(-3, -4);
	}

	@Test
	public void checkSubstract() {
		assertEquals(1, calc.sub(3, 2));
	}

	@Test
	public void checkMulti() {
		assertEquals(10, calc.multi(5, 2));
	}

	@Test
	public void checkDivide() {
		assertEquals(2, calc.div(4, 2));
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
