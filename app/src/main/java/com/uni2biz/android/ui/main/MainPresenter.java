package com.uni2biz.android.ui.main;

import com.uni2biz.android.data.model.LoginModel;

public class MainPresenter {
    private MainContract.View listener;
    private LoginModel loginModel;

    public MainPresenter(MainContract.View mainContract) {
        listener = mainContract;
        loginModel = new LoginModel();
    }

    public void validate(String login, String password) {
        // validation:
        if (login.length() > 5 && !password.isEmpty() && password.length() > 5) {
            listener.onValidateSuccess();
            System.out.println("ValidateSuccess");
        } else {
            listener.onValidationError("fill all fields please!");
        }
        // request to model
        loginModel.login(login, password);
        // can be wrap with <Call> retrofit
    }
}
