package com.jason.studydagger2.ui.test;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jason.studydagger2.R;
import com.jason.studydagger2.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class  TestFragment1 extends BaseFragment {


    public TestFragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test_fragment1, container, false);
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
}
