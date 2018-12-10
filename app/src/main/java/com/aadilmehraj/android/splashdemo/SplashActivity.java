package com.aadilmehraj.android.splashdemo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    public static final int LOGO_ANIM_DURATION = 1500;
    public static final int TITLE_ANIM_DURATION = 1500;

    public static final int SPLASH_DURATION = 3000;

    private ImageView mLogoView;
    private TextView mTitleView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        mLogoView = findViewById(R.id.logo);
        mTitleView = findViewById(R.id.title);
    }


    @Override
    protected void onResume() {
        super.onResume();
        animateViews();
        openMainActivity();

    }


    /**
     * Animate ImageView and TextView using alpha animation
     */
    private void animateViews() {
        ObjectAnimator logoAnim = ObjectAnimator.ofFloat(mLogoView, "alpha", 0f, 1f)
            .setDuration(LOGO_ANIM_DURATION);
        ObjectAnimator titleAnim = ObjectAnimator.ofFloat(mTitleView, "alpha", 0f, 1f)
            .setDuration(TITLE_ANIM_DURATION);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(logoAnim, titleAnim);
        animatorSet.start();
    }


    /**
     * Opens the {@link MainActivity}
     */
    private void openMainActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                        startActivity(new Intent(SplashActivity.this, MainActivity.class),
                            ActivityOptions.makeSceneTransitionAnimation(SplashActivity.this)
                                .toBundle());
                    }
                });
            }
        }, SPLASH_DURATION);
    }
}
