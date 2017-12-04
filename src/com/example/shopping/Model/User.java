package com.example.shopping.Model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//用户表
public class User {
	int ID;
	int Sort;
	String LoginName;
	String Password;
	String Name;
	int Sex;
	String Image;// 头像图片
	String RegisterTime;// 注册时间
	String Birthday;// 生日
	String Site;// 收货地址
	
	public User() {
	}

	public User(int iD, int sort,String loginName, String password,
			String name, int sex, String image, String registerTime,
			String birthday, String site) {
		super();
		LoginName = loginName;
		Password = password;
		ID = iD;
		Sort = sort;
		Name = name;
		Sex = sex;
		Image = image;
		RegisterTime = registerTime;
		Birthday = birthday;
		Site = site;
	}



	// ***************公共方法***************************

	// 查询单个记录
	public User selectUserByID(List<User> list, int id) {
		User l = new User();
		for (User user : list) {
			if (user.ID == id) {
				l = user;
				break;
			}
		}
		return l;
	}

	// 排序通过属性排序
	public List<User> sortUserListBySort(List<User> list) {
		Comparator<User> comparator = new Comparator<User>() {
			public int compare(User s1, User s2) {
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
	public boolean deleteUserByID(List<User> list, int id) {
		boolean b = false;
		User l = new User();
		for (User user : list) {
			if (user.ID == id) {
				l = user;
				b = true;
				break;
			}
		}
		list.remove(l);
		return b;
	}

	// 修改一条记录通过id
	public boolean updateUserByID(List<User> list, int id, String name) {
		boolean b = false;
		for (User user : list) {
			if (user.ID == id) {
				user.Name = name;
				b = true;
				break;
			}
		}
		return b;
	}

}
