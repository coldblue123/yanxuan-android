package com.example.shopping.Common;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;
import cn.androiddevelop.cycleviewpager.lib.CycleViewPager;
import cn.androiddevelop.cycleviewpager.lib.CycleViewPager.ImageCycleViewListener;

import com.example.shopping.Goods_more_index;
import com.example.shopping.R;
import com.example.shopping.Model.Goods;
import com.example.shopping.Model.Quanju;
import com.stevenhu.android.phone.bean.ADInfo;

//公共方法类
public class CommonMath extends Activity {
	
	// 轮播图相关组件
		private ScrollView mScrollView;
		private float mLastX;
		private ViewPager viewPager;
		private List<ImageView> views = new ArrayList<ImageView>();
		private List<ADInfo> infos = new ArrayList<ADInfo>();
		private CycleViewPager cycleViewPager;
		
	
	/**
	 * 获取图片名称获取图片的资源id的方法
	 * 
	 * @param imageName
	 * @return
	 */
	public static int getImageResourceID(String imageName) {
		String[] str = imageName.split(",");
		imageName = str[0];
		Class drawable = R.drawable.class;
		int r_id;
		Field field = null;
		try {
			field = drawable.getField(imageName);
			String a = field.getName();
			r_id = field.getInt(a);
		} catch (Exception e) {
			r_id = R.drawable.noproduct;
			Log.e("ERROR", "PICTURE NOT　FOUND！");
		}
		return r_id;
	}

	// 返回图片组资源ID
	public static int[] getImageResourceListID(String imageName) {
		String[] str = imageName.split(",");
		int[] r_ids = {};
		Class drawable = R.drawable.class;

		try {
			for (int i = 0; i < str.length; i++) {
				r_ids = addIntArry(r_ids, getImageResourceID(str[i]));
			}
		} catch (Exception e) {
			r_ids = addIntArry(r_ids, R.drawable.noproduct);
			Log.e("ERROR", "PICTURE NOT　FOUND！");
		}
		return r_ids;
	}

	// 添加数组元素
	@SuppressLint("NewApi")
	static int[] addIntArry(int[] ary, int value) {
		ary = Arrays.copyOf(ary, ary.length + 1);
		ary[ary.length - 1] = value;
		return ary;
	}

	// 修改一条记录访问量通过id
	public boolean updateGoodsByID(int id) {
		boolean b = false;
		Quanju q = (Quanju) getApplicationContext();// 获取所有表数据
		for (Goods goods : q.GoodsList) {
			if (goods.getID() == id) {
				goods.setVistNum(goods.getVistNum() + 1);
				b = true;
				break;
			}
		}
		return b;
	}
	

	// 轮播图初始化方法-----报错不能用
	@SuppressLint("NewApi")
	public  void initialize(int[] imageUrls,ImageCycleViewListener mAdCycleViewListener ) {

		cycleViewPager = (CycleViewPager) getFragmentManager()
				.findFragmentById(R.id.fragment_cycle_viewpager_content);

		for (int i = 0; i < imageUrls.length; i++) {
			ADInfo info = new ADInfo();
			info.setUrl(imageUrls[i]);
			info.setContent("图片-->" + i);
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
		// 设置轮播
		cycleViewPager.setWheel(false);

		// 设置轮播时间，默认5000ms
		cycleViewPager.setTime(2000);
		// 设置圆点指示图标组居中显示，默认靠右
		// cycleViewPager.setIndicatorCenter();
	}
	
	
		
	

}
