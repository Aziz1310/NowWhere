package com.maher.nowhere.CinemaActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.maher.nowhere.ProfileActivity.ProfileActivity;
import com.maher.nowhere.R;
import com.maher.nowhere.model.Film;
import com.maher.nowhere.model.User;
import com.maher.nowhere.utiles.Urls;
import com.maher.nowhere.utiles.Utiles;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import pub.devrel.easypermissions.EasyPermissions;

public class FilmDetailActivity extends YouTubeBaseActivity implements EasyPermissions.PermissionCallbacks,
        YouTubePlayer.OnInitializedListener {

    private Toolbar toolbar;

    private ImageView mImageView;
    private ImageView btnIgo, img1;
    private Film film;
    private TextView tvTitle;
    private TextView tvPlace, tvDay, tvMonth, tvYear;
    private TextView tvDate, tvAdresse, tvDescription, tvAprop, dateFilm, auteurFilm, acteurFilm, tv_genreFilm,
            natFilm;
    private YouTubePlayerView youTubeView;
    private static final int RECOVERY_DIALOG_REQUEST = 1;
    private boolean isFullScreen = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);
        setupToolbar();

        mImageView = (ImageView) findViewById(R.id.rlFilm_header);
        tvDescription = (TextView) findViewById(R.id.tvDescriptionFilm);
        tvAprop = (TextView) findViewById(R.id.tvapropFilm);//
        tvTitle = (TextView) findViewById(R.id.tvTitleFilm);//
        tvAdresse = (TextView) findViewById(R.id.tvAdresseLieufilm);
        tvDate = (TextView) findViewById(R.id.tvDateFilm);
        dateFilm = (TextView) findViewById(R.id.dateFilm);
        tvPlace = (TextView) findViewById(R.id.tvLieuFilm);
        tvDay = (TextView) findViewById(R.id.tvDayFilm);
        tvMonth = (TextView) findViewById(R.id.tvMonthFilm);
        tvYear = (TextView) findViewById(R.id.tvYearFilm);
        auteurFilm = (TextView) findViewById(R.id.auteurFilm);
        acteurFilm = (TextView) findViewById(R.id.acteurFilm);
        tv_genreFilm = (TextView) findViewById(R.id.genrefilm);
        natFilm = (TextView) findViewById(R.id.natFilm);
        film = (Film) getIntent().getSerializableExtra("film");
        youTubeView = findViewById(R.id.youtube_view);


        youTubeView.initialize(Utiles.DEVELOPER_KEY, this);


        if (film != null) {


            tvDescription.setText(film.getSynopsis());
            film.setcDate(new Utiles().parseDate2(film.getDateSortie()));
            dateFilm.setText(String.format("%s %s %s(%s)", film.getDayOfWeek(), film.getMonth(), film.getYear(), film.getDuree()));
            tvAdresse.setText(film.getOwner().getAdresse());
            auteurFilm.setText(film.getRealisateur());
            acteurFilm.setText(film.getActeurs());
            tvTitle.setText(film.getNom());
            tvPlace.setText(film.getOwner().getNom());
            tv_genreFilm.setText(film.getGenre());
            natFilm.setText(film.getNationalites());

            film.setcDate(new Utiles().parseDate2(film.getDateDebut()));
            String dateDebut = film.getDayOfWeek();
            film.setcDate(new Utiles().parseDate2(film.getDateFin()));
            String dateFin = film.getDayOfWeek();
            tvDate.setText(String.format("De %s au %s %s\n%s", dateDebut, dateFin, film.getMonth(), film.getHeureDefusin()));

            /*Picasso.with(this).
                    load(Uri.parse(Urls.IMG_URL_film + film.getPhoto()))
                    .into(mImageView, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                        }

                        @Override
                        public void onError() {
                        }
                    });
*/

        }
    }


    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
       /* setSupportActionBar(toolbar);
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        } catch (NullPointerException ignore) {
        }*/

        User user = User.getCurrentUser(this);

        CircleImageView profile = (CircleImageView) toolbar.findViewById(R.id.toolbarProfileImg);
        Picasso.with(this).
                load(Uri.parse(Urls.IMG_URL_USER + user.getImage()))
                .into(profile, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError() {
                    }
                });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FilmDetailActivity.this, ProfileActivity.class));
            }
        });
    }

   /* @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }*/

    @Override
    public void onBackPressed() {
        if (isFullScreen && player!=null)
            player.setFullscreen(false);
        else
            finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            String errorMessage = String.format(
                    "error initializing youtube player", errorReason.toString());

        }
    }

    YouTubePlayer player;

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        YouTubePlayer player, boolean wasRestored) {
        this.player = player;
        if (!wasRestored) {

            // loadVideo() will auto play video
            // Use cueVideo() method, if you don't want to play it automatically
            if (film != null)
                player.cueVideo(Utiles.getYouTubeVideoId(film.getVideo()));

            // Showing player controls
            player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
            player.setPlaybackEventListener(new YouTubePlayer.PlaybackEventListener() {
                @Override
                public void onPlaying() {
                    hideYoutuveOverlay();
                }

                @Override
                public void onPaused() {
                    showYoutuveOverlay();
                }

                @Override
                public void onStopped() {
                    showYoutuveOverlay();
                }

                @Override
                public void onBuffering(boolean b) {

                }

                @Override
                public void onSeekTo(int i) {

                }
            });
            player.setPlayerStateChangeListener(new YouTubePlayer.PlayerStateChangeListener() {
                @Override
                public void onLoading() {
                    //  hideYoutuveOverlay();
                }

                @Override
                public void onLoaded(String s) {

                }

                @Override
                public void onAdStarted() {
                    //   hideYoutuveOverlay();
                }

                @Override
                public void onVideoStarted() {
                    //    hideYoutuveOverlay();
                }

                @Override
                public void onVideoEnded() {
                    showYoutuveOverlay();
                }

                @Override
                public void onError(YouTubePlayer.ErrorReason errorReason) {

                }

            });
        }
        player.setOnFullscreenListener(new YouTubePlayer.OnFullscreenListener() {
            @Override
            public void onFullscreen(boolean b) {
                isFullScreen = b;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Utiles.DEVELOPER_KEY, this);
        }
    }

    private YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.youtube_view);
    }

    private void hideYoutuveOverlay() {
        System.out.println("hide youtube player...");
        findViewById(R.id.ll1).setVisibility(View.GONE);
        findViewById(R.id.ll2).setVisibility(View.GONE);
    }

    private void showYoutuveOverlay() {
        findViewById(R.id.ll1).setVisibility(View.VISIBLE);
        findViewById(R.id.ll2).setVisibility(View.VISIBLE);
    }
}
