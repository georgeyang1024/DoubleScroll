package cn.georgeyang.doublescroll;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangsp on 2016/11/14.
 */
public class SAdapter extends FragmentStatePagerAdapter {
    private List<BaseListFragment> list;
    private static final String[] titles = new String[]{"normal","list","recy","scroll","sina"};

    public SAdapter(FragmentManager fm) {
        super(fm);
        list = new ArrayList<>();
        list.add(new Fragment1());
        list.add(new Fragment2());
        list.add(new Fragment3());
        list.add(new Fragment4());
        list.add(new FragmentSina());
    }

    public View getSlidableView (int index) {
        return list.get(index).getSlidableView();
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
