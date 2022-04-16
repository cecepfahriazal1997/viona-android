package com.viona.mobile.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.viona.mobile.R;
import com.viona.mobile.activity.Dashboard;
import com.viona.mobile.adapter.NotificationAdapter;
import com.viona.mobile.model.NotificationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cecep Rokani on 3/18/2019.
 */

public class NotificationFragment extends Fragment implements View.OnClickListener {
    private Dashboard parent;
    private Handler handler;
    private final boolean isVisible = true;
    private RecyclerView lists;
    private NotificationAdapter NotificationAdapter;
    private List<NotificationModel> list = new ArrayList<>();

    public NotificationFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parent = ((Dashboard) getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notification, container, false);

        findView(rootView);
        init();
        return rootView;
    }

    private void findView(View rootView) {
        lists = rootView.findViewById(R.id.list);
    }

    private void init() {
        list.clear();
        try {
            String title[] = {
                    "There is a deals near you !",
                    "There's an interesting new deals for you",
                    "Verify your account"
            };

            boolean isNew[] = {
                    true,
                    true,
                    false
            };

            for (int i = 0; i < title.length; i++) {
                NotificationModel data = new NotificationModel();

                data.setTitle(title[i]);
                data.setDate("2" + (i + 1) + " Mar 2022 0" + (i + 1) + ":00");
                data.setNewNotif(isNew[i]);
                data.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
                list.add(data);
            }
            setItemDealsLatest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setItemDealsLatest() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(parent.getApplicationContext(), RecyclerView.VERTICAL, false);
        NotificationAdapter = new NotificationAdapter(parent.getApplicationContext(), list, new NotificationAdapter.OnClickListener() {
            @Override
            public void onClickListener(int position) {
            }
        });

        lists.setLayoutManager(linearLayoutManager);
        lists.setAdapter(NotificationAdapter);
    }

    @Override
    public void setUserVisibleHint(boolean isFragmentVisible) {
        super.setUserVisibleHint(true);
        if (handler != null)
            handler.removeCallbacksAndMessages(null);
        if (this.isVisible()) {
            if (isVisible && isFragmentVisible) {
                handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                    }
                });
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }
}