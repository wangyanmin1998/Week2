package com.bwie.week2.contract;

import com.bwie.week2.model.bean.ShopBean;

/*
 *@auther:王彦敏
 *@Date: 2019/12/30
 *@Time:13:54
 *@Description:
 * */
public interface IHomeContract {
    interface IView{
       void onHomeSuccess(ShopBean shopBean);
       void onHomeFailure(Throwable throwable);

    }
    interface IPresenter{
        void getHomeData();
    }
    interface IModel{
        void getHomeData(IHomeCallback iHomeCallback);
        interface IHomeCallback{
            void onHomeSuccess(ShopBean shopBean);
            void onHomeFailure(Throwable throwable);

        }
    }

}
