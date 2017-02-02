package com.example.qr_code.UI;

import java.util.Timer;
import java.util.TimerTask;

import com.example.qr_code.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class Welcome_Activity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		
		Timer  time = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Welcome_Activity.this,MainActivity.class);
				startActivity(intent);
				Welcome_Activity.this.finish();
			}
		};
		time.schedule(task, 3000);
	}
}
