package com.example.bookingcar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.VolleyError;
import com.example.callback.AlertDialogListenr;
import com.example.callback.VolleyResponeListenr;
import com.example.tool.Alertdialogs;
import com.example.tool.Tools;
import com.example.tool.VolleyJson;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends Activity implements OnClickListener {
	Button register;
	EditText phone;
	EditText password;
	String user, pass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Tools.CancelActionbar(RegisterActivity.this);
		setContentView(R.layout.activity_register);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		register = (Button) findViewById(R.id.register);
		phone = (EditText) findViewById(R.id.phone);
		password = (EditText) findViewById(R.id.password);
		register.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.register:
			user = phone.getText().toString().trim();
			pass = password.getText().toString().trim();

			if (user.length() != 0 && pass.length() != 0) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("coachphone", user);
				map.put("coachpassword", pass);
				VolleyJson.getJSONVolley(this, "/coach/save", map);
				VolleyJson.setResponseListener(new VolleyResponeListenr() {

					@Override
					public void ResponseSucc(JSONObject response) {
						try {
							if (response.getInt("coach") > 0) {
								Alertdialogs.alertdialog(true, "注册成功,快去登录补充信息吧~你的编号是" + response.getInt("coach") + "！",
										RegisterActivity.this);
								Alertdialogs.setResponseListener(new AlertDialogListenr() {

									@Override
									public void Dialogs(Boolean sumbit) {
										// TODO Auto-generated method stub
										if (sumbit) {
											Intent intent = new Intent(RegisterActivity.this, Login_Activity.class);
											startActivity(intent);
											finish();
											overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
										}
									}
								});
							} else {
								Alertdialogs.alertdialog(false, "注册失败", RegisterActivity.this);
							}
						} catch (JSONException e) { // TODO Auto-generated catch
							e.printStackTrace();
						}
					}

					@Override
					public void ResponseError(VolleyError error) {
						// TODO Auto-generated method stub
					}

				});

			} else {
				Alertdialogs.alertdialog(false, "填写信息不完整!", RegisterActivity.this);
			}

			break;
		}

	}

}
