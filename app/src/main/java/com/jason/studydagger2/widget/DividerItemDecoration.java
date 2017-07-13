package com.jason.studydagger2.widget;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jason.studydagger2.base.contract.WxNewsContract;

/**
 * Created by jason_sunyf on 2017/7/13.
 * Email:yufeng.sun@21wendao.cn
 */

public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private Paint mPaint;
    private int dividerHeight;
    private int offsetHeight;

    public DividerItemDecoration(int color, int offsetHeight, int dividerHeight) {
        this.dividerHeight = dividerHeight;
        this.offsetHeight = offsetHeight;
        mPaint = new Paint();
        mPaint.setColor(color);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        //获得每个item的位置
        int itemPosition = parent.getChildAdapterPosition(view);
        //第一个item不绘制分割线
//        if (itemPosition != 0) {
        //设置间隔区域为10px，即可绘制区域为10px
        outRect.set(0, 0, 0, offsetHeight);
//        }
    }

    //重写onDraw ：在间隔区域里绘制一个矩形，即分割线
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        //获取recyclerView 的child view的个数
        int childCount = parent.getChildCount();
        //遍历每个Item获取他们的位置信息，然后绘制对应的分割线
        for (int i = 0; i < childCount; i++) {
            //获取每个item的位置
            final View child = parent.getChildAt(i);
            int index = parent.getChildAdapterPosition(child);
//            if (index == 0) {
//                continue;
//            }
            //获取布局参数
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            final int left = parent.getPaddingLeft();
            final int top = child.getBottom() + params.bottomMargin + Math.round(ViewCompat.getTranslationY(child));
            final int right = parent.getWidth() - parent.getPaddingRight();
            final int bottom = top + dividerHeight;
            c.drawRect(left, top, right, bottom, mPaint);
        }
    }
}
