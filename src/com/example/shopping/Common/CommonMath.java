package com.example.shopping.Common;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.R.string;
import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.example.shopping.R;
import com.example.shopping.Model.Goods;
import com.example.shopping.Model.Quanju;

//公共方法类
public class CommonMath extends Activity{
	/**
	 * 获取图片名称获取图片的资源id的方法
	 * 
	 * @param imageName
	 * @return
	 */
	public static int getImageResourceID(String imageName) {
		String [] str=imageName.split(",");
		imageName=str[0];
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
	
	//返回圖片組資源ID 
	public static ArrayList<Integer> getImageResourceListID(String imageName) {
		String [] str=imageName.split(",");
		ArrayList<Integer> r_ids=new ArrayList<Integer>();
		Class drawable = R.drawable.class;
		
		try {
			for (int i = 0; i < str.length; i++) {
				r_ids.add( new Integer(getImageResourceID(str[i])));
			}
		} catch (Exception e) {
			r_ids.add( new Integer(R.drawable.noproduct));
			Log.e("ERROR", "PICTURE NOT　FOUND！");
		}
		return r_ids;
	}
	
	  // 修改一条记录访问量通过id
	public  boolean updateGoodsByID(int id) {
				boolean b = false;
				Quanju q = (Quanju) getApplicationContext();// 获取所有表数据
				for (Goods goods : q.GoodsList) {
					if (goods.getID() == id) {
						goods.setVistNum(goods.getVistNum()+1);
						b = true;
						break;
					}
				}
				return b;
			}
	
	
	

}
