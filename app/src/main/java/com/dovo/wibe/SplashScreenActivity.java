package com.dovo.wibe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.dovo.wibe.services.BaseWibeActivity;
import com.dovo.wibe.wellness.WellnessIntroActivity;
import com.dovo.wibe.wellness.WellnessSolutionActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.dovo.wibe.services.CommonMethodsUtil.makeToast;

public class SplashScreenActivity extends BaseWibeActivity {
    private FirebaseAuth mAuth;
    Handler handler;
    Runnable obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser currentUser = mAuth.getCurrentUser();
        handler = new Handler();
        obj = new Runnable() {
            @Override
            public void run() {
                if(currentUser!=null){
                    makeToast("Signed in as " + currentUser.getDisplayName(),getBaseContext());
                    Intent intent = new Intent(getBaseContext(),WellnessSolutionActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(getBaseContext(),WellnessIntroActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        handler.postDelayed(obj,3000);
    }
}
