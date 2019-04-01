package com.uni2biz.android.presenters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.uni2biz.android.R;
import com.uni2biz.android.mvp.MainVP;
import com.uni2biz.android.ui.AuthorizationFragment;

public class MainPresenter implements MainVP {
    @Override
    public void openFirstFragment(FragmentManager fragmentManager) {
        Fragment f = fragmentManager.findFragmentById(R.id.container);
        if (f == null) {
            f = AuthorizationFragment.newInstance();
        }
        fragmentManager.beginTransaction().add(R.id.container, f).commit();
    }

}
