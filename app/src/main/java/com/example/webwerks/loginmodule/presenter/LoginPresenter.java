package com.example.webwerks.loginmodule.presenter;


import android.text.TextUtils;

import com.example.webwerks.loginmodule.model.request.LoginRequest;
import com.example.webwerks.loginmodule.model.response.LoginResponse;
import com.example.webwerks.loginmodule.service.RestServices;
import com.example.webwerks.loginmodule.view.LoginView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginPresenter implements loginpresenter{

    private LoginView mView;
    private String TAG = "LoginPresenter";


    public LoginPresenter(LoginView loginview){
        this.mView = loginview;
    }


    @Override
    public void handleLogin(String email, String password) {

        String email_validator = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";
        Pattern p = Pattern.compile(email_validator);
        Matcher m = p.matcher(email);

        if (email.equals("") || TextUtils.isEmpty(email)
                || password.equals("") || TextUtils.isEmpty(password)) {

            mView.showValidationError();

        } else if (!m.find()) {
            mView.emailValidation();

        } else if (TextUtils.isEmpty(password)) {
            mView.passwordValidation();
        }else {
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setEmail(email);
            loginRequest.setPassword(password);
            LoginResponse loginResponse = RestServices.getInstance().authenticUser(loginRequest);
            if (loginResponse.getStatus() != -1){
                mView.loginView(loginResponse);
            }
            else {
                mView.errorView(loginResponse.getMessage());
            }
        }

    }

    public void onStop(){
        mView = null;
    }

}
