package com.maher.nowhere.ProfileActivity.fragments.page;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.maher.nowhere.R;
import com.maher.nowhere.Register.SignUpActivity;
import com.maher.nowhere.Register.SignUpView;
import com.maher.nowhere.mainActivity.MainActivity;
import com.maher.nowhere.mainActivity.adapter.AcceuilAdapter;
import com.maher.nowhere.mainActivity.fragments.acceuil.AccueilPresenter;
import com.maher.nowhere.model.Post;
import com.maher.nowhere.model.Publication;
import com.maher.nowhere.model.User;
import com.maher.nowhere.providers.AccueilManager;
import com.maher.nowhere.utiles.Urls;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.vansuita.pickimage.IPickResult;
import com.vansuita.pickimage.PickImageDialog;
import com.vansuita.pickimage.PickSetup;

import org.w3c.dom.Text;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PageFragment extends Fragment implements PageView,IPickResult.IPickClick{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static final int MEDIA_TYPE_IMAGE = 1;
    private static final int REQUEST_SIGNUP = 0;
    public static final int SELECT_PICTURE = 1;
    // LogCat tag
    private static final String TAG = MainActivity.class.getSimpleName();
    // Camera activity request codes
    public static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private Uri fileUri; // file url to store image/video
    private String filePath = null;
    private Bitmap bitmap;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private View view;
    private RecyclerView recyclerView;
    private LinearLayoutManager lm;
    private ArrayList<Publication> posts;
    private EditText etComment;
    private ImageView imgGalerie, imgSend;


    private AccueilManager accueilManager;
    private LottieAnimationView lottieAnimationView;

    public PageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PageFragment newInstance(String param1, String param2) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_page, container, false);
        setRetainInstance(true);

        lottieAnimationView = (LottieAnimationView) view.findViewById(R.id.loadingAnimation);
        final PagePresenter pagePresenter = new PagePresenter(this, getActivity());
        pagePresenter.getListPublication(User.getCurrentUser(getActivity()).getId());


        etComment = view.findViewById(R.id.etComment);
        imgGalerie = view.findViewById(R.id.imageView6);
        imgSend = view.findViewById(R.id.imageView4);
        recyclerView = view.findViewById(R.id.rv_acceuil);
        CircleImageView profile_img=view.findViewById(R.id.profile_img);
        Picasso.with(getActivity()).load(Uri.parse(Urls.IMG_URL_USER+User.getCurrentUser(getActivity()).getImage())).into(profile_img);




        lm = new LinearLayoutManager(getActivity(), LinearLayout.VERTICAL, false);
        AcceuilAdapter acceuilAdapter = new AcceuilAdapter(getActivity(), posts);
        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(acceuilAdapter);

        imgGalerie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  PickImageDialog.on((AppCompatActivity) getActivity(), new PickSetup());

                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

                startActivityForResult(chooserIntent, SELECT_PICTURE);


            }
        });
        imgSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etComment.getText().toString().isEmpty() && bitmap !=null){
                    try {
                        pagePresenter.addPost(User.getCurrentUser(getActivity()).getId(),etComment.getText().toString(),fullyReadFileToBytes(bitmap));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void showProgress() {
        lottieAnimationView.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        lottieAnimationView.setVisibility(View.GONE);

    }

    @Override
    public void networkError() {
        System.out.println("network error load pageFragment");

    }

    @Override
    public void loadAllPosts(ArrayList<Publication> publications) {
        System.out.println("load all publications froma page fragment");

        posts = new ArrayList<>();
        posts = publications;
        AcceuilAdapter acceuilAdapter = new AcceuilAdapter(getActivity(), posts);
        recyclerView.setAdapter(acceuilAdapter);
    }

    @Override
    public void loadNoPosts(ArrayList<Publication> posts) {
        System.out.println("load No publication from page fragment");

    }

    @Override
    public void onSuccesAddPublication() {
        final PagePresenter pagePresenter = new PagePresenter(this, getActivity());
        pagePresenter.getListPublication(User.getCurrentUser(getActivity()).getId());

    }

    @Override
    public void onErrorAddPublication() {
        final PagePresenter pagePresenter = new PagePresenter(this, getActivity());
        pagePresenter.getListPublication(User.getCurrentUser(getActivity()).getId());

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    byte[] fullyReadFileToBytes(Bitmap f) throws IOException {

        File file = new File(filePath);
        OutputStream os = new BufferedOutputStream(new FileOutputStream(file));
        f.compress(Bitmap.CompressFormat.JPEG, 100, os);
        os.close();

        int size = (int) file.length();

        byte bytes[] = new byte[size];
        byte tmpBuff[] = new byte[size];
        FileInputStream fis = new FileInputStream(file);

        try {

            int read = fis.read(bytes, 0, size);
            if (read < size) {
                int remain = size - read;
                while (remain > 0) {
                    read = fis.read(tmpBuff, 0, remain);
                    System.arraycopy(tmpBuff, 0, bytes, size - remain, read);
                    remain -= read;
                }
            }
        } catch (IOException e) {
            throw e;
        } finally {
            fis.close();
        }

        return bytes;
    }

    public void AllowRunTimePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {

            Toast.makeText(getActivity(), "READ_EXTERNAL_STORAGE permission Access Dialog", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

        }
    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] Result) {

        switch (RC) {

            case 1:

                if (Result.length > 0 && Result[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(getActivity(), "Permission Granted", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(getActivity(), "Permission Canceled", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }


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
            // TODO perform some logging or show user Feedback
            return null;
        }
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity(). getContentResolver().query(uri, projection, null, null, null);
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
    public void onGaleryClick() {
        Intent intent = new Intent();
        intent.setType("image*//*");
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
        if (getActivity().getApplicationContext().getPackageManager().hasSystemFeature(
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
        imgGalerie.setImageBitmap(bitmap);

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
            Toast.makeText(getActivity().getApplicationContext(),
                    "Sorry! Your device doesn't support camera",
                    Toast.LENGTH_LONG).show();
            // will close the app if the device does't have camera
            getActivity().finish();
        }
    }

    /**
     * Receiving activity result method will be called after closing the camera
     */

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // if the result is capturing Image
        System.out.println("maher image "+requestCode+" "+resultCode+" "+data);

        if (resultCode == Activity.RESULT_OK && data!=null) {
            if (requestCode == SELECT_PICTURE) {

                //selectedImagePath = getPath(selectedImageUri);
                Uri selectedImageUri = data.getData();
                filePath = getPath(selectedImageUri);
                System.out.println("maher image "+filePath);
                //launchUploadActivity();
                previewMedia();
            }

            if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
                if (resultCode == Activity.RESULT_OK) {

                    // successfully captured the image
                    // launching upload activity
                    launchUploadActivity();
                    previewMedia();


                } else if (resultCode == Activity.RESULT_CANCELED) {

                    // user cancelled Image capture
                    Toast.makeText(getActivity().getApplicationContext(),
                            "User cancelled image capture", Toast.LENGTH_SHORT)
                            .show();

                } else {
                    // failed to capture image
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                            .show();
                }

            }
        }
    }



}
