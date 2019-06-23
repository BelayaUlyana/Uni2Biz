package org.uni2biz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements FragmentNavigation {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openFirstFragment();
    }

    public void openFirstFragment() {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.container);
        if (f == null) {
            f = AuthorizationFragment.newInstance();
        }
        getSupportFragmentManager().beginTransaction().add(R.id.container, f).commit();
    }


    @Override
    public void openRegistrationFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, RegistrationFragment.newInstance()).addToBackStack(null).commit();
    }

    @Override
    public void openForgotPasswordFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, ForgotPasswordFragment.newInstance()).addToBackStack(null).commit();
    }
}