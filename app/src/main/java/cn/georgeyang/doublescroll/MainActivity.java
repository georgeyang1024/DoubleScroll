package cn.georgeyang.doublescroll;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private DoubleScrollView scrollView;
    private DoubleScrollViewPager viewPager;
    private SAdapter adapter;
    private View layoutTabTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        scrollView = (DoubleScrollView) findViewById(R.id.layoutContent);
        scrollView.setupTitleView(findViewById(R.id.layoutTop));
        scrollView.setContentView(findViewById(R.id.layoutContentBottom));
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        viewPager = (DoubleScrollViewPager) findViewById(R.id.viewPager);
        layoutTabTitle = findViewById(R.id.layoutTabTitle);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                scrollView.setContentInnerScrollableView(adapter.getSlidableView(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        adapter = new SAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);

        scrollView.postDelayed(new Runnable() {
            @Override
            public void run() {
                scrollView.setContentInnerScrollableView(adapter.getSlidableView(0));
                viewPager.setTagHeight(scrollView.getMeasuredHeight() - layoutTabTitle.getMeasuredHeight());
            }
        },100);

    }
}
