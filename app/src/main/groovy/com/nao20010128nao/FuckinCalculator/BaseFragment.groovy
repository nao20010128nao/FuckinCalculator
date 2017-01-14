package com.nao20010128nao.FuckinCalculator

import android.support.annotation.IdRes
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.view.View

/**
 * Created by nao on 2017/01/14.
 */
public abstract class BaseFragment extends Fragment {
    @Nullable
    public View findViewById(@IdRes int id){
        return view.findViewById(id)
    }
}