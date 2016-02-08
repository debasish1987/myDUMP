package com.semco.ipo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		   MenuInflater inflater = getMenuInflater();
		    inflater.inflate(R.menu.main, menu);
		    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.java:
			startActivity(new Intent(this,JavaActivity.class));
			break;

		case R.id.thread:
			startActivity(new Intent(this,ThreadActivity.class));
			break;
			
		case R.id.jniA:
			startActivity(new Intent(this,JNIActivity.class));
			break;
			
		case R.id.jniThreadA:
			startActivity(new Intent(this,JniThreadActivity.class));
			break;
			
		case R.id.rsA:
			startActivity(new Intent(this,RSActivity.class));
			break;
			
		default:
			break;
		}
		
		
		return super.onOptionsItemSelected(item);
	}
}
