package com.example.shopping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.GridView;
import android.widget.SimpleAdapter;

public class Find_list extends Activity {

	private GridView gview;
    private List<Map<String, Object>> data_list;
    private SimpleAdapter sim_adapter;
    // 图片封装为一个数组
    private int[] icon = {
    		R.drawable.test1,
    		R.drawable.test1,
    		R.drawable.test1
    };
    private String[] price = { "230", "11", "44"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_list);
        gview = (GridView) findViewById(R.id.gview);
        //新建List
        data_list = new ArrayList<Map<String, Object>>();
        //获取数据
        getData();
        //新建适配器
        String [] from ={"image","text"};
        int [] to = {R.id.image_show,R.id.price_show};
        sim_adapter = new SimpleAdapter(this, data_list, R.layout.find_list_item, from, to);
        //配置适配器
        gview.setAdapter(sim_adapter);
    }

    
    
    public List<Map<String, Object>> getData(){        
        //cion和iconName的长度是相同的，这里任选其一都可以
        for(int i=0;i<icon.length;i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", "¥" + price[i]);
            data_list.add(map);
        }
            
        return data_list;
    }

}
