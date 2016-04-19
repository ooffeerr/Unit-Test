package calculator.example.com.calculator;

/**
 * Implemetation for various arithmetics operations
 */
public class Arithmetics implements IArithmetics {

	private final IArithmeticsListener listener;
	private final long sleepDuration;

	public Arithmetics(IArithmeticsListener listener) {
		this.listener = listener;
		this.sleepDuration = 2000;
	}

	/*
	Notice: this is a private (Package protected). We don't want to use it elsewhere, other then the tests.
	Some points for discussion:
	1. Is this meaning altering the code's behaviour?
	2. What else can be done to minimize the pain with async code testing? (Hint : another class extraction)
	 */

	Arithmetics(IArithmeticsListener listener, long sleepDuration) {
		this.listener = listener;
		this.sleepDuration = sleepDuration;
	}

	public int multiply(int num1, int num2) {
		return num1 * num2;
	}

	public void slow_add(final int num1, final int num2) {
		new Thread() {
			@Override
			public void run() {
				try {
					sleep(sleepDuration);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				// the long calculation is done on the worker thread.
				final int sum = num1 + num2;
				listener.onSlowAddCompleted(sum);
			}
		}.start();
	}
}
