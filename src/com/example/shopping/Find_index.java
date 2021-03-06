package com.example.shopping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopping.Model.Goods;
import com.example.shopping.Model.Quanju;

public class Find_index extends Activity {

	String findEditStr;// 搜索框传的值
	private GridView gview;
	ArrayList<HashMap<String, Object>> list;// 获取列表数据
	Quanju q; // 定义数据表
	Intent intent;//页面跳转
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.find_index);

		// 接受MainActivity首页搜索框传来的值
		findEditStr = getIntent().getStringExtra("findEditStr");
		q = (Quanju) getApplicationContext();// 获取所有表数据
		setSimple();// 填充数据
		// 搜索事件
		btFindGo();
	}

	// ************按钮事件**************************

	/**
	 * 本方法作用：ListView与SimpleAdatper结合实现列表填充数据
	 */
	public void setSimple() {
		// 模糊查询 list转化为HashMap//不要用static
		list = Goods.getListToHashMap(Goods.selectGoodsByNameOrClass(
				q.GoodsList, findEditStr));
		SimpleAdapter adapter = new SimpleAdapter // 调用SimpleAdapter适配器
		(Find_index.this, // 当前类
				list, // 选项所有数据
				R.layout.find_list_item, // 与数据匹配的布局
				new String[] { "GoodsID", "Image", "Intro", "UintName",
						"PriceStr" }, // 字符串数组，里面放参数名。
				new int[] { R.id.lable_GoodsID, R.id.image_show,
						R.id.lable_Intro, R.id.lable_show, R.id.price_show } // int数组，里面放数据的控件id，位置要与参数名一一对应。
		) { // SimpleAdapter每条记录的事件
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
						intent = new Intent(Find_index.this,
								Goods_more_index.class);
						intent.putExtra("goodsID", goodsID); // 传递字符串数据
						startActivity(intent);
					}
				});
				return view;
			}
		};
		gview = (GridView) findViewById(R.id.gview);// 找ListView控件
		gview.setAdapter(adapter); // 把适配器设置给ListView控件
	}

	// 主页搜索事件
	public void btFindGo() {
		TextView bt = (TextView) Find_index.this.findViewById(R.id.find);
		bt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				TextView tv = (TextView) Find_index.this
						.findViewById(R.id.findText);
				findEditStr = tv.getText().toString().trim();// 获取输入框值
				// 跳转到第二个页面
				setSimple();// 填充数据
			}
		});

	}

}
