package com.support.android.designlibdemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by cdm on 2016/6/15.
 */
public class MyFABBehavior extends FloatingActionButton.Behavior {
    boolean isAnim;
    private static final String TAG = "ScrollAwareFAB";
    int disDy;
    public MyFABBehavior(Context context, AttributeSet attrs) {
        super();
    }

    @Override
    public boolean onStartNestedScroll(final CoordinatorLayout coordinatorLayout, final FloatingActionButton child,
                                       final View directTargetChild, final View target, final int nestedScrollAxes) {
        //上下滚动，才协调滚动事件
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL
                || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }


    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, final FloatingActionButton child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        if (isAnim){
            return;
        }
        if ((dy > 0 && disDy < 0) || (dy < 0 && disDy >0)){
            disDy = 0;
        }
        disDy += dy;
        if (disDy > child.getHeight() && child.getVisibility() == View.VISIBLE) {
            //向上滚动，并且处于可见状态--隐藏FAB
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(child, "scaleX", 0).setDuration(200);
            animator1.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    isAnim = true;
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    if (child.getVisibility() != View.INVISIBLE){
                        child.setVisibility(View.INVISIBLE);
                    }
                    isAnim = false;
                }
            });
            animator1.start();
            ObjectAnimator animator = ObjectAnimator.ofFloat(child, "scaleY", 0).setDuration(200);
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    if (child.getVisibility() != View.INVISIBLE){
                        child.setVisibility(View.INVISIBLE);
                    }
                }
            });
            animator.start();

        } else if (disDy < -child.getHeight() && child.getVisibility() != View.VISIBLE) {
            //向下滚动并且处于不可见状态--显示FAB
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(child, "scaleX", 1f).setDuration(200);
            animator1.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    if (child.getVisibility() != View.VISIBLE){
                        child.setVisibility(View.VISIBLE);
                    }
                }
            });
            animator1.start();
            ObjectAnimator animator = ObjectAnimator.ofFloat(child, "scaleY", 1f).setDuration(200);
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    if (child.getVisibility() != View.VISIBLE){
                        child.setVisibility(View.VISIBLE);
                    }
                }
            });
            animator.start();
        }
    }
}