package com.example.shopping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import cn.androiddevelop.cycleviewpager.lib.CycleViewPager;
import cn.androiddevelop.cycleviewpager.lib.CycleViewPager.ImageCycleViewListener;

import com.example.shopping.Common.CommonMath;
import com.example.shopping.Common.MyGridView;
import com.example.shopping.Model.Goods;
import com.example.shopping.Model.Quanju;
import com.stevenhu.android.phone.bean.ADInfo;

public class Goods_more_index extends Activity {

	// 轮播图相关组件
	private ScrollView mScrollView;
	private float mLastX;
	private ViewPager viewPager;
	private List<ImageView> views = new ArrayList<ImageView>();
	private List<ADInfo> infos = new ArrayList<ADInfo>();
	private CycleViewPager cycleViewPager;
	// 轮播图图片
	private int[] imageUrls = { R.drawable.banner_test,
			R.drawable.banner_test1, R.drawable.banner_test,
			R.drawable.banner_test, R.drawable.banner_test };

	// ***自定义字段****
	int goodsID;// 商品id
	ArrayList<HashMap<String, Object>> data_list;// 获取列表数据
	Goods goodsModel;
	Quanju q; // 定义数据表
	private GridView gridView;
	private LinearLayout ll;
	Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.goods_more_index);
		q = (Quanju) getApplicationContext();// 获取所有表数据
		// 接受MainActivity首页搜索框传来的值
		goodsID = getIntent().getExtras().getInt("goodsID");
		goodsModel = Goods.selectGoodsByID(q.GoodsList, goodsID);
		// 初始化商品信息
		getGoodsDetail();
		// 轮播图控件初始化
		initialize();
		//推荐添加视图
		addGridView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.goods_more_index, menu);
		return true;
	}

	
	// 商品信息详细显示
		void getGoodsDetail(){
			imageUrls=CommonMath.getImageResourceListID(goodsModel.getImage());
			//商品名设置
			TextView tvGoods = (TextView) Goods_more_index.this.findViewById(R.id.textGoodsName);
			tvGoods.setText(goodsModel.getName());
			//价格设置
			TextView tvPrice = (TextView) Goods_more_index.this.findViewById(R.id.textView3);
			tvPrice.setText( "¥ " + goodsModel.getPrice());
			//简介设置
			TextView tvIntro = (TextView) Goods_more_index.this.findViewById(R.id.textView2);
			tvIntro.setText(goodsModel.getIntro());
		};
		
	//轮播图初始化
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

	
	// 添加GridView视图
		@SuppressLint("InlinedApi")
		private void addGridView() {
			// 填充容器定位
			ll = (LinearLayout)Goods_more_index.this.findViewById(R.id.fujin_btnlist_goods);
			// 设置GridView属性
			gridView = new MyGridView(this);// 注意这里使用的是MyGridView,如果使用GridView的话，只会显示一行多一点，第二行显示不完全，使用MyGridView的话，能够完全显示出来。commend
			gridView.setNumColumns(2);
			gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
			gridView.setSelector(R.color.selectorColor);
			// 填充gridView数据与事件
			setSimple();
			// 添加GirdView到界面
			ll.addView(gridView, new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		}
		/**
		 * 本方法作用：GridView与SimpleAdatper结合实现列表填充数据--与列事件
		 */
		public void setSimple() {
			// 模糊查询 list转化为HashMap//不要用staticGoods.selectGoodsByNameOrClass(
//			q.GoodsList,goodsModel.getClass().toString())
			data_list = Goods.getListToHashMap(Goods.selectGoodsByTop(q.GoodsList,
					6));
			SimpleAdapter adapter = new SimpleAdapter // 调用SimpleAdapter适配器
			(Goods_more_index.this, // 当前类
					data_list, // 选项所有数据
					R.layout.find_list_item, // 与数据匹配的布局
					new String[] { "GoodsID","Image", "Intro", "UintName", "PriceStr" }, // 字符串数组，里面放参数名。
					new int[] { R.id.lable_GoodsID, R.id.image_show, R.id.lable_Intro, R.id.lable_show,
							R.id.price_show } // int数组，里面放数据的控件id，位置要与参数名一一对应。
			)
			{
				// SimpleAdapter每条记录的事件
				@Override
				public View getView(int position, View convertView, ViewGroup parent) {
					 View  view =super.getView(position, convertView, parent);
					 TextView lableGoods= (TextView)view.findViewById(R.id.lable_GoodsID);
					 final int goodsID=Integer.parseInt(lableGoods.getText().toString());//id转int 需要最终变量
					 LinearLayout lineargoods= (LinearLayout)view.findViewById(R.id.linear_goods);
					 lineargoods.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							intent = new Intent(Goods_more_index.this, Goods_more_index.class);
							intent.putExtra("goodsID", goodsID); // 传递字符串数据
							startActivity(intent);
						}
					});		 
					return view;
				}
			}
			;

			gridView.setAdapter(adapter); // 把适配器设置给ListView控件
		}

	
	
	
	//添加购物车监听事件
	

}
