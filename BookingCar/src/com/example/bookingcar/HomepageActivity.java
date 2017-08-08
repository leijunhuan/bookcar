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
	// ͼƬ��Դid
	private final int[] images = { R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e };
	// ͼƬ���⼯��
	private final String[] imageDescriptions = { "��һ��ͼƬ", "�ڶ���ͼƬ", "������ͼƬ", "������ͼƬ", "������ͼƬ" };
	private ArrayList<ImageView> imageList;
	// ��һ��ҳ���λ��
	protected int lastPosition = 0;

	// �ж��Ƿ��Զ�����viewPager
	private boolean isRunning = true;
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			// ִ�Ż�������һ��ҳ��
			// ��ȡ��ǰҳ viewpager.getCurrentItem();
			// ���õڼ�ҳ viewPager.setCurrentItem(1);
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

	// ��ʼ���ؼ�
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

	// ��ʼ��ͼƬ��Դ
	private void initImageView() {
		// TODO Auto-generated method stub
		// ��ʼ��ͼƬ��Դ
		imageList = new ArrayList<ImageView>();
		for (int i : images) {
			// ��ʼ��ͼƬ��Դ
			ImageView imageView = new ImageView(this);
			imageView.setBackgroundResource(i);
			imageList.add(imageView);

			// ���ָʾС��
			ImageView point = new ImageView(this);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100, 15);
			params.rightMargin = 20;
			params.bottomMargin = 10;
			point.setLayoutParams(params);
			point.setBackgroundResource(R.drawable.point_bg);
			if (i == R.drawable.a) {
				// Ĭ�Ͼ۽��ڵ�һ��
				point.setBackgroundResource(R.drawable.point_bg_focus);
				point.setEnabled(true);
			} else {
				point.setEnabled(false);
			}
			point_group.addView(point);
		}
		viewPager.setAdapter(new MyPageAdapter());
		// ���õ�ǰviewPager��λ��
		viewPager.setCurrentItem(Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2 % imageList.size()));
		viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				// ҳ���л�����ã�position���µ�ҳ��λ��
				// ʵ��������ѭ������
				position %= imageList.size();

				image_desc.setText(imageDescriptions[position]);

				// �ѵ�ǰ������Ϊtrue������һ������Ϊfalse;������point_groupͼ��
				point_group.getChildAt(position).setEnabled(true);
				point_group.getChildAt(position).setBackgroundResource(R.drawable.point_bg_focus);
				// ���þ۽�ʱ��ͼ����ʽ
				point_group.getChildAt(lastPosition).setEnabled(false);
				point_group.getChildAt(lastPosition).setBackgroundResource(R.drawable.point_bg);
				// ��һ���ָ�ԭ��ͼ��
				lastPosition = position;
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				// ҳ�����ڻ���ʱ��ص�
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				// ��pageView ״̬�����ı��ʱ�򣬻ص�
			}
		});

		/*
		 * �Զ�ѭ���� 1.��ʱ����Timer 2.�����̣߳�while true ѭ�� 3.ClockManger
		 * 4.��Handler�����ӳ�ʱ��Ϣ��ʵ��ѭ����������
		 */
		handler.sendEmptyMessageDelayed(0, 3000);

	}

	private class MyPageAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			// ���ҳ�������
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
			// �����Ӧλ���ϵ�view; container view����������ʵ����viewpage����
			// position:viewpager�ϵ�λ��
			// ��container�������
			container.addView(imageList.get(position % imageList.size()));
			return imageList.get(position % imageList.size());
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// ���ٶ�Ӧλ���ϵ�Object
			// super.destroyItem(container, position, object);
			container.removeView((View) object);
			object = null;
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		// ֹͣ����
		isRunning = false;
		super.onDestroy();
	}
}
