package com.uni2biz.android.ui.main;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.uni2biz.android.AuthorizationFragment;
import com.uni2biz.android.R;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openFragment();
    }

    @Override
    public void onValidateSuccess() {
        //success - go to profile
        System.out.println("ValidateSuccess");
    }

    @Override
    public void onValidationError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    private void openFragment() {
        //if (false) {   // if user doesn't logged in
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.container);
        if (f == null) {
            f = AuthorizationFragment.newInstance(); // start with open first login view
        }
        Log.e("TAG", f.toString());
        getSupportFragmentManager().beginTransaction().add(R.id.container, f).commit();
    }
}
