package com.maher.nowhere.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.airbnb.lottie.LottieAnimationView;
import com.chootdev.csnackbar.Duration;
import com.chootdev.csnackbar.Snackbar;
import com.chootdev.csnackbar.Type;
import com.maher.nowhere.R;
import com.maher.nowhere.Register.SignUpActivity;
import com.maher.nowhere.mainActivity.MainActivity;
import com.maher.nowhere.providers.AccountManager;

public class LoginActivity extends AppCompatActivity implements LoginView{

    private AppCompatButton btnLogin,btnLoginFb;
    private EditText etUserName,etPassword;
    private AccountManager accountManager;
    private LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUserName=(EditText) findViewById(R.id.etEmail);
        etPassword=(EditText)findViewById(R.id.etPassword);
        lottieAnimationView=(LottieAnimationView)findViewById(R.id.loadingAnimation);

        btnLogin=(AppCompatButton)findViewById(R.id.btnLogin);
        btnLoginFb=(AppCompatButton)findViewById(R.id.btnLoginFb);

        final LoginPresenter loginPresenter=new LoginPresenter(this,this);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName =etUserName.getText().toString();
                String password=etPassword.getText().toString();
                loginPresenter.login(userName,password);


            }
        });
        btnLoginFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter_from_right,R.anim.exit_to_left);
            }
        });

    }



    @Override
     public void hideSoftKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (imm.isAcceptingText()) { // verify if the soft keyboard is open
            try {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

            } catch (NullPointerException ignored) {

            }
        }
    }

    @Override
    public void usernameEmpty() {
        Snackbar.with(this, null);
        Snackbar.type(Type.ERROR);
        Snackbar.message("UserName cannot be empty");
        Snackbar.duration(Duration.SHORT);
        Snackbar.show();
    }

    @Override
    public void passwordError(int code) {
        switch (code){
            case LoginInteractor.EMPTY_PASSWORD:
                Snackbar.with(this, null);
                Snackbar.type(Type.ERROR);
                Snackbar.message("Password cannot be empty");
                Snackbar.duration(Duration.SHORT);
                Snackbar.show();
                break;
            case LoginInteractor.PASSWORD_LESS_THAN_4:
                Snackbar.with(this, null);
                Snackbar.type(Type.WARNING);
                Snackbar.message("Password cannot be less than 4 characters");
                Snackbar.duration(Duration.SHORT);
                Snackbar.show();
                break;
        }
    }


    @Override
    public void showProgress() {
        lottieAnimationView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        lottieAnimationView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void wrongCredontiel() {
        Snackbar.with(LoginActivity.this, null);
        Snackbar.type(Type.ERROR);
        Snackbar.message("Wrong credential");
        Snackbar.duration(Duration.SHORT);
        Snackbar.show();
    }

    @Override
    public void navigateToHome() {
        Intent intent=new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }

}
