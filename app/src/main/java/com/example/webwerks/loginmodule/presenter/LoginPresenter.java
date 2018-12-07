package com.example.webwerks.loginmodule.presenter;

import android.util.Log;
import com.example.webwerks.loginmodule.model.Loginmodel;
import com.example.webwerks.loginmodule.service.retrofitservice;
import com.example.webwerks.loginmodule.view.loginview;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class LoginPresenter {

    private loginview mView;
    private retrofitservice mretrofit;
    private String TAG = "LoginPresenter";

    public LoginPresenter(loginview loginview){

        this.mView = loginview;

        if (this.mretrofit == null){
            this.mretrofit = new retrofitservice();
        }
    }


    public void userLogin(String strEmail, String strPassword){

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
