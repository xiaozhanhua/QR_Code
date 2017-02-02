package com.example.Fragment;

import java.util.Hashtable;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.listener.SaveListener;

import com.example.checkNetwork.network;
import com.example.javabean.QrCode;
import com.example.qr_code.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class makeQrFragment extends Fragment implements OnClickListener {
	private EditText et;
	private Button btMake;
	private ImageView iv;
	private String qrStr;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view =  inflater.inflate(R.layout.makeqrfragment, container, false);
		Bmob.initialize(getActivity(), "deb09e50f449c6bcbc364eccc20775f5");
		et =(EditText) view.findViewById(R.id.editText);
		btMake =(Button)view.findViewById(R.id.make_Qrcode);
		iv = (ImageView) view.findViewById(R.id.imageView);
		btMake.setOnClickListener(this);
		return view;
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.make_Qrcode:
			qrStr = et.getText().toString();
			if(qrStr.equals("")||qrStr.length()==0){
				Toast.makeText(getActivity(), "内容不能为空",Toast.LENGTH_LONG).show();
			}else{
			//	检查网络状况，有网络就保存信息到云端，没有网络就Toast显示"当前网络不可用！"
				if (network.isNetworkConnected(getActivity())) {
					try {
						Bitmap bm = Create2DCode(qrStr);
						iv.setImageBitmap(bm);
						
						QrCode qr = new QrCode();
						qr.setQrContent(qrStr);
						qr.save(getActivity(), new SaveListener(){

							@Override
							public void onFailure(int code, String msg) {
								// TODO Auto-generated method stub
								Toast.makeText(getActivity(), "保存到云端失败",Toast.LENGTH_LONG).show();
							}

							@Override
							public void onSuccess() {
								// TODO Auto-generated method stub
								Toast.makeText(getActivity(), "保存到云端成功",Toast.LENGTH_LONG).show();
							}
							
						});
					} catch (WriterException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} 
				else {
					Toast.makeText(getActivity(), "当前网络不可用，请连接网络后重新制作",
							Toast.LENGTH_LONG).show();
				}

			}
			break;
	}
}
	
	
	/**
	 * 用字符串生成二维码
	 */
	public Bitmap Create2DCode(String str) throws WriterException {
		Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		//生成二维矩阵,编码时指定大小,不要生成了图片以后再进行缩放,这样会模糊导致识别失败
		BitMatrix matrix = new MultiFormatWriter().encode(str,BarcodeFormat.QR_CODE, 500, 500,hints);
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		//二维矩阵转为一维像素数组,也就是一直横着排了
		int[] pixels = new int[width * height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if(matrix.get(x, y)){
					pixels[y * width + x] = 0xff000000;
				}
				
			}
		}
		
		Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		//通过像素数组生成bitmap,具体参考api
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		return bitmap;
	}
}
