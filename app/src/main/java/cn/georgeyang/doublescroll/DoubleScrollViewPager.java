package cn.georgeyang.doublescroll;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yangsp on 2016/11/14.
 */
public class DoubleScrollViewPager extends ViewPager {
    public DoubleScrollViewPager(Context context) {
        super(context);
    }

    public DoubleScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private int tagHeight;

    public void setTagHeight(int height) {
        tagHeight = height;
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (tagHeight > 0) {
            heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(tagHeight, View.MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
