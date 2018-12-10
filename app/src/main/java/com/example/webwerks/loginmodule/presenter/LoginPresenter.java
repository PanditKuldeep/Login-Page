package com.example.webwerks.loginmodule.presenter;


import android.text.TextUtils;
import android.util.Log;
import com.example.webwerks.loginmodule.model.Loginmodel;
import com.example.webwerks.loginmodule.service.retrofitservice;
import com.example.webwerks.loginmodule.view.loginview;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class LoginPresenter implements loginpresenter{

    private loginview mView;
    private retrofitservice mretrofit;
    private String TAG = "LoginPresenter";


    public LoginPresenter(loginview loginview){

        this.mView = loginview;

        if (this.mretrofit == null){
            this.mretrofit = new retrofitservice();
        }
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
            userLogin(email,password);
        }

    }

    private void userLogin(String strEmail, String strPassword){

        mretrofit.userLogin().userLogin(strEmail,strPassword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Loginmodel>() {
                               @Override
                               public void onSuccess(Loginmodel model) {

                                   if (model!=null){
                                       mView.loginView(model);
                                       Log.d(TAG, "not null");
                                   }
                               }

                               @Override
                               public void onError(Throwable e) {
                                   Log.d(TAG, "onError");
                                   mView.errorView("401");
                               }
                           });

    }


    public void onStop(){
        mView = null;
    }

}
