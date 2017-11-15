package com.maher.nowhere.Register;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.chootdev.csnackbar.Duration;
import com.chootdev.csnackbar.Snackbar;
import com.chootdev.csnackbar.Type;
import com.maher.nowhere.R;
import com.maher.nowhere.callbaks.VolleyCallback;
import com.maher.nowhere.helpers.JsonToObjectParser;

import com.maher.nowhere.mainActivity.MainActivity;
import com.maher.nowhere.model.User;
import com.maher.nowhere.providers.AccountManager;
import com.maher.nowhere.utiles.InputValidator;
import com.vansuita.pickimage.IPickResult;
import com.vansuita.pickimage.PickImageDialog;
import com.vansuita.pickimage.PickSetup;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignUpActivity extends AppCompatActivity implements IPickResult.IPickClick,SignUpView {

    public static final int MEDIA_TYPE_IMAGE = 1;
    private static final int REQUEST_SIGNUP = 0;
    private static final int SELECT_PICTURE = 1;
    // LogCat tag
    private static final String TAG = MainActivity.class.getSimpleName();
    // Camera activity request codes
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private Uri fileUri; // file url to store image/video
    private String filePath = null;
    private Bitmap bitmap;

    private CircleImageView imgProfile;
    private EditText etUserName, etPassword, etName;

    private LottieAnimationView lottieAnimationView;
    private AppCompatButton btnRegister;
    private FloatingActionButton btnGalerie;
    private InputValidator inputValidator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etUserName = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etName = (EditText) findViewById(R.id.etName);
        lottieAnimationView = (LottieAnimationView) findViewById(R.id.loadingAnimation);
        imgProfile = (CircleImageView) findViewById(R.id.imageView);
        btnGalerie = (FloatingActionButton) findViewById(R.id.btnGalerie);
        btnRegister = (AppCompatButton) findViewById(R.id.btnRegister);

        final SignUpPresenter signUpPresenter=new SignUpPresenter(this,this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideSoftKeyBoard();
                String userName = etUserName.getText().toString();
                String password = etPassword.getText().toString();
                String name = etName.getText().toString();
                signUpPresenter.signUp(name,password,userName,bitmap);

            }
        });
        btnGalerie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickImageDialog.on(SignUpActivity.this, new PickSetup());

            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }

    /**
     * Receiving activity result method will be called after closing the camera
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // if the result is capturing Image
        System.out.println("maher image");

        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {

                //selectedImagePath = getPath(selectedImageUri);
                Uri selectedImageUri = data.getData();
                filePath = getPath(selectedImageUri);
                //launchUploadActivity();

                previewMedia();
            }

            if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
                if (resultCode == RESULT_OK) {

                    // successfully captured the image
                    // launching upload activity
                    launchUploadActivity();

                    previewMedia();


                } else if (resultCode == RESULT_CANCELED) {

                    // user cancelled Image capture
                    Toast.makeText(getApplicationContext(),
                            "User cancelled image capture", Toast.LENGTH_SHORT)
                            .show();

                } else {
                    // failed to capture image
                    Toast.makeText(getApplicationContext(),
                            "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                            .show();
                }

            }
        }
    }

    private void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        // start the image capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }

    public void takeImageFromCamera() {
        captureImage();
        if (!isDeviceSupportCamera()) {
            Toast.makeText(getApplicationContext(),
                    "Sorry! Your device doesn't support camera",
                    Toast.LENGTH_LONG).show();
            // will close the app if the device does't have camera
            finish();
        }
    }

    @Override
    public void onGaleryClick() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,
                "Select Picture"), SELECT_PICTURE);
        //startActivityForResult(intent, SELECT_PICTURE);
    }

    @Override
    public void onCameraClick() {
        takeImageFromCamera();
    }

    private boolean isDeviceSupportCamera() {
        if (getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }


    private static File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Android File Upload");

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(TAG, "Oops! Failed create"
                        + "Android File Upload" + " directory");
                return null;
            }
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "Maldini_" + timeStamp + ".jpg");

        } else {
            return null;
        }

        return mediaFile;
    }

    private void previewMedia() {
        // Checking whether captured media is image or video

        // bimatp factory
        BitmapFactory.Options options = new BitmapFactory.Options();

        // down sizing image as it throws OutOfMemory Exception for larger
        // images
        options.inSampleSize = 8;

        bitmap = BitmapFactory.decodeFile(filePath, options);

        //Setting the Bitmap to ImageView
        imgProfile.setImageBitmap(bitmap);

    }

    /**
     * Creating file uri to store image/video
     */
    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    public String getOutputMediaFileName(File type) {
        return type.getName();
    }

    private void launchUploadActivity() {
        filePath = fileUri.getPath();
    }

    public String getPath(Uri uri) {
        // just some safety built in
        if (uri == null) {
            // TODO perform some logging or show user feedback
            return null;
        }
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(column_index);
            cursor.close();
            return path;
        }
        // this is our fallback here
        return uri.getPath();

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
    public void SignUpError() {
        Snackbar.with(SignUpActivity.this, null);
        Snackbar.type(Type.ERROR);
        Snackbar.message("Error");
        Snackbar.duration(Duration.SHORT);
        Snackbar.show();
    }

    @Override
    public void navigateToHome() {
        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
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
            case SignUpInteractor.EMPTY_PASSWORD:
                Snackbar.with(this, null);
                Snackbar.type(Type.ERROR);
                Snackbar.message("Password cannot be empty");
                Snackbar.duration(Duration.SHORT);
                Snackbar.show();
                break;
            case SignUpInteractor.PASSWORD_LESS_THAN_4:
                Snackbar.with(this, null);
                Snackbar.type(Type.WARNING);
                Snackbar.message("Password cannot be less than 4 characters");
                Snackbar.duration(Duration.SHORT);
                Snackbar.show();
                break;
        }


    }

    @Override
    public void imageEmpty() {
        Snackbar.with(this, null);
        Snackbar.type(Type.WARNING);
        Snackbar.message("Please choose an image");
        Snackbar.duration(Duration.SHORT);
        Snackbar.show();
    }

    @Override
    public void emailError(int code) {
        switch (code){
            case SignUpInteractor.EMPTY_EMAIL:
                Snackbar.with(this, null);
                Snackbar.type(Type.ERROR);
                Snackbar.message("Email cannot be empty");
                Snackbar.duration(Duration.SHORT);
                Snackbar.show();
                break;
            case SignUpInteractor.EMAIL_BADLY_FORMATED:
                Snackbar.with(this, null);
                Snackbar.type(Type.WARNING);
                Snackbar.message("Email address is badly formatted");
                Snackbar.duration(Duration.SHORT);
                Snackbar.show();
                break;
        }

    }

}
