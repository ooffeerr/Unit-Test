package calculator.example.com.calculator;

/**
 * Implemetation for various arithmetics operations
 */
public class Arithmetics implements IArithmetics {

	private final IArithmeticsListener listener;
	private final long sleepTimeout;
	private final IAddImplSelector implSelector;

	public Arithmetics(IArithmeticsListener listener, IAddImplSelector implSelector) {
		this.listener = listener;
		this.sleepTimeout = 2000;
		this.implSelector = implSelector;
	}

	Arithmetics(IArithmeticsListener listener, long sleepTimout, IAddImplSelector implSelector) {
		this.listener = listener;
		this.sleepTimeout = sleepTimout;
		this.implSelector = implSelector;
	}

	public int multiply(int num1, int num2) {
		return num1 * num2;
	}

	public void slow_add(final int num1, final int num2) {
		new Thread() {
			@Override
			public void run() {
				try {
					sleep(sleepTimeout);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				// the long calculation is done on the worker thread.
				final int sum = implSelector.performAdd() ? num1 + num2 : num1 - num2;
				listener.onSlowAddCompleted(sum);
			}
		}.start();
	}
}
