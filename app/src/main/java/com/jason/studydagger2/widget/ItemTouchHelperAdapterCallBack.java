package com.jason.studydagger2.widget;

/**
 * Created by jason_sunyf on 2017/7/12.
 * Email:yufeng.sun@21wendao.cn
 */

public interface ItemTouchHelperAdapterCallBack {
    //数据交换
    void onItemMove(int fromPosition,int toPosition);
    //数据删除
    void onItemDissmiss(int position);
}
