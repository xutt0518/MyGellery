package com.example.mygellery;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class AnimationActivity extends Activity {
	private Button Alpha;
	private Button Scale;
	private Button translate;
	private Button rotate;
	private ImageView img;
	private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        Alpha=(Button)findViewById(R.id.alpha);
        Scale=(Button)findViewById(R.id.scale);
        translate=(Button)findViewById(R.id.translater);
        rotate=(Button)findViewById(R.id.rotate);
        img=(ImageView)findViewById(R.id.imageview);
        img.setImageResource(R.drawable.friends);
        ClickListener listener=new ClickListener();
        Alpha.setOnClickListener(listener);
        Scale.setOnClickListener(listener);
        translate.setOnClickListener(listener);
        rotate.setOnClickListener(listener);
    }
    class ClickListener implements OnClickListener{
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.alpha:
			int i=7;
			AlphaAnimation alpha=new AlphaAnimation(0.0f,1.0f);
			alpha.setDuration(5000);
			alpha.setFillAfter(true);
			//alpha.setInterpolator(context, i);
			img.startAnimation(alpha);
			break;
		case R.id.scale:
			ScaleAnimation scale=new ScaleAnimation(0, 100, 0, 100, 50,50);
			scale.setFillAfter(false);
			scale.setFillBefore(true);
			scale.setDuration(5000);
			img.startAnimation(scale);
			break;
		case R.id.translater:
		    break;
		case R.id.rotate:
			RotateAnimation rotate=new RotateAnimation(0,360,50,50);
			rotate.setFillBefore(true);
			rotate.setDuration(5000);
			img.startAnimation(rotate);
			break;
		default:
		   break;
		}
	}
    }
}
