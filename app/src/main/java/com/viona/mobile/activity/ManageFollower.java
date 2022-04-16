package com.viona.mobile.activity;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.viona.mobile.R;
import com.viona.mobile.adapter.ManageFollowerAdapter;
import com.viona.mobile.model.FollowerModel;

import java.util.ArrayList;
import java.util.List;

public class ManageFollower extends Master implements View.OnClickListener {
    private RecyclerView lists;
    private ManageFollowerAdapter ManageFollowerAdapter;
    private List<FollowerModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_follower);

        findView();
        init();
    }

    private void findView() {
        title   = findViewById(R.id.title);
        back    = findViewById(R.id.back);
        lists   = findViewById(R.id.list);
    }

    private void init() {
        list.clear();
        try {
            String title[] = {
                    "James Brawn",
                    "Diane Franqui",
                    "Tracy C. Barnes",
                    "Gerald E. Robinson",
                    "Doris R. Degennaro",
            };

            String joined[] = {
                    "20 April 2022",
                    "20 April 2022",
                    "21 April 2022",
                    "23 April 2022",
                    "25 April 2022",
            };

            for (int i = 0; i < title.length; i++) {
                FollowerModel data = new FollowerModel();

                data.setName(title[i]);
                data.setJoined(joined[i]);
                list.add(data);
            }
            setItemDealsLatest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setItemDealsLatest() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        ManageFollowerAdapter = new ManageFollowerAdapter(getApplicationContext(), list, new ManageFollowerAdapter.OnClickListener() {
            @Override
            public void onClickListener(int position) {
            }
        });

        lists.setLayoutManager(linearLayoutManager);
        lists.setAdapter(ManageFollowerAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
        }
    }
}
