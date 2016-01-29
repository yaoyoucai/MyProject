package com.yaohuan.base;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

/**
 * Created by yh on 2016/1/28.
 * activity基类
 */
public abstract class BaseActivity extends ActionBarActivity implements View.OnClickListener {
    /**
     * 注意必须调用一个参数的onCreate方法，否则会出错
     * @param savedInstanceState
     */
   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       initView();
       initData();
       initListener();
   }

    public abstract void initView();

    public abstract void initListener();

    public abstract void initData();

    public abstract void processClick(View v);
    @Override
    public void onClick(View v) {
        processClick(v);
    }
}
