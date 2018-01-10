package com.dovo.wibe.wellness;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dovo.wibe.R;
import com.dovo.wibe.resources.ScoreBar;
import com.dovo.wibe.services.BaseWibeActivity;

public class WellnessSolutionActivity extends BaseWibeActivity {

    int wellnessScore;
    TextView tvScore;
    View scoreView;
    ImageView ivMarker;
    ImageView ivHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellness_solution);
        wellnessScore = wibeFrontendServices.getWellnessScore();
        initializeViews();
    }

    private void showAlert() {
        final android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(this);
        alertDialog.setMessage("This will restart your wellness assessment?");
        alertDialog.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getBaseContext(), WellnessIntroActivity.class);
                startActivity(intent);
                WellnessSolutionActivity.this.finish();
            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.show();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus)
        setScore();
    }

    private void initializeViews() {
        scoreView = findViewById(R.id.scoreView2);
        ivHome = findViewById(R.id.ivHome);
        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlert();
            }
        });
        ScoreBar scoreBar = new ScoreBar();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            scoreView.setBackground(scoreBar);
        }
        tvScore =  findViewById(R.id.tvScore);
        tvScore.setText(wellnessScore+"/500");
        ivMarker = findViewById(R.id.ivMarker);
    }

    public void setScore() {
        int width = scoreView.getWidth();
        Log.i("setScore",  ", " + width);
        // int left = (width * score) / 500;
        int adjustedScore = wellnessScore - 150;
        int left = (width * adjustedScore) / 350;
        Log.i("setScore", left + ", " + width);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(left, 0, 0, 0);
        ivMarker.setVisibility(View.VISIBLE);
        animate(left);
    }

    private void animate(final int newLeftMargin) {
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ivMarker.getLayoutParams();
                params.leftMargin = (int) (newLeftMargin * interpolatedTime);
                ivMarker.setLayoutParams(params);
            }
        };
        a.setDuration(500); // in ms
        ivMarker.startAnimation(a);
    }

    public void retakeTest(View v){
        Intent intent = new Intent(getBaseContext(),FlipperActivity.class);
        intent.putExtra("retake",true);
        startActivity(intent);
        finish();
    }
}
