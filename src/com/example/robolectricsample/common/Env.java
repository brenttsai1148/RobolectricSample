package com.example.robolectricsample.common;

import java.net.URLEncoder;

import com.example.robolectricsample.utility.L;

public class Env {
	
	/**
	 * 0:Production<br>
	 * 1:Staging<br>
	 */
	public static final int SERVER_ENV = 1; 
	
	//[serviceURL]
	private static final String serviceURL_array[] = {
		"http://nicall.taiwanmobile.com/path",
		"http://175.99.74.34"
	};
	public static final String serviceURL = serviceURL_array[SERVER_ENV];
	
	//[serviceName]
	private static final String serviceName_array[] = {
		"EBG024",
		"EBG024"
	};
	public static final String serviceName = serviceName_array[SERVER_ENV];
	
	//[sipDomain]
	private static final String sipDomain_array[] = {
		"voip.callout.com",
		"voip.callout.com"		//"voip.co.com"	內部測試
	};	
	public static final String sipDomain = sipDomain_array[SERVER_ENV];;
	
	//[sipProxy]
	private static final String sipProxy_array[] = {
		"175.99.74.33:5090",
		"175.99.74.33:5090",
	};
	//[sipProxy]備份
	private static final String sipProxy_array_backUp[] = {
		"175.99.74.33:5060",
		"175.99.74.33:5060"
	};
	
	public static final String sipProxy[] = {sipProxy_array[SERVER_ENV],sipProxy_array_backUp[SERVER_ENV]};
	
	//[verifySMMsisdn]門號認證時，發送簡訊的主叫號
	private static final String verifySMMsisdn_array[] = {
		"935120177",
		"935120177"
	};
	public static final String verifySMMsisdn = verifySMMsisdn_array[SERVER_ENV];
	
	
	//[voiceTWMGmail] voice twm Gmail
	private static final String voiceTWMGmail_array[][] = {
		{"voice.twm@gmail.com"},
		{"voice.twm@gmail.com", "TangWen@taiwanmobile.com", "BrentTsai@taiwanmobile.com"}
	};
	public static final String[] voiceTWMGmail = voiceTWMGmail_array[SERVER_ENV];	

	
	//[serviceInfoUrl]產品列表 (含購買button)
	private static final String serviceInfoUrl_array[] = {
		"/MobileWEB/index",
		"/MobileWEB/index"
	};
	public static final String serviceInfoUrl = serviceURL+ serviceInfoUrl_array[SERVER_ENV];

	
	//[servicePostData]產品列表 (含購買button)
	private static final String serviceInfoPostData_array[] = {
		"linkMethod=ProductList",
		"linkMethod=ProductList"
	};
	private static final String serviceInfoPostData = serviceInfoPostData_array[SERVER_ENV];
	/**
	 * 產品列表 PostData
	 * @param lang 語系 EN:英文, CN:簡中, TW:繁中(未填時的預設值)
	 * @return
	 */
	public static String getServicePostData(String caller, String sessionID, String lang) {
		StringBuffer postData = new StringBuffer();
		postData.append(serviceInfoPostData);
		if(caller!=null) {
			try {
				postData.append("&caller=").append(URLEncoder.encode(caller, "UTF-8"));
			} catch (Exception e) {
				L.e(e);
			}
		}
		if(sessionID!=null) {
			try {
				postData.append("&sessionID=").append(URLEncoder.encode(sessionID, "UTF-8"));
			} catch (Exception e) {
				L.e(e);
			}
		}
		if(lang!=null) {
			try {
				postData.append("&lang=").append(URLEncoder.encode(lang, "UTF-8"));
			} catch (Exception e) {
				L.e(e);
			}
		}
		return postData.toString();
	}
		
		
	
	//[agreetmentUrl]服務條款
	private static final String agreetmentUrl_array[] = {
		"/customer/ViewRequest?linkMethod=agreement",
		"/customer/ViewRequest?linkMethod=agreement"
	};
	private static final String agreetmentUrl = serviceURL+ agreetmentUrl_array[SERVER_ENV];
	/**
	 * 服務條款
	 * @param lang 語系 EN:英文, CN:簡中, TW:繁中(未填時的預設值)
	 * @return
	 */
	public static String getAgreetmenturl(String lang) {
		if(lang==null) {
			return agreetmentUrl;
		} else {
			return agreetmentUrl + "&lang=" + lang ;
		}
	}
	
	
	//[privateUrl]隱私權條款
	private static final String privateUrl_array[] = {
		"/customer/ViewRequest?linkMethod=private",
		"/customer/ViewRequest?linkMethod=private"
	};	
	private static final String privateUrl = serviceURL+ privateUrl_array[SERVER_ENV];;
	/**
	 * 隱私權條款
	 * @param lang 語系 EN:英文, CN:簡中, TW:繁中(未填時的預設值)
	 * @return
	 */
	public static String getPrivateurl(String lang) {
		if(lang==null) {
			return privateUrl;
		} else {
			return privateUrl + "&lang=" + lang ;
		}		
	}
	
	
	//[downloadAPP]最新版本下載路徑
	private static final String downloadAPP_array[] = {
		"http://goo.gl/OCLGFK",
		"http://goo.gl/OCLGFK"
	};
	public static final String downloadAPP = downloadAPP_array[SERVER_ENV];
	
	
	//[MSISDN_Auth]是否要做簡訊認證門號
	private static final boolean MSISDN_AuthReq_array[] = {
		true,
		true
	};
	public static final boolean MSISDN_AuthReq = MSISDN_AuthReq_array[SERVER_ENV];

	
	
}
