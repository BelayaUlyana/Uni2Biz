package com.uni2biz.android.presenters;



import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;


import com.uni2biz.android.R;
import com.uni2biz.android.mvp.FragmentNavigation;
import com.uni2biz.android.mvp.MainMVP;
import com.uni2biz.android.ui.AuthorizationFragment;
import com.uni2biz.android.ui.ForgotPasswordFragment;
import com.uni2biz.android.ui.RegistrationFragment;

public class MainPresenter implements MainMVP, FragmentNavigation {
    private FragmentManager fragmentManager;

    public MainPresenter(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void openFirstFragment() {
        Fragment f = fragmentManager.findFragmentById(R.id.container);
        if (f == null) {
            f = AuthorizationFragment.newInstance();
        }
        fragmentManager.beginTransaction().add(R.id.container, f).commit();
    }

    @Override
    public void openRegistrationFragment() {
        fragmentManager.beginTransaction().replace(R.id.container, RegistrationFragment.newInstance()).addToBackStack(null).commit();
    }

    @Override
    public void openForgotPasswordFragment() {
        fragmentManager.beginTransaction().replace(R.id.container, ForgotPasswordFragment.newInstance()).addToBackStack(null).commit();
    }

}
