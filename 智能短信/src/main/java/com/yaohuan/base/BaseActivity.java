package com.yaohuan.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

/**
 * Created by yh on 2016/1/28.
 */
public abstract class BaseActivity extends ActionBarActivity implements View.OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    public abstract void initView();

    public abstract void initListener();

    public abstract void initData();

    public abstract void processClick();
    @Override
    public void onClick(View v) {

    }
}
