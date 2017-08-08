package com.example.tool;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.callback.VolleyResponeListenr;

import android.content.Context;
public class VolleyJson {
	static JSONObject json;
	//����Ļص�����
	private static VolleyResponeListenr volleyResponeListenr;

	public static void getJSONVolley(Context context, String url, Map<String, String> map) {

		// ������Ҫ��ȡ��һ��RequestQueue���󣬿��Ե������·�����ȡ��
		RequestQueue requestQueue = Volley.newRequestQueue(context);
		// String JSONDateUrl = "http://58.20.44.20:8080/coach/save";
		// ������Volley ��StringRequestʵ�����Ľӿڡ���Ҫ��ע�ļ����ط��� StringRequest�����м�����Ҫ�Ĳ�����
		// 1.int���͵�method����ʾʹ���������͵�����,��get,post
		// 2.String url:��ʾ��Ҫ���������
		// 3.Listener<String> listener:����ɹ��󷵻ص�����
		// 4.ErrorListener errorListener:���󷢳ɴ������õķ���
		url="http://58.20.44.228:8080"+url;
		JSONObject jsonObject = new JSONObject(map);
		JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Method.POST, url, jsonObject,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						//��json����ص���
						volleyResponeListenr.ResponseSucc(response);

					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						volleyResponeListenr.ResponseError(error);
					}
				}) {

		};
		requestQueue.add(jsonRequest);
	}

	public static void setResponseListener(VolleyResponeListenr listenr) {
		volleyResponeListenr = listenr;
	}
}
