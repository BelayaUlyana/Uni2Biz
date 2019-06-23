package org.uni2biz;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.uni2biz.API.AuthorizationRequest;
import org.uni2biz.API.AuthorizationResponse;
import org.uni2biz.API.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public class AuthorizationFragment extends Fragment {

    private EditText mLogin, mPassword;
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
                validate(mLogin.getText().toString(), mPassword.getText().toString());
            }
        });
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    private void validate(String login, String password) {
        if (login.length() > 4 && !password.isEmpty() && password.length() > 4) {
            onValidateSuccess();
        } else {
            onValidationError(getString(R.string.fillAllFields));
        }
    }

    public void onValidateSuccess() {
        System.out.println("ValidateSuccess -> callback to activity to open another fragment/activity");

        Call<AuthorizationResponse> call = RetrofitClient.getApiService().auth(
                new AuthorizationRequest(mLogin.getText().toString(), Helpers.md5(mPassword.getText().toString())));
        call.enqueue(new Callback<AuthorizationResponse>() {
            @Override
            public void onResponse(Call<AuthorizationResponse> call, Response<AuthorizationResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(),"Authorization Successful", Toast.LENGTH_LONG).show();
                } else {
                    String msg = response.body().getMessage();
                    String fields = response.body().getFields();
                    int code = response.body().getCode();
                    String res = response.body().getResponse();
                    Log.e("MSG",msg + "***" + fields + "***" + code + "***" + res);
                    Toast.makeText(getContext(), msg + " " + fields + " " + code + " " + res, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AuthorizationResponse> call, Throwable throwable) {
                if (throwable instanceof HttpException) {
                    HttpException e = (HttpException) throwable;
                }
            }
        });
    }

    public void onValidationError(String msg) {
        Toast.makeText(this.getActivity(), msg, Toast.LENGTH_LONG).show();
    }
}
