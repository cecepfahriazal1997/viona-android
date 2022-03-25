package com.viona.mobile.activity;

import android.os.Bundle;
import android.view.View;

import com.viona.mobile.R;

public class DefaultActivity extends Master implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        findView();
    }

    private void findView() {
        title   = findViewById(R.id.title);
        back    = findViewById(R.id.back);
    }

    private void init() {}

    private void setData() {}

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
        }
    }
}
