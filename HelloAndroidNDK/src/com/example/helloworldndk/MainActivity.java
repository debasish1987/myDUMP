package com.example.helloworldndk;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
	public native String addFromJNICPP(int a, int b);
	
	static {
		System.loadLibrary("compJNI");
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TextView myTextField = (TextView) findViewById(R.id.myTextField);
		myTextField.setText(addFromJNICPP(2,3).toString());
		
	}
}
