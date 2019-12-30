package com.bwie.week2.view.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bwie.week2.R;
import com.bwie.week2.base.BaseActivity;
import com.bwie.week2.model.bean.Bean;
import com.bwie.week2.presenter.HomePresenter;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondActivity extends BaseActivity<HomePresenter> {


    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.bt_ma)
    Button btMa;
    @BindView(R.id.bt_one)
    Button btOne;
    @BindView(R.id.bt_two)
    Button btTwo;
    @BindView(R.id.img_an)
    ImageView imgAn;

    @Override
    protected int layoutId() {
        return R.layout.activity_second;
    }

    @Override
    protected void initData() {
        // TODO: 2019/12/30  初始化数据
        CodeUtils.init(this);

        imgAn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                CodeUtils.analyzeByImageView(imgAn, new CodeUtils.AnalyzeCallback() {
                    @Override
                    public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                        Toast.makeText(SecondActivity.this, result, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnalyzeFailed() {
                        Toast.makeText(SecondActivity.this, "解析失败", Toast.LENGTH_SHORT).show();
                    }
                });

                return true;
            }
        });

    }

    @Override
    protected void initView() {

    }

    @Override
    protected HomePresenter providePresenter() {
        return null;
    }


    @OnClick({R.id.bt_ma, R.id.bt_one, R.id.bt_two})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_ma:
                String name = etName.getText().toString().trim();
                if (!TextUtils.isEmpty(name)){
                    Bitmap image = CodeUtils.createImage(name, 400, 400, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round));
                    imgAn.setImageBitmap(image);
                }
                break;
            case R.id.bt_one:
                EventBus.getDefault().post("张奕漫");

                break;
            case R.id.bt_two:
                EventBus.getDefault().post(new Bean("张奕漫",18));

                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onGetString(String s){
        Toast.makeText(this, "大哥的名字是:"+s, Toast.LENGTH_SHORT).show();
    }

    @Subscribe
    public void onGetBean(Bean bean){
        Toast.makeText(this, bean.getName()+bean.getAge()+"", Toast.LENGTH_SHORT).show();
    }

}
