package com.example.javabean;

import cn.bmob.v3.BmobObject;

public class QrCode extends BmobObject{
	private String qrContent;
	
	public String getQrContent() {
		return qrContent;
	}

	public void setQrContent(String qrContent) {
		this.qrContent = qrContent;
	}

}
