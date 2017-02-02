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
				Toast.makeText(getActivity(), "���ݲ���Ϊ��",Toast.LENGTH_LONG).show();
			}else{
			//	�������״����������ͱ�����Ϣ���ƶˣ�û�������Toast��ʾ"��ǰ���粻���ã�"
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
								Toast.makeText(getActivity(), "���浽�ƶ�ʧ��",Toast.LENGTH_LONG).show();
							}

							@Override
							public void onSuccess() {
								// TODO Auto-generated method stub
								Toast.makeText(getActivity(), "���浽�ƶ˳ɹ�",Toast.LENGTH_LONG).show();
							}
							
						});
					} catch (WriterException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} 
				else {
					Toast.makeText(getActivity(), "��ǰ���粻���ã��������������������",
							Toast.LENGTH_LONG).show();
				}

			}
			break;
	}
}
	
	
	/**
	 * ���ַ������ɶ�ά��
	 */
	public Bitmap Create2DCode(String str) throws WriterException {
		Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		//���ɶ�ά����,����ʱָ����С,��Ҫ������ͼƬ�Ժ��ٽ�������,������ģ������ʶ��ʧ��
		BitMatrix matrix = new MultiFormatWriter().encode(str,BarcodeFormat.QR_CODE, 500, 500,hints);
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		//��ά����תΪһά��������,Ҳ����һֱ��������
		int[] pixels = new int[width * height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if(matrix.get(x, y)){
					pixels[y * width + x] = 0xff000000;
				}
				
			}
		}
		
		Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		//ͨ��������������bitmap,����ο�api
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		return bitmap;
	}
}
