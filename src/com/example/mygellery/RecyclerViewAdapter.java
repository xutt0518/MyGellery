package com.example.mygellery;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
	private List<String> list;
	private Context context;
	private static final int TYPE_HEADER=0;
	private static final int TYPE_NORMAL=1;
	private View mHeaderView;
	public RecyclerViewAdapter(Context context) {
		super();
		this.context = context;
		list=new ArrayList<String>();
		init();
	}
	public void init(){
		for(int i=0;i<=40;i++){
			String data="第"+i+"条数据";
			list.add(data);
		}
	}
	public void getfirst(){
		list.clear();
		init();
	
		System.out.println("getFirst---");
	}
	@Override
	public int getItemViewType(int position) {
		if(mHeaderView==null) return TYPE_HEADER; 
		if(position==0) return TYPE_HEADER;
		return TYPE_HEADER;
	}
    
	@Override
	public int getItemCount() {
		System.out.println("getItemCount---"+list.size());
		return list.size();
	}
	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder arg0, int arg1) {
		MyViewHolder holder=(MyViewHolder) arg0;
		String data=list.get(arg1);
		System.out.println("onBindViewHolder----"+data);
		holder.textview.setText(data);
	}
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
	    View view=LayoutInflater.from(context).inflate(R.layout.item_recycler_layout,arg0, false);
		return new MyViewHolder(view);
	}
	public static class MyViewHolder extends RecyclerView.ViewHolder{
		private TextView textview;
		private ImageView image;
		public MyViewHolder(View arg0) {
			super(arg0);
			textview=(TextView)arg0.findViewById(R.id.item_tv);
			image=(ImageView)arg0.findViewById(R.id.imge_tv);
		}		
	}

}

