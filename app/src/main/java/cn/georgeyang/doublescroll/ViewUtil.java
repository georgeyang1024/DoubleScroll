package cn.georgeyang.doublescroll;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ScrollView;

/**
 * Created by george.yang on 16/11/16.
 */

public class ViewUtil {
    public static boolean isScrollToTop(View view) {
        if (view instanceof ListView) {
            ListView listView = (ListView) view;
            int first = listView.getFirstVisiblePosition();
            return first == 0;
        } else if (view instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) view;
            return !recyclerView.canScrollVertically(-1);
        } else if (view instanceof ScrollView) {
            ScrollView scrollView = (ScrollView) view;
            return scrollView.getScrollY() == 0;
        } else if (view instanceof DSRefView) {
            DSRefView dsRefView = (DSRefView) view;
            return dsRefView.isReleaseTouch();
        }
        return true;
    }


    public static void scrollToTop (View view) {
        if (view instanceof ScrollView) {
            ScrollView scrollView = (ScrollView) view;
            scrollView.smoothScrollTo(0,0);
        } else if (view instanceof ListView) {
            ListView listView = (ListView) view;
            listView.setSelection(0);
        }
    }

    public static  <T> T findView(View view, Class<T> tClass) {
        if (view.getClass() == tClass) {
            return (T) view;
        } else if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i=0;i<viewGroup.getChildCount();i++) {
                View subView = viewGroup.getChildAt(i);
                T find = findView(subView,tClass);
                if (find!=null) {
                    return find;
                }
            }
        }
        return null;
    }
}
