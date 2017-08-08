package com.example.tool;

import com.example.bookingcar.R;
import com.example.callback.AlertDialogListenr;
import com.example.callback.VolleyResponeListenr;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class Alertdialogs {
	static boolean sumbit = false;
	private static AlertDialogListenr alertDialogListenr;
	public static void alertdialog(Boolean titles, String message,Context context) {
		
		int btp = 0;
		String title = null;
		if (titles) {
			title = "正确";
			btp = R.drawable.ic_launcher;
		} else {
			title = "错误";
			btp = R.drawable.ic_launcher;
		}
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title);// 设置标题
		builder.setMessage(message);
		builder.setIcon(btp);// 设置图标
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				sumbit=true;
				alertDialogListenr.Dialogs(sumbit);
			}

		});
		AlertDialog dialog = builder.create();// 获取dialog
		dialog.show();// 显示对话框
	}
	public static void setResponseListener(AlertDialogListenr listenr) {
		alertDialogListenr = listenr;
	}
}
