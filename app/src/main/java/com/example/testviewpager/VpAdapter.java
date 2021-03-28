package com.example.testviewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class VpAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> listFragments = new ArrayList<>();

    public VpAdapter(FragmentManager fm, int i) {
        super(fm, i);
    }

    public void addFragment(Fragment fragment) {
        listFragments.add(fragment);
    }

    public void removeFragment(Fragment fragment) {
        listFragments.remove(fragment);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return listFragments.get(position);
    }


    @Override
    public int getCount() {
        return listFragments.size();
    }
}
