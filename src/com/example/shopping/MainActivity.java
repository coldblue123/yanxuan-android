package com.example.shopping;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.androiddevelop.cycleviewpager.lib.CycleViewPager;
import cn.androiddevelop.cycleviewpager.lib.CycleViewPager.ImageCycleViewListener;

import com.example.shopping.Model.Quanju;
import com.stevenhu.android.phone.bean.ADInfo;

public class MainActivity extends Activity {

	private LinearLayout layout_menu_1, layout_menu_2, layout_menu_3,
			layout_menu_4;
	Intent intent;
	
	
	private List<ImageView> views = new ArrayList<ImageView>();
	private List<ADInfo> infos = new ArrayList<ADInfo>();
	private CycleViewPager cycleViewPager;
	
	private int[] imageUrls = {
			R.drawable.banner_test,
			R.drawable.banner_test,
			R.drawable.banner_test,
			};
	

	Quanju q; // 定义全局类
	private ImageView imagv_1, imagv_2, imagv_3, imagv_4;
	private TextView textV_1, textV_2, textV_3, textV_4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		q = (Quanju) getApplicationContext();// 获取所有表数据
		q.Init();// 初始化数据

		// 页面底部导航的跳转方法
		routerPageFun();
		 // 搜索事件初始化加载 
		  btFindGo();
		// 初始化banner轮播图
			initialize();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// 页面底部导航的跳转方法
	public void routerPageFun() {
		layout_menu_1 = (LinearLayout) findViewById(R.id.layout_menu_1);
		layout_menu_2 = (LinearLayout) findViewById(R.id.layout_menu_2);
		layout_menu_3 = (LinearLayout) findViewById(R.id.layout_menu_3);
		layout_menu_4 = (LinearLayout) findViewById(R.id.layout_menu_4);

		// 更改图标
		imagv_1 = (ImageView) findViewById(R.id.imagev_1);
		imagv_1.setImageResource(R.drawable.center1_2);
		// 更改文字颜色
		textV_1 = (TextView) findViewById(R.id.textV_1);
		textV_1.setTextColor(getResources().getColor(R.color.text_bg));

		// 跳转方法
		layout_menu_2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = new Intent();
				intent.setClass(MainActivity.this, MainActivity2.class);
				startActivity(intent);
				overridePendingTransition(0, 0);
				finish();
			}
		});

		layout_menu_3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = new Intent();
				intent.setClass(MainActivity.this, MainActivity3.class);
				startActivity(intent);
				overridePendingTransition(0, 0);
				finish();

			}
		});
		layout_menu_4.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = new Intent();
				intent.setClass(MainActivity.this, MainActivity4.class);
				startActivity(intent);
				overridePendingTransition(0, 0);
				finish();
			}
		});
	}
	
	
	// *********按钮事件方法******************

		// 主页搜索事件
		public void btFindGo() {
			TextView bt = (TextView) MainActivity.this.findViewById(R.id.find);
			bt.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					TextView tv = (TextView) MainActivity.this
							.findViewById(R.id.findText);
					String findEditStr = tv.getText().toString().trim();// 获取输入框值
					// 跳转到第二个页面
				    intent = new Intent(MainActivity.this, Find_index.class);
					intent.putExtra("findEditStr", findEditStr); // 传递字符串数据
					startActivity(intent);
				}
			});

		}
		
		
		@SuppressLint("NewApi")
		private void initialize() {
			
			cycleViewPager = (CycleViewPager) getFragmentManager()
					.findFragmentById(R.id.fragment_cycle_viewpager_content);
			
			for(int i = 0; i < imageUrls.length; i ++){
				ADInfo info = new ADInfo();
				info.setUrl(imageUrls[i]);
				info.setContent("图片-->" + i );
				infos.add(info);
			}
			
			
			ImageView imageView = new ImageView(this);  
			imageView.setBackgroundResource(imageUrls[0]);
			views.add(imageView);
			
			for (int i = 0; i < infos.size(); i++) {
				ImageView imageView2 = new ImageView(this);  
				imageView2.setBackgroundResource(imageUrls[i]);
				views.add(imageView2);
			}
			// 将第一个ImageView添加进来
			ImageView imageView3 = new ImageView(this);  
			imageView3.setBackgroundResource(imageUrls[2]);
			views.add(imageView3);
			
			// 设置循环，在调用setData方法前调用
			cycleViewPager.setCycle(true);

			// 在加载数据前设置是否循环
			cycleViewPager.setData(views, infos, mAdCycleViewListener);
			//设置轮播
			cycleViewPager.setWheel(false);

		    // 设置轮播时间，默认5000ms
			cycleViewPager.setTime(2000);
			//设置圆点指示图标组居中显示，默认靠右
//			cycleViewPager.setIndicatorCenter();
		}
		
		// 点击图片之后的监听操作
		private ImageCycleViewListener mAdCycleViewListener = new ImageCycleViewListener() {

			@Override
			public void onImageClick(ADInfo info, int position, View imageView) {
				if (cycleViewPager.isCycle()) {
					position = position - 1;
					Toast.makeText(MainActivity.this,
							"position-->" + info.getContent(), Toast.LENGTH_SHORT)
							.show();
				}
				
			}

		};

}
