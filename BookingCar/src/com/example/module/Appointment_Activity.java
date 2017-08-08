package com.example.module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.VolleyError;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.bookingcar.HomepageActivity;
import com.example.bookingcar.Login_Activity;
import com.example.bookingcar.R;
import com.example.callback.AlertDialogListenr;
import com.example.callback.VolleyResponeListenr;
import com.example.bookingcar.MainActivity;
import com.example.model.ItemBean;
import com.example.tool.Alertdialogs;
import com.example.tool.AppointAdapter;
import com.example.tool.DefineApplication;
import com.example.tool.Record_Activity;
import com.example.tool.Tools;
import com.example.tool.VolleyJson;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.ListView;
import cn.jpush.android.api.JPushInterface;

public class Appointment_Activity extends Activity implements OnClickListener {
	Button add_click;
	private SwipeMenuListView listView;
	private AppointAdapter adapter;
	static List<ItemBean> itemBeanList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Tools.CancelActionbar(Appointment_Activity.this);
		setContentView(R.layout.activity_appointment);
		init();
		listviews();
	}

	public void list() {
		itemBeanList = new ArrayList<ItemBean>();
		Map<String, String> map = new HashMap<String, String>();
		VolleyJson.getJSONVolley(this, "/notice/find", map);
		VolleyJson.setResponseListener(new VolleyResponeListenr() {
			@SuppressWarnings("unchecked")
			@Override
			public void ResponseSucc(JSONObject response) {
				
				try {
					JSONArray jsonArray =response.getJSONArray("notice");	
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject list = jsonArray.getJSONObject(i);
						System.err.println(list.toString());
						int map = R.drawable.agree;
						if (list.get("readrecord").equals("0")) {
							map = R.drawable.agree;
						} else {
							itemBeanList.add(new ItemBean(R.drawable.ic_launcher, "ÐÕÃû:  " + list.get("studentname"),
									"Ê±¼ä:  " + list.get("starttime") + "-" + list.get("endtime"), map));
						}
						adapter = new AppointAdapter(Appointment_Activity.this, itemBeanList);
						listView.setAdapter(adapter);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void ResponseError(VolleyError error) {
				// TODO Auto-generated method stub
			}
		});

	}

	private void listviews() {
		// TODO Auto-generated method stub
		list();
		SwipeMenuCreator creator = new SwipeMenuCreator() {

			@Override
			public void create(SwipeMenu menu) {
				// create "open" item
				SwipeMenuItem openItem = new SwipeMenuItem(getApplicationContext());
				// set item background
				openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9, 0xCE)));
				// set item width
				openItem.setWidth(dp2px(90));
				// set item title
				openItem.setTitle("Open");
				// set a icon
				openItem.setIcon(R.drawable.ic_action_favorite);
				// set item title fontsize
				openItem.setTitleSize(18);
				// set item title font color
				openItem.setTitleColor(Color.WHITE);
				// add to menu
				menu.addMenuItem(openItem);

				// create "delete" item
				SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
				// set item background
				deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
				// set item width
				deleteItem.setWidth(dp2px(90));
				// set a icon
				deleteItem.setIcon(R.drawable.ic_delete);
				// add to menu
				menu.addMenuItem(deleteItem);
			}
		};
		// set creator
		listView.setMenuCreator(creator);

		listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
				switch (index) {
				case 0:
					// open
					Intent intent = new Intent(Appointment_Activity.this, MainActivity.class);
					startActivity(intent);
					break;
				case 1:
					// delete
					itemBeanList.remove(position);
					adapter.notifyDataSetChanged();
					break;
				}
				// false : close the menu; true : not close the menu
				return false;
			}
		});
	}

	public int dp2px(float dipValue) {
		final float scale = this.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	private void init() {
		// TODO Auto-generated method stub
		add_click = (Button) findViewById(R.id.add_click);
		listView = (SwipeMenuListView) findViewById(R.id.listView1);
		add_click.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Animation loadAnimation;
		switch (v.getId()) {
		case R.id.add_click:
			loadAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
			add_click.startAnimation(loadAnimation);
			Intent intent = new Intent(Appointment_Activity.this, Record_Activity.class);
			startActivity(intent);
			finish();
			overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
			break;
		}
	}
}
