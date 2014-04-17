package com.wizard.android.activity;

import com.wizard.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class StartActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		Log.i("----------------------------------",
				"++++++++++++++++++++++++++");
	}

}
