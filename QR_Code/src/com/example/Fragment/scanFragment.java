package com.example.Fragment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.qr_code.R;
import com.example.qr_code.UI.*;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class scanFragment extends Fragment {
	
	private final static int SCANNIN_GREQUEST_CODE = 1;
	/**
	 * ��ʾɨ����
	 */
	private TextView mTextView ;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.scanfragment, container, false);
		mTextView = (TextView) view.findViewById(R.id.result); 
		
		//�����ť��ת����ά��ɨ����棬�����õ���startActivityForResult��ת
		//ɨ������֮������ý���
		Button mButton = (Button) view.findViewById(R.id.button1);
		mButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(getActivity(), MipcaActivityCapture.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
			}
		});
		return view;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
		case SCANNIN_GREQUEST_CODE:
			if(resultCode == -1){
				Bundle bundle = data.getExtras();
				//��ʾɨ�赽�Ľ��
				String path = bundle.getString("result").trim();  
				Pattern pattern = Pattern.compile("([a-zA-z]+://[^\\u4e00-\\u9fa5\\s]*)");
				Matcher mhttp = pattern.matcher(path);
				if(!mhttp.find()){
					mTextView.setText("��ȡ�����ı���Ϣ,�������£�\n\n"+bundle.getString("result"));
				}else{
					mTextView.setText("�ղŻ�ȡ����һ����ҳ���ӣ�\n\n"+bundle.getString("result"));
					  Intent intent = new Intent();        
					  intent.setAction("android.intent.action.VIEW");    
					  Uri url = Uri.parse(path);   
					  intent.setData(url);  
					  startActivity(intent);
				}
			}
			break;
		}
    }	
	
	
}
