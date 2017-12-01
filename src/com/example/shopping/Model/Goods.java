package com.example.shopping.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import com.example.shopping.Common.Common;

//商品表
public class Goods {
	int ID;
	int Sort;
	String MerchantName; // 商家名
	String Name;// 商品名称
	double Price;// 单价
	String Uint;// 包装单位
	int ClassifyID;// 分类ID
	String Classify;// 分类名
	String Intro;// 简介
	String Image;// 图片

	public Goods() {
	}

	public Goods(int iD, int sort, String merchantName, String name,
			double price, String uint, int classifyID, String classify,
			String intro, String image) {
		ID = iD;
		Sort = sort;
		MerchantName = merchantName;
		Name = name;
		Price = price;
		Uint = uint;
		ClassifyID = classifyID;
		Classify = classify;
		Intro = intro;
		Image = image;
	}

	// ***************公共方法***************************
	// 增加忽略

	// 模糊(商品名称或类别名\商家名)查询列表
	public static List<Goods> selectGoodsByNameOrClass(List<Goods> list, String  findStr) {
		List<Goods> relist=new ArrayList<Goods>();
		for (Goods goods : list) {
			if (goods.Name.contains(findStr)||goods.Classify.contains(findStr)
					||goods.MerchantName.contains(findStr)) {
				relist.add(goods); // 添加数据给list集合
			}
		}
		return relist;
	}

	// 模糊查询列表
		public static ArrayList<HashMap<String, Object>> getListToHashMap(List<Goods> list) {
			ArrayList<HashMap<String, Object>> list2=new ArrayList<HashMap<String,Object>>();
			for (Goods goods : list) {
					// 添加数据给map集合
					HashMap<String, Object> map1 = new HashMap<String, Object>(); // 定义map集合(数组），一个map集合对应ListView的一栏。
					map1.put("MerchantName", goods.MerchantName);
					map1.put("Price", goods.Price);
					map1.put("PriceStr","￥ " +goods.Price);
					map1.put("Name", goods.Name);
					map1.put("UintName", goods.Uint+"    "+goods.Name);
					map1.put("Classify", goods.Classify);
					map1.put("Intro", goods.Intro);
					map1.put("Image", new Common().getImageResource(goods.Image));
					// -----把map集合放进list集合里---------
					list2.add(map1); // 添加数据给list集合
				}
			return list2;
		}
	// 查询单个记录
	public Goods selectGoodsByID(List<Goods> list, int id) {
		Goods l = new Goods();
		for (Goods goods : list) {
			if (goods.ID == id) {
				l = goods;
				break;
			}
		}
		return l;
	}

	// 排序通过属性排序
	public List<Goods> sortGoodsListBySort(List<Goods> list) {
		Comparator<Goods> comparator = new Comparator<Goods>() {
			public int compare(Goods s1, Goods s2) {
				// 先排序号
				if (s1.Sort != s2.Sort) {
					return s1.Sort - s2.Sort;
				} else {
					// 年龄相同则按姓名排序
					if (!s1.Name.equals(s2.Name)) {
						return s1.Name.compareTo(s2.Name);
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
	public boolean deleteGoodsByID(List<Goods> list, int id) {
		boolean b = false;
		Goods l = new Goods();
		for (Goods goods : list) {
			if (goods.ID == id) {
				l = goods;
				b = true;
				break;
			}
		}
		list.remove(l);
		return b;
	}

	// 修改一条记录通过id
	public boolean updateGoodsByID(List<Goods> list, int id, String name) {
		boolean b = false;
		for (Goods goods : list) {
			if (goods.ID == id) {
				goods.Name = name;
				b = true;
				break;
			}
		}
		return b;
	}

}
