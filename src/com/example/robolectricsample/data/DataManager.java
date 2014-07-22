package com.example.robolectricsample.data;

import java.text.SimpleDateFormat;
import java.util.Locale;

import android.content.Context;

public class DataManager {

	private static DataManager datamanager = null;
	private Context appcontext = null;
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());	
	
	public DataManager(Context context) {
		appcontext = context;
	}
	
	public static DataManager getInstance(Context context) {
		if(datamanager==null) {
			datamanager =  new DataManager(context);
		}
		return datamanager;
	}
	
	public static DataManager getInstance() {
		return datamanager;
	}
	

	
	/**
	 * @author Reming 20140121
	 * 2.用戶登入
	 * @param myRLoginInput 登入輸入欄位
	 * @param context
	 * @param getDataResponse
	 * @return
	 */
//	public void customerLogin(final RLoginInput myRLoginInput, final Context context, final GetDataResponse getDataResponse) {
//		try {	
//			//1.Call API
//			CallAPIResponse callAPIResponse = null;			
//			callAPIResponse = new CallAPIResponse() {
//				@Override
//				public void onStart() {
//					if(getDataResponse!=null)getDataResponse.onStart();
//				}
//				@Override
//				public void onSuccess(Object response) {
//					RCustomerLogin myRCustomerLogin = (RCustomerLogin) response;	//用戶登入
//					DataCache.myRMemberData = new RMemberData();
//					DataCache.myRMemberData.retcode = myRCustomerLogin.retcode;
//					DataCache.myRMemberData.retmsg = myRCustomerLogin.retmsg;
//					DataCache.myRMemberData.verification = myRCustomerLogin.verification;
//					DataCache.myRMemberData.account = myRLoginInput.account;
//					DataCache.myRMemberData.service = Env.serviceName;
//					DataCache.myRMemberData.register_type = myRLoginInput.register_type;
//					DataCache.myRMemberData.register_uid = myRLoginInput.register_uid;
//					DataCache.myRMemberData.register_pwd = myRLoginInput.register_pwd;
//					DataCache.myRMemberData.lang = EBGPreferences.getLangforAPI();
//					DataCache.myRMemberData.android_version = myRCustomerLogin.android_version;
//					DataCache.myRMemberData.ios_version = myRCustomerLogin.ios_version;
//					DataCache.myRMemberData.svc_status = myRCustomerLogin.svc_status;
//					DataCache.myRMemberData.reject_api = myRCustomerLogin.reject_api;					
//					DataCache.myRMemberData.sessionID = myRCustomerLogin.sessionID;
//					DataCache.myRMemberData.reg_service = myRCustomerLogin.reg_service;
//					//DataCache.myRMemberData.mobile = myRRegInput.mobile;	//登入沒有此欄位
//					//DataCache.myRMemberData.caller_id						//註冊時才給值
//					//DataCache.myRMemberData.caller_pwd					//註冊時才給值
//					//DataCache.myRMemberData.nickname						//目前空值
//					
//					//sessionID
//					DataCache.mySESSION_ID = myRCustomerLogin.sessionID;
//					EBGPreferences.setSESSION_ID(myRCustomerLogin.sessionID);
//					EBGPreferences.setMemberAccount(myRLoginInput.account);
//					EBGPreferences.setRegisterType(myRLoginInput.register_type);
//					//寫入DB input & output data
//					DBmanager.getInstance(appcontext).setMemberData(null, null, myRLoginInput, (RCustomerLogin) response);
//					//判斷版本編號
//					try {
//						String versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
//						if(Utils.versionCompare(versionName, ((RCustomerLogin) response).android_version)) {
//							EBGPreferences.setNewAbout(true);
//						}
//					} catch (NameNotFoundException e) {
//						L.e(e);
//					} catch (Exception e) {
//						L.e(e);
//					}
//					//set Control
//					setAPPControl(myRCustomerLogin.svc_status , myRCustomerLogin.reject_api);
//					//callback
//					if(getDataResponse!=null)getDataResponse.onSuccess((RCustomerLogin) response);
//				}
//				@Override
//				public void onFailure(Object response) {
//					String errorMessage = (String)response;
//					if(getDataResponse!=null)getDataResponse.onFailure(errorMessage);
//				}
//				@Override
//				public void onFinish() {
//					if(getDataResponse!=null)getDataResponse.onFinish();
//				}
//				@Override
//				public void onSuccess(Object[] response) {
//					
//				}			
//			};
//
//			//L.d("register_pwd="+myRLoginInput.register_pwd);
//			(new HTTPApi()).customerLogin( myRLoginInput.caller_id, myRLoginInput.caller_pwd, myRLoginInput.account, myRLoginInput.register_type, myRLoginInput.register_uid, myRLoginInput.register_pwd, context,callAPIResponse, null, null);
//		} catch (Exception e) {
//			L.e(e);
//		}
//	}
	
	
}
