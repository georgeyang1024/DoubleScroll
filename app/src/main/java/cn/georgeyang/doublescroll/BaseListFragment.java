package cn.georgeyang.doublescroll;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;

/**
 * Created by yangsp on 2016/11/14.
 */
public abstract class BaseListFragment extends Fragment {
    abstract View getSlidableView();
}
