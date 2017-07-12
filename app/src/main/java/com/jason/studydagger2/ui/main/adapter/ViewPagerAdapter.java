package com.jason.studydagger2.ui.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.jason.studydagger2.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason_sunyf on 2017/7/12.
 * Email:yufeng.sun@21wendao.cn
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> mFragments=new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        if (fragments!=null){
            mFragments.addAll(fragments);
        }
    }
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // viewpager在滑动时会销毁之前的fragment
        // 滑动过来则会再次创建 数组里的fragment
        // 所以需要 super的方法 删除掉
    }

    public void update(List<BaseFragment> fragments) {
        mFragments.clear();
        if (fragments != null) {
            mFragments.addAll(fragments);
        }
        notifyDataSetChanged();
    }
}
