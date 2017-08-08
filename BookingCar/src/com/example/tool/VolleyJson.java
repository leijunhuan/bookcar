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
	//轩哥的回调函数
	private static VolleyResponeListenr volleyResponeListenr;

	public static void getJSONVolley(Context context, String url, Map<String, String> map) {

		// 首先需要获取到一个RequestQueue对象，可以调用如下方法获取到
		RequestQueue requestQueue = Volley.newRequestQueue(context);
		// String JSONDateUrl = "http://58.20.44.20:8080/coach/save";
		// 现在用Volley 的StringRequest实现它的接口。需要关注的几个地方： StringRequest对象有几个重要的参数：
		// 1.int类型的method：标示使用哪种类型的请求,如get,post
		// 2.String url:标示需要请求的链接
		// 3.Listener<String> listener:请求成功后返回的类型
		// 4.ErrorListener errorListener:请求发成错误后调用的返回
		url="http://58.20.44.228:8080"+url;
		JSONObject jsonObject = new JSONObject(map);
		JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Method.POST, url, jsonObject,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						//将json加入回调中
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
