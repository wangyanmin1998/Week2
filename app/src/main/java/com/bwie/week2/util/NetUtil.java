package com.bwie.week2.util;

import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bwie.week2.R;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/*
 *@auther:王彦敏
 *@Date: 2019/12/30
 *@Time:13:37
 *@Description:
 * */
public class NetUtil {
    private static NetUtil netUtil;
    private final Handler handler;
    private final OkHttpClient okhttpclient;

    private NetUtil(){
        handler = new Handler();
        // TODO: 2019/12/30 1.创建拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        // TODO: 2019/12/30 2.设置拦截器
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okhttpclient = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    public static NetUtil getInstance() {
        if (netUtil==null){
            synchronized (NetUtil.class){
                if (netUtil==null){
                    netUtil=new NetUtil();
                }
            }
        }

        return netUtil;
    }

    public void getJsonGet(String httpUrl,MyCallback myCallback){
        Request build = new Request.Builder()
                .get()
                .url(httpUrl)
                .build();
        Call call = okhttpclient.newCall(build);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                myCallback.onError(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    String string = response.body().string();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            myCallback.onGetJson(string);
                        }
                    });
                }else {
                    myCallback.onError(new Exception("解析失败"));
                }
            }
        });

    }
    

    public void getPhotoGet(String httpUrl, ImageView imageView){
        Glide.with(imageView).load(httpUrl)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher_round)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(80)))
                .into(imageView);
    }


    // TODO: 2019/12/30  接口回调
    public interface  MyCallback{
        void onGetJson(String json);
        void onError(Throwable throwable);

    }
}
