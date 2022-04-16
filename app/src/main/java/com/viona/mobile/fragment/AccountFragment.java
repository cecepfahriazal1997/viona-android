package com.viona.mobile.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.viona.mobile.R;
import com.viona.mobile.activity.Dashboard;
import com.viona.mobile.activity.Login;
import com.viona.mobile.activity.PageManager;
import com.viona.mobile.adapter.SettingsAdapter;
import com.viona.mobile.model.SettingsModel;

import java.util.ArrayList;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Cecep Rokani on 3/18/2019.
 */

public class AccountFragment extends Fragment implements View.OnClickListener {
    private Dashboard parent;
    private Handler handler;
    private final boolean isVisible = true;
    private RecyclerView lists;
    private SettingsAdapter SettingsAdapter;
    private List<SettingsModel> list = new ArrayList<>();
    private FancyButton logout;
    private RelativeLayout contentPageCreate;

    public AccountFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parent = ((Dashboard) getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_account, container, false);

        findView(rootView);
        init();
        return rootView;
    }

    private void findView(View rootView) {
        lists = rootView.findViewById(R.id.list);
        logout = rootView.findViewById(R.id.logout);
        contentPageCreate = rootView.findViewById(R.id.content_manage_create);

        logout.setOnClickListener(this::onClick);
        contentPageCreate.setOnClickListener(this::onClick);
    }

    private void init() {
        list.clear();
        try {
            String title[] = {
                    "Profile Manager",
                    "Page Manager",
                    "Change Password",
                    "About",
                    "Privacy & Police",
            };

            int icon[] = {
                    R.drawable.user_solid,
                    R.drawable.shop,
                    R.drawable.lock_solid,
                    R.drawable.info,
                    R.drawable.privacy
            };

            for (int i = 0; i < title.length; i++) {
                SettingsModel data = new SettingsModel();

                data.setTitle(title[i]);
                data.setImage(icon[i]);
                list.add(data);
            }
            setItemDealsLatest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setItemDealsLatest() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(parent.getApplicationContext(), RecyclerView.VERTICAL, false);
        SettingsAdapter = new SettingsAdapter(parent.getApplicationContext(), list, new SettingsAdapter.OnClickListener() {
            @Override
            public void onClickListener(int position) {
            }
        });

        lists.setLayoutManager(linearLayoutManager);
        lists.setAdapter(SettingsAdapter);
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
            case R.id.logout:
                parent.functionHelper.startIntent(Login.class, true, null);
                break;
            case R.id.content_manage_create:
                parent.functionHelper.startIntent(PageManager.class, false, null);
                break;
        }
    }
}