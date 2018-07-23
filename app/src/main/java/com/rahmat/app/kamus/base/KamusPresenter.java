package com.rahmat.app.kamus.base;

import com.rahmat.app.kamus.KamusApplication;

public class KamusPresenter<V extends BaseContract.View> extends BasePresenter<V>
        implements BaseContract.Presenter{

    protected KamusApplication mApplication = KamusApplication.getInstance();

    public KamusPresenter(){

    }
}
