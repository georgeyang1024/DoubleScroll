package cn.georgeyang.doublescroll;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Random;

/**
 * Created by george.yang on 16/11/15.
 */

public class FragmentSina extends BaseListFragment {
    private DSRefView layout;
    private SwipeRefreshLayout refreshLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.layout_sina,null);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                    }
                },3000);
            }
        });
        layout = (DSRefView) view.findViewById(R.id.layout);
        ListView listView = (ListView) layout.findViewById(R.id.listView);
        String[] data = new String[100];
        for (int i = 0; i < 100; i++) {
            data[i] = new Random().nextDouble()+"";
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,data);
        listView.setAdapter(adapter);
        layout.setSlideableView(listView);
        return view;
    }


    @Override
    View getSlidableView() {
        return layout;
    }
}
