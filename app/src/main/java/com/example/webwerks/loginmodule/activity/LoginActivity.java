package com.example.webwerks.loginmodule.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.webwerks.loginmodule.R;
import com.example.webwerks.loginmodule.model.Loginmodel;
import com.example.webwerks.loginmodule.presenter.LoginPresenter;
import com.example.webwerks.loginmodule.view.loginview;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,loginview {
    Context context;
    EditText etEmail,etPassword;
    Button btnLogin;
    String strEmail,strPassword;
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
                validation();
                break;
        }
    }

    private void validation() {

        strEmail = etEmail.getText().toString();
        strPassword = etPassword.getText().toString();

        String email_validator = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";
        Pattern p = Pattern.compile(email_validator);
        Matcher m = p.matcher(strEmail);


        if (etEmail.equals("") || etEmail.length() == 0
                || etPassword.equals("") || etPassword.length() == 0){

            Toast.makeText(context, "All fields are required.", Toast.LENGTH_LONG).show();
        }else if (!m.find()){
            Toast.makeText(context, "Your Email Id is Invalid.", Toast.LENGTH_LONG).show();
        }else if (etPassword.length()==0){
            Toast.makeText(context, "Enter your Password.", Toast.LENGTH_LONG).show();
        }else {
            mPresenter.userLogin(strEmail,strPassword);
        }
    }



    public void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    public void loginView(Loginmodel loginmodel) {

        Toast.makeText(this,loginmodel.user_msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void errorView(String s) {

        Toast.makeText(this,"Email or password is wrong. try again",Toast.LENGTH_LONG).show();
    }
}
