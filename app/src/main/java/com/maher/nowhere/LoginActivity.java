package com.maher.nowhere;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Button;

import com.maher.nowhere.mainActivity.MainActivity;
import com.maher.nowhere.model.User;

public class LoginActivity extends AppCompatActivity {

    private AppCompatButton btnLogin,btnLoginFb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin=(AppCompatButton)findViewById(R.id.btnLogin);
        btnLoginFb=(AppCompatButton)findViewById(R.id.btnLoginFb);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                //User u=new User();
              //  intent.putExtra("nom",u);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
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
}
