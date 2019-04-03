package com.uni2biz.android.presenters;


import com.uni2biz.android.mvp.AuthorizationMVP;
import com.uni2biz.android.ui.AuthorizationFragment;

public class AuthorizationPresenter implements AuthorizationMVP.Presenter {

    private AuthorizationMVP.View listener = new AuthorizationFragment();

    @Override
    public void validate(String login, String password) {
        if (login.length() > 5 && !password.isEmpty() && password.length() > 5) {
            listener.onValidateSuccess();
            System.out.println("ValidateSuccess");
        } else {
            listener.onValidationError("Fill all fields please!");
        }
    }
}
