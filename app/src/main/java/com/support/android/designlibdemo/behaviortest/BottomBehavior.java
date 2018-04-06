package com.support.android.designlibdemo.behaviortest;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by cdm on 2016/7/6.
 */
public class BottomBehavior extends CoordinatorLayout.Behavior<View> {
    private int totalDis;

    public BottomBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        //上下滚动，才协调滚动事件
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL
                || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        
        totalDis += dy;
        if (totalDis > 0){
            if (totalDis > child.getHeight()){
                totalDis = child.getHeight();
            }
        }else if (totalDis <= 0){
            totalDis = 0;
        }
        child.setTranslationY(totalDis);

    }
}
