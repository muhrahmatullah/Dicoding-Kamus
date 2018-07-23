package com.rahmat.app.kamus.base;

import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity<P extends KamusPresenter> extends AppCompatActivity
        implements BaseContract.View{

    protected final static String LOADING_DIALOG_TAG = "loading";

    private boolean mActivityIsInBackground;

    private boolean mActivityStopped;

    protected P mPresenter;

    @Override
    public void setToast(String message) {

    }
}
