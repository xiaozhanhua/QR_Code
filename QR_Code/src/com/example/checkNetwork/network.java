package com.example.checkNetwork;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class network {
	
//	判断网络是否连接
	public static boolean isNetworkConnected(Context context) {  
	    if (context != null) {  
// 获取手机所有连接管理对象（包括对wifi,net等连接的管理）
	    	ConnectivityManager connectivityManager =(ConnectivityManager) context
	    			.getSystemService(Context.CONNECTIVITY_SERVICE);
	    	NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
	        if (networkInfo != null) {  
	            return true;  
	        }  
	    }  
	    return false;  
	}  
	
}
