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
			title = "��ȷ";
			btp = R.drawable.ic_launcher;
		} else {
			title = "����";
			btp = R.drawable.ic_launcher;
		}
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title);// ���ñ���
		builder.setMessage(message);
		builder.setIcon(btp);// ����ͼ��
		builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				sumbit=true;
				alertDialogListenr.Dialogs(sumbit);
			}

		});
		AlertDialog dialog = builder.create();// ��ȡdialog
		dialog.show();// ��ʾ�Ի���
	}
	public static void setResponseListener(AlertDialogListenr listenr) {
		alertDialogListenr = listenr;
	}
}
