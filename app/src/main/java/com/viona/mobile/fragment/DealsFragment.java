package com.viona.mobile.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.viona.mobile.R;
import com.viona.mobile.activity.Dashboard;
import com.viona.mobile.activity.DetailDeals;
import com.viona.mobile.adapter.DealsAdapter;
import com.viona.mobile.model.DealsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cecep Rokani on 3/18/2019.
 */

public class DealsFragment extends Fragment implements View.OnClickListener {
    private Dashboard parent;
    private Handler handler;
    private final boolean isVisible = true;
    private RecyclerView lists;
    private DealsAdapter dealsAdapter;
    private List<DealsModel> listDeals = new ArrayList<>();

    public DealsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parent = ((Dashboard) getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_deals, container, false);

        findView(rootView);
        initDeals();
        return rootView;
    }

    private void findView(View rootView) {
        lists = rootView.findViewById(R.id.list);
    }

    private void initDeals() {
        listDeals.clear();
        try {
            String name[] = {
                    "Promo KFC Special 5 Discount 50%",
                    "Meg Keju Serbaguna 165 ml Discount 10%",
                    "Richeese 50% Special",
                    "Macdonal's Promo Buy 1 Get 1",
                    "Xi Boba Promo 3 Combo",
                    "Xi Nona Special 40%",
                    "Upnormal Buy 1 Get 1",
                    "Nasi Goreng Mafia Promo 32%"
            };

            float pricesOld[] = {
                    78999,
                    24000,
                    65000,
                    80999,
                    35699,
                    40000,
                    30000,
                    30000
            };

            String owner[] = {
                    "KFC",
                    "Meg Milk",
                    "Richeese",
                    "Macdonal",
                    "Xi Boba",
                    "Xi Nona",
                    "Upnormal",
                    "Mafia"
            };

            float discount[] = {
                    50,
                    10,
                    50,
                    0,
                    0,
                    40,
                    0,
                    32
            };

            for (int i = 0; i < name.length; i++) {
                DealsModel data = new DealsModel();

                data.setName(name[i]);
                data.setPrice(pricesOld[i] - (pricesOld[i] * (discount[i] / 100)));
                data.setPriceOld((pricesOld[i]));
                data.setOwner(owner[i]);
                data.setDiscount(String.valueOf(discount[i]));
                data.setPeriod(("2" + (i + 1) + " Maret 2022"));

                listDeals.add(data);
            }
            setItemDealsLatest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setItemDealsLatest() {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        dealsAdapter = new DealsAdapter(parent.getApplicationContext(), listDeals, R.layout.item_deals_vertical, new DealsAdapter.OnClickListener() {
            @Override
            public void onClickListener(int position) {
                parent.functionHelper.startIntent(DetailDeals.class, false, null);
            }
        });

        lists.setLayoutManager(staggeredGridLayoutManager);
        lists.setAdapter(dealsAdapter);
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