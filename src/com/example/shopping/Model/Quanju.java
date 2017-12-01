package com.example.shopping.Model;

import java.util.ArrayList;
import java.util.List;

import android.app.Application;

public class Quanju extends Application {

	// 分类表
	public List<Classify> ClassifyList = new ArrayList<Classify>();
	// 商品表
	public List<Goods> GoodsList = new ArrayList<Goods>();
	// 购物车表
	public List<ShoppingCar> ShoppingCarList = new ArrayList<ShoppingCar>();
	// 用户表
	public List<User> UserList = new ArrayList<User>();

	// 初始化数据库
	public void Init() {
		//分类表初始化
		if (ClassifyList.size()<=0) {
			String[] ArrayClassify = { "舌尖上的特产", "来自星星零食", "来自热剧的零食", "来自穿越的零食" };
			for (int i = 0; i < ArrayClassify.length; i++) {
				ClassifyList.add(new Classify(i, i, ArrayClassify[i]));
			}
		}
		//商品表初始化
		if (GoodsList.size()<=0) {
			String[] merchantNameList = { "四川休闲食品", "北京御食园食品有限公司", "江苏徐州天意企业",
					"三大珍珠奶茶有限公司", "上海润东化工有限公司", "焦作智力多食品饮料有限公司",
					"宿松县国龙商贸有限责任公司", "湖北通山鲜竹企业", "天第包装材料有限公司", "天津市三川木制品厂" };
			String[] nameList = { "泡泡糖", "大刀肉", "牛奶糖",
					"薄荷棒", "麻辣条", "唐生肉",
					"牛板筋", "牛羊配", "棒棒冰", "牛奶提子" };
			double [] priceList={12.1,1.3,23.1,1.3,3.8,
					12.1,1.3,23.1,1.3,3.8};
			String[] uintList = { "袋", "袋", "袋",
					"袋", "袋", "袋",
					"袋", "袋", "袋", "袋" };
			int [] classifyIDList={1,1,1,1,1,
					1,1,1,1,1};
			String[] classifyList = { "来自星星零食", "来自星星零食", "来自星星零食",
					"来自星星零食", "来自热剧的零食", "来自热剧的零食",
					"来自热剧的零食", "来自热剧的零食", "来自星星零食", "来自星星零食" };
			
			String[] introList = { "来自星星零食1", "来自星星零食2", "来自星星零食3",
					"来自星星零食4", "来自热剧的零食5", "来自热剧的零食6",
					"来自热剧的零食7", "来自热剧的零食8", "来自星星零食9", "来自星星零食10" };
			String[] imageList = { "a1.png", "a2.png", "a3.png",
					"a4.png", "a5.png", "a6.png",
					"a7.png", "a8.png", "a9.png", "a10.png" };
			/*
			 * iD, int sort, String merchantName, String name, double price,
			 * String uint, int classifyID, String classify, String intro,
			 * String image
			 */
			for (int i = 0; i < merchantNameList.length; i++) {
            	GoodsList.add(new Goods(i, i, merchantNameList[i],
            			nameList[i], priceList[i],
            			uintList[i], classifyIDList[i], 
            			classifyList[i], introList[i],
            			imageList[i]));
			}
		}
		//用户表初始化
		if (UserList.size()<=0) {
			/*int iD, int sort, String name, int sex, String image,
			String registerTime, String birthday, String site
			*/
			
			UserList.add(new User(1, 1,"admin",
        			1, "a1.png",
        			"2017-11-29", "1995-10-8", 
        			"广西市北海市银海区桂林电子科技大学"));
		}

	}

	
}
