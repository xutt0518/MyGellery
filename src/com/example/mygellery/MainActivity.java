package com.example.mygellery;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
//private Context context;
	private ImageAdapter imgAdapter = null;         // 声明图片资源对象  
    private Gallery gallery = null;  
    private Button seconed;
    private Button third;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgAdapter = new ImageAdapter(this);  
        gallery=(Gallery)findViewById(R.id.gallery);
        //TextView text=(TextView)findViewById(R.id.tv);
        gallery.setAdapter(imgAdapter);
        gallery.setOnItemClickListener(listener);  
        gallery.setSelection(imgAdapter.images.length*100);
        gallery.setUnselectedAlpha(0.3f); 
        seconed=(Button)findViewById(R.id.second);
        third=(Button)findViewById(R.id.third);
        seconed.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(MainActivity.this,TestRecyclerViewActicity.class);
				startActivity(intent);
			}       	
        });
        third.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(MainActivity.this,LoadRecyclerViewActicity.class);
				startActivity(intent);
			}       	
        });
    }
    AdapterView.OnItemClickListener listener=new AdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view,
				int position, long id) {
			Toast.makeText(MainActivity.this, "图片"+(position+1), Toast.LENGTH_SHORT).show();
			
		}
	};
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    class ImageAdapter extends BaseAdapter{
    	private Context context;
    	public ImageAdapter(Context context) {
			super();
			this.context = context;
		}

		private Integer[] images={R.drawable.gellty1,R.drawable.geller2,R.drawable.gelleery3,R.drawable.gerry4
    			                 ,R.drawable.gerry5,R.drawable.gerry6,R.drawable.gerry7,R.drawable.gerry8};
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return Integer.MAX_VALUE;
		}
		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return images[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ImageView imageview=new ImageView(context);
			imageview.setImageResource(images[position % images.length]);
			imageview.setLayoutParams(new Gallery.LayoutParams(480, 400));
			imageview.setScaleType(ImageView.ScaleType.CENTER);
			imageview.setBackgroundColor(Color.alpha(1));
		    return imageview;
		}
    	
    }
}
