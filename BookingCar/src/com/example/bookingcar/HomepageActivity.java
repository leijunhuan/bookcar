package com.example.bookingcar;

import java.util.ArrayList;

import com.example.module.Appointment_Activity;
import com.example.module.Information_Activity;
import com.example.module.Setprogress_Activity;
import com.example.module.Sregistration_Activity;
import com.example.module.Viewprogress_Activity;
import com.example.tool.Tools;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.jpush.android.api.JPushInterface;

public class HomepageActivity extends FragmentActivity implements OnClickListener {
	private ViewPager viewPager;
	private LinearLayout point_group;
	private TextView image_desc;
	private Button appointment;
	private Button information_setting;
	private Button set_progress;
	private Button view_progress;
	private Button student_registration;
	Intent intent;
	// 图片资源id
	private final int[] images = { R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e };
	// 图片标题集合
	private final String[] imageDescriptions = { "第一张图片", "第二张图片", "第三张图片", "第四张图片", "第五张图片" };
	private ArrayList<ImageView> imageList;
	// 上一个页面的位置
	protected int lastPosition = 0;

	// 判断是否自动滚动viewPager
	private boolean isRunning = true;
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			// 执着滑动到下一个页面
			// 获取当前页 viewpager.getCurrentItem();
			// 设置第几页 viewPager.setCurrentItem(1);
			viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
			if (isRunning) {
				handler.sendEmptyMessageDelayed(0, 5000);
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Tools.CancelActionbar(HomepageActivity.this);
		setContentView(R.layout.activity_homepage);
		JPushInterface.setDebugMode(true);
		JPushInterface.init(this);
		try {
            Class.forName("android.os.AsyncTask");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		init();
		initImageView();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.appointment:
			intent=new Intent(HomepageActivity.this,Appointment_Activity.class);
			startActivity(intent);
			finish();
			overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
			break;
		case R.id.information_setting:
			intent=new Intent(HomepageActivity.this,Information_Activity.class);
			startActivity(intent);
			finish();
			overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
			break;
		case R.id.set_progress:
			intent=new Intent(HomepageActivity.this,Setprogress_Activity.class);
			startActivity(intent);
			finish();
			overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
			break;
		case R.id.view_progress:
			intent=new Intent(HomepageActivity.this,Sregistration_Activity.class);
			startActivity(intent);
			finish();
			overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
			break;
		case R.id.student_registration:
			intent=new Intent(HomepageActivity.this,Viewprogress_Activity.class);
			startActivity(intent);
			finish();
			overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
			break;
		}
	}

	// 初始化控件
	private void init() {
		// TODO Auto-generated method stub
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		point_group = (LinearLayout) findViewById(R.id.point_group);
		image_desc = (TextView) findViewById(R.id.image_desc);
		
		appointment = (Button) findViewById(R.id.appointment);
		information_setting = (Button) findViewById(R.id.information_setting);
		set_progress = (Button) findViewById(R.id.set_progress);
		view_progress = (Button) findViewById(R.id.view_progress);
		student_registration = (Button) findViewById(R.id.student_registration);
		
		image_desc.setText(imageDescriptions[0]);
		
		appointment.setOnClickListener(this);
		information_setting.setOnClickListener(this);
		set_progress.setOnClickListener(this);
		view_progress.setOnClickListener(this);
		student_registration.setOnClickListener(this);
	}

	// 初始化图片资源
	private void initImageView() {
		// TODO Auto-generated method stub
		// 初始化图片资源
		imageList = new ArrayList<ImageView>();
		for (int i : images) {
			// 初始化图片资源
			ImageView imageView = new ImageView(this);
			imageView.setBackgroundResource(i);
			imageList.add(imageView);

			// 添加指示小点
			ImageView point = new ImageView(this);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100, 15);
			params.rightMargin = 20;
			params.bottomMargin = 10;
			point.setLayoutParams(params);
			point.setBackgroundResource(R.drawable.point_bg);
			if (i == R.drawable.a) {
				// 默认聚焦在第一张
				point.setBackgroundResource(R.drawable.point_bg_focus);
				point.setEnabled(true);
			} else {
				point.setEnabled(false);
			}
			point_group.addView(point);
		}
		viewPager.setAdapter(new MyPageAdapter());
		// 设置当前viewPager的位置
		viewPager.setCurrentItem(Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2 % imageList.size()));
		viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				// 页面切换后调用，position是新的页面位置
				// 实现无限制循环播放
				position %= imageList.size();

				image_desc.setText(imageDescriptions[position]);

				// 把当前点设置为true，将上一个点设为false;并设置point_group图标
				point_group.getChildAt(position).setEnabled(true);
				point_group.getChildAt(position).setBackgroundResource(R.drawable.point_bg_focus);
				// 设置聚焦时的图标样式
				point_group.getChildAt(lastPosition).setEnabled(false);
				point_group.getChildAt(lastPosition).setBackgroundResource(R.drawable.point_bg);
				// 上一个恢复原有图标
				lastPosition = position;
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				// 页面正在滑动时间回调
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				// 当pageView 状态发生改变的时候，回调
			}
		});

		/*
		 * 自动循环： 1.定时器：Timer 2.开子线程：while true 循环 3.ClockManger
		 * 4.用Handler发送延迟时信息，实现循环，最简单最方便
		 */
		handler.sendEmptyMessageDelayed(0, 3000);

	}

	private class MyPageAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			// 获得页面的总数
			return Integer.MAX_VALUE;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			// TODO Auto-generated method stub
			if (view == object) {
				return true;
			} else {
				return false;
			}
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// 获得相应位置上的view; container view的容器，其实就是viewpage自身
			// position:viewpager上的位置
			// 给container添加内容
			container.addView(imageList.get(position % imageList.size()));
			return imageList.get(position % imageList.size());
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// 销毁对应位置上的Object
			// super.destroyItem(container, position, object);
			container.removeView((View) object);
			object = null;
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		// 停止滚动
		isRunning = false;
		super.onDestroy();
	}
}
