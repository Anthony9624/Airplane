package com.airticket.client.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.airticket.client.R;
import com.google.android.material.tabs.TabLayout;

public class FlightActivity extends BaseActivity {

    private Toolbar mToolbar;
    private TabLayout mTlTabs;
    private ViewPager mVpContents;
    private Fragment[] mFragments = new Fragment[2];
    private String[] mLabels = new String[]{"添加航班", "添加机票"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight);
        setToolBar("航班管理",true);
        initData();
        initView();
    }

    private void initData() {
        mFragments[0] = new AddFlightFragment();
        mFragments[1] = new AddTicketFragment();
    }

    private void initView() {
        mTlTabs = (TabLayout) findViewById(R.id.id_tl_tabs);
        mVpContents = (ViewPager) findViewById(R.id.id_vp_content);
        mVpContents.setAdapter(new MyFragAdapter(getSupportFragmentManager()));
        mTlTabs.setupWithViewPager(mVpContents);
    }

    class MyFragAdapter extends FragmentPagerAdapter {
        public MyFragAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments[position];
        }

        @Override
        public int getCount() {
            return mFragments.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mLabels[position];
        }

    }

}
