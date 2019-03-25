package com.uni2biz.android;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.hdodenhof.circleimageview.CircleImageView;


public class RegistrationFragment extends Fragment {


    public static RegistrationFragment newInstance() {
        return new RegistrationFragment();
    }

    CircleImageView btnAvatar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnAvatar = view.findViewById(R.id.btnPhoto);
        btnAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
    }

    private static final int KEY_REQUEST_CODE_GALLERY = 666;

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Эй ты выбери фотку себе"), KEY_REQUEST_CODE_GALLERY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == KEY_REQUEST_CODE_GALLERY && data != null) {
            // достать данные из галлереи и вставить в imageview
            Uri photoUri = data.getData();
            btnAvatar.setImageURI(photoUri);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
