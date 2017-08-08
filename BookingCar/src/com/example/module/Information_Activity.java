package com.example.module;

import android.app.Activity;

import com.example.bookingcar.R;
import com.example.tool.Tools;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class Information_Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Tools.CancelActionbar(Information_Activity.this);
		setContentView(R.layout.activity_information);

	}

}
