package com.example.robolectricsample;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.robolectricsample.httpapi.CallAPIResponse;
import com.example.robolectricsample.httpapi.VolleyAsyncHttpClient;
import com.example.robolectricsample.utility.L;
public class LoginActivity extends Activity implements android.view.View.OnClickListener{
	
	public static final int REQUEST_CODE_Login_Complete = 0;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		findViewById(R.id.login).setOnClickListener(this);
		findViewById(R.id.mockito).setOnClickListener(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.login:
			login("brent","123");
			break;
		case R.id.mockito:
			mockito();
		}
	}
	private void mockito() {
		

		//You can mock concrete classes, not only interfaces
//		LinkedList mockedList = mock(LinkedList.class);
//
//		//stubbing
//		when(mockedList.get(0)).thenReturn("first");
//		when(mockedList.get(1)).thenThrow(new RuntimeException());
//
//		//following prints "first"
//		System.out.println(mockedList.get(0));
//
//		//following throws runtime exception
//		System.out.println(mockedList.get(1));
//
//		//following prints "null" because get(999) was not stubbed
//		System.out.println(mockedList.get(999));
//		 
//		//Although it is possible to verify a stubbed invocation, usually it's just redundant
//		//If your code cares what get(0) returns then something else breaks (often before even verify() gets executed).
//		//If your code doesn't care what get(0) returns then it should not be stubbed. Not convinced? See here.
//		verify(mockedList).get(0);

		
		
		
		
		

		

		//Let's import Mockito statically so that the code looks clearer

		//mock creation
//		List mockedList = mock(List.class);
//
//		//using mock object
//		mockedList.add("one");
//		mockedList.clear();
//
//		//verification
//		verify(mockedList).add("one");
//		verify(mockedList).clear();



	}
	public String result = "";
	public CallAPIResponse CallAPIResponse = new CallAPIResponse(){

		@Override
		public void onStart() {
			L.d();
		}

		@Override
		public void onSuccess(Object response) {
			if(response instanceof String){
				L.d((String)response);
				result = (String)response;
			}
//			goBackMainActivity();
			L.d();
		}

		@Override
		public void onSuccess(Object[] response) {
			L.d();
		}

		@Override
		public void onFailure(Object response) {
			L.e(response.toString());
		}

		@Override
		public void onFinish() {
			L.d();
		}
		
	};
	
	
	public void login(String account,String password){
		VolleyAsyncHttpClient.getInstance(this).post(this, "http://kkbox.open-168.com/mobileapi/Login.aspx?account="+account+"&password="+password,  CallAPIResponse);
	}
	
	public void goBackMainActivity(){
		setResult(Activity.RESULT_OK);
		finish();
	}
}
