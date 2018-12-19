package com.example.webwerks.loginmodule.service;


import com.example.webwerks.loginmodule.model.request.LoginRequest;
import com.example.webwerks.loginmodule.model.response.BaseResponse;
import com.example.webwerks.loginmodule.model.response.LoginResponse;

import io.reactivex.Single;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RestServices {

    ApiServices apiServices;
    private static RestServices instance;



    private RestServices(){
        apiServices = ApiClient.getInstance().getApiServices();
    }

    public static RestServices getInstance() {

        if (instance == null) {
            instance = new RestServices();
        }

        return instance;
    }


    public LoginResponse authenticUser(LoginRequest request){

        Single<LoginResponse> loginObservable = apiServices.userLogin(request.getEmail(),request.getPassword());
        LoginResponse response = attachCommonRxProperiesAndExecute(loginObservable,LoginResponse.class);

        return response;
    }

    private <E> E attachCommonRxProperiesAndExecute(Single<E> observable, final Class errClass) {
        return observable.subscribeOn(Schedulers.io())
                .onErrorReturn(new Function<Throwable, E>() {
                    @Override
                    public E apply(Throwable throwable) throws Exception {

                        BaseResponse baseResponse = (BaseResponse) errClass.newInstance();
                        baseResponse.setStatus(-1);
                        baseResponse.setMessage(throwable.getMessage());
                        return (E) baseResponse;
                    }
                })
                .blockingGet();
    }
}
