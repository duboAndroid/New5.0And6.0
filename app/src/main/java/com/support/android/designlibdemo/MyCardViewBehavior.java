package com.support.android.designlibdemo;

import android.content.Context;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by cdm on 2016/6/15.
 */
public class MyCardViewBehavior extends CoordinatorLayout.Behavior<CardView> {
    public MyCardViewBehavior() {
        super();
    }
private  int disDy;
    public MyCardViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, "MyCardViewBehavior: ===");
    }

    private static final String TAG = "MyCardViewBehavior";
    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, CardView child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
//        Log.d(TAG, "onNestedScroll: =====================");
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, CardView child, View directTargetChild, View target, int nestedScrollAxes) {
//        Log.d(TAG, "onStartNestedScroll: =========" + (nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL||super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes)));
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL||super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, CardView child, View target, int dx, int dy, int[] consumed) {
        Log.d(TAG, "onNestedPreScroll: =============" + dy);
        if ((dy > 0 && disDy < 0) || (dy < 0 && disDy > 0)){
            disDy = 0;
        }
        disDy += dy;
        if (disDy > child.getHeight()+50){
            child.setCardBackgroundColor(Color.BLUE);
        }else if (disDy < -child.getHeight()-50 ){
            child.setCardBackgroundColor(Color.RED);
        }
//        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, CardView child, View dependency) {
//        Log.d(TAG, "onDependentViewChanged: =============");
        return super.onDependentViewChanged(parent, child, dependency);
    }

    @Override
    public boolean onInterceptTouchEvent(CoordinatorLayout parent, CardView child, MotionEvent ev) {
//        Log.d(TAG, "onInterceptTouchEvent: ==");
        return super.onInterceptTouchEvent(parent, child, ev);
    }

    @Override
    public boolean onTouchEvent(CoordinatorLayout parent, CardView child, MotionEvent ev) {
//        Log.d(TAG, "onTouchEvent: ===");
        return super.onTouchEvent(parent, child, ev);
    }

    @Override
    public boolean blocksInteractionBelow(CoordinatorLayout parent, CardView child) {
//        Log.d(TAG, "blocksInteractionBelow: ====");
        return super.blocksInteractionBelow(parent, child);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, CardView child, View dependency) {
//        Log.d(TAG, "layoutDependsOn: ====");
        return dependency instanceof CardView;
    }

    @Override
    public boolean isDirty(CoordinatorLayout parent, CardView child) {
//        Log.d(TAG, "isDirty: ====");
        return super.isDirty(parent, child);
    }

    @Override
    public boolean onMeasureChild(CoordinatorLayout parent, CardView child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
//        Log.d(TAG, "onMeasureChild: =========");
        return super.onMeasureChild(parent, child, parentWidthMeasureSpec, widthUsed, parentHeightMeasureSpec, heightUsed);
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, CardView child, int layoutDirection) {
//        Log.d(TAG, "onLayoutChild: =======");
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    @Override
    public void onNestedScrollAccepted(CoordinatorLayout coordinatorLayout, CardView child, View directTargetChild, View target, int nestedScrollAxes) {
//        Log.d(TAG, "onNestedScrollAccepted: ===========");
        super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, CardView child, View target) {
//        Log.d(TAG, "onStopNestedScroll: ===========");
        super.onStopNestedScroll(coordinatorLayout, child, target);
    }

    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, CardView child, View target, float velocityX, float velocityY, boolean consumed) {
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
    }

    @Override
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, CardView child, View target, float velocityX, float velocityY) {
//        Log.d(TAG, "onNestedPreFling: ============");
        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY);
    }

    @Override
    public WindowInsetsCompat onApplyWindowInsets(CoordinatorLayout coordinatorLayout, CardView child, WindowInsetsCompat insets) {
//        Log.d(TAG, "onApplyWindowInsets: ===========");
        return super.onApplyWindowInsets(coordinatorLayout, child, insets);
    }

    @Override
    public void onRestoreInstanceState(CoordinatorLayout parent, CardView child, Parcelable state) {
//        Log.d(TAG, "onRestoreInstanceState: =============");
        super.onRestoreInstanceState(parent, child, state);
    }

    @Override
    public Parcelable onSaveInstanceState(CoordinatorLayout parent, CardView child) {
//        Log.d(TAG, "onSaveInstanceState: =============");
        return super.onSaveInstanceState(parent, child);
    }
}
