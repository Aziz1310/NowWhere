package com.maher.nowhere.Settings;

/**
 * Created by maher on 14/11/2017.
 */

public interface SettingsView {
    void showProgress();
    void hideProgress();
    void Error();
    void goBack();
    void hideSoftKeyBoard();
    void usernameEmpty();
    void passwordError(int code);
    void imageEmpty();
    void emailError(int code);


}
