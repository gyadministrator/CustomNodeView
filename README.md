
![image](https://github.com/gyadministrator/CustomNodeView/blob/master/images/spot.png)

怎样使用?

工程的Gradle引入方式：

    repositories {
            google()
            jcenter()
            mavenCentral()
        }

    allprojects {
        repositories {
            google()
            jcenter()
            maven { url 'https://jitpack.io' }
            mavenCentral()
        }
    }

  dependencies {
		implementation 'com.github.gyadministrator:CustomNodeView:1.2'
	}

在activity使用，非常简单。
  
package com.android.custom.node;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.android.custom.nodeview.adapter.NodeAdapter;
import com.android.custom.nodeview.entity.NodeData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<NodeData> allValues = new ArrayList<>();
    private NodeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void initData() {
        // 模拟一些假的数据
        @SuppressLint("UseSparseArrays") Map<Integer, String[]> listOne = new HashMap<>();
        listOne.put(0, new String[]{"隐患上报", "2020.12.15 10:12:46"});
        listOne.put(1, new String[]{"服务区1号", "服务区一号"});

        @SuppressLint("UseSparseArrays") Map<Integer, String[]> listTwo = new HashMap<>();
        listTwo.put(0, new String[]{"隐患上报11", "2020.12.15 10:12:46"});
        listTwo.put(1, new String[]{"服务区1号11", "服务区一号"});
        listTwo.put(2, new String[]{"服务区2号11", "服务区一号"});

        @SuppressLint("UseSparseArrays") Map<Integer, String[]> listThree = new HashMap<>();
        listThree.put(0, new String[]{"隐患上报3", "2020.12.15 10:12:46"});
        listThree.put(1, new String[]{"服务区3号", "服务区一号"});

        allValues.add(new NodeData(2, listOne));
        allValues.add(new NodeData(3, listTwo));
        allValues.add(new NodeData(2, listThree));

        for (int i = 0; i < 6; i++) {
            @SuppressLint("UseSparseArrays") Map<Integer, String[]> list = new HashMap<>();
            list.put(0, new String[]{"隐患上报" + i, "2020.12.15 10:12:46"});
            list.put(1, new String[]{"服务区3号" + i, "服务区一号"});

            allValues.add(new NodeData(2, list));
        }

        adapter = new NodeAdapter(this, allValues, false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}


