package com.uni2biz.android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.uni2biz.android.ui.main.MainPresenter;

public class AuthorizationFragment extends Fragment {

    private EditText mLogin;
    private EditText mPassword;
    MainPresenter presenter;

    public static AuthorizationFragment newInstance() {
        return new AuthorizationFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_authorization, container, false);
        mLogin = view.findViewById(R.id.etUserName);
        mPassword= view.findViewById(R.id.etPassword);

        Button btnRegistration = view.findViewById(R.id.btnRegistration);
        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegistrationFragment();
            }
        });

        TextView btnForgotPassword = view.findViewById(R.id.btnForgotPassword);
        btnForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openForgotPasswordFragment();
            }
        });

        Button btnSignIn = view.findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateLogin();
            }
        });
        return view;
    }

    private void openForgotPasswordFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, ForgotPasswordFragment.newInstance()).commit();
    }

    private void openRegistrationFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, RegistrationFragment.newInstance()).commit();
    }


    private void validateLogin() {
        presenter.validate(
                (mLogin.getText().toString().trim()),
                (mPassword.getText().toString().trim())
        );
    }

}
