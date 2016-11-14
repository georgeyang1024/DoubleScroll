package cn.georgeyang.doublescroll;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.Random;

/**
 * Created by yangsp on 2016/11/14.
 */
public class Fragment2 extends BaseListFragment {
    public ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.layout_listview,null);
        listView = (ListView) view.findViewById(R.id.listView);
        String[] data = new String[100];
        for (int i = 0; i < 100; i++) {
            data[i] = new Random().nextDouble()+"";
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,data);
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    View getSlidableView() {
        return listView;
    }
}
