package calculator.example.com.calculator;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

/**
 * Click listener that calls arithmetics for multiply
 */
class MultiplyClickListener implements View.OnClickListener {

	private final EditText number1;
	private final EditText number2;
	private final IArithmetics arithmetics;
	private final EditText result;

	public MultiplyClickListener(EditText number1, EditText number2, IArithmetics arithmetics, EditText result) {
		this.number1 = number1;
		this.number2 = number2;
		this.result = result;
		this.arithmetics = arithmetics;
	}

	@Override
	public void onClick(View v) {
		if (validInput(number1, number2)) {
			int num1 = Integer.parseInt(number1.toString());
			int num2 = Integer.parseInt(number2.toString());
			int product = arithmetics.multiply(num1, num2);
			result.setText(String.valueOf(product));
		}
	}

	private boolean validInput(EditText number_1, EditText number_2) {
		return !TextUtils.isEmpty(number_1.toString()) &&
				!TextUtils.isEmpty(number_2.toString());
	}
}
