package com.uni2biz.android.mvp;


public interface AuthorizationMVP {
    interface View {
        void onValidateSuccess();
        void onValidationError(String msg);
    }

    interface Presenter {
        void validate(String login, String password);
    }
}
