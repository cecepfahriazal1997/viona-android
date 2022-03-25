package com.viona.mobile.activity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.viona.mobile.R;
import com.viona.mobile.api.Service;
import com.viona.mobile.fragment.AccountFragment;
import com.viona.mobile.fragment.DealsFragment;
import com.viona.mobile.fragment.HomeFragment;
import com.viona.mobile.fragment.NotificationFragment;
import com.viona.mobile.helper.GeneralHelper;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends com.viona.mobile.activity.Master implements View.OnClickListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private boolean doubleBackToExitPressedOnce = false;
    private Handler handler;
    private final boolean isPaused = false;
    public int tabIconColor;
    public int tabIconColor2;
    public int position = 0;
    public String tmpRoleTeacher;
    private final int[] tabIcons = {
            R.drawable.home,
            R.drawable.deal,
            R.drawable.bell,
            R.drawable.account
    };

    public int totalNotification = 0;

    private final String[] title = {
            "Home",
            "Deals",
            "Notification",
            "Account"
    };

    public Service clientApiService;
    public GeneralHelper functionHelper;
    private ViewPagerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tabs);

        this.clientApiService = service;
        this.functionHelper = helper;
        tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary);
        tabIconColor2 = ContextCompat.getColor(getApplicationContext(), R.color.grayLvlSeven);

        setupViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        changeColorIcon();
        tmpRoleTeacher = roleTeacher;
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment(), title[0]);
        adapter.addFragment(new DealsFragment(), title[1]);
        adapter.addFragment(new NotificationFragment(), title[2]);
        adapter.addFragment(new AccountFragment(), title[3]);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
    }

    @Override
    public void onClick(View v) {

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }
    }

    private void setupTabIcons() {
        for (int i = 0; i < tabIcons.length; i++) {
            if (tabLayout.getTabAt(0).isSelected()) {
                setTabIcon(0, tabIcons[0], tabIconColor);
            }
            setTabIcon(i, tabIcons[i], tabIconColor2);
        }
    }

    private void changeColorIcon() {
        tabLayout.addOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                        position = tab.getPosition();
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        super.onTabUnselected(tab);
                        tab.getIcon().setColorFilter(tabIconColor2, PorterDuff.Mode.SRC_IN);
                    }
                }
        );
    }

    public void setTabIcon(int pos, int icon, int color) {
        tabLayout.getTabAt(pos).setIcon(icon);
        if (color != 0)
            tabLayout.getTabAt(pos).getIcon().setColorFilter(color, PorterDuff.Mode.SRC_IN);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        helper.showToast("Please click button back again to exit the Application !", 0);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
                handler.removeCallbacksAndMessages(null);
            }
        }, 2000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}
