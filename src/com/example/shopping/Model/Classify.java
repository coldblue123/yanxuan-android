package com.example.shopping.Model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//分类表
public class Classify {
	int ID;
	int Sort;
	String Name;// 分类名称
	
	public Classify() {
	}
	public Classify(int iD, int sort, String name) {
		super();
		ID = iD;
		Sort = sort;
		Name = name;
	}
	
	// ***************公共方法***************************
		// 增加忽略

		// 查询单个记录
		public Classify selectClassifyByID(List<Classify> list, int id) {
			Classify l = new Classify();
			for (Classify classify : list) {
				if (classify.ID == id) {
					l = classify;
					break;
				}
			}
			return l;
		}
		
		// 排序通过属性排序
		public List<Classify> sortClassifyListBySort(List<Classify> list) {
			Comparator<Classify> comparator = new Comparator<Classify>() {
				public int compare(Classify s1, Classify s2) {
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
		public boolean deleteClassifyByID(List<Classify> list, int id) {
			boolean b = false;
			Classify l = new Classify();
			for (Classify classify : list) {
				if (classify.ID == id) {
					l = classify;
					b = true;
					break;
				}
			}
			list.remove(l);
			return b;
		}
	 
		//修改一条记录通过id
		public boolean updateClassifyByID(List<Classify> list, int id, String name) {
			boolean b = false;
			for (Classify classify : list) {
				if (classify.ID == id) {
					classify.Name = name;
					b = true;
					break;
				}
			}
			return b;
		}
		
	
	
}
