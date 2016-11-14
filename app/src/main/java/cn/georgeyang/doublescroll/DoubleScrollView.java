package cn.georgeyang.doublescroll;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Matrix;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.ScrollView;

/**
 * Created by yangsp on 2016/11/14.
 */
public class DoubleScrollView extends ScrollView {
    public DoubleScrollView(Context context) {
        super(context);
    }

    public DoubleScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DoubleScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DoubleScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private View mTitle;

    public void setupTitleView(View view) {
        mTitle = view;
    }

    private View mContentView;

    public void setContentView(View view) {
        this.mContentView = view;
    }

    private View scrollableView;

    public void setContentInnerScrollableView(View scrollableView) {
        this.scrollableView = scrollableView;
    }

    private int maxMoveY;
    private int tabHeight;
    private float mFirstY;
    private Matrix eventMatrix;

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (mTitle == null || mContentView == null) {
            return super.dispatchTouchEvent(event);
        }

        if (maxMoveY == 0) {
            maxMoveY = mTitle.getMeasuredHeight();
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mFirstY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (scrollableView != null) {
                    float mCurrentY = event.getY();

                    if (tabHeight == 0) {
                        tabHeight = mContentView.getMeasuredHeight() - scrollableView.getMeasuredHeight();
                    }
                    eventMatrix = new Matrix();
                    eventMatrix.setTranslate(0, -tabHeight);

                    boolean isDown = mCurrentY > mFirstY;//往下拉
                    boolean isUp = mCurrentY < mFirstY;//

                    if (isUp) {
                        if (getScrollY() >= maxMoveY) {
                            event.transform(eventMatrix);
                            return scrollableView.dispatchTouchEvent(event);
                        }
                    } else if (isDown) {
                        if (!isScrollToTop(scrollableView)) {
                            event.transform(eventMatrix);
                            return scrollableView.dispatchTouchEvent(event);
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return super.dispatchTouchEvent(event);
    }

    public static boolean isScrollToTop(View view) {
        if (view instanceof ListView) {
            ListView listView = (ListView) view;
            int first = listView.getFirstVisiblePosition();
            if (first == 0) {
                return true;
            } else {
                return false;
            }
        } else if (view instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) view;
            return recyclerView.canScrollVertically(1);
        } else if (view instanceof ScrollView) {
            ScrollView scrollView = (ScrollView) view;
            return scrollView.getScrollY() == 0;
        }
        return true;
    }
}
