package calculator.example.com.calculator;

/**
 * A hilarious selector that returns true half of the times
 */
public class SelectorImplementation implements IAddImplSelector{

	@Override
	public boolean performAdd() {
		return Math.random() > 0.5 ;
	}
}
