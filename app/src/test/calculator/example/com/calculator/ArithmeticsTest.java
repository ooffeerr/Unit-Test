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
	private static final int ZERO = 0;

	@Test
	public void testTwoTimesTwo_FourIsReturned() throws Exception {
		// notice the AAA pattern : arrange, act, assert
		// Arrange
		// 1. The dependencies, should hopefully always be mocked. Notice - the listener isn't really used by the tested flow, but we strive to use the same
		// constructor we use in real life. This is not always possible.
		// 2. "Dependencies injection" - is the process of giving an object everything it's dependent on. If our code is indeed ideally testable - all
		// dependencies should be injected during construction or as the tested method's params.

		IArithmeticsListener listener = Mockito.mock(IArithmeticsListener.class);
		IAddImplSelector iAddImplSelector = Mockito.mock(IAddImplSelector.class);
		Arithmetics arithmetics = new Arithmetics(listener, iAddImplSelector);

		//Act - where the tested code is actually run.
		int result = arithmetics.multiply(TWO, TWO);

		//Assert - where we expect a result. - REMEMBER! IT IS CRUCIAL TO SEE THE TEST FAILS!
		assertEquals("product value is wrong", FOUR, result);
	}

	@Test
	public void testSlowAddTwoPlusTwo_performAddTrue_FourIsDelegated() throws Exception {
		//Arrange
		IArithmeticsListener listener = Mockito.mock(IArithmeticsListener.class);
		IAddImplSelector iAddImplSelector = Mockito.mock(IAddImplSelector.class);

		/*
		NOTICE:
		 1.  Unit Tests can't handle randomness. A Unit Tests that fails or succeeds only half of the time is more bad then good - as it requires maintaining
		 (Imagine seeing random failures on our Build server :0 )
		 2. One way of handling - extract the randomness to a dependency, and mock it.
		 3. A similar Unit Test should/can be written with the other return value of
		 */
		Mockito.when(iAddImplSelector.performAdd()).thenReturn(true);

		Arithmetics arithmetics = new Arithmetics(listener, 0, iAddImplSelector);

		// act
		arithmetics.slow_add(TWO, TWO);


		Thread.sleep(20);										// once again - SEE THE TEST FAIL! - fixed the result
		Mockito.verify(listener, Mockito.times(1)).onSlowAddCompleted(FOUR);
	}

	@Test
	public void testSlowAddTwoPlusTwo_performAddFalse_ZeroIsDelegated() throws Exception {
		//Arrange
		IArithmeticsListener listener = Mockito.mock(IArithmeticsListener.class);
		IAddImplSelector iAddImplSelector = Mockito.mock(IAddImplSelector.class);

		/*
		NOTICE:
		An exact test like the one above. lots of duplicated code, for
		 */
		Mockito.when(iAddImplSelector.performAdd()).thenReturn(false);

		Arithmetics arithmetics = new Arithmetics(listener, 0, iAddImplSelector);

		// act
		arithmetics.slow_add(TWO, TWO);

		Thread.sleep(20);											// This is the correct test result - but again, DON'T LET THE TEST PASS WITHOUT SEEING IT FAIL
		Mockito.verify(listener, Mockito.times(1)).onSlowAddCompleted(ZERO);
	}
}
