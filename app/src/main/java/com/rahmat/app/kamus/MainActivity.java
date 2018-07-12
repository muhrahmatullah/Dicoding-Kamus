package com.rahmat.app.kamus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mancj.materialsearchbar.MaterialSearchBar;

public class MainActivity extends AppCompatActivity implements MaterialSearchBar.OnSearchActionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onSearchStateChanged(boolean enabled) {

    }

    @Override
    public void onSearchConfirmed(CharSequence text) {

    }

    @Override
    public void onButtonClicked(int buttonCode) {

    }
}
