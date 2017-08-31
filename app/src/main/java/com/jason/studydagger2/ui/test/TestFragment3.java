package com.jason.studydagger2.ui.test;


import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jason.studydagger2.R;
import com.jason.studydagger2.base.BaseFragment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment3 extends BaseFragment {


    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    Unbinder unbinder;
    String[] tabs = {"语文", "数学", "英语", "地理"};
    List<String> mtabs = new ArrayList<>();
    TestAdapter mAdapter;
    List<String> mStrings1 = new ArrayList<>();
    List<String> mStrings2 = new ArrayList<>();
    List<String> mStrings3 = new ArrayList<>();
    List<String> mStrings4 = new ArrayList<>();
    List<String> mStrings5 = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_test_fragment3, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;

    }

    @Override
    protected void initEventAndData() {
        mAdapter = new TestAdapter(mStrings5, getActivity());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);
        mtabs = Arrays.asList(tabs);
        for (int i = 0; i < mtabs.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(mtabs.get(i)));
            mStrings1.add("第一个data" + i);
            mStrings2.add("第二个data" + i);
            mStrings3.add("第三个data" + i);
            mStrings4.add("第四个data" + i);
        }
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mStrings5.clear();
                for (int i = 0; i < mtabs.size(); i++) {

                    if (tab.getPosition() == mtabs.size() - 1) {
                        getData(i);
                    }

                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private List<String> getData(int i) {
        switch (i) {
            case 0:
                return mStrings1;
            case 1:
                return mStrings2;
            case 2:
                return mStrings3;
            case 3:
                return mStrings4;
        }
        return null;
    }


    @Override
    public void showErrorMsg(String msg) {

    }

    @Override
    public void stateError() {

    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateMain() {

    }

    @Override
    protected void initInject() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
