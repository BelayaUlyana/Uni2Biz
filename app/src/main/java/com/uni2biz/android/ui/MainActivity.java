package com.uni2biz.android.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.uni2biz.android.R;
import com.uni2biz.android.mvp.FragmentNavigation;
import com.uni2biz.android.mvp.MainMVP;
import com.uni2biz.android.presenters.MainPresenter;

public class MainActivity extends AppCompatActivity implements FragmentNavigation {

    FragmentNavigation navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        MainMVP callback = new MainPresenter(fragmentManager);
        callback.openFirstFragment();
        navigation = new MainPresenter(fragmentManager);
    }

    @Override
    public void openRegistrationFragment() {
        navigation.openRegistrationFragment();
    }

    @Override
    public void openForgotPasswordFragment() {
        navigation.openForgotPasswordFragment();
    }
}
