package com.caelan.superrecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.caelan.superrecycle.superadapter.DataSource;
import com.caelan.superrecycle.superadapter.ItemAdapter;
import com.caelan.superrecycle.superadapter.SimpleDataSource;
import com.caelan.superrecycle.superadapter.SuperAdapter;
import com.caelan.superrecycle.superadapter.SuperViewHolder;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        RecyclerView recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SuperAdapter adapter = new SuperAdapter(this);

        adapter.with(new ItemAdapter<TextBean>(R.layout.item_simple_text) {
            @Override
            public void onBindViewHolder(SuperViewHolder holder, TextBean textBean) {
                TextView textView = holder.get(R.id.simple_text);
                textView.setText(textBean.getText());
            }
        }, new ItemAdapter<ImageBean>(R.layout.item_simple_image) {
            @Override
            public void onBindViewHolder(SuperViewHolder holder, ImageBean imageBean) {
                ImageView imageView = holder.get(R.id.simple_image);
                imageView.setImageResource(imageBean.getImageRes());
            }
        });

        DataSource dataSource = new SimpleDataSource(obtainData(), new SimpleDataSource.Intercept() {
            @Override
            public int onIntercept(int position, Object data) {
                if (data instanceof TextBean) {
                    return 0;
                } else if (data instanceof ImageBean) {
                    return 1;
                }
                return 0;
            }
        });
        adapter.setDataSource(dataSource);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    ArrayList<Object> obtainData() {
        ArrayList<Object> arrayList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            TextBean textBean = new TextBean("I am " + i);
            arrayList.add(textBean);
            ImageBean imageBean = new ImageBean(R.mipmap.ic_launcher_round);
            arrayList.add(imageBean);
        }
        return arrayList;
    }
}
