package com.example.webwerks.loginmodule.view;


import com.example.webwerks.loginmodule.model.response.LoginResponse;

public interface LoginView {

  void loginView(LoginResponse loginmodel);

  void errorView(String s);

  void showValidationError();

  void emailValidation();

  void passwordValidation();

}

