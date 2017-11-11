package com.maher.nowhere;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.airbnb.lottie.LottieAnimationView;
import com.chootdev.csnackbar.Duration;
import com.chootdev.csnackbar.Snackbar;
import com.chootdev.csnackbar.Type;
import com.maher.nowhere.callbaks.VolleyCallback;
import com.maher.nowhere.helpers.JsonToObjectParser;
import com.maher.nowhere.mainActivity.MainActivity;
import com.maher.nowhere.model.User;
import com.maher.nowhere.providers.AccountManager;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private AppCompatButton btnLogin,btnLoginFb;
    private EditText etUserName,etPassword;
    private AccountManager accountManager;
    private LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        accountManager=new AccountManager(this);
        etUserName=(EditText) findViewById(R.id.etEmail);
        etPassword=(EditText)findViewById(R.id.etPassword);
        lottieAnimationView=(LottieAnimationView)findViewById(R.id.loadingAnimation);

        btnLogin=(AppCompatButton)findViewById(R.id.btnLogin);
        btnLoginFb=(AppCompatButton)findViewById(R.id.btnLoginFb);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideSoftKeyBoard();
                String userName =etUserName.getText().toString();
                String password=etPassword.getText().toString();
               // if(checkInput(userName,password))
              //  signIn(userName,password);

                Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);

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

    private void signIn(String userName,String password){
        lottieAnimationView.setVisibility(View.VISIBLE);
        accountManager.signin(userName, password, new VolleyCallback() {
            @Override
            public void onSuccess(Object response) {
                lottieAnimationView.setVisibility(View.INVISIBLE);
                User user= new JsonToObjectParser().parseUser((JSONObject)response);
                User.setCurrentUser(user,LoginActivity.this);
                Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }

            @Override
            public void onError(Object error) {
                lottieAnimationView.setVisibility(View.INVISIBLE);
                Snackbar.with(LoginActivity.this, null)
                        .type(Type.ERROR)
                        .message("Wrong credential")
                        .duration(Duration.SHORT)
                        .show();

            }
        });
    }
    private boolean checkInput(String userName,String password){
        if (userName.isEmpty()) {
          //  etUserName.setBackground(getResources().getDrawable(R.drawable.text_box_error));
            Snackbar.with(this, null)
                    .type(Type.ERROR)
                    .message("UserName cannot be empty")
                    .duration(Duration.SHORT)
                    .show();
            return false;
        }
        if (password.isEmpty()) {
          //  password.setBackground(getResources().getDrawable(R.drawable.text_box_error));
            Snackbar.with(this, null)
                    .type(Type.ERROR)
                    .message("Password cannot be empty")
                    .duration(Duration.SHORT)
                    .show();
            return false;
        }
        if (password.length() < 4) {
         //   password.setBackground(getResources().getDrawable(R.drawable.text_box_warning));
            Snackbar.with(this, null)
                    .type(Type.WARNING)
                    .message("Password cannot be less than 4 characters")
                    .duration(Duration.SHORT)
                    .show();
            return false;
        }
        return true;


    }

    private void hideSoftKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        if (imm.isAcceptingText()) { // verify if the soft keyboard is open
            try {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

            } catch (NullPointerException ignored) {

            }
        }
    }
}
