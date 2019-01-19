package com.bway.caoyuan.view;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;
import android.widget.GridView;

public class RecyclerViewGrid extends GridView {
    
    
    
    public RecyclerViewGrid(Context context) {
        super(context);
    }

    public RecyclerViewGrid(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerViewGrid(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }


}
