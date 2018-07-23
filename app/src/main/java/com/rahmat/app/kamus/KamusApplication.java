package com.rahmat.app.kamus;

public class KamusApplication extends android.app.Application{

    private static KamusApplication mKamusApplication;

    public static KamusApplication getInstance(){
        return  mKamusApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

}
