package com.uni2biz.android.ui.main;

public interface MainContract {

    interface View {
        void onValidateSuccess();
        void onValidationError(String msg);
    }

}
