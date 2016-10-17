package com.example.mygellery;

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

public class LoadRecyclerViewActicity extends Activity{

	private SwipeRefreshLayout demoswipe;
	private RecyclerView demo_recycler;
	private RefreshRecyclerAdapter adapter;
	private LinearLayoutManager linearLayoutManager;
	private int lastVisibleItem;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recycler);
		demoswipe=(SwipeRefreshLayout)findViewById(R.id.demo_swipe);
		demo_recycler=(RecyclerView)findViewById(R.id.demo_recycler);
		linearLayoutManager=new LinearLayoutManager(this);
	    linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
	    demo_recycler.setLayoutManager(linearLayoutManager);
	    adapter = new RefreshRecyclerAdapter(this);
	        //添加分隔线
	    // demo_recycler.addItemDecoration(new AdvanceDecoration(this, OrientationHelper.VERTICAL));
	     demo_recycler.setAdapter(adapter);
		demoswipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			
			@Override
			public void onRefresh() {
				List<String> data=new ArrayList<String>();
				for(int i=0;i<=5;i++){
					data.add("第"+i+"条数据");
				}
				adapter.addItem(data);
				demoswipe.setRefreshing(false);
				Toast.makeText(LoadRecyclerViewActicity.this, "", Toast.LENGTH_LONG).show();
			}
		});
	}
	public class RefreshRecyclerAdapter extends RecyclerView.Adapter<RefreshRecyclerAdapter.ViewHolder>{
	    private LayoutInflater mInflater;
	    private List<String> mTitles=null;
	    public RefreshRecyclerAdapter(Context context){
	       this.mInflater=LayoutInflater.from(context);
	        this.mTitles=new ArrayList<String>();
	        for (int i=0;i<20;i++){
	            int index=i+1;
	           mTitles.add("item"+index);
	        }
	    }
	    /**
	     * item显示类型
	     * @param parent
	     * @param viewType
	     * @return
	     */
	    @Override
	    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
	        View view=mInflater.inflate(R.layout.item_recycler_layout,parent,false);
	        //这边可以做一些属性设置，甚至事件监听绑定
	        //view.setBackgroundColor(Color.RED);
	        ViewHolder viewHolder=new ViewHolder(view);

	        return viewHolder;
	    }

	    /**
	     * 数据的绑定显示
	     * @param holder
	     * @param position
	     */
	    @Override
	    public void onBindViewHolder(ViewHolder holder, int position) {
	       holder.item_tv.setText(mTitles.get(position));
	        holder.itemView.setTag(position);
	    }
	    @Override
	    public int getItemCount() {
	        return mTitles.size();
	    }

	    //自定义的ViewHolder，持有每个Item的的所有界面元素
	    public class ViewHolder extends RecyclerView.ViewHolder {
	        public TextView item_tv;
	        public ViewHolder(View view){
	            super(view);
	            item_tv = (TextView)view.findViewById(R.id.item_tv);
	        }
	    }

	    //添加数据
	    public void addItem(List<String> newDatas) {
	        //mTitles.add(position, data);
	        //notifyItemInserted(position);
	        newDatas.addAll(mTitles);
	        mTitles.removeAll(mTitles);
	        mTitles.addAll(newDatas);
	        notifyDataSetChanged();
	    }

	    public void addMoreItem(List<String> newDatas) {
	        mTitles.addAll(newDatas);
	        notifyDataSetChanged();
	    }
	}

}
