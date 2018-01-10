package com.dovo.wibe.wellness;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dovo.wibe.BuildConfig;
import com.dovo.wibe.R;
import com.dovo.wibe.models.RegisteredUser;
import com.dovo.wibe.services.BaseWibeActivity;
import com.dovo.wibe.services.TinyDB;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;


public class SignUpActivity extends BaseWibeActivity {
    com.google.android.gms.common.SignInButton btnGoogle;
    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 50;
    int wellnessScore;
    TextView tvScore;
    TextView tvScoreDesc;
    TextView tvSignUpText;
    GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    FirebaseFirestore db;
    ImageView ivHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        wellnessScore = wibeFrontendServices.getWellnessScore();
        db = FirebaseFirestore.getInstance();
        initializeViews();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void showAlert() {
        final android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(this);
        alertDialog.setMessage("This will restart your wellness assessment?");
        alertDialog.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getBaseContext(), WellnessIntroActivity.class);
                startActivity(intent);
                SignUpActivity.this.finish();
            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.show();
    }

    private void initializeViews() {
        btnGoogle = findViewById(R.id.btnGoogle);
        ivHome = findViewById(R.id.ivHome);
        tvScoreDesc = findViewById(R.id.tvScoreDesc);
        tvSignUpText = findViewById(R.id.tvSignUpText);
        tvScore = findViewById(R.id.tvScore);

        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlert();
            }
        });

        if (wellnessScore < 250) {
            tvScore.setBackgroundResource(R.drawable.template_red);
            tvScore.setText("Red Zone\nScore : " + wellnessScore+"/500" );
            tvScoreDesc.setText("You are in bad shape!");
        } else if (wellnessScore < 350) {
            tvScore.setBackgroundResource(R.drawable.template_orange);
            tvScore.setText("Orange Zone\nScore : " + wellnessScore+"/500" );
            tvScoreDesc.setText("You are in the vulnerable zone!");
        } else if (wellnessScore < 450) {
            tvScore.setBackgroundResource(R.drawable.template_yellow);
            tvScore.setText("Yellow Zone\nScore : " + wellnessScore+"/500" );
            tvScore.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            tvScoreDesc.setText("Not bad. But you can still improve.");
        } else {
            tvScore.setBackgroundResource(R.drawable.template_green);
            tvScore.setText("Green Zone\nScore : " + wellnessScore+"/500" );
            tvScoreDesc.setText("Congratulations. You are in top shape");
            tvSignUpText.setText("We All Can Learn from you.\n" +
                    "Signup and Be a Mentor.");
        }

        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRegister(view);
            }
        });
    }

    public void  onClickRegister(View v){
        if(v.getId()==R.id.btnGoogle){
            if (BuildConfig.DEBUG) {
                // do something for a debug build
                Log.i(TAG,"onClick " + v.getId() + " debug");
                Intent intent = new Intent(getBaseContext(), WellnessSolutionActivity.class);
                startActivity(intent);
                finish();
            }else{
                Log.i(TAG,"onClick " + v.getId());
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        }

        if(v.getId()==R.id.btnFacebook){
           //todo facebook
        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // [START_EXCLUDE]
                updateUI(null);
                // [END_EXCLUDE]
            }
        }
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            TinyDB tinyDB = new TinyDB(getBaseContext());
            RegisteredUser registeredUser = new RegisteredUser();
            registeredUser.setAnswers(tinyDB.getListString("wellnessAnswers"));
            registeredUser.setRegisteredUserId(user.getUid());
            registeredUser.setTimestamp(new Date(System.currentTimeMillis()));
            db.collection(RegisteredUser.TABLE_NAME).document(user.getUid()).set(registeredUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Log.i("SignUp", "db add success");
                        Intent intent = new Intent(getBaseContext(), WellnessSolutionActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Log.i("SignUp", "db add fail");
                    }
                }
            });
        }
           /* mStatusTextView.setText(getString(R.string.google_status_fmt, user.getEmail()));
            mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));*/
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        showProgressDialog();
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId() + "," + acct.getIdToken());
        // [START_EXCLUDE silent]
        // [END_EXCLUDE]
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), acct.getId());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            updateUI(null);
                        }

                        // [START_EXCLUDE]
                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
    }

    public ProgressDialog mProgressDialog;

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Signing in!");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
