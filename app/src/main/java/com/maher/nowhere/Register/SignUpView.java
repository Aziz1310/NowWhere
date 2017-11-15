package com.maher.nowhere.Register;

/**
 * Created by maher on 14/11/2017.
 */

public interface SignUpView {
    void showProgress();
    void hideProgress();
    void SignUpError();
    void navigateToHome();
    void hideSoftKeyBoard();
    void usernameEmpty();
    void passwordError(int code);
    void imageEmpty();
    void emailError(int code);


}
