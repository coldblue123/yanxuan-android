   界面
   |---find_list.xml   搜索信息界面 
   |---activity_main.xml 首页
   |---activity_main2.xml 推荐首页
   |---activity_main3.xml 购物车
   |---activity_main4.xml 我的
   
复用
组件
   |---banner.xml 轮播图组件
   |---find.xml 搜索框组件
   |---find_list2.xml 热点推荐组件
   |---lable.xml 标签1 组件
   |---lable2.xml 标签2 组件
   |---show_com_list.xml 显示分类商品组件
    
    
 
商品详细相关界面
	goods_more_index.xml  --主页
	goods_more_bottom.xml --底部导航
	goods_more_label1.xml
	goods_more_label2.xml
	goods_more_label3.xml
	goods_more_label4.xml
	goods_more_label5.xml
	goods_more_label6.xml --以上为静态标签
      
    
 购物车
    activity_main3.xml 购物车
    shopping_car_bottom.xml
	shopping_car_item.xml
	shopping_car_label1.xml
	shopping_car_title.xml
	
你要的头部
	title.xml	
    
    
    
    
    
    
   
   
    //不显示不占空间
    android:visibility="gone"
    
    // 超出换行
    android:ellipsize="end"
	android:singleLine="true" 
	
	// 不自动获取焦点
	android:focusable="true" 
    android:focusableInTouchMode="true" 
    
    // GridView
    1.android:numColumns=”auto_fit”   //GridView的列数设置为自动
	2.android:columnWidth=”90dp "       //每列的宽度，也就是Item的宽度
	3.android:stretchMode=”columnWidth"//缩放与列宽大小同步
	4.android:verticalSpacing=”10dp”          //两行之间的边距
	5.android:horizontalSpacing=”10dp”      //两列之间的边距 
	6.android:cacheColorHint="#00000000" //去除拖动时默认的黑色背景
	7.android:listSelector="#00000000"        //去除选中时的黄色底色
	8.android:scrollbars="none"                   //隐藏GridView的滚动条
	9.android:fadeScrollbars="true"             //设置为true就可以实现滚动条的自动隐藏和显示
	10.android:fastScrollEnabled="true"      //GridView出现快速滚动的按钮(至少滚动4页才会显示)
	11.android:fadingEdge="none"                //GridView衰落(褪去)边缘颜色为空，缺省值是vertical。(可以理解为上下边缘的提示色)
	12.android:fadingEdgeLength="10dip"   //定义的衰落(褪去)边缘的长度
	13.android:stackFromBottom="true"       //设置为true时，你做好的列表就会显示你列表的最下面
	14.android:transcriptMode="alwaysScroll" //当你动态添加数据时，列表将自动往下滚动最新的条目可以自动滚动到可视范围内
	15.android:drawSelectorOnTop="false"  //点击某条记录不放，颜色会在记录的后面成为背景色,内容的文字可见(缺省为false)

		// 轮播图
		viewPager = (ViewPager)findViewById(R.id.viewPager);
		// 滚动条
		mScrollView = (ScrollView)findViewById(R.id.scrollView1);
		// 处理滚动条和轮播图的冲突事件
		viewPager.setOnTouchListener(new OnTouchListener() {
			@Override
		    public boolean onTouch(View v, MotionEvent event) {
		        int action = event.getAction();
		        if(action == MotionEvent.ACTION_DOWN) {
		            // 记录点击到ViewPager时候，手指的X坐标
		            mLastX = event.getX();
		        }
		        if(action == MotionEvent.ACTION_MOVE) {
		            // 超过阈值
		            if(Math.abs(event.getX() - mLastX) > 10f) {
		                mScrollView.requestDisallowInterceptTouchEvent(true);
		            }
		        }
		        if(action == MotionEvent.ACTION_UP) {
		            // 用户抬起手指，恢复父布局状态
		            mScrollView.requestDisallowInterceptTouchEvent(false);
		        }
		        return true;
		    }
		});