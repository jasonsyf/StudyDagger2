package com.jason.studydagger2.ui.WxNews.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jason.studydagger2.R;
import com.jason.studydagger2.easylibrary.ImageLoader;
import com.jason.studydagger2.mvpmodel.bean.WxNewsBean;
import com.jason.studydagger2.widget.SquareImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jason_sunyf on 2017/7/12.
 * Email:yufeng.sun@21wendao.cn
 */

public class WxItemAdapter extends RecyclerView.Adapter<WxItemAdapter.ViewHolder> {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<WxNewsBean> mList;

    public WxItemAdapter(Context context, List<WxNewsBean> list) {
        this.mContext = context;
        this.mList = list;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.item_wechat,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageLoader.load((Activity) mContext,mList.get(position).getPicUrl(),holder.ivImage);
        holder.tvTitle.setText(mList.get(position).getTitle());
        holder.tvFrom.setText(mList.get(position).getDescription());
        holder.tvTime.setText(mList.get(position).getCtime());
    }
    @Override
    public int getItemCount() {
        return mList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_wechat_item_title)
        TextView tvTitle;
        @BindView(R.id.tv_wechat_item_time)
        TextView tvTime;
        @BindView(R.id.tv_wechat_item_from)
        TextView tvFrom;
        @BindView(R.id.iv_wechat_item_image)
        ImageView ivImage;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
