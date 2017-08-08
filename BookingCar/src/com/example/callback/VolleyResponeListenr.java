package com.example.callback;

import org.json.JSONObject;

import com.android.volley.VolleyError;

public interface VolleyResponeListenr {
	public void ResponseSucc(JSONObject response);
	public void ResponseError(VolleyError error);
	
}
