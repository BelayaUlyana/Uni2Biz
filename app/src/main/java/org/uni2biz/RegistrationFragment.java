package org.uni2biz;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.uni2biz.API.RegistrationRequest;
import org.uni2biz.API.RegistrationResponse;
import org.uni2biz.API.RetrofitClient;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;


public class RegistrationFragment extends Fragment {

    public static RegistrationFragment newInstance() {
        return new RegistrationFragment();
    }

    CircleImageView btnAvatar;
    private static final int KEY_REQUEST_CODE_GALLERY = 666;
    private EditText mUserName, mFirstName, mLastName, mPhoneNumber, mEmail, mCity, mPassword;
    private Spinner genderSpinner, roleSpinner;
    private CheckBox checkBox;
    private TextInputLayout inputLayoutUserName, inputLayoutFirstName, inputLayoutLastName, inputLayoutPhone, inputLayoutEmail, inputLayoutUniversity, inputLayoutPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        btnAvatar = view.findViewById(R.id.btnPhoto);
        btnAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
        mUserName = view.findViewById(R.id.userName);
        genderSpinner = view.findViewById(R.id.titleSpinner);
        mFirstName = view.findViewById(R.id.firstName);
        mLastName = view.findViewById(R.id.lastName);
        mPhoneNumber = view.findViewById(R.id.phone);
        mEmail = view.findViewById(R.id.email);
        mCity = view.findViewById(R.id.university);
        roleSpinner = view.findViewById(R.id.role);
        mPassword = view.findViewById(R.id.password);
        checkBox = view.findViewById(R.id.checkBox);
        inputLayoutUserName = view.findViewById(R.id.inputLayoutUserName);
        inputLayoutFirstName = view.findViewById(R.id.inputLayoutFirstName);
        inputLayoutLastName = view.findViewById(R.id.inputLayoutLastName);
        inputLayoutPhone = view.findViewById(R.id.inputLayoutPhone);
        inputLayoutEmail = view.findViewById(R.id.inputLayoutEmail);
        inputLayoutUniversity = view.findViewById(R.id.inputLayoutUniversity);
        inputLayoutPassword = view.findViewById(R.id.inputLayoutPassword);
        checkBox = view.findViewById(R.id.checkBox);

        Button btnRegister = view.findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    onValidateSuccess();
                } else {
                    onValidationError("Fill all fields please!");
                }
            }
        });
        return view;
    }

    private void onValidationError(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }

    private void onValidateSuccess() {
//        Toast.makeText(getContext(), R.string.ValidateSuccess, Toast.LENGTH_SHORT).show();
        if (validate()) {
            Call<RegistrationResponse> call = RetrofitClient.getApiService().register(
                    new RegistrationRequest(mUserName.getText().toString(),
                            Helpers.md5(mPassword.getText().toString()),
                            mFirstName.getText().toString(),
                            mLastName.getText().toString(),
                            mPhoneNumber.getText().toString(),
                            mCity.getText().toString(),
                            mEmail.getText().toString(),
                            getLanguage2(),
                            genderSpinner.getGravity(),
                            getSubscribe(),
                            roleSpinner.getGravity()));
            call.enqueue(new Callback<RegistrationResponse>() {
                @Override
                public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                    Log.e("API", "response " + response);
                    Log.e("API", "response body " + response.body());
                    if (response.isSuccessful()) {
                        String msg = response.body().getResult();
                        Log.e("API", "result = " + msg);
                    } else {
                        Toast.makeText(getContext(), response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                        System.out.println(response.errorBody());
                    }
                }

                @Override
                public void onFailure(Call<RegistrationResponse> call, Throwable throwable) {
                    if (throwable instanceof HttpException) {
                        HttpException e = (HttpException) throwable;
                        System.out.println("code = " + e.code());
                    }
                }
            });
        } else {
            onValidationError(getString(R.string.fillAllFields));
        }
    }


    private boolean validate() {
        boolean flag = true;

        if (!mUserName.getText().toString().matches("^[a-zA-Z][a-zA-Z0-9-_\\.]{4,20}$")) {
            inputLayoutUserName.setError(getString(R.string.errorUserName));
            flag = false;
        } else {
            inputLayoutUserName.setErrorEnabled(false);
        }
        if (genderSpinner.getSelectedItemPosition() == 0) {
            TextView errorText = (TextView) genderSpinner.getSelectedView();
            errorText.setError(getString(R.string.errorSpinner));
            flag = false;
        }
        if (mFirstName.getText().length() == 0) {
            inputLayoutFirstName.setError(getString(R.string.errorFirstName));
            flag = false;
        } else {
            inputLayoutFirstName.setErrorEnabled(false);
        }
        if (mLastName.getText().length() == 0) {
            inputLayoutLastName.setError(getString(R.string.errorLastName));
            flag = false;
        } else {
            inputLayoutLastName.setErrorEnabled(false);
        }
        if (!mPhoneNumber.getText().toString().matches("\\+3[1-9][0-9]{10}")) {
            inputLayoutPhone.setError(getString(R.string.errorPhone));
            flag = false;
        } else {
            inputLayoutPhone.setErrorEnabled(false);
        }
        if (!mEmail.getText().toString().matches("^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$")) {
            inputLayoutEmail.setError(getString(R.string.emailValide));
            flag = false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }
        if (mCity.getText().length() == 0) {
            inputLayoutUniversity.setError(getString(R.string.errorUniversity));
            flag = false;
        } else {
            inputLayoutUniversity.setErrorEnabled(false);
        }
        if (roleSpinner.getSelectedItemPosition() == 0) {
            TextView errorText = (TextView) roleSpinner.getSelectedView();
            errorText.setError(getString(R.string.errorSpinner));
            flag = false;
        }
        if (!mPassword.getText().toString().matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{5,}")) {
            inputLayoutPassword.setError(getString(R.string.pswValide));
            flag = false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }
        return flag;
    }

    public String getLocation() {

        String countryName = "";

        Geocoder geocoder = new Geocoder(this.getContext(), Locale.getDefault());
        List<Address> addresses;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this.getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION,
                    android.Manifest.permission.ACCESS_FINE_LOCATION}, 1000);
        } else {
            LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//            country = getLocation(this.getContext(), location.getLatitude(), location.getLongitude());
            try {
                addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 10);
                if (addresses.size() > 0) {
                    for (Address adr : addresses) {
                        if (adr.getLocality() != null && adr.getLocality().length() > 0) {
                            countryName = adr.getCountryName();
                            break;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Toast.makeText(getContext(), countryName, Toast.LENGTH_SHORT).show();
        }
        return countryName;
    }

    private int getLanguage2(){
        switch (getLocation()){
            case "England": {
                return 1;
            }
            case "Россия": {
                return 2;
            }
            case "France": {
                return 3;
            }
            case "Украина": {
                Toast.makeText(getContext(), "Язык укр ", Toast.LENGTH_SHORT).show();
                return 4;
            }
            case "پاکِستان": {
                return 5;
            }
            default:
                return 1;
        }
    }
    private int getLanguage() {
//        Log.e(Locale.getDefault().getDisplayLanguage());
        switch (Locale.getDefault().getDisplayLanguage()) {
            case "English": {
                return 1;
            }
            case "русский": {
                return 2;
            }
            case "français": {
                return 3;
            }
            case "українська": {
                return 4;
            }
            case "اردو": {
                return 5;
            }
            default:
                return 1;
        }
    }

    private int getSubscribe() {
        if (checkBox.isChecked()) {
            return 1;
        } else return 0;
    }


    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, getString(R.string.СhoosePhoto)), KEY_REQUEST_CODE_GALLERY);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == KEY_REQUEST_CODE_GALLERY && data != null) {
            Uri photoUri = data.getData();
            btnAvatar.setImageURI(photoUri);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


}
