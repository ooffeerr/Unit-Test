package calculator.example.com.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements IArithmeticsListener{

	EditText number_1;
	EditText number_2;
	private Button multipy;
	private Button slow_add;
	private EditText result;
	private IArithmetics arithmetics;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		number_1 = (EditText) findViewById(R.id.number_1);
		number_2 = (EditText) findViewById(R.id.number_2);
		multipy = (Button) findViewById(R.id.multiply);
		slow_add = (Button) findViewById(R.id.slow_add);
		result = (EditText) findViewById(R.id.result);

		multipy.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (validInput(number_1, number_2)) {
					int num1 =  Integer.parseInt(number_1.getText().toString());
					int num2 = Integer.parseInt(number_2.getText().toString());
					int product = arithmetics.multiply(num1, num2);
					result.setText(String.valueOf(product));
				}
			}
		});

		slow_add.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!validInput(number_1, number_2)) return;

				int num1 = Integer.valueOf(number_1.getText().toString());
				int num2 = Integer.valueOf(number_2.getText().toString());
				arithmetics.slow_add(num1, num2);
			}
		});

		setArithmetics(new Arithmetics(this));
	}

	private boolean validInput(EditText number_1, EditText number_2) {
		return !TextUtils.isEmpty(number_1.getText()) &&
				!TextUtils.isEmpty(number_2.getText());
	}

	@Override
	public void onSlowAddCompleted(final int sum) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				result.setText(String.valueOf(sum));
			}
		});
	}

	public void setArithmetics(IArithmetics arithmetics) {
		this.arithmetics = arithmetics;
	}
}
