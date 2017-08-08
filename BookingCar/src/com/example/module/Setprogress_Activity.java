package com.example.module;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import com.example.bookingcar.R;
import com.example.tool.Tools;
public class Setprogress_Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Tools.CancelActionbar(Setprogress_Activity.this);
		setContentView(R.layout.activity_setprogress);

	}

}
