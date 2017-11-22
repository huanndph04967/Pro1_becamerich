package com.example.dinhhuan.becamerich.View;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter  extends FragmentStatePagerAdapter {
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment frag=null;
        switch (position){
            case 0:
                frag=new AndroidFragment();
                break;
            case 1:
                frag=new IOSFragment();
                break;

        }
        return frag;
    }
    @Override
    public int getCount() {
        return 2;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title="Thống Kê";
                break;
            case 1:
                title="Biểu Đồ";
                break;

        }

        return title;
    }

}
