package com.example.tool;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class Tools {
	@SuppressLint("NewApi")
	public static void CancelActionbar(Activity activity){
		activity.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
	    if(VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
	      Window window = activity.getWindow();
	      window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
	          | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
	      window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
	              | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
	              | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
	      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
	      window.setStatusBarColor(Color.TRANSPARENT);
	      window.setNavigationBarColor(Color.TRANSPARENT);
	    }
	}
}
