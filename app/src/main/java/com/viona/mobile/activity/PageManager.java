package com.viona.mobile.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.gson.GsonBuilder;
import com.viona.mobile.R;
import com.viona.mobile.adapter.DealsAdapter;
import com.viona.mobile.model.DealsModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mehdi.sakout.fancybuttons.FancyButton;

public class PageManager extends Master implements View.OnClickListener {
    private FancyButton submit, information, deals, follower;
    private FrameLayout content;
    private DealsAdapter dealsAdapter;
    private List<DealsModel> listDeals = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_page);

        findView();
        init();
    }

    private void findView() {
        title = findViewById(R.id.title);
        back = findViewById(R.id.back);
        submit = findViewById(R.id.submit);
        information = findViewById(R.id.information);
        deals = findViewById(R.id.deals);
        content = findViewById(R.id.content);
    }

    private void init() {
        title.setText("Manage Page");
        back.setOnClickListener(this::onClick);
        information.setOnClickListener(this::onClick);
        deals.setOnClickListener(this::onClick);
        setTabInformation();
    }

    private void changeTabColor(int type) {
        int colorPrimary = ContextCompat.getColor(this, R.color.colorPrimary);
        int colorPrimaryDark = ContextCompat.getColor(this, R.color.colorPrimaryDark);
        int grayLvlThree = ContextCompat.getColor(this, R.color.grayLvlThree);
        int grayLvlSeven = ContextCompat.getColor(this, R.color.grayLvlSeven);
        int white = ContextCompat.getColor(this, R.color.white);
        int black = ContextCompat.getColor(this, R.color.black);

        switch (type) {
            case 0: // if tab page information is clicked
                information.setBackgroundColor(colorPrimary);
                information.setFocusBackgroundColor(colorPrimaryDark);
                information.setTextColor(white);
                deals.setBackgroundColor(grayLvlThree);
                deals.setFocusBackgroundColor(grayLvlSeven);
                deals.setTextColor(black);
                break;
            case 1: // if tab deal manager is clicked
                information.setBackgroundColor(grayLvlThree);
                information.setFocusBackgroundColor(grayLvlSeven);
                information.setTextColor(black);
                deals.setBackgroundColor(colorPrimary);
                deals.setFocusBackgroundColor(colorPrimaryDark);
                deals.setTextColor(white);
                break;
        }
    }

    private void setTabInformation() {
        try {
            View view = helper.inflateView(R.layout.tab_page_information);

            follower  = view.findViewById(R.id.follower);

            content.removeAllViews();
            content.addView(view);
            follower.setOnClickListener(this::onClick);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setTabDeals() {
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
            View view = helper.inflateView(R.layout.tab_deals);
            RecyclerView lists = view.findViewById(R.id.list);
            content.removeAllViews();
            content.addView(view);

            setItemDeals(lists);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setItemDeals(RecyclerView lists) {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        dealsAdapter = new DealsAdapter(getApplicationContext(), listDeals, R.layout.item_deals_vertical, new DealsAdapter.OnClickListener() {
            @Override
            public void onClickListener(int position) {
                showActionsDialog(position);
            }
        });

        lists.setLayoutManager(staggeredGridLayoutManager);
        lists.setAdapter(dealsAdapter);
    }

    private void showActionsDialog(final int position) {
        CharSequence items[] = new CharSequence[]{"Edit", "Delete"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose option");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    Map<String, String> param = new HashMap<>();
                    param.put("type", "edit");
                    param.put("data", new GsonBuilder().create().toJson(listDeals.get(position)));
                } else {
                    confirmDelete(listDeals.get(position).getId());
                }
            }
        });
        builder.show();
    }

    private void confirmDelete(String id) {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case DialogInterface.BUTTON_POSITIVE:
                    case DialogInterface.BUTTON_NEGATIVE:
                        dialogInterface.dismiss();
                        return;
                    default:
                        return;
                }
            }
        };
        helper.popupConfirm("Are you sure?", "Selected advertisements will be removed!", dialogClickListener);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.information:
                changeTabColor(0);
                setTabInformation();
                break;
            case R.id.deals:
                changeTabColor(1);
                setTabDeals();
                break;
            case R.id.follower:
                helper.startIntent(ManageFollower.class, false, null);
                break;
        }
    }
}
