package org.refme.refme_android_prototype.refme_android.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.refme.refme_android_prototype.refme_android.fragments.ReferalsFragment;
import org.refme.refme_android_prototype.refme_android.fragments.ReferFragment;

/**
 * Created by prashant on 10/9/15.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {


    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch(position){
            case 0:
                return new ReferalsFragment();
            case 1:
                return new ReferFragment();
        }

        return new ReferalsFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }
}
