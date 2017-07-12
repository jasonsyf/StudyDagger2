package com.jason.studydagger2.ui.WxNews.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jason.studydagger2.R;
import com.jason.studydagger2.base.BaseFragment;
import com.jason.studydagger2.base.contract.WxNewsContract;
import com.jason.studydagger2.mvpmodel.bean.WxNewsBean;
import com.jason.studydagger2.mvppresenter.WxNewsPresenter;
import com.jason.studydagger2.ui.WxNews.adapter.WxItemAdapter;
import com.jason.studydagger2.util.toast.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class WxNewsFragment extends BaseFragment<WxNewsPresenter>  implements WxNewsContract.View {

    @BindView(R.id.wxnews_recycle)
    RecyclerView mWxnewsRecycle;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout mSwipeLayout;
    Unbinder unbinder;
    WxItemAdapter mAdapter;
    List<WxNewsBean> mList;
    boolean isLoadingMore = false;
    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
        mPresenter.attachView(this);
        mPresenter.getWxNewsData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wx_news, container, false);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {


        mList=new ArrayList<>();
        mAdapter = new WxItemAdapter(getContext(), mList);
        mWxnewsRecycle.setLayoutManager(new LinearLayoutManager(getContext()));
        mWxnewsRecycle.setAdapter(mAdapter);
        mWxnewsRecycle.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = ((LinearLayoutManager) mWxnewsRecycle.getLayoutManager()).findLastVisibleItemPosition();
                int totalItemCount = mWxnewsRecycle.getLayoutManager().getItemCount();
                if (lastVisibleItem >= totalItemCount - 2 && dy > 0) {  //还剩2个Item时加载更多
                    if(!isLoadingMore){
                        isLoadingMore = true;
                        mPresenter.getMoreWxNewsData();
                    }
                }
            }
        });
        mSwipeLayout.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getWxNewsData();
            }
        });
        stateLoading();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showErrorMsg(String msg) {
        ToastUtil.showSnackBarShort(mWxnewsRecycle, msg);
    }

    @Override
    public void showLoading(String msg) {
    }

    @Override
    public void stateError() {
        if(mSwipeLayout.isRefreshing()) {
            mSwipeLayout.setRefreshing(false);
        }
    }

    @Override
    public void stateEmpty() {
        ToastUtil.showSnackBarShort(mWxnewsRecycle, "没数据");
    }

    @Override
    public void stateLoading() {

    }


    @Override
    public void stateMain() {

    }

    @Override
    public void showContent(List<WxNewsBean> wxNewsBeanList) {
        if(mSwipeLayout.isRefreshing()) {
            mSwipeLayout.setRefreshing(false);
        }
        stateMain();
        mList.clear();
        mList.addAll(wxNewsBeanList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMoreContent(List<WxNewsBean> wxNewsBeanList) {
        stateMain();
        mList.addAll(wxNewsBeanList);
        mAdapter.notifyDataSetChanged();
        isLoadingMore = false;
    }
}
