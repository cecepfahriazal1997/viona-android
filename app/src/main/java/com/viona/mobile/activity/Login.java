package com.viona.mobile.activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.viona.mobile.R;

import mehdi.sakout.fancybuttons.FancyButton;

public class Login extends com.viona.mobile.activity.Master implements View.OnClickListener {
    private FancyButton btnSumit, btnRegister;
    private EditText username, password;
    private TextView resetPassword;
    private String role;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnRegister     = findViewById(R.id.register);
        btnSumit        = findViewById(R.id.submit);
        username        = findViewById(R.id.username);
        password        = findViewById(R.id.password);
        resetPassword   = findViewById(R.id.reset_password);

        init();
    }

    private void init() {
        role = getIntent().getStringExtra("role");
        btnRegister.setOnClickListener(this::onClick);
        btnSumit.setOnClickListener(this::onClick);
        resetPassword.setOnClickListener(this::onClick);
        resetPassword.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
    }

    private void login() {
        try {
//            param.clear();
//            param.put("username", username.getText().toString());
//            param.put("password", password.getText().toString());
//            param.put("role", role);
//            param.put("tokenFirebase", helper.getSession("token_firebase"));
//            service.apiService(service.login, param, null, true, new Service.hashMapListener() {
//                @Override
//                public String getHashMap(Map<String, String> hashMap) {
//                    try {
//                        if (hashMap.get("status").equals("true")) {
//                            helper.saveSession("role", getIntent().getStringExtra("role"));
//                            JSONObject profile = new JSONObject(hashMap.get("data"));
//                            helper.saveSessionBatch(profile);
//                            helper.startIntent(Dashboard.class, false, null);
//                            finish();
//                        } else {
//                            helper.showToast(hashMap.get("message"), 0);
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    return null;
//                }
//            });
            helper.startIntent(Dashboard.class, false, null);
            finish();
        } catch (Exception e) {
            e.printStackTrace();
            helper.showToast(e.getLocalizedMessage(), 1);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit:
                if (username.getText().toString().isEmpty()) {
                    username.setError("Enter username ...");
                    username.requestFocus();
                    username.setFocusable(true);
                    username.setFocusableInTouchMode(true);
                } else if (password.getText().toString().isEmpty()) {
                    password.setError("Enter password ...");
                    password.requestFocus();
                    password.setFocusable(true);
                    password.setFocusableInTouchMode(true);
                } else {
                    login();
                }
                break;
            case R.id.register:
                param.clear();
                param.put("role", role);
                helper.startIntent(Register.class, false, param);
                break;
        }
    }
}