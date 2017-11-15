package com.maher.nowhere.login;

import android.widget.EditText;

/**
 * Created by maher on 14/11/2017.
 */

public interface LoginView {
    void showProgress();
    void hideProgress();
    void wrongCredontiel();
    void navigateToHome();
    void hideSoftKeyBoard();
    void usernameEmpty();
    void passwordError(int code);


}
