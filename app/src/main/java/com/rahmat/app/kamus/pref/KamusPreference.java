package com.rahmat.app.kamus.pref;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.rahmat.app.kamus.R;

public class KamusPreference {

    SharedPreferences sharedPreferences;
    private final static String KEY = "first_run";
    Context context;

    public KamusPreference(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.context = context;
    }

    public void setFirstRun(Boolean isFirstRun) {
        sharedPreferences.edit().putBoolean(KEY, isFirstRun).apply();
    }

    public Boolean getFirstRun() {
        return sharedPreferences.getBoolean(KEY, true);
    }
}
