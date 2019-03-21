package com.uni2biz.android.ui.main;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.uni2biz.android.ForgotPasswordFragment;
import com.uni2biz.android.LoginFragment;
import com.uni2biz.android.R;
import com.uni2biz.android.RegistrationFragment;

public class MainActivity extends AppCompatActivity implements MainContract.View, LoginFragment.OnFragmentInteractionListener {

    private MainPresenter presenter;
    private EditText editTextUserName;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenter(this);

        //if (false) {   // if user doesn't logged in
                openFragment(LoginFragment.newInstance("", "")); // start with open first login view
        //} else {
             //openFragment(ProfileFragment.bewInstance());
        //}

        handleNavigation();
    }


    private void unit() {
        editTextUserName = (EditText) findViewById(R.id.etUserName);
        editTextPassword = (EditText) findViewById(R.id.etPassword);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        //code
    }

    private void handleNavigation() {
        Button btnRegistration = findViewById(R.id.btnRegistration);
        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(RegistrationFragment.newInstance("", ""));
            }
        });

        Button btnSignIn = findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateLogin();
            }
        });
    }


    private void validateLogin() {
        // make validation on input data and if ok send request to server
        // (actually to presenter -> presenter to model -> model back to presenter -> presenter to view)
        presenter.validate(
                ((TextView) findViewById(R.id.etUserName)).getText().toString().trim(),
                ((TextView) findViewById(R.id.etPassword)).getText().toString().trim()
        );
    }


    @Override
    public void onValidateSuccess() {
        //success - go to profile
    }


    @Override
    public void onValidationError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }


    private void openFragment(Fragment fragment) {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.container);
        if (f != null && !f.isAdded()) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.container, fragment);
            transaction.addToBackStack("");
            transaction.commit();
        }
    }
}
