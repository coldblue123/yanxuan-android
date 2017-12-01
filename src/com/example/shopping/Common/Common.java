package com.example.shopping.Common;


import java.lang.reflect.Field;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.util.Log;

import com.example.shopping.R;

//公共方法类
public class Common extends Activity {
	/**
	 * 获取图片名称获取图片的资源id的方法
	 * 
	 * @param imageName
	 * @return
	 */
	public static int getImageResource(String imageName) {
		Class drawable = R.drawable.class;
		Field field = null;
		int r_id;
		try {
			field = drawable.getField("a1");
			field = drawable.getField(imageName);
			r_id = field.getInt(field.getName());
		} catch (Exception e) {
			r_id = R.drawable.a0;
			Log.e("ERROR", "PICTURE NOT　FOUND！");
		}
		return r_id;
	}

	/**
	 * 根据图片的名称获取对应的资源id
	 * 
	 * @param resourceName
	 * @return
	 */
	public int getDrawResourceID(String resourceName) {
		ApplicationInfo appInfo = getApplicationInfo();
		return getResources().getIdentifier(resourceName, "drawable",
				appInfo.packageName);
	}
}
