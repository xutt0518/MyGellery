package com.example.mygellery;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import java.util.logging.LogRecord;



import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TestRecyclerViewActicity extends Activity{

	private ViewPager viewPager;
	private SelectedPagerAdapter selectedPagerAdapter;
	private ImageView[] tips;//底部。。。
	private Timer timer;
	private final int  CAROUSEL_TIME = 3000;//滚动间隔	
	private int currentIndex=0;//当前选中	
	private TextView tvContent;
	private PtrClassicFrameLayout ptrClassicFrameLayout;
	private RecyclerView mRecyclerView;
	private int page = 0;
	private PtrClassicFrameLayout ptrframelayout;
	private RecyclerView recycler;
	private TextView text;
	private RecyclerViewAdapter recyclerViewAdapter;
	private RecyclerAdapterWithHF mAdapter;
	private List<String> data;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_testrecycler);
		ptrframelayout=(PtrClassicFrameLayout)findViewById(R.id.ptr);
		recycler=(RecyclerView)findViewById(R.id.recycler);
		data=new ArrayList<String>();
		
		init();					
	}
	public void init(){
		recyclerViewAdapter=new RecyclerViewAdapter(this);
		
		mAdapter = new RecyclerAdapterWithHF(recyclerViewAdapter);
	/*	final GridLayoutManager manager = new GridLayoutManager(this, 3);
		manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
			  @Override
			  public int getSpanSize(int position) {
				  return manager.getSpanCount() ;
		//return mAdapter.isHeader(position) ? manager.getSpanCount() : 1;
			  }
			});
		recycler.setLayoutManager(manager);*/
		recycler.setLayoutManager(new LinearLayoutManager(this));
		//把滚动Banner加入头部
		
		mAdapter.addCarouse(initCarouselHead());
		recycler.setAdapter(mAdapter);
		recycler.setItemAnimator(new DefaultItemAnimator());
	    recycler.addItemDecoration(new GridItemDecoration(this, true));
		//recycler.setAdapter(recyclerViewAdapter);
		ptrframelayout.setPtrHandler(ptrDefaultHandler);
	}
/*	public PtrHandler hander=new PtrHandler(){
		@Override
		public boolean checkCanDoRefresh(PtrFrameLayout frame, View content,
				View header) {
			return true;
		}
		@Override
		public void onRefreshBegin(final PtrFrameLayout frame) {
			  frame.postDelayed(new Runnable(){
					@Override
					public void run() {	
						page=0;
				    recyclerViewAdapter.getfirst();
				    mAdapter.notifyDataSetChanged();
				    frame.refreshComplete();
					}			    	 
			     }, 200);
		}
		
	};*/
	private PtrDefaultHandler ptrDefaultHandler=new PtrDefaultHandler() {
		@Override
		public void onRefreshBegin(PtrFrameLayout frame) {
			frame.postDelayed(new Runnable() {
				@Override
				public void run() {
					page = 0;
					recyclerViewAdapter.getfirst();
					mAdapter.notifyDataSetChanged();
					ptrframelayout.refreshComplete();
				}
			},700);
		}
	};
	TimerTask task = new TimerTask() {
		public void run() {
			handler.sendEmptyMessage(CAROUSEL_TIME);
		}
	};
	
	private Handler handler=new Handler(){
		public void handleMessage(Message msg){
			switch (msg.what) {
			case CAROUSEL_TIME:
				if(currentIndex>=tips.length-1){//已经滚动到最后,从第一页开始
					viewPager.setCurrentItem(0);
				}else{//开始下一页
					viewPager.setCurrentItem(currentIndex+1);
				}
				break;
			}
		}
	};

	private ICarousePagerSelectView carousePagerSelectView=new ICarousePagerSelectView() {
		@Override
		public void carouseSelect(int index) {
	//		Toast.makeText(getActivity(), carousePageStr[index], Toast.LENGTH_SHORT).show();
		}
	};
	//初始化
	private View initCarouselHead(){
		View headView = LayoutInflater.from(this).inflate(R.layout.fragment_selected_header,recycler,false);

//		tvContent=(TextView) headView.findViewById(R.id.tv_content);		
		viewPager = (ViewPager)headView.findViewById(R.id.viewpager);
		selectedPagerAdapter=new SelectedPagerAdapter(this,carousePagerSelectView);
		viewPager.setOffscreenPageLimit(2);
		viewPager.setCurrentItem(0);
		viewPager.addOnPageChangeListener(onPageChangeListener);
		viewPager.setAdapter(selectedPagerAdapter);

		ViewGroup group = (ViewGroup) headView.findViewById(R.id.viewGroup);// 初始化底部显示控件
		tips = new ImageView[3];
		for (int i = 0; i < tips.length; i++){
			ImageView imageView = new ImageView(this);
			if (i == 0) {
				imageView.setBackgroundResource(R.drawable.yuan1);
			} else {
				imageView.setBackgroundResource(R.drawable.yuan4);
			}

			tips[i] = imageView;
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
			//LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
			layoutParams.leftMargin = 10;// 设置点点点view的左边距
			layoutParams.rightMargin = 10;// 设置点点点view的右边距
			group.addView(imageView, layoutParams);
		}

		timer = new Timer(true);//初始化计时器
		timer.schedule(task, 0, CAROUSEL_TIME);//延时0ms后执行,3000ms执行一次

		return headView;
	}
	private OnPageChangeListener onPageChangeListener=new OnPageChangeListener() {
		@Override
		public void onPageSelected(int index) {
			setImageBackground(index);// 改变点点点的切换效果
			currentIndex=index;
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}
		
	
	};
	
	/**
	 * 改变点点点的切换效果
	 * @param selectItems
	 */
	private void setImageBackground(int selectItems) {
		for (int i = 0; i < tips.length; i++) {
			if (i == selectItems) {
				tips[i].setBackgroundResource(R.drawable.yuan1);
			} else {
				tips[i].setBackgroundResource(R.drawable.yuan4);
			}
		}
	}

	public void setPullRefresh(boolean pullRefresh) {
		ptrClassicFrameLayout.setPullToRefresh(pullRefresh);
	}
}
