package com.viona.mobile.activity;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.viona.mobile.R;
import com.viona.mobile.adapter.DealsAdapter;
import com.viona.mobile.model.DealsModel;

import java.util.ArrayList;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;

public class DealsResults extends Master implements View.OnClickListener {
    private FancyButton back;
    private RecyclerView list;
    private DealsAdapter dealsAdapter;
    private List<DealsModel> listDeals = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_deals_results);
        list = findViewById(R.id.list);

        findView();
        init();
        initDeals();
    }

    private void findView() {
        title = findViewById(R.id.title);
        back = findViewById(R.id.back);
    }

    private void init() {
        back.setOnClickListener(this::onClick);
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
                data.setDiscount(String.valueOf((int) discount[i]));
                data.setPeriod(("2" + (i + 1) + " Maret 2022"));

                listDeals.add(data);
            }
            setItemDeals();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setItemDeals() {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        dealsAdapter = new DealsAdapter(getApplicationContext(), listDeals, R.layout.item_deals_vertical, new DealsAdapter.OnClickListener() {
            @Override
            public void onClickListener(int position) {
                helper.startIntent(DetailDeals.class, false, null);
            }
        });

        list.setLayoutManager(staggeredGridLayoutManager);
        list.setAdapter(dealsAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
        }
    }
}
