package com.example.mygellery;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class TestRecyclerViewActicity extends Activity{

	private PtrClassicFrameLayout ptr;
	private TextView text;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_testrecycler);
		ptr=(PtrClassicFrameLayout)findViewById(R.id.ptr);
		text=(TextView)findViewById(R.id.text);
		ptr.setPtrHandler(new PtrHandler(){
			@Override
			public boolean checkCanDoRefresh(PtrFrameLayout frame,
					View content, View header) {
				return true;
			}
			@Override
			public void onRefreshBegin(final PtrFrameLayout frame) {
		     frame.postDelayed(new Runnable(){
				@Override
				public void run() {
					text.setText("Ë¢ÐÂ");
			     frame.refreshComplete();
				}
		    	 
		     }, 300);
				
			}});
	}
}
