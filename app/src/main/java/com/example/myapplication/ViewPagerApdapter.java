package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStateManagerControl;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerApdapter extends FragmentStatePagerAdapter {


    public ViewPagerApdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return  new YeuThichFragment();
            case 1: return new GioHangFragment();
            default:
                return new YeuThichFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0: title = "Yêu thích"; break;
            case 1: title = "Giỏ hàng"; break;
        }
        return title;
    }
}
