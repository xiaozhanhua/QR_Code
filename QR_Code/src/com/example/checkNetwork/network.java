package com.example.checkNetwork;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class network {
	
//	�ж������Ƿ�����
	public static boolean isNetworkConnected(Context context) {  
	    if (context != null) {  
// ��ȡ�ֻ��������ӹ�����󣨰�����wifi,net�����ӵĹ���
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
