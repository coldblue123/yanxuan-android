package com.example.shopping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.shopping.Model.Goods;
import com.example.shopping.Model.Quanju;

public class Find_index extends Activity {

	String findEditStr;// 搜索框传的值
	private GridView gview;
	ArrayList<HashMap<String, Object>> list;// 获取列表数据
	private SimpleAdapter sim_adapter;
	Quanju q; // 定义数据表
	// 图片封装为一个数组
	private int[] icon = { R.drawable.test1, R.drawable.test1,
			R.drawable.test1, R.drawable.test1, R.drawable.test1 };
	private String[] price = { "230", "230", "230", "11", "44" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.find_index);

		// 接受MainActivity首页搜索框传来的值
		findEditStr = getIntent().getStringExtra("findEditStr");
		q = (Quanju) getApplicationContext();// 获取所有表数据
		initdata();// 列表数据初始
		setSimple();// 填充数据
	}

	/**
	 * 本方法作用：准备数据 初始化数据
	 * */
	public void initdata() {
		// 模糊查询 list转化为HashMap//不要用static
		list = Goods.getListToHashMap(Goods.selectGoodsByNameOrClass(
				q.GoodsList, findEditStr));
		// Toast.makeText(getApplicationContext(),
		// "数组："+findEditStr+"|"+Goods.selectGoodsByNameOrClass(q.GoodsList,findEditStr).size(),
		// 1).show();
	}

	/**
	 * 本方法作用：ListView与SimpleAdatper结合实现列表填充数据
	 */
	public void setSimple() {
		SimpleAdapter adapter = new SimpleAdapter // 调用SimpleAdapter适配器
		(Find_index.this, // 当前类
				list, // 选项所有数据
				R.layout.find_list_item, // 与数据匹配的布局
				new String[] { "Image", "Intro", "UintName", "PriceStr" }, // 字符串数组，里面放参数名。
				new int[] { R.id.image_show, R.id.lable_Intro, R.id.lable_show,
						R.id.price_show } // int数组，里面放数据的控件id，位置要与参数名一一对应。
		)
		/*
		 * { //SimpleAdapter每条记录的事件
		 * 
		 * @Override public View getView(final int position, View convertView,
		 * ViewGroup parent) { // TODO Auto-generated method stub View view =
		 * super.getView(position, convertView, parent); Button bt1 =
		 * (Button)view.findViewById(R.id.button1); // 找view的button1 final
		 * TextView tv = (TextView)view.findViewById(R.id.text2); //
		 * 找view的button1 bt1.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { if (position==0) {
		 * Toast.makeText(getApplicationContext(), "第一列菜数目加一", 1) .show(); } if
		 * (position==1) { Toast.makeText(getApplicationContext(), tv.getText(),
		 * 1) .show(); tv.setText("红烧肉"); }
		 * 
		 * } }); return view;
		 * 
		 * }
		 * 
		 * }
		 */;
		gview = (GridView) findViewById(R.id.gview);// 找ListView控件
		gview.setAdapter(adapter); // 把适配器设置给ListView控件
		// 处理选择结果 ListView每行的单击事件
		gview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getApplicationContext(), "你选择了" + position, 1)
						.show();

			}
		});

	}
	
	
	
	
	

}
