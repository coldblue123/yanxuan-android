package com.example.shopping;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	private LinearLayout 
	layout_menu_1,
	layout_menu_2,
	layout_menu_3,
	layout_menu_4;
	Intent intent;
	
	private ImageView imagv_1,imagv_2,imagv_3,imagv_4;
	private TextView textV_1,textV_2,textV_3,textV_4;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     // 页面底部导航的跳转方法
        routerPageFun();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
   // 页面底部导航的跳转方法
   public void routerPageFun() {
	   layout_menu_1=(LinearLayout)findViewById(R.id.layout_menu_1);
       layout_menu_2=(LinearLayout)findViewById(R.id.layout_menu_2);
       layout_menu_3=(LinearLayout)findViewById(R.id.layout_menu_3);
       layout_menu_4=(LinearLayout)findViewById(R.id.layout_menu_4);
       
       // 更改图标
       imagv_1=(ImageView)findViewById(R.id.imagev_1);
       imagv_1.setImageResource(R.drawable.center1_2);
       // 更改文字颜色
       textV_1=(TextView)findViewById(R.id.textV_1);
       textV_1.setTextColor(getResources().getColor(R.color.text_bg));
       
       //跳转方法
       layout_menu_2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = new Intent();
			    intent.setClass(MainActivity.this,MainActivity2.class);
			    startActivity(intent);
			    overridePendingTransition(0,0); 
			    finish();
			}
		});
       
       layout_menu_3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = new Intent();
			    intent.setClass(MainActivity.this,MainActivity3.class);
			    startActivity(intent);
			    overridePendingTransition(0,0); 
			    finish();
			    
			}
		});
       layout_menu_4.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = new Intent();
			    intent.setClass(MainActivity.this,MainActivity4.class);
			    startActivity(intent);
			    overridePendingTransition(0,0); 
			    finish();
			}
		});
   }
    
}
