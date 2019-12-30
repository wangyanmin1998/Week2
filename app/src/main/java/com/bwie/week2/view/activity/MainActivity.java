package com.bwie.week2.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bwie.week2.R;
import com.bwie.week2.base.BaseActivity;
import com.bwie.week2.contract.IHomeContract;
import com.bwie.week2.model.bean.ShopBean;
import com.bwie.week2.presenter.HomePresenter;
import com.bwie.week2.view.adapter.MyAdapter;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<HomePresenter> implements IHomeContract.IView {

    @BindView(R.id.recy)
    RecyclerView recy;

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        mPresenter.getHomeData();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected HomePresenter providePresenter() {
        return new HomePresenter();
    }

    @Override
    public void onHomeSuccess(ShopBean shopBean) {
        List<ShopBean.ResultBean> result = shopBean.getResult();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recy.setLayoutManager(linearLayoutManager);
        MyAdapter myAdapter = new MyAdapter(result);
        recy.setAdapter(myAdapter);
        myAdapter.setOnTagClickListner(new MyAdapter.onTagClickListner() {
            @Override
            public void onTagClick(int position) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onHomeFailure(Throwable throwable) {
        Toast.makeText(this, "123", Toast.LENGTH_SHORT).show();
    }


}
