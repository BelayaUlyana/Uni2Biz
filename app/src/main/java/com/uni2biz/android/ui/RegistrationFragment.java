package com.uni2biz.android.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uni2biz.android.R;

import de.hdodenhof.circleimageview.CircleImageView;


public class RegistrationFragment extends Fragment {

    public static RegistrationFragment newInstance() {
        return new RegistrationFragment();
    }

    CircleImageView btnAvatar;
    private static final int KEY_REQUEST_CODE_GALLERY = 666;

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
        return view;
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
