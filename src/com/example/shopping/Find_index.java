package com.example.shopping;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Find_index extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.find_index);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.find_index, menu);
		return true;
	}

}
