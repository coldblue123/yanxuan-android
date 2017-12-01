package com.example.shopping.Model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//购物车表
public class ShoppingCar {
	int ID;
	int Sort;
	String ShoppingCarID;// 商品ID
	String ShoppingCarName;// 商品名
	double Price;// 单价
	String Uint;// 包装单位
	int Num;// 购买数目
	String Image;// 封面图片
	String Time;// 加入时间

	public ShoppingCar() {
	}

	public ShoppingCar(int iD, int sort, String shoppingCarID, String shoppingCarName,
			double price, String uint, int num, String image, String time) {
		ID = iD;
		Sort = sort;
		ShoppingCarID = shoppingCarID;
		ShoppingCarName = shoppingCarName;
		Price = price;
		Uint = uint;
		Num = num;
		Image = image;
		Time = time;
	}

	// ***************公共方法***************************

	// 查询单个记录
	public ShoppingCar selectShoppingCarByID(List<ShoppingCar> list, int id) {
		ShoppingCar l = new ShoppingCar();
		for (ShoppingCar shoppingCar : list) {
			if (shoppingCar.ID == id) {
				l = shoppingCar;
				break;
			}
		}
		return l;
	}

	// 排序通过属性排序
	public List<ShoppingCar> sortShoppingCarListBySort(List<ShoppingCar> list) {
		Comparator<ShoppingCar> comparator = new Comparator<ShoppingCar>() {
			public int compare(ShoppingCar s1, ShoppingCar s2) {
				// 先排序号
				if (s1.Sort != s2.Sort) {
					return s1.Sort - s2.Sort;
				} else {
					// 年龄相同则按姓名排序
					if (!s1.ShoppingCarName.equals(s2.ShoppingCarName)) {
						return s1.ShoppingCarName.compareTo(s2.ShoppingCarName);
					} else {
						// 姓名也相同则按学号排序
						return s1.ID - s2.ID;
					}
				}
			}
		};
		// 这里就会自动根据规则进行排序
		Collections.sort(list, comparator);
		return list;
	}

	// 删除一条记录
	public boolean deleteShoppingCarByID(List<ShoppingCar> list, int id) {
		boolean b = false;
		ShoppingCar l = new ShoppingCar();
		for (ShoppingCar shoppingCar : list) {
			if (shoppingCar.ID == id) {
				l = shoppingCar;
				b = true;
				break;
			}
		}
		list.remove(l);
		return b;
	}

	// 修改一条记录通过id
	public boolean updateShoppingCarByID(List<ShoppingCar> list, int id, String name) {
		boolean b = false;
		for (ShoppingCar shoppingCar : list) {
			if (shoppingCar.ID == id) {
				shoppingCar.ShoppingCarName = name;
				b = true;
				break;
			}
		}
		return b;
	}

}
