package com.example.testviewpager;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends FragmentActivity implements IncrementFragmentClickListener {

    @BindView(R.id.vpMain)
    ViewPager vpMain;
    private Unbinder unbinder;
    private TestFragment testFragment;
    private VpAdapter vpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        vpAdapter = new VpAdapter(getSupportFragmentManager(), 0);
        testFragment = TestFragment.getInstance(this::incrementClickListener, 0, this);
        setupMainViewPager(vpMain, testFragment, true);
    }

    private void setupMainViewPager(ViewPager vpMain, TestFragment fragment, boolean isIncrement) {
        if (isIncrement) {
            vpAdapter.addFragment(fragment);
        } else {
            vpAdapter.removeFragment(fragment);
        }
        vpMain.setAdapter(vpAdapter);
        vpMain.setCurrentItem(vpAdapter.getCount());
    }

    @Override
    public void incrementClickListener(boolean isIncrement) {
        if (isIncrement) {
            testFragment = TestFragment.getInstance(this::incrementClickListener, vpAdapter.getCount(), this);
            setupMainViewPager(vpMain, testFragment, isIncrement);
        } else {
            setupMainViewPager(vpMain, (TestFragment) vpAdapter.getItem(vpMain.getCurrentItem()), isIncrement);

        }
    }

    @Override
    public void onBackPressed() {
        if (vpMain.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            vpMain.setCurrentItem(vpMain.getCurrentItem() - 1);

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}