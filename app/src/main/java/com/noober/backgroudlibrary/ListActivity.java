package com.noober.backgroudlibrary;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.noober.background.BackgroundLibrary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListActivity extends AppCompatActivity {
    private List<Map<String, Object>> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        BackgroundLibrary.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //不需要任何特殊处理
        ListView listView = findViewById(R.id.listView);
        for (int i = 0; i < 20; i++) {
            data.add(new HashMap<String, Object>());
        }
        SimpleAdapter simAdapt = new SimpleAdapter(
                this,
                data,
                R.layout.item,
                new String[]{},// 与下面数组元素要一一对应
                new int[]{});
        listView.setAdapter(simAdapt);
    }
}
