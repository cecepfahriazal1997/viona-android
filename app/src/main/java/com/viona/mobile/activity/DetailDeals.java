package com.viona.mobile.activity;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.viona.mobile.R;
import com.viona.mobile.adapter.DealsAdapter;
import com.viona.mobile.adapter.PlatformAdapter;
import com.viona.mobile.model.DealsModel;
import com.viona.mobile.model.PlatformModel;

import java.util.ArrayList;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;

public class DetailDeals extends Master implements View.OnClickListener {
    private FancyButton back;
    private RecyclerView listDeals, listPlatform;
    private DealsAdapter dealsAdapter;
    private PlatformAdapter platformAdapter;
    private List<DealsModel> deals = new ArrayList<>();
    private List<PlatformModel> platform = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_deals);

        findView();
        init();
        initDeals();
        initPlatform();
    }

    private void findView() {
        title = findViewById(R.id.title);
        back = findViewById(R.id.back);
        listDeals = findViewById(R.id.list_interest);
        listPlatform = findViewById(R.id.list_platform);
    }

    private void init() {
        title.setText("Deal Detail");
        back.setOnClickListener(this::onClick);
    }

    private void initDeals() {
        deals.clear();
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

                deals.add(data);
            }
            setItemDeals();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setItemDeals() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false);
        dealsAdapter = new DealsAdapter(getApplicationContext(), deals, R.layout.item_deals_horizontal, new DealsAdapter.OnClickListener() {
            @Override
            public void onClickListener(int position) {
                helper.startIntent(DetailDeals.class, false, null);
            }
        });

        listDeals.setLayoutManager(linearLayoutManager);
        listDeals.setAdapter(dealsAdapter);
    }

    private void initPlatform() {
        platform.clear();
        try {
            String name[] = {
                    "Tokopedia",
                    "Shopee",
                    "Bukalapak",
                    "Gojek",
                    "Grab",
            };

            for (int i = 0; i < name.length; i++) {
                PlatformModel data = new PlatformModel();

                data.setName(name[i]);

                platform.add(data);
            }
            setItemPlatform();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setItemPlatform() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false);
        platformAdapter = new PlatformAdapter(getApplicationContext(), platform, new PlatformAdapter.OnClickListener() {
            @Override
            public void onClickListener(int position) {
            }
        });

        listPlatform.setLayoutManager(linearLayoutManager);
        listPlatform.setAdapter(platformAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
        }
    }
}
