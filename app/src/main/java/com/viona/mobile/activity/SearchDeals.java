package com.viona.mobile.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.viona.mobile.R;
import com.viona.mobile.adapter.SearchDealsAdapter;
import com.viona.mobile.model.SearchDealsModel;

import java.util.ArrayList;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;

public class SearchDeals extends Master implements View.OnClickListener {
    private FancyButton back;
    private RecyclerView list;
    private SearchDealsAdapter adapter;
    private List<SearchDealsModel> data = new ArrayList<>();
    private EditText keyword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_deals);

        findView();
        initDeals();
        init();
    }

    private void findView() {
        title = findViewById(R.id.title);
        back = findViewById(R.id.back);
        list = findViewById(R.id.list);
        keyword = findViewById(R.id.keyword);
    }

    private void init() {
        back.setOnClickListener(this::onClick);
        keyword.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    finish();
                    helper.startIntent(DealsResults.class, false, null);
                    return true;
                }
                return false;
            }
        });
    }

    private void initDeals() {
        data.clear();
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

            for (int i = 0; i < name.length; i++) {
                SearchDealsModel item = new SearchDealsModel();

                item.setTitle(name[i]);

                data.add(item);
            }
            setItemDealsLatest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setItemDealsLatest() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        adapter = new SearchDealsAdapter(getApplicationContext(), data, new SearchDealsAdapter.OnClickListener() {
            @Override
            public void onClickListener(int position) {
                helper.startIntent(DealsResults.class, false, null);
            }
        });

        list.setLayoutManager(linearLayoutManager);
        list.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
        }
    }
}
