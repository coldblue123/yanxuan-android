package com.example.shopping;

import java.util.ArrayList;
import java.util.List;

import cn.androiddevelop.cycleviewpager.lib.CycleViewPager;
import cn.androiddevelop.cycleviewpager.lib.CycleViewPager.ImageCycleViewListener;

import com.stevenhu.android.phone.bean.ADInfo;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

public class Goods_more_index extends Activity {

	
	// 轮播图相关组件
		private ScrollView mScrollView;
		private float mLastX;
		private ViewPager viewPager;
		private List<ImageView> views = new ArrayList<ImageView>();
		private List<ADInfo> infos = new ArrayList<ADInfo>();
		private CycleViewPager cycleViewPager;
		// 轮播图图片
		private int[] imageUrls = {
				R.drawable.banner_test,
				R.drawable.banner_test1,
				R.drawable.banner_test,
				R.drawable.banner_test,
				R.drawable.banner_test
				};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.goods_more_index);
		// 轮播图初始化
		initialize();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.goods_more_index, menu);
		return true;
	}
	
	// 轮播图初始化方法
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
//		cycleViewPager.setIndicatorCenter();
	}
	
	// 点击图片之后的监听操作
	private ImageCycleViewListener mAdCycleViewListener = new ImageCycleViewListener() {

		@Override
		public void onImageClick(ADInfo info, int position, View imageView) {
			if (cycleViewPager.isCycle()) {
				position = position - 1;
				Toast.makeText(Goods_more_index.this,
						"position-->" + info.getContent(), Toast.LENGTH_SHORT)
						.show();
			}
			
		}

	};

}
