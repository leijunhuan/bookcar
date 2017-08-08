package com.example.bookingcar;

import com.example.tool.Tools;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import cn.jpush.android.api.JPushInterface;


public class MainActivity extends Activity {
	private final int SPLASH_DISPLAY_LENGHT = 3000; // —”≥Ÿ¡˘√Î

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Tools.CancelActionbar(MainActivity.this);
		setContentView(R.layout.activity_main);
		delay();
	}

	public void delay(){
		new Handler().postDelayed(new Runnable() {
			public void run() {
				Intent mainIntent = new Intent(MainActivity.this, HomepageActivity.class);
				MainActivity.this.startActivity(mainIntent);
				MainActivity.this.finish();
			}

		}, SPLASH_DISPLAY_LENGHT);
	}
}