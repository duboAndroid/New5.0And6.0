package com.support.android.designlibdemo.behaviortest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.support.android.designlibdemo.R;

import java.util.ArrayList;
import java.util.List;

public class MyMainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private List<String> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_with_header);

        for (int i = 0;i < 200 ;i++){
            datas.add("position = " + i);
        }

        rv = (RecyclerView) findViewById(R.id.rv);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        FakeHeaderAdapter adapter = new FakeHeaderAdapter(this);
//        MyAdapter adapter = new MyAdapter();
        rv.setAdapter(adapter);

        adapter.setData(datas);
        View header = View.inflate(this, R.layout.fake_header,null);
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,getResources().getDimensionPixelSize(R.dimen.fake_header_height));
        header.setLayoutParams(params);
        adapter.setHeader(header);
    }

}
