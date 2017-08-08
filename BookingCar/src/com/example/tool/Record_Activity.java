package com.example.tool;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.pickerview.OptionsPopupWindow;
import com.example.pickerview.TimePopupWindow;
import com.example.pickerview.TimePopupWindow.OnTimeSelectListener;
import com.example.pickerview.TimePopupWindow.Type;
import com.android.volley.VolleyError;
import com.example.bookingcar.HomepageActivity;
import com.example.bookingcar.Login_Activity;
import com.example.bookingcar.R;
import com.example.bookingcar.RegisterActivity;
import com.example.callback.AlertDialogListenr;
import com.example.callback.VolleyResponeListenr;
import com.example.module.Appointment_Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Record_Activity extends Activity  implements OnClickListener{
	private TextView sTime, eTime;
	private Button start, end,sumbit;
	TimePopupWindow spTime,epTime;
	OptionsPopupWindow pwOptions;
	EditText Ename,Ephone;
	private DefineApplication defineApplication;
	private Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Tools.CancelActionbar(Record_Activity.this);
		setContentView(R.layout.activity_record);
		defineApplication =(DefineApplication)getApplication();
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		sTime = (TextView) findViewById(R.id.sTime);
		eTime = (TextView) findViewById(R.id.eTime);
		start = (Button) findViewById(R.id.start);
		end = (Button) findViewById(R.id.end);
		Ename = (EditText) findViewById(R.id.Ename);
		Ephone = (EditText) findViewById(R.id.Ephone);
		sumbit = (Button) findViewById(R.id.sumbit);
		sumbit.setOnClickListener(this);
		// 时间选择器
		spTime = new TimePopupWindow(this, Type.ALL);
		// 时间选择后回调
		spTime.setOnTimeSelectListener(new OnTimeSelectListener() {

			@Override
			public void onTimeSelect(Date date) {
				sTime.setText(getTime(date));
			}
		});
		// 弹出时间选择器
		start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				spTime.showAtLocation(start, Gravity.BOTTOM, 0, 0, new Date());
			}
		});

		epTime = new TimePopupWindow(this, Type.ALL);
		// 时间选择后回调
		epTime.setOnTimeSelectListener(new OnTimeSelectListener() {

			@Override
			public void onTimeSelect(Date date) {
				eTime.setText(getTime(date));
			}
		});
		// 弹出时间选择器
		end.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				epTime.showAtLocation(end, Gravity.BOTTOM, 0, 0, new Date());
			}
		});
	}

	public static String getTime(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return format.format(date);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.sumbit:
			String name=Ename.getText().toString().trim();
			String phone=Ephone.getText().toString().trim();
			String stime=sTime.getText().toString().trim();
			String etime=eTime.getText().toString().trim();
			if(name.equals("")&&phone.equals("")){
				Alertdialogs.alertdialog(false, "姓名与手机为空",Record_Activity.this);
			}else if(stime.length()<6&&etime.length()<6){
				Alertdialogs.alertdialog(false, "您的起始时间没有选择",Record_Activity.this);
			}else{
				Map<String, String> map = new HashMap<String, String>();
				map.put("coachid", defineApplication.coach);
				map.put("studentname", name);
				map.put("studentphone", phone);
				map.put("starttime", stime);
				map.put("endtime", etime);
				VolleyJson.getJSONVolley(this, "/notice/save", map);
				VolleyJson.setResponseListener(new VolleyResponeListenr() {

					@Override
					public void ResponseSucc(JSONObject response) {
						try {
							if (response.getInt("notice") > 0) {
								Alertdialogs.alertdialog(true, "预约成功",Record_Activity.this);
								Alertdialogs.setResponseListener(new AlertDialogListenr() {

									@Override
									public void Dialogs(Boolean sumbit) {
										// TODO Auto-generated method stub
										if(sumbit){
											Intent intent = new Intent(Record_Activity.this,Appointment_Activity.class);
											startActivity(intent);
											finish();
											overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
										}
									}
								});
							} else {
								Alertdialogs.alertdialog(false, "预约失败",Record_Activity.this);
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
			}
			break;
		}
	}
}
