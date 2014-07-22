package com.example.robolectricsample.httpapi;

import org.json.JSONObject;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.robolectricsample.utility.L;

public class VolleyAsyncHttpClient {
	
	static RequestQueue mVolleyQueue;
	static VolleyAsyncHttpClient volleyAsyncHttpClient;
	
	public static VolleyAsyncHttpClient getInstance(Context context){
		if(volleyAsyncHttpClient==null){
			volleyAsyncHttpClient = new VolleyAsyncHttpClient(context);
		}
		return volleyAsyncHttpClient;
	}
	
	public VolleyAsyncHttpClient(Context context) {
		mVolleyQueue = Volley.newRequestQueue(context);
	}
	
	public void post(Context c, String serviceURL, JSONObject jsonObject , final CallAPIResponse callAPIResponse) {
		
		Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				L.d();
				callAPIResponse.onFinish();
			}
		};
		
		Response.ErrorListener responseErrorListener = new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				try {
					L.d();
					callAPIResponse.onFailure(error.getMessage());
				} catch (Exception e) {
					L.d(e.getMessage());
				}
			}
		};
		
		JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.POST,serviceURL, jsonObject, 
				responseListener, responseErrorListener);
		mVolleyQueue.add(jsonObjRequest);
		callAPIResponse.onStart();
	}
	
	public void post(Context c, String serviceURL, final CallAPIResponse callAPIResponse) {
		
		Response.Listener<String> responseListener = new Response.Listener<String>() {

			@Override
			public void onResponse(String arg0) {
				callAPIResponse.onSuccess(arg0);
				
			}
		};
		
		Response.ErrorListener responseErrorListener = new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				try {
					L.d();
					callAPIResponse.onFailure(error.getMessage());
				} catch (Exception e) {
					L.d(e.getMessage());
				}
			}
		};
		
		
		StringRequest stringRequest = new StringRequest(Request.Method.GET, serviceURL, responseListener, responseErrorListener);
		
		mVolleyQueue.add(stringRequest);
		callAPIResponse.onStart();
	}
}
