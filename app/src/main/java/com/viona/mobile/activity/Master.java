package com.viona.mobile.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.viona.mobile.api.Service;
import com.viona.mobile.helper.GeneralHelper;

import java.util.HashMap;
import java.util.Map;

public class Master extends AppCompatActivity {
    protected GeneralHelper helper;
    protected Service service;
    protected ProgressDialog pDialog;
    protected Map<String, String> param = new HashMap<>();
    protected ImageButton back;
    protected TextView title;
    protected String roleStudent = "santri", roleTeacher = "ustadz";
    String[] permissionImage = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pDialog = new ProgressDialog(this);
        helper = new GeneralHelper(this);
        service = new Service(this, pDialog);
        helper.setupProgressDialog(pDialog, "Loading data ...");
    }
}