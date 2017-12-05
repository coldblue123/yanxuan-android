package com.example.shopping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.androiddevelop.cycleviewpager.lib.CycleViewPager;
import cn.androiddevelop.cycleviewpager.lib.CycleViewPager.ImageCycleViewListener;

import com.example.shopping.Common.MyGridView;
import com.example.shopping.Model.Goods;
import com.example.shopping.Model.Quanju;
import com.stevenhu.android.phone.bean.ADInfo;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends Activity {
	private LinearLayout layout_menu_1, layout_menu_2, layout_menu_3,
			layout_menu_4;
	Intent intent;
	private ImageView imagv_1, imagv_2, imagv_3, imagv_4;
	private TextView textV_1, textV_2, textV_3, textV_4;
	// 轮播图相关组件
	private ScrollView mScrollView;
	private float mLastX;
	private ViewPager viewPager;
	private List<ImageView> views = new ArrayList<ImageView>();
	private List<ADInfo> infos = new ArrayList<ADInfo>();
	private CycleViewPager cycleViewPager;
	// 轮播图图片
	private int[] imageUrls;

	// ***********自定义变量********
	Quanju q; // 定义全局类
	private GridView gridView;
	private LinearLayout ll;
	ArrayList<HashMap<String, Object>> data_list;// 获取列表数据

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity2);
		q = (Quanju) getApplicationContext();// 获取所有表数据
		imageUrls=q.bannerImageUrls2;
		// 页面底部导航的跳转方法
		routerPageFun();
		// 热门推荐动态加载
		this.addGridView((LinearLayout) MainActivity2.this
				.findViewById(R.id.fujin_btnlist_tl),Goods.selectGoodsByTop(Goods.sortGoodsListBySort(q.GoodsList, 0),
						4));
		// 特色推荐动态加载
		this.addGridView((LinearLayout) MainActivity2.this
				.findViewById(R.id.fujin_btnlist_t2),Goods.selectGoodsByTop(Goods.sortGoodsListBySort(q.GoodsList, 1),
						4));
		// 初始化banner轮播图
		initialize();
		TextView tv=(TextView)MainActivity2.this
			.findViewById(R.id.textView_title);
		tv.setText("热荐");
		//位置定位
		mScrollView.smoothScrollTo(0,20);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_activity2, menu);
		return true;
	}

	// 页面底部导航的跳转方法
	public void routerPageFun() {
		layout_menu_1 = (LinearLayout) findViewById(R.id.layout_menu_1);
		layout_menu_2 = (LinearLayout) findViewById(R.id.layout_menu_2);
		layout_menu_3 = (LinearLayout) findViewById(R.id.layout_menu_3);
		layout_menu_4 = (LinearLayout) findViewById(R.id.layout_menu_4);

		imagv_2 = (ImageView) findViewById(R.id.imagev_2);
		imagv_2.setImageResource(R.drawable.center2_2);

		textV_2 = (TextView) findViewById(R.id.textV_2);
		textV_2.setTextColor(getResources().getColor(R.color.text_bg));

		layout_menu_1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = new Intent();
				intent.setClass(MainActivity2.this, MainActivity.class);
				startActivity(intent);
				overridePendingTransition(0, 0);
				finish();
			}
		});
		layout_menu_3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = new Intent();
				intent.setClass(MainActivity2.this, MainActivity3.class);
				startActivity(intent);
				overridePendingTransition(0, 0);
				finish();
			}
		});
		layout_menu_4.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = new Intent();
				intent.setClass(MainActivity2.this, MainActivity4.class);
				startActivity(intent);
				overridePendingTransition(0, 0);
				finish();
			}
		});
	}

	// 跳转搜索页面----一定不要私有,界面才能找到
	public void gotoFindIndex(View view) {
		String findEditStr = view.getTag().toString();// 通过tag取值
		// 跳转到第二个页面
		intent = new Intent(MainActivity2.this, Find_index.class);
		intent.putExtra("findEditStr", findEditStr); // 传递字符串数据
		startActivity(intent);
	}

	// 轮播图初始化
	@SuppressLint("NewApi")
	private void initialize() {

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
		cycleViewPager.setWheel(true);

		// 设置轮播时间，默认5000ms
		cycleViewPager.setTime(2000);
		// 设置圆点指示图标组居中显示，默认靠右
		// cycleViewPager.setIndicatorCenter();
		// 卷轴视图获取
		mScrollView = (ScrollView) findViewById(R.id.scrollView1);
		// 基础轮播视图-解决与ScrollView冲突
		viewPager = cycleViewPager.getViewPager();
		viewPager.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction();

				if (action == MotionEvent.ACTION_DOWN) {
					// 记录点击到ViewPager时候，手指的X坐标
					mLastX = event.getX();
				}
				if (action == MotionEvent.ACTION_MOVE) {
					// 超过阈值
					if (Math.abs(event.getX() - mLastX) > 60f) {
						// mRefreshLayout.setEnabled(false);//下拉刷新视图处理
						mScrollView.requestDisallowInterceptTouchEvent(true);
					}
				}
				if (action == MotionEvent.ACTION_UP) {
					// 用户抬起手指，恢复父布局状态
					mScrollView.requestDisallowInterceptTouchEvent(false);
					// mRefreshLayout.setEnabled(true);//下拉刷新视图处理
				}
				return false;
			}
		});

	}

	// 点击图片之后的监听操作
	private ImageCycleViewListener mAdCycleViewListener = new ImageCycleViewListener() {

		@Override
		public void onImageClick(ADInfo info, int position, View imageView) {
			if (cycleViewPager.isCycle()) {
				position = position - 1;
				Toast.makeText(MainActivity2.this,
						"position-->" + info.getContent(), Toast.LENGTH_SHORT)
						.show();
			}

		}

	};

	// 添加视图
	private void addGridView(LinearLayout addll,List<Goods> list) {
		// 填充容器定位
		ll = addll;
		// 设置GridView属性
		gridView = new MyGridView(this);// 注意这里使用的是MyGridView,如果使用GridView的话，只会显示一行多一点，第二行显示不完全，使用MyGridView的话，能够完全显示出来。commend
		gridView.setNumColumns(2);
		gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
		gridView.setSelector(R.color.selectorColor);
		// 填充gridView数据与事件
		setSimple(list);
		// 添加GirdView到界面
		ll.addView(gridView, new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	}

	/**
	 * 本方法作用：GridView与SimpleAdatper结合实现列表填充数据--与列事件
	 */
	public void setSimple(List<Goods> list) {
		// 模糊查询 list转化为HashMap//不要用static
		data_list = Goods.getListToHashMap(list);
		SimpleAdapter adapter = new SimpleAdapter // 调用SimpleAdapter适配器
		(MainActivity2.this, // 当前类
				data_list, // 选项所有数据
				R.layout.find_list_item, // 与数据匹配的布局
				new String[] { "GoodsID", "Image", "Intro", "UintName",
						"PriceStr" }, // 字符串数组，里面放参数名。
				new int[] { R.id.lable_GoodsID, R.id.image_show,
						R.id.lable_Intro, R.id.lable_show, R.id.price_show } // int数组，里面放数据的控件id，位置要与参数名一一对应。
		) {
			// SimpleAdapter每条记录的事件
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View view = super.getView(position, convertView, parent);
				TextView lableGoods = (TextView) view
						.findViewById(R.id.lable_GoodsID);
				final int goodsID = Integer.parseInt(lableGoods.getText()
						.toString());// id转int 需要最终变量
				LinearLayout lineargoods = (LinearLayout) view
						.findViewById(R.id.linear_goods);
				lineargoods.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						intent = new Intent(MainActivity2.this,
								Goods_more_index.class);
						intent.putExtra("goodsID", goodsID); // 传递字符串数据
						startActivity(intent);
					}
				});
				return view;
			}
		};

		gridView.setAdapter(adapter); // 把适配器设置给ListView控件
		/*
		 * // 处理选择结果 ListView每行的单击事件 gridView.setOnItemClickListener(new
		 * OnItemClickListener() {
		 * 
		 * @Override public void onItemClick(AdapterView<?> parent, View view,
		 * int position, long id) { Toast.makeText(getApplicationContext(),
		 * "你选择了" + position, 1) .show();
		 * 
		 * } });
		 */
	}

}