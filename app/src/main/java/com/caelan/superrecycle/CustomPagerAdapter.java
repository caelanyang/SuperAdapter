package com.caelan.superrecycle;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;

import com.caelan.superrecycle.tabfragment.BlueFragment;
import com.caelan.superrecycle.tabfragment.GreenFragment;
import com.caelan.superrecycle.tabfragment.RedFragment;

/**
 * Created by yangjiacheng on 2018/4/18.
 * ...
 */
public class CustomPagerAdapter extends FragmentPagerAdapter {

    private SparseArray<Fragment> fragmentSparseArray = new SparseArray<>();

    private Context mContext;

    CustomPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }


    @Override
    public Fragment getItem(int position) {
        return obtainFragment(position);
    }

    private Fragment obtainFragment(int position) {
        Fragment fragment = fragmentSparseArray.get(position);
        if (fragment == null) {
            if (position == 0) {
                fragment = new RedFragment();
            } else if (position == 1) {
                fragment = new BlueFragment();
            } else {
                fragment = new GreenFragment();
            }
            fragmentSparseArray.put(position, fragment);
        }
        return fragment;
    }


    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.red);
            case 1:
                return mContext.getString(R.string.blue);
            case 2:
                return mContext.getString(R.string.green);
            default:
                return null;
        }
    }
}
