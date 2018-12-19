package com.example.webwerks.loginmodule.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.webwerks.loginmodule.R;
import com.example.webwerks.loginmodule.model.response.LoginResponse;
import com.example.webwerks.loginmodule.presenter.LoginPresenter;
import com.example.webwerks.loginmodule.view.LoginView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,LoginView {
    Context context;
    EditText etEmail,etPassword;
    Button btnLogin;
    LoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

    }

    private void initView() {

        mPresenter = new LoginPresenter(this);

        context = this;
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnLogin:
                 mPresenter.handleLogin(etEmail.getText().toString(),etPassword.getText().toString());
                break;
        }
    }


    public void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    public void showValidationError() {
        Toast.makeText(this,"All field required",Toast.LENGTH_LONG).show();
    }

    @Override
    public void emailValidation() {
        Toast.makeText(context, "Your Email Id is Invalid.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void passwordValidation() {
        Toast.makeText(context, "Enter your Password.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void loginView(LoginResponse loginmodel) {

        Toast.makeText(context,loginmodel.user_msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void errorView(String s) {

        Toast.makeText(context,"Email or password is wrong. try again",Toast.LENGTH_LONG).show();
    }

}
