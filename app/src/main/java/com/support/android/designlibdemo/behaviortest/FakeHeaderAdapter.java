package com.support.android.designlibdemo.behaviortest;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * Created by cdm on 2016/7/6.
 */
public class FakeHeaderAdapter extends BaseRecyclerViewAdapter<String> {
    public FakeHeaderAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public BaseItemViewHolder getViewHolder(View view) {
        return new MyHolder(view);
    }

    @Override
    public View createView() {
        return new TextView(mContext);
    }

    private class MyHolder extends BaseItemViewHolder{

        public MyHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void initChildView(View view) {

        }

        @Override
        public void bindData(String str) {
            ((TextView)itemView).setText(str);
        }
    }
}
