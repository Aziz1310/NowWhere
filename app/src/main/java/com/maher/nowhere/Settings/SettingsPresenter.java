package com.maher.nowhere.Settings;

import android.content.Context;

import com.maher.nowhere.Register.SignUpInteractor;
import com.maher.nowhere.Register.SignUpView;

/**
 * Created by maher on 14/11/2017.
 */

public class SettingsPresenter implements SettingsInteractor.OnSignUpFinishedListener {

    private SettingsView settingsView;
    private SettingsInteractor settingsInteractor;
    private Context mcContext;

    public SettingsPresenter(SettingsView settingsView, Context mcContext) {
        this.settingsView = settingsView;
        this.mcContext = mcContext;
        settingsInteractor = new SettingsInteractor();
    }


    void signUp(String username, String password, String email, byte[] image, byte[] imageCover) {

        if (settingsInteractor.checkInput(username, password,email,image,imageCover, this)) {
            settingsView.showProgress();
            settingsInteractor.settings(username, password,email,image,imageCover, this,mcContext);
        }

    }

    @Override
    public void onUsernameError() {
        settingsView.usernameEmpty();
    }

    @Override
    public void onPasswordError(int code) {
        settingsView.passwordError(code);
    }

    @Override
    public void onSuccess() {
        settingsView.hideProgress();
        settingsView.goBack();
    }

    @Override
    public void onError() {
        settingsView.hideProgress();
        settingsView.Error();
    }

    @Override
    public void onEmailError(int code) {
        settingsView.emailError(code);
    }

    @Override
    public void onImageEmpty() {
        settingsView.imageEmpty();

    }
}
