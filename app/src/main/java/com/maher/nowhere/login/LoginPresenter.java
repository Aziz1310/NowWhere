package com.maher.nowhere.login;

import android.content.Context;
import android.widget.EditText;

/**
 * Created by maher on 14/11/2017.
 */

public class LoginPresenter implements LoginInteractor.OnLoginFinishedListener {

    private LoginView loginView;
    private LoginInteractor loginInteractor;
    private Context mcContext;

    public LoginPresenter(LoginView loginView, Context mcContext) {
        this.loginView = loginView;
        this.mcContext = mcContext;
        loginInteractor = new LoginInteractor();
    }


    void login(String username, String password) {

        if (loginInteractor.checkInput(username, password, this)) {
            loginView.hideSoftKeyBoard();
            loginView.showProgress();
            loginInteractor.login(username, password, this,mcContext);
        }

    }

    @Override
    public void onUsernameError() {
        loginView.usernameEmpty();
    }

    @Override
    public void onPasswordError(int code) {
        loginView.passwordError(code);
    }

    @Override
    public void onSuccess() {
        loginView.hideProgress();
        loginView.navigateToHome();
    }

    @Override
    public void onError() {
        loginView.hideProgress();
        loginView.wrongCredontiel();
    }
}
