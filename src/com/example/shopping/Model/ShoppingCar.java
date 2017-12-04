package com.example.shopping.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import com.example.shopping.Common.CommonMath;

import android.R.string;

//购物车表
public class ShoppingCar {
	int ID;
	int Sort;
	int GoodsID;// 商品ID
	String GoodsName;// 商品名
	double Price;// 单价
	String Uint;// 包装单位
	int Num;// 购买数目
	String Image;// 封面图片
	String Time;// 加入时间
	int Sign;//0 没有购买 1 已经购买   2还未付款


	public ShoppingCar(int iD, int sort, int goodsID, String goodsName,
			double price, String uint, int num, String image, String time,
			int sign) {
		super();
		ID = iD;
		Sort = sort;
		GoodsID = goodsID;
		GoodsName = goodsName;
		Price = price;
		Uint = uint;
		Num = num;
		Image = image;
		Time = time;
		Sign = sign;
	}
	public int getID() {
		return ID;
	}

	public int getSign() {
		return Sign;
	}

	public void setSign(int sign) {
		Sign = sign;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getSort() {
		return Sort;
	}

	public void setSort(int sort) {
		Sort = sort;
	}


	public int getGoodsID() {
		return GoodsID;
	}


	public void setGoodsID(int goodsID) {
		GoodsID = goodsID;
	}



	public String getGoodsName() {
		return GoodsName;
	}



	public void setGoodsName(String goodsName) {
		GoodsName = goodsName;
	}



	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = price;
	}

	public String getUint() {
		return Uint;
	}

	public void setUint(String uint) {
		Uint = uint;
	}

	public int getNum() {
		return Num;
	}

	public void setNum(int num) {
		Num = num;
	}

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

	public ShoppingCar() {
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
	
	// 查询单个记录
	public static double getTotal(List<ShoppingCar> list) {
		double l=0 ;
		for (ShoppingCar shoppingCar : list) {
			l=l+(shoppingCar.getPrice()*(double)shoppingCar.getNum());
		}
		return l;
	}
	// 查询列表通过是否购买查询
	public static List<ShoppingCar> selectShoppingBySign(List<ShoppingCar> list,
				int sign) {
			List<ShoppingCar> relist = new ArrayList<ShoppingCar>();
			for (ShoppingCar shoppingCar : list) {
				if (shoppingCar.Sign==sign) {
					relist.add(shoppingCar); // 添加数据给list集合
				}
			}
			return relist;
		}
	
	// 查询列表通过商品id和是否购买
	public static List<ShoppingCar> selectShoppingByGoodID(List<ShoppingCar> list,
				int goodid,int sign) {
			List<ShoppingCar> relist = new ArrayList<ShoppingCar>();
			for (ShoppingCar shoppingCar : list) {
				if (shoppingCar.GoodsID==goodid
						&& shoppingCar.Sign==sign) {
					relist.add(shoppingCar); // 添加数据给list集合
				}
			}
			return relist;
		}
	
	//查询修改后的集合
	public static List<ShoppingCar> setShoppingByGoodID(List<ShoppingCar> list,
			int goodid,int sign,ShoppingCar setShoppingCar ) {
		List<ShoppingCar> relist = new ArrayList<ShoppingCar>();
		for (ShoppingCar shoppingCar : list) {
			if (shoppingCar.GoodsID==goodid
					&& shoppingCar.Sign==sign) {
				shoppingCar.setGoodsName(setShoppingCar.getGoodsName());
				shoppingCar.setPrice(setShoppingCar.getPrice());
				shoppingCar.setUint(setShoppingCar.getUint());
				shoppingCar.setNum(setShoppingCar.getNum());
				shoppingCar.setImage(setShoppingCar.getImage());
				shoppingCar.setTime(setShoppingCar.getTime());
				shoppingCar.setSign(setShoppingCar.getSign());
			}
			relist.add(shoppingCar); // 添加数据给list集合
		}
		return relist;
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
					if (!s1.GoodsName.equals(s2.GoodsName)) {
						return s1.GoodsName.compareTo(s2.GoodsName);
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
				shoppingCar.GoodsName = name;
				b = true;
				break;
			}
		}
		return b;
	}

	
	// 数据组装对应模型
		public static ArrayList<HashMap<String, Object>> getListToHashMap(
				List<ShoppingCar> list) {
			ArrayList<HashMap<String, Object>> list2 = new ArrayList<HashMap<String, Object>>();
			for (ShoppingCar shoppingCar : list) {
				// 添加数据给map集合
				HashMap<String, Object> map1 = new HashMap<String, Object>(); // 定义map集合(数组），一个map集合对应ListView的一栏。
				map1.put("Price", shoppingCar.Price);
				map1.put("PriceStr", "¥ " + shoppingCar.Price);
				map1.put("GoodsName", shoppingCar.GoodsName);
				map1.put("Uint", shoppingCar.Uint);
				map1.put("GoodsID", shoppingCar.GoodsID);
				map1.put("ShoopingID", shoppingCar.ID);
				map1.put("Num", shoppingCar.Num);
				map1.put("Time","加入时间:"+shoppingCar.Time);
				map1.put("Image", CommonMath.getImageResourceID(shoppingCar.Image));
				// -----把map集合放进list集合里---------
				list2.add(map1); // 添加数据给list集合
			}
			return list2;
		}
}
