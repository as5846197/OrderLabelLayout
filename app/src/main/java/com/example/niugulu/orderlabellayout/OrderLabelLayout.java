package com.example.niugulu.orderlabellayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by niugulu on 16/4/10.
 */
public class OrderLabelLayout extends ViewGroup {
    private int verticalSpacing = 10;
    private int horizontalSpacing = 5;

    public OrderLabelLayout(Context context) {
        super(context);
    }

    public OrderLabelLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OrderLabelLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int layoutWidth = MeasureSpec.getSize(widthMeasureSpec);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int childLeft = paddingLeft;
        int lineHeight = paddingTop;
        int childrenCount = getChildCount();
        for (int i = 0; i < childrenCount; i++) {
            View child = getChildAt(i);
            lineHeight = Math.max(child.getMeasuredHeight() + paddingTop, lineHeight);
            if (child.getMeasuredWidth() + childLeft + paddingRight > layoutWidth) {
                childLeft = paddingLeft;
                lineHeight += child.getMeasuredHeight() + verticalSpacing;
            }
            childLeft += child.getMeasuredWidth() + horizontalSpacing;
        }
        setMeasuredDimension(layoutWidth, lineHeight + paddingBottom);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int layoutWidth = r - l;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int childLeft = paddingLeft;
        int lineHeight = paddingTop;
        int childrenCount = getChildCount();
        for (int i = 0; i < childrenCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == View.GONE) {
                continue;
            }
            if (child.getMeasuredWidth() + childLeft + paddingRight > layoutWidth) {
                childLeft = paddingLeft;
                lineHeight += child.getMeasuredHeight() + verticalSpacing;
            }
            if (child.getVisibility() == View.GONE) {
                continue;
            }
            child.layout(childLeft, lineHeight, childLeft + child.getMeasuredWidth(), lineHeight + child.getMeasuredHeight());
            childLeft += child.getMeasuredWidth() + horizontalSpacing;
        }
    }
}
