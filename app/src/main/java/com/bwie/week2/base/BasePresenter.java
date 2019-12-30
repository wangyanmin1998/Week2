package com.bwie.week2.base;

import android.widget.BaseAdapter;

/*
 *@auther:王彦敏
 *@Date: 2019/12/30
 *@Time:13:47
 *@Description:
 * */
public abstract class BasePresenter<V> {
    protected V view;

    public BasePresenter(){
        initModel();
    }

    protected abstract void initModel();

    public void attach(V view){
        this.view=view;
    }

    public void detach(){
        view=null;
    }

}
