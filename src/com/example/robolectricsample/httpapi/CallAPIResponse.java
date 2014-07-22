package com.example.robolectricsample.httpapi;


/**
 * @author Reming 20131220
 *
 */
public interface CallAPIResponse {

	public void onStart();

	public void onSuccess(Object response);
	
	public void onSuccess(Object[] response);
	
	public void onFailure(Object response);
	
	public void onFinish();
}

/* Reming Mark 20131220
public interface CallAPIResponse {

	public void onStart();

	public void onSuccess(int statusCode, Header[] headers, byte[] responseBody);
	
	public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error);
	
	public void onFinish();
}
*/
