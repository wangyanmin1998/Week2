package com.bwie.week2.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;

/*
 *@auther:王彦敏
 *@Date: 2019/12/30
 *@Time:13:49
 *@Description:
 * */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        mPresenter=providePresenter();
        if (mPresenter != null) {
        mPresenter.attach(this);
    }
        ButterKnife.bind(this);
    initView();

    initData();
}

    protected abstract int layoutId();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract P providePresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detach();
        }
    }
}
