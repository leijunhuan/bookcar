package com.example.tool;

import java.util.List;

import com.example.bookingcar.R;
import com.example.model.ItemBean;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AppointAdapter extends BaseAdapter {
	private List<ItemBean> mList;
	private LayoutInflater mInflater;

	public AppointAdapter(Context context, List<ItemBean> list) {
		this.mList = list;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	// 返回每一项的显示内容
	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.listview_layout, null);
			viewHolder.image = (ImageView) convertView.findViewById(R.id.s_image);
			viewHolder.name = (TextView) convertView.findViewById(R.id.tv_name);
			viewHolder.date = (TextView) convertView.findViewById(R.id.tv_date);
			viewHolder.agree = (ImageView) convertView.findViewById(R.id.agree);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		ItemBean bean = mList.get(position);
		viewHolder.image.setImageResource(bean.ItemTIamge);
		viewHolder.name.setText(bean.ItemName);
		viewHolder.date.setText(bean.ItemDate);
		viewHolder.agree.setImageResource(bean.ItemAgree);
		return convertView;
	}

	class ViewHolder {
		public ImageView image;
		public TextView name;
		public TextView date;
		public ImageView agree;

	}

}
