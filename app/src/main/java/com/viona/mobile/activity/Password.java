package com.viona.mobile.activity;

import android.os.Bundle;
import android.view.View;

import com.viona.mobile.R;

import mehdi.sakout.fancybuttons.FancyButton;

public class Password extends Master implements View.OnClickListener {
    private FancyButton submit, close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        findView();
        init();
    }

    private void findView() {
        submit = findViewById(R.id.submit);
        close = findViewById(R.id.close);
    }

    private void init() {
        submit.setOnClickListener(this::onClick);
        close.setOnClickListener(this::onClick);
    }

    private void setData() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit:
            case R.id.close:
                helper.startIntent(Dashboard.class, true, null);
                break;
        }
    }
}
