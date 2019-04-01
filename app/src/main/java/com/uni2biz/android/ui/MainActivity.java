package com.uni2biz.android.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.uni2biz.android.R;
import com.uni2biz.android.mvp.MainVP;
import com.uni2biz.android.presenters.MainPresenter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        MainVP callback = new MainPresenter();
        callback.openFirstFragment(fragmentManager);
    }
}