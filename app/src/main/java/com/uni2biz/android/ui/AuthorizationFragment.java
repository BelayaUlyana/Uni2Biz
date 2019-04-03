package com.uni2biz.android.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.uni2biz.android.R;
import com.uni2biz.android.mvp.AuthorizationMVP;
import com.uni2biz.android.mvp.FragmentNavigation;
import com.uni2biz.android.presenters.AuthorizationPresenter;

public class AuthorizationFragment extends Fragment implements AuthorizationMVP.View {

    private EditText mLogin;
    private EditText mPassword;
    AuthorizationMVP.Presenter presenter;
    private FragmentNavigation mListener;

    public static AuthorizationFragment newInstance() {
        return new AuthorizationFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentNavigation) {
            mListener = (FragmentNavigation) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentNavigation");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_authorization, container, false);
        presenter = new AuthorizationPresenter();
        mLogin = view.findViewById(R.id.etUserName);
        mPassword = view.findViewById(R.id.etPassword);
        Button btnRegistration = view.findViewById(R.id.btnRegistration);
        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.openRegistrationFragment();
            }
        });

        TextView btnForgotPassword = view.findViewById(R.id.btnForgotPassword);
        btnForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.openForgotPasswordFragment();
            }
        });

        Button btnSignIn = view.findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.validate(mLogin.getText().toString(), mPassword.getText().toString());
            }
        });
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onValidateSuccess() {
        System.out.println("ValidateSuccess -> callback to activity to open another fragment/activity");
    }

    @Override
    public void onValidationError(String msg) {
        System.out.println("ValidationError -> " + msg);
    }
}
