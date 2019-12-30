package com.bwie.week2.model;

import com.bwie.week2.contract.IHomeContract;
import com.bwie.week2.model.bean.ShopBean;
import com.bwie.week2.util.NetUtil;
import com.google.gson.Gson;

/*
 *@auther:王彦敏
 *@Date: 2019/12/30
 *@Time:13:54
 *@Description:
 * */
public class HomeModel implements IHomeContract.IModel {

    @Override
    public void getHomeData(IHomeCallback iHomeCallback) {
        String httpUrl="http://172.17.8.100/small/commodity/v1/findCommodityByKeyword?page=1&count=10&keyword=%E6%9D%BF%E9%9E%8B";
        NetUtil.getInstance().getJsonGet(httpUrl, new NetUtil.MyCallback() {
            @Override
            public void onGetJson(String json) {
                ShopBean shopBean = new Gson().fromJson(json, ShopBean.class);
                iHomeCallback.onHomeSuccess(shopBean);
            }

            @Override
            public void onError(Throwable throwable) {
                iHomeCallback.onHomeFailure(throwable);
            }
        });
    }
}
