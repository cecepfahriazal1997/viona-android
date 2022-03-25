package com.viona.mobile.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;
import com.makeramen.roundedimageview.RoundedImageView;
import com.viona.mobile.R;

import mehdi.sakout.fancybuttons.FancyButton;

public class Register extends Master implements View.OnClickListener {
    private FancyButton back, register, login;
    private ImageButton filePicker;
    private final int FILE_PICKER = 0;
    private RoundedImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        findView();
        init();
    }

    private void findView() {
        title = findViewById(R.id.title);
        back = findViewById(R.id.back);
        filePicker = findViewById(R.id.file_picker);
        image = findViewById(R.id.image);
        register = findViewById(R.id.submit);
        login = findViewById(R.id.login);
    }

    private void init() {
        back.setOnClickListener(this::onClick);
        filePicker.setOnClickListener(this::onClick);
        register.setOnClickListener(this::onClick);
        login.setOnClickListener(this::onClick);
    }

    private void setData() {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
            case R.id.login:
                helper.startIntent(Login.class, true, null);
                break;
            case R.id.file_picker:
                helper.openFileChooser(this, FILE_PICKER, permissionImage);
                break;
            case R.id.submit:
                helper.startIntent(Password.class, false, null);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            Image tempImage = ImagePicker.getFirstImageOrNull(data);
            if (tempImage != null) {
                String path = tempImage.getPath();
//                files.add(new FilePart("image", new File(path)));
                Bitmap bitmap = helper.loadImageFromStorage(path);
                image.setImageBitmap(bitmap);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
