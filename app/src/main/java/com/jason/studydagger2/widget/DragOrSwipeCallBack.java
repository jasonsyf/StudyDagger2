package com.jason.studydagger2.widget;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by jason_sunyf on 2017/7/12.
 * Email:yufeng.sun@21wendao.cn
 */

public class DragOrSwipeCallBack extends ItemTouchHelper.Callback {
    private ItemTouchHelperAdapterCallBack mAdapterCallBack;

    public DragOrSwipeCallBack(ItemTouchHelperAdapterCallBack adapter) {
        mAdapterCallBack = adapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int drag = ItemTouchHelper.DOWN | ItemTouchHelper.UP;//允许上下的拖动
        int swipe = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;//允许左右侧滑
        return makeMovementFlags(drag, swipe);
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        //回调adapter 接口实现方法上下拖动的具体实现 （交换数据）
        mAdapterCallBack.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        //回调adapter 接口实现方法左右侧滑的具体实现 （删除）
        mAdapterCallBack.onItemDissmiss(viewHolder.getAdapterPosition());
    }


}
