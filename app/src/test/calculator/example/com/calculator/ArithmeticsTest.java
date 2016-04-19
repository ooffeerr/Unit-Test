package calculator.example.com.calculator;

import org.junit.Test;
import org.mockito.Mockito;
import android.test.AndroidTestCase;

import static junit.framework.Assert.assertEquals;

/**
 * Testing for {@link Arithmetics}
 */
public class ArithmeticsTest  {

	private static final int TWO = 2;
	private static final int FOUR = 4;

	@Test
	public void testTwoTimesTwo_FourIsReturned() throws Exception {
		// notice the AAA pattern : arrange, act, assert
		// Arrange
		// 1. The dependencies, should hopefully always be mocked. Notice - the listener isn't really used by the tested flow, but we strive to use the same
		// constructor we use in real life. This is not always possible.
		// 2. "Dependencies injection" - is the process of giving an object everything it's dependent on. If our code is indeed ideally testable - all
		// dependencies should be injected during construction or as the tested method's params.

		IArithmeticsListener listener = Mockito.mock(IArithmeticsListener.class);
		Arithmetics arithmetics = new Arithmetics(listener);

		//Act - where the tested code is actually run.
		int result = arithmetics.multiply(TWO, TWO);

		//Assert - where we expect a result. - REMEMBER! IT IS CRUCIAL TO SEE THE TEST FAILS!
		assertEquals("product value is wrong", 0, result);
	}

}
