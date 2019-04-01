package com.uni2biz.android.presenters;

import android.support.v4.app.FragmentManager;

import com.uni2biz.android.R;
import com.uni2biz.android.mvp.AuthorizationVP;
import com.uni2biz.android.ui.AuthorizationFragment;
import com.uni2biz.android.ui.ForgotPasswordFragment;
import com.uni2biz.android.ui.RegistrationFragment;

public class AuthorizationPresenter implements AuthorizationVP.Presenter {

    private AuthorizationVP.View listener = new AuthorizationFragment();


    @Override
    public void validate(String login, String password) {
        if (login.length() > 5 && !password.isEmpty() && password.length() > 5) {
            listener.onValidateSuccess();
            System.out.println("ValidateSuccess");
        } else {
            listener.onValidationError("Fill all fields please!");
        }
    }


    public void openForgotPasswordFragment(FragmentManager fragmentManager) {
        fragmentManager.beginTransaction().replace(R.id.container, ForgotPasswordFragment.newInstance()).addToBackStack(null).commit();
    }

    public void openRegistrationFragment(FragmentManager fragmentManager) {
        fragmentManager.beginTransaction().replace(R.id.container, RegistrationFragment.newInstance()).addToBackStack(null).commit();
    }

}
