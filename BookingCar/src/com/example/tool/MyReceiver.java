package com.example.tool;

import com.example.module.Appointment_Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import cn.jpush.android.api.JPushInterface;

public class MyReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Bundle bundle = intent.getExtras();
        Log.d("tag", "onReceive - " + intent.getAction());

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
        }else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            System.out.println("�յ����Զ�����Ϣ����Ϣ�����ǣ�" + bundle.getString(JPushInterface.EXTRA_MESSAGE));
            // �Զ�����Ϣ����չʾ��֪ͨ������ȫҪ������д����ȥ����
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            System.out.println("�յ���֪ͨ");
            // �����������Щͳ�ƣ�������Щ��������
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            System.out.println("�û��������֪ͨ");
            // ����������Լ�д����ȥ�����û���������Ϊ
            Intent i = new Intent(context, Appointment_Activity.class);  //�Զ���򿪵Ľ���
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        } else {
            Log.d("tag", "Unhandled intent - " + intent.getAction());
  }
	}

}