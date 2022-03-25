package com.viona.mobile.activity;

import android.os.Bundle;
import android.os.Handler;

import com.viona.mobile.R;

public class SplashScreen extends Master {
    private static final int SPLASH_TIME_OUT  = 3000;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        initial();
    }

    private void initial() {
        handler             = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (helper.getSession("id") != null && helper.getSession("role") != null) {
                    helper.startIntent(Dashboard.class, false, null);
                    finish();
                } else {
                    helper.startIntent(Walkthrough.class, true, null);
                }
            }
        }, SPLASH_TIME_OUT);
    }

    public void onStop()
    {
        super.onStop();
        handler.removeCallbacksAndMessages(null);
    }

    public void onDestroy()
    {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
