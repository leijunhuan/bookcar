package com.example.bookingcar;

import android.app.Activity;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.VolleyError;
import com.example.callback.AlertDialogListenr;
import com.example.callback.VolleyResponeListenr;
import com.example.tool.Alertdialogs;
import com.example.tool.DefineApplication;
import com.example.tool.Record_Activity;
import com.example.tool.Tools;
import com.example.tool.VolleyJson;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.os.Build;

public class Login_Activity extends Activity implements OnClickListener {
	Button login_click;
	Button register_click;
	EditText phone;
	EditText password;
	String user, pass;
	Context context;
	private DefineApplication defineApplication = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Tools.CancelActionbar(Login_Activity.this);
		setContentView(R.layout.activity_login);
		defineApplication = (DefineApplication) getApplication();
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		login_click = (Button) findViewById(R.id.login);
		register_click = (Button) findViewById(R.id.register);
		phone = (EditText) findViewById(R.id.phone);
		password = (EditText) findViewById(R.id.password);
		login_click.setOnClickListener(this);
		register_click.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.login:
			user = phone.getText().toString().trim();
			pass = password.getText().toString().trim();

			if (user.length() != 0 && pass.length() != 0) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("coachphone", user);
				map.put("coachpassword", pass);
				VolleyJson.getJSONVolley(this, "/coach/login", map);
				VolleyJson.setResponseListener(new VolleyResponeListenr() {

					@Override
					public void ResponseSucc(JSONObject response) {
						try {
							if (response.getInt("coach") > 0) {
								Alertdialogs.alertdialog(true, "µ«¬º≥…π¶", Login_Activity.this);
								defineApplication.coach = response.getString("coach");
								defineApplication.phone = response.getString("phone");
								Alertdialogs.setResponseListener(new AlertDialogListenr() {

									@Override
									public void Dialogs(Boolean sumbit) {
										// TODO Auto-generated method stub
										if (sumbit) {
											Intent intent = new Intent(Login_Activity.this, HomepageActivity.class);
											startActivity(intent);
											finish();
											overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
										}
									}
								});
							} else {
								Alertdialogs.alertdialog(false, "µ«¬º ß∞‹,«ÎºÏ≤È’À∫≈√‹¬Î", Login_Activity.this);
							}
						} catch (JSONException e) { // TODO Auto-generated catch
													// block
							e.printStackTrace();
						}
					}

					@Override
					public void ResponseError(VolleyError error) {
						// TODO Auto-generated method stub
					}

				});

			} else {
				Alertdialogs.alertdialog(false, "’À∫≈√‹¬Î≤ªÕÍ’˚!", Login_Activity.this);
			}
			break;

		case R.id.register:
			Intent intent = new Intent(Login_Activity.this, RegisterActivity.class);
			startActivity(intent);
			finish();
			overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
			break;
		}
	}

}
