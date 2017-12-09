package com.example.shopping.Model;

import java.util.ArrayList;
import java.util.List;

import com.example.shopping.R;

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
	// 首页轮播图存储
	public int[] bannerImageUrls;
	
	// 选择中的购物车商品
	public List<ShoppingCar> ShoppingChoiceCarList = new ArrayList<ShoppingCar>();
	// 首页轮播图存储
	public int[] bannerImageUrls2;
	//当前登录数据
	public User currentUser;

	// 初始化数据库
	public void Init() {
		// 轮播图初始化
		int[] imageUrls = { R.drawable.banner1, R.drawable.banner2,
				R.drawable.banner3,R.drawable.banner4 };
		
		int[] imageUrls2 = { R.drawable.banner1, R.drawable.banner2,
				R.drawable.banner3,R.drawable.banner4 };
		
		bannerImageUrls = imageUrls;
		bannerImageUrls2 = imageUrls2;
		// 分类表初始化
		if (ClassifyList.size() <= 0) {
			String[] ArrayClassify = { "舌尖上的特产", "来自星星零食", "来自热剧的零食", "来自穿越的零食" };
			for (int i = 0; i < ArrayClassify.length; i++) {
				ClassifyList.add(new Classify(i, i, ArrayClassify[i]));
			}
		}
		// 商品表初始化
		if (GoodsList.size() <= 0) {
			String[] merchantNameList = { "四川休闲食品", "北京御食园食品有限公司", "江苏徐州天意企业",
					"三大珍珠奶茶有限公司", "上海润东化工有限公司", "焦作智力多食品饮料有限公司",
					"宿松县国龙商贸有限责任公司", "湖北通山鲜竹企业", "天第包装材料有限公司", "天津市三川木制品厂" };
			String[] nameList = { "包邮重庆特产正宗好哥们酸辣粉260g*5袋方便速食有调料红薯粗粉丝",
					"洪胜记凤梨酥整箱1000g 厦门特产手工凤梨酥特色糕点零食小吃美食",
					"华味亨焦糖瓜子250g*6 山核桃味瓜子批发果仁饱满半斤小包装新货",
					"盛耳 桂圆干500g*2龙眼干新货莆田特产干货",
					"良品铺子夹心麻薯早餐食品糯米糍传统糕点特产美食小吃零食大礼包",
					"松桂坊五花腊肉湖南特产农家湘西腊肠咸肉正宗柴火自制烟熏肉500g",
					"柳江人家正宗螺蛳粉广西柳州特产螺狮粉 螺丝粉3包包邮",
					"炫果多 泰国椰青9个 大号果 进口原箱新鲜水果椰子汁椰子送开椰器",
					"20枚玫瑰鲜花饼云南特产玫瑰花饼胡先生正宗糕点早餐零食美食1kg",
					"良品铺子手工蛋黄酥传统糕点点心零食 休闲食品特产美食小吃盒装" };
			double[] priceList = { 29.9, 49.6, 36.80, 19.9, 33.90, 48.0, 38.6,
					69.0, 39.3, 13.9 };
			String[] uintList = { "袋", "盒", "包", "包", "盒", "斤", "包", "个", "盒",
					"盒" };
			int[] classifyIDList = { 0, 1, 2, 3, 0, 1, 2, 3, 0, 1 };
			String[] classifyList = { "舌尖上的特产", "来自星星零食", "来自热剧的零食", "来自穿越的零食",
					"舌尖上的特产", "来自星星零食", "来自热剧的零食", "来自穿越的零食", "舌尖上的特产",
					"来自星星零食" };

			String[] introList = {
					"酸辣粉起源于四川,是四川省、重庆市、贵州省等地的传统特色小吃,属于渝菜、川菜、贵州小吃。其特点是麻、辣、鲜、香、酸且油而不腻。",
					"凤梨酥相传最早起源于中国三国时期，其凤梨闽南话发音又称“旺来”，象征子孙旺旺来的意思",
					"腊（xī là 同“臘”）肉是指肉经腌制后再经过烘烤（或日光下曝晒）的过程所制成的加工品。腊肉的防腐能力强，能延长保存时间，并增添特有的风味，这是与咸肉的主要区别。",
					"麻薯是一种由糯米粉或其他淀粉类制成的有弹性和粘性的食品，来源于日本，日语发音（もち/mochi），台湾音译之为“麻糬”。",
					"桂圆亦称龙眼，是一种水果。性温味甘，益心脾，补气血，具有良好有滋养补益作用，可用于心脾虚损、气血不足所致的失眠、健忘、惊悸、眩晕等症。",
					"瓜子本身营养就很高，其中所含有的维生素、蛋白质、油类含量都属佼佼者。",
					"鲜花饼是以云南特有的食用玫瑰花入料的酥饼，是具有云南特色的云南经典点心代表。鲜花饼在云南当地烘焙品牌大都均有销售。",
					"椰,就是椰子树的意思。椰子,是椰子树的果实,是由外层为纤维的壳组成的,提供椰子皮纤维和内含可食厚肉质的大坚果。",
					" 蟹壳黄酥饼是以面粉、雪里蕻干菜、猪肥膘肉为主要材料制作而成的面食。",
					"螺蛳粉是广西柳州市的小吃米粉，具有辣、爽、鲜、酸、烫的独特风味。螺蛳粉的味美还因为它有着独特的汤料。"};
			String[] imageList = {
					"malafen1,malafen2,malafen3,malafen4,malafen5",
					"fenlisu1,fenlisu2,fenlisu3,fenlisu4,fenlisu5",
					"narou2,narou1,narou3,narou4,narou5",
					"jxmashu1,jxmashu2,jxmashu3,jxmashu4,jxmashu5",
					"guiyuan1,guiyuan2,guiyuan3,guiyuan4,guiyuan5",
					"guzi3,guzi1,guzi2,guzi4,guzi5",
					"yunlanxuanhuabing1,yunlanxuanhuabing2,yunlanxuanhuabing3,yunlanxuanhuabing4,yunlanxuanhuabing5,yunlanxuanhuabing6",
					"yezi7,yezi1,yezi2,yezi3,yezi4,yezi5,yezi6,yezi8",
					"xiehuangsu4,xiehuangsu1,xiehuangsu2,xiehuangsu3,xiehuangsu5",
					"lzhluoshifen4,lzhluoshifen1,lzhluoshifen2,lzhluoshifen3,lzhluoshifen5"};
			/*
			 * iD, int sort, String merchantName, String name, double price,
			 * String uint, int classifyID, String classify, String intro,
			 * String image
			 */
			for (int i = 0; i < merchantNameList.length; i++) {
				GoodsList.add(new Goods(i, i, merchantNameList[i], nameList[i],
						priceList[i], uintList[i], classifyIDList[i],
						classifyList[i], introList[i], imageList[i]));
			}
		}
		// 用户表初始化
		if (UserList.size() <= 0) {
			/*
			 * int iD, int sort, String name, int sex, String image, String
			 * registerTime, String birthday, String site
			 */

			UserList.add(new User(1, 1,"admin","123", "admin", 1, "a1.png", "2017-11-29",
					"1995-10-8", "广西市北海市银海区桂林电子科技大学"));
		}

	}

}
