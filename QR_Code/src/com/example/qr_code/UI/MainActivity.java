package com.example.qr_code.UI;

import java.util.ArrayList;

import com.example.Fragment.*;
import com.example.qr_code.R;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends FragmentActivity implements OnCheckedChangeListener{
	private ArrayList<Fragment> fragments;
	private RadioGroup radiogroup;
	private makeQrFragment makeQrFragment;
	private scanFragment scanFragment;
	private aboutFragment aboutFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		radiogroup = (RadioGroup) findViewById(R.id.radioGroup1);
		radiogroup.setOnCheckedChangeListener(this);
		    fragments = new ArrayList<Fragment>();
			fragments.add(makeQrFragment);
			fragments.add(scanFragment);
			fragments.add(aboutFragment);
			FragmentManager manager = getSupportFragmentManager();
			FragmentTransaction transaction = manager.beginTransaction();
			transaction.replace(R.id.myFrame, fragments.get(0));
			transaction.commit();
	}

	private void init(){
		makeQrFragment = new makeQrFragment();
		scanFragment = new scanFragment();
		aboutFragment = new aboutFragment();
	}
	
	public void onCheckedChanged(RadioGroup view, int checkId) {
		int childCount = radiogroup.getChildCount();
		int checkedIndex = 0;
		RadioButton btnButton = null;
		for (int i = 0; i < childCount; i++) {
			btnButton = (RadioButton) radiogroup.getChildAt(i);
			if (btnButton.isChecked()) {
				checkedIndex = i;
				break;
			}
		}

		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		switch (checkedIndex) {
		case 0:
			transaction.replace(R.id.myFrame, fragments.get(0));
			transaction.commit();
			break;
		case 1:
			transaction.replace(R.id.myFrame, fragments.get(1));
			transaction.commit();
			break;
		case 2:
			transaction.replace(R.id.myFrame, fragments.get(2));
			transaction.commit();
			break;
		default:
			break;
		}
		
	}

}
