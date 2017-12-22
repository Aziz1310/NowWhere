package com.maher.nowhere.Register;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by maher on 14/11/2017.
 */

public class SignUpPresenter implements SignUpInteractor.OnSignUpFinishedListener {

    private SignUpView signUpView;
    private SignUpInteractor signUpInteractor;
    private Context mcContext;

    public SignUpPresenter(SignUpView signUpView, Context mcContext) {
        this.signUpView = signUpView;
        this.mcContext = mcContext;
        signUpInteractor = new SignUpInteractor();
    }


    void signUp(String username, String password, String email, byte[] image, byte[] imageCover) {

        if (signUpInteractor.checkInput(username, password,email,image,imageCover, this)) {
            signUpView.showProgress();
            signUpInteractor.signUp(username, password,email,image,imageCover, this,mcContext);
        }

    }

    @Override
    public void onUsernameError() {
        signUpView.usernameEmpty();
    }

    @Override
    public void onPasswordError(int code) {
        signUpView.passwordError(code);
    }

    @Override
    public void onSuccess() {
        signUpView.hideProgress();
        signUpView.navigateToHome();
    }

    @Override
    public void onError() {
        signUpView.hideProgress();
        signUpView.SignUpError();
    }

    @Override
    public void onEmailError(int code) {
        signUpView.emailError(code);
    }

    @Override
    public void onImageEmpty() {
        signUpView.imageEmpty();

    }
}
