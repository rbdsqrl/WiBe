package com.dovo.wibe.wellness;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

import com.dovo.wibe.R;
import com.dovo.wibe.services.BaseWibeActivity;

public class WellnessIntroActivity extends BaseWibeActivity {
    private ViewFlipper mViewFlipper;
    private RelativeLayout rrLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellness_intro);
        initializeViews();
    }

    private void initializeViews() {
        rrLoader = findViewById(R.id.rrLoader);
        mViewFlipper = findViewById(R.id.view_flipper);
        mViewFlipper.setAutoStart(true);
        mViewFlipper.setFlipInterval(4000);
        mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(getBaseContext(), R.anim.top_in));
        mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(getBaseContext(), R.anim.top_out));
        mViewFlipper.startFlipping();
    }

    public void nextActivity(View v){
        rrLoader.setVisibility(View.VISIBLE);
        Handler handler= new Handler();
        Runnable obj= new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getBaseContext(),FlipperActivity.class);
                startActivity(intent);
                finish();
            }
        };
        handler.postDelayed(obj,1500);
    }
}
