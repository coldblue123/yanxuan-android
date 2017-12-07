package com.example.shopping;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopping.Common.MyGridView;
import com.example.shopping.Model.Goods;
import com.example.shopping.Model.Quanju;
import com.example.shopping.Model.ShoppingCar;

public class MainActivity3 extends Activity {
	private LinearLayout layout_menu_1, layout_menu_2, layout_menu_3,
			layout_menu_4;
	Intent intent;

	private ImageView imagv_1, imagv_2, imagv_3, imagv_4;
	private TextView textV_1, textV_2, textV_3, textV_4;

	// ***********自定义变量********
	private Quanju q; // 定义全局类
	private GridView gridView;
	private LinearLayout ll;
	private ArrayList<HashMap<String, Object>> data_list;// 获取列表数据
	private double total;// 总价
	List<ShoppingCar> list;// 数据
	ShoppingCar sCar;// 单条购物车数据
	private ScrollView mScrollView;
	DecimalFormat df = new DecimalFormat("######0.00");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity3);
		q = (Quanju) getApplicationContext();// 获取所有表数据
		// 底部导航跳转页面方法
		routerPageFun();
		// 购物车动态加载
		list = ShoppingCar.selectShoppingBySign(q.ShoppingCarList, 0);
		this.addCarGridView((LinearLayout) MainActivity3.this
				.findViewById(R.id.fujin_btnlist_t2), list);
		// 初始化选中数据--测试
		q.ShoppingChoiceCarList = new ArrayList<ShoppingCar>();
		// 修改选中总价格和选中数量
		setTotalText();
		// 热门推荐动态加载
		this.addGridView((LinearLayout) MainActivity3.this
				.findViewById(R.id.fujin_btnlist_tl), Goods.selectGoodsByTop(
				Goods.sortGoodsListBySort(q.GoodsList, 0), 6));
		mScrollView = (ScrollView) findViewById(R.id.scrollView1);
		// 位置定位
		mScrollView.smoothScrollTo(0, 20);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_activity3, menu);
		return true;
	}

	// 修改选中总价格和选中数量
	public void setTotalText() {
		// 合计数量统计
		TextView tv = (TextView) MainActivity3.this
				.findViewById(R.id.shopping_much);
		tv.setText("已选（" + q.ShoppingChoiceCarList.size() + "）");
		// 是否选择同步
		CheckBox cbBox = (CheckBox) MainActivity3.this
				.findViewById(R.id.shopping_ok);
		// 判断是否有选择
		if (q.ShoppingChoiceCarList.size() > 0)
			cbBox.setChecked(true);
		else
			cbBox.setChecked(false);
		TextView tvtotal = (TextView) MainActivity3.this
				.findViewById(R.id.textView_total);
		total = ShoppingCar.getTotal(q.ShoppingChoiceCarList);
		tvtotal.setText("¥ " + df.format(total));
	}

	// 底部导航跳转页面方法
	public void routerPageFun() {
		layout_menu_1 = (LinearLayout) findViewById(R.id.layout_menu_1);
		layout_menu_2 = (LinearLayout) findViewById(R.id.layout_menu_2);
		layout_menu_3 = (LinearLayout) findViewById(R.id.layout_menu_3);
		layout_menu_4 = (LinearLayout) findViewById(R.id.layout_menu_4);
		imagv_3 = (ImageView) findViewById(R.id.imagev_3);
		imagv_3.setImageResource(R.drawable.center3_2);

		textV_3 = (TextView) findViewById(R.id.textV_3);
		textV_3.setTextColor(getResources().getColor(R.color.text_bg));

		layout_menu_2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = new Intent();
				intent.setClass(MainActivity3.this, MainActivity2.class);
				startActivity(intent);
				overridePendingTransition(0, 0);
				finish();
			}
		});
		layout_menu_1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = new Intent();
				intent.setClass(MainActivity3.this, MainActivity.class);
				startActivity(intent);
				overridePendingTransition(0, 0);
				finish();
			}
		});
		layout_menu_4.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = new Intent();
				intent.setClass(MainActivity3.this, MainActivity4.class);
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
		intent = new Intent(MainActivity3.this, Find_index.class);
		intent.putExtra("findEditStr", findEditStr); // 传递字符串数据
		startActivity(intent);
	}

	// 添加视图
	@SuppressLint("InlinedApi")
	private void addGridView(LinearLayout addll, List<Goods> list) {
		// 填充容器定位
		ll = addll;
		ll.removeAllViews();// 清空布局
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
		(MainActivity3.this, // 当前类
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
						intent = new Intent(MainActivity3.this,
								Goods_more_index.class);
						intent.putExtra("goodsID", goodsID); // 传递字符串数据
						startActivity(intent);
					}
				});
				return view;
			}
		};

		gridView.setAdapter(adapter); // 把适配器设置给ListView控件
	}

	// 添加购物车视图
	@SuppressLint("InlinedApi")
	private void addCarGridView(LinearLayout addll, List<ShoppingCar> list) {
		// 填充容器定位
		ll = addll;
		ll.removeAllViews();// 清空布局
		// 设置GridView属性
		gridView = new MyGridView(this);// 注意这里使用的是MyGridView,如果使用GridView的话，只会显示一行多一点，第二行显示不完全，使用MyGridView的话，能够完全显示出来。commend
		gridView.setNumColumns(1);
		gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
		gridView.setSelector(R.color.selectorColor);
		// 填充gridView数据与事件
		setCarSimple(list);
		// 添加GirdView到界面
		ll.addView(gridView, new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	}

	// 购车填充数据
	public void setCarSimple(List<ShoppingCar> list) {
		// 模糊查询 list转化为HashMap//不要用static

		data_list = ShoppingCar.getListToHashMap(list);
		SimpleAdapter adapter = new SimpleAdapter // 调用SimpleAdapter适配器
		(MainActivity3.this, // 当前类
				data_list, // 选项所有数据
				R.layout.shopping_car_item, // 与数据匹配的布局
				new String[] { "Image", "Time", "GoodsName", "Num", "PriceStr",
						"ShoopingID", "GoodsID" }, // 字符串数组，里面放参数名。
				new int[] { R.id.show_car_img, R.id.goods_intro,
						R.id.goods_name, R.id.goods_num, R.id.price_car_show,
						R.id.lable_shoopingID, R.id.lable_car_goodsID } // int数组，里面放数据的控件id，位置要与参数名一一对应。
		) {
			// SimpleAdapter每条记录的事件
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View view = super.getView(position, convertView, parent);

				TextView lableGoods = (TextView) view
						.findViewById(R.id.lable_car_goodsID);
				final int goodsID = Integer.parseInt(lableGoods.getText()
						.toString());// id转int 需要最终变量
				// 查看商品详情单击事件
				final LinearLayout lineargoods = (LinearLayout) view
						.findViewById(R.id.linear_car_goods);
				lineargoods.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						intent = new Intent(MainActivity3.this,
								Goods_more_index.class);
						intent.putExtra("goodsID", goodsID); // 传递字符串数据
						startActivity(intent);
					}
				});

				// ----------数目加减/商品选择操作-------------
				TextView lableShooping = (TextView) view
						.findViewById(R.id.lable_shoopingID);
				final int shoopingID = Integer.parseInt(lableShooping.getText()
						.toString());// id转int 需要最终变量
				// 单选按钮选择商品
				final CheckBox checkBox = (CheckBox) view
						.findViewById(R.id.select_all);
				checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							sCar = ShoppingCar.selectShoppingCarByID(
									q.ShoppingCarList, shoopingID);
							// 选中添加该条购物车
							q.ShoppingChoiceCarList.add(sCar);
							// 修改选中总价格和选中数量
							setTotalText();
						} else {
							// 没有删除
							sCar = ShoppingCar.selectShoppingCarByID(
									q.ShoppingCarList, shoopingID);
							q.ShoppingChoiceCarList.remove(sCar);
							// 修改选中总价格和选中数量
							setTotalText();
						}
					}
				});

				final TextView txtGoodsNum = (TextView) view
						.findViewById(R.id.goods_num);
				// 加加单击事件
				ImageView addImageView = (ImageView) view
						.findViewById(R.id.btn_add);
				addImageView.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						sCar = ShoppingCar.selectShoppingCarByID(
								q.ShoppingCarList, shoopingID);
						boolean found = q.ShoppingChoiceCarList.contains(sCar); // 是否包含在选择中
						// 修改选中的购物商品
						int index = q.ShoppingChoiceCarList.indexOf(sCar);// 选中索引值
						// 购物车中的一定修改
						int index2 = q.ShoppingCarList.indexOf(sCar);// 索引值没有为-1
						int newNum = sCar.getNum() + 1;
						sCar.setNum(newNum);
						if (found) {
							q.ShoppingChoiceCarList.set(index, sCar);// 修改
						}
						q.ShoppingCarList.set(index2, sCar);// 修改
						// 修改选中总价格和选中数量
						setTotalText();
						txtGoodsNum.setText(""+sCar.getNum());
					}
				});
				// 减减单击事件
				ImageView delImageView = (ImageView) view
						.findViewById(R.id.btn_delete);
				delImageView.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						sCar = ShoppingCar.selectShoppingCarByID(
								q.ShoppingCarList, shoopingID);
						if (sCar.getNum()==1) {
							Toast.makeText(getApplicationContext(),
									"受不了,不能再减少了o(*￣︶￣*)o", 1).show();
						}else {
							
							boolean found = q.ShoppingChoiceCarList.contains(sCar); // 是否包含在选择中
							// 修改选中的购物商品
							int index = q.ShoppingChoiceCarList.indexOf(sCar);// 选中索引值
							// 购物车中的一定修改
							int index2 = q.ShoppingCarList.indexOf(sCar);// 索引值没有为-1
							int newNum = sCar.getNum() - 1;
							sCar.setNum(newNum);
							if (found) {
								q.ShoppingChoiceCarList.set(index, sCar);// 修改
							}
							q.ShoppingCarList.set(index2, sCar);// 修改
							// 修改选中总价格和选中数量
							setTotalText();
							txtGoodsNum.setText(""+sCar.getNum());
						}	
					}
				});
				return view;
			}
		};

		gridView.setAdapter(adapter); // 把适配器设置给ListView控件
	}

}
