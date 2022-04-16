package com.viona.mobile.activity;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;
import com.viona.mobile.R;
import com.viona.mobile.fragment.WalkthroughFragment;

import java.util.ArrayList;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;

public class Walkthrough extends com.viona.mobile.activity.Master implements View.OnClickListener {
    private ViewPager mViewPager;
    private DotsIndicator pageIndicatorView;
    private FancyButton back, next;
    private final String[] title = {"Discover Deals", "Compare Deals", "Add Deals", "Manage Deals", "Promote Deals"};
    private final String[] desc = {
            "Let’s discover deals through the massive and integrated directory of Viona.",
            "Compare the deals to find out which one is the most suitable to your preferences.",
            "Every users can contribute on the provision of the deals information, You can add freely any deals to Viona's directory.",
            "You have total control of every deals that you add to Viona's directory, Manage them as you see fit.",
            "Every users can promote their deals on Viona platform."
    };

    private final int[] image = {R.drawable.placeholder, R.drawable.placeholder, R.drawable.placeholder, R.drawable.placeholder, R.drawable.placeholder};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walkthrough);

        mViewPager          = findViewById(R.id.viewPager);
        next                = findViewById(R.id.next);
        back                = findViewById(R.id.back);
        pageIndicatorView   = findViewById(R.id.indicator);

        setupViewPager(mViewPager);
        pageIndicatorView.setViewPager(mViewPager);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (mViewPager.getCurrentItem() > 0) {
                    back.setVisibility(View.VISIBLE);

                    if (mViewPager.getCurrentItem() == title.length - 1) {
                        next.setText("Finish");
                        next.setOnClickListener(Walkthrough.this::onClick);
                    } else {
                        next.setText("Next");
                        next.setOnClickListener(Walkthrough.this::onClick);
                    }
                } else {
                    back.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        back.setOnClickListener(this);
        next.setOnClickListener(this);
    }

    private void setupViewPager(ViewPager viewPager) {
        WalkthroughFragmentAdapter adapter = new WalkthroughFragmentAdapter(getSupportFragmentManager());
        for (int i = 0; i < title.length; i++) {
            adapter.addFrag(WalkthroughFragment.newInstance(
                    title[i],
                    desc[i],
                    image[i],
                    i));
        }

        viewPager.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                if (mViewPager.getCurrentItem() - 1 >= 0)
                    mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1,true);
                break;
            case R.id.next:
                if (next.getText().toString().equalsIgnoreCase("next")) {
                    if (mViewPager.getCurrentItem() <= mViewPager.getChildCount())
                        mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
                } else {
                    helper.startIntent(Login.class, false, null);
                }
                break;
        }
    }

    class WalkthroughFragmentAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        public WalkthroughFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        public void addFrag(Fragment fragment) {
            mFragmentList.add(fragment);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
    }
}
