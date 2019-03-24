package com.uni2biz.android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class LoginFragment extends Fragment {

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
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
        // make validation on input data and if ok send request to server
        // (actually to presenter -> presenter to model -> model back to presenter -> presenter to view)
     /*   presenter.validate(
                ((TextView) findViewById(R.id.etUserName)).getText().toString().trim(),
                ((TextView) findViewById(R.id.etPassword)).getText().toString().trim()
        );*/
    }


}
