package com.uni2biz.android.mvp;

import android.support.v4.app.FragmentManager;

public interface AuthorizationVP {
    interface View {
        //        void onLogin(String login, String password);
        void onValidateSuccess();
        void onValidationError(String msg);
    }

    interface Presenter {
        void validate(String login, String password);
        void openForgotPasswordFragment(FragmentManager fragmentManager);
        void openRegistrationFragment(FragmentManager fragmentManager);
    }
}
