package com.bwie.week2.presenter;

import com.bwie.week2.base.BasePresenter;
import com.bwie.week2.contract.IHomeContract;
import com.bwie.week2.model.HomeModel;
import com.bwie.week2.model.bean.ShopBean;

/*
 *@auther:王彦敏
 *@Date: 2019/12/30
 *@Time:13:57
 *@Description:
 * */
public class HomePresenter extends BasePresenter<IHomeContract.IView> implements IHomeContract.IPresenter {

    private HomeModel homeModel;

    @Override
    protected void initModel() {
        homeModel = new HomeModel();
    }

    @Override
    public void getHomeData() {
        homeModel.getHomeData(new IHomeContract.IModel.IHomeCallback() {
            @Override
            public void onHomeSuccess(ShopBean shopBean) {
                view.onHomeSuccess(shopBean);
            }

            @Override
            public void onHomeFailure(Throwable throwable) {
                view.onHomeFailure(throwable);
            }
        });
    }
}
