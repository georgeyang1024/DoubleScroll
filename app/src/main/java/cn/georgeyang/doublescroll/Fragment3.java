package cn.georgeyang.doublescroll;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Random;

/**
 * Created by yangsp on 2016/11/14.
 */
public class Fragment3 extends BaseListFragment {
    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.layout_recyclerview,null);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(new NormalRecyclerViewAdapter(getContext()));

        //fix  bug: recyclerView & scrollView
        //see http://stackoverflow.com/questions/38074949/recyclerview-inside-scrollview-some-items-are-not-shown
        recyclerView.setNestedScrollingEnabled(false);
        return view;
    }

    @Override
    View getSlidableView() {
        return recyclerView;
    }
}
