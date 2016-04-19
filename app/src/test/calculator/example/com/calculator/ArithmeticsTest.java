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
		assertEquals("product value is wrong", FOUR, result);
	}

	@Test
	public void testSlowAddTwoPlusTwo_FourIsDelegated() throws Exception {
		//Arrange
		IArithmeticsListener listener = Mockito.mock(IArithmeticsListener.class);
		Arithmetics arithmetics = new Arithmetics(listener, 0);

		// act
		arithmetics.slow_add(TWO, TWO);

		/*
		assert: Discussion points :
		1. Having Sleep durations inside a test is a bad thing. consider tens of tests that sleep and have a very long running test suite -
		while a major Unit Test's forte is it's fastness.
		2. Alot of methods don't have return values, but their calculation still need to be verified - the {@link Mockito#verify} method to the rescue.
		3. Notice the importance of encapsulation - the Arithmetics class doesn't know or care about how the listener is implemented, so the correctness of
		 of Arithmetics has nothing to do with the listener (This is how we gain great code by aiming for testability, even without writing the tests themselves.

		Side note: This separation of concerns can't happen when using inheritance: inheritance means each inheriting class's correctness depends on it's parent correctness -
		  Hence each piece inheriting code is born with a hunchback - it's never independent.
		   Whenever possible, when aiming for Unit Tests, try to avoid inheritance, and use composition (a reference to the common behaviour) instead.
		 */


		Thread.sleep(20);										// once again - SEE THE TEST FAIL!
		Mockito.verify(listener, Mockito.times(1)).onSlowAddCompleted(1);
	}
}
