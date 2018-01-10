package com.dovo.wibe.wellness;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.dovo.wibe.R;
import com.dovo.wibe.models.Guest;
import com.dovo.wibe.resources.ScoreBar;
import com.dovo.wibe.services.BaseWibeActivity;
import com.dovo.wibe.services.TinyDB;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FlipperActivity extends BaseWibeActivity {
    int prevScore = 0;
    float prevMargin = 0;
    int selectedQuestions = 0;
    boolean[] isSelected;
    int[] scores;
    String[] answers;
    Spinner sActivity;
    Spinner sChildAge;
    Spinner sEmployment;
    Spinner sWorkHour;
    Spinner sWorkRole;

    TextView btn4;
    TextView btn5;
    TextView btn6;
    TextView btn7;
    TextView btn8;

    TextView btn26;
    TextView btn19;
    TextView btn18;

    TextView btnNone;
    TextView btn1;
    TextView btn3;
    TextView btn7plus;
    ImageView ivMarker;
    ImageView ivHome;
    FloatingActionButton fbDone;
    RelativeLayout rrLoading;
    View scoreId;

    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private ViewFlipper mainViewFlipper;
    private Context mContext;
    View scoreView;
    FirebaseFirestore db;
    private final GestureDetector detector = new GestureDetector(new SwipeGestureDetector());
    Guest guest;
    TinyDB tinyDB;
    String androidId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flipper);
        db = FirebaseFirestore.getInstance();
        tinyDB = new TinyDB(getBaseContext());
        initializeViews();
    }

    private void initializeViews() {
        mContext = getBaseContext();
        rrLoading = findViewById(R.id.rrLoading);
        isSelected = new boolean[20];
        answers = new String[20];
        scores = new int[20];
        ivHome = findViewById(R.id.ivHome);
        scoreId = findViewById(R.id.scoreId);
        scoreView = findViewById(R.id.scoreView);
        sActivity = findViewById(R.id.sActivity);
        sWorkHour = findViewById(R.id.sWorkHour);
        sWorkRole = findViewById(R.id.sWorkRole);
        sChildAge = findViewById(R.id.sChildAge);
        sEmployment = findViewById(R.id.sEmployment);
        ivMarker = findViewById(R.id.ivMarker);
        fbDone = findViewById(R.id.fbDone);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);

        btn18 = findViewById(R.id.btn18);
        btn19 = findViewById(R.id.btn19);
        btn26 = findViewById(R.id.btn26);

        btnNone = findViewById(R.id.btnNone);
        btn1 = findViewById(R.id.btn1);
        btn3 = findViewById(R.id.btn3);
        btn7plus = findViewById(R.id.btn7plus);

        ScoreBar scoreBar = new ScoreBar();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            scoreView.setBackground(scoreBar);
        }

        mainViewFlipper = findViewById(R.id.mainViewFlipper);

        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlert();
            }
        });

        mainViewFlipper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View view, final MotionEvent event) {
                detector.onTouchEvent(event);
                return true;
            }
        });
        //20
        ArrayAdapter<String> employment = new ArrayAdapter<String>(this,
                R.layout.custom_spinner_item, getResources().getStringArray(R.array.array_employment));
        sEmployment.setAdapter(employment);

        sEmployment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //20
                if (i != 0) {
                    if (isSelected[19]) {

                    } else {
                        isSelected[19] = true;
                        selectedQuestions++;
                    }
                    answers[19] = getResources().getStringArray(R.array.array_employment)[i];
                    scores[19] = webyScores.scoreForEmployment[i-1];
                    setScore();
                    checkPageOne();
                } else {
                    if (isSelected[19]) {
                        answers[19] = "";
                        scores[19] = 0;
                        isSelected[19] = false;
                        selectedQuestions--;
                        Log.i("calling from", "-19");
                        setScore();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //19
        ArrayAdapter<String> childAge = new ArrayAdapter<String>(this,
                R.layout.custom_spinner_item, getResources().getStringArray(R.array.array_child));
        sChildAge.setAdapter(childAge);

        sChildAge.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //19
                if (i != 0) {
                    if (isSelected[18]) {

                    } else {
                        isSelected[18] = true;
                        selectedQuestions++;
                    }
                    answers[18] = getResources().getStringArray(R.array.array_child)[i];
                    scores[18] = webyScores.scoreForChildAge[i-1];
                    setScore();
                    checkPageTwo();
                } else {
                    if (isSelected[18]) {
                        scores[18] = 0;
                        answers[18] = "";
                        isSelected[18] = false;
                        selectedQuestions--;
                        Log.i("calling from", "-18");
                        setScore();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //18
        ArrayAdapter<String> workHour = new ArrayAdapter<String>(this,
                R.layout.custom_spinner_item, getResources().getStringArray(R.array.array_work_hours));
        sWorkHour.setAdapter(workHour);

        sWorkHour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //18
                if (i != 0) {
                    if (isSelected[17]) {

                    } else {
                        isSelected[17] = true;
                        selectedQuestions++;
                    }
                    answers[17] = getResources().getStringArray(R.array.array_work_hours)[i];
                    scores[17] = webyScores.scoreForWorkHour[i-1];
                    setScore();
                    checkPageThree();
                } else {
                    if (isSelected[17]) {
                        answers[17] = "";
                        scores[17] = 0;
                        isSelected[17] = false;
                        Log.i("calling from", "-17");
                        selectedQuestions--;
                        setScore();
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //17
        ArrayAdapter<String> workRole = new ArrayAdapter<String>(this,
                R.layout.custom_spinner_item, getResources().getStringArray(R.array.array_work_role));
        sWorkRole.setAdapter(workRole);
        sWorkRole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //17
                if (i != 0) {
                    if (isSelected[16]) {

                    } else {
                        isSelected[16] = true;
                        selectedQuestions++;
                    }
                    answers[16] = getResources().getStringArray(R.array.array_work_role)[i];
                    scores[16] = webyScores.scoreForWorkRole[i-1];
                    setScore();
                    checkPageThree();
                } else {
                    if (isSelected[16]) {
                        scores[16] = 0;
                        answers[16] = "";
                        isSelected[16] = false;
                        selectedQuestions--;
                        Log.i("calling from", "-16");
                        setScore();
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //16
        ArrayAdapter<String> activityLevel = new ArrayAdapter<String>(this,
                R.layout.custom_spinner_item, getResources().getStringArray(R.array.array_activity));
        sActivity.setAdapter(activityLevel);
        sActivity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //16
                if (i != 0) {
                    if (isSelected[15]) {

                    } else {
                        isSelected[15] = true;
                        selectedQuestions++;
                    }
                    answers[15] = getResources().getStringArray(R.array.array_activity)[i];
                    scores[15] = webyScores.scoreForActivity[i-1];
                    setScore();
                } else {
                    if (isSelected[15]) {
                        scores[15] = 0;
                        answers[15] = "";
                        isSelected[15] = false;
                        selectedQuestions--;
                        Log.i("calling from", "-15");
                        setScore();
                    }
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        if (getIntent().getBooleanExtra("retake", false)) {
            setOldScore();
        }

    }

    private void setOldScore() {
        ArrayList<String> ans = tinyDB.getListString("wellnessAnswers");
        ArrayList<Integer> scrs = tinyDB.getListInt("wellnessAnsScores");
        for (int i = 0; i < 20; i++) {
            answers[i] = ans.get(i);
            scores[i] = scrs.get(i);
            if (scores[i] == 0) {
                isSelected[i] = false;
            } else {
                isSelected[i] = true;
                selectedQuestions++;
            }
        }
        if (isSelected[15])
            sActivity.setSelection(Arrays.asList(getResources().getStringArray(R.array.array_activity)).indexOf(answers[15]));
        if (isSelected[17])
            sWorkHour.setSelection(Arrays.asList(getResources().getStringArray(R.array.array_work_hours)).indexOf(answers[17]));
        if (isSelected[16])
            sWorkRole.setSelection(Arrays.asList(getResources().getStringArray(R.array.array_work_role)).indexOf(answers[16]));
        if (isSelected[18])
            sChildAge.setSelection(Arrays.asList(getResources().getStringArray(R.array.array_child)).indexOf(answers[18]));
        if (isSelected[19])
            sEmployment.setSelection(Arrays.asList(getResources().getStringArray(R.array.array_employment)).indexOf(answers[19]));
        setScore();
        Log.i("calling from", "old score");
        setButtons();
    }

    private void showAlert() {
        final android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(this);
        alertDialog.setMessage("This will restart your wellness assessment?");
        alertDialog.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getBaseContext(), WellnessIntroActivity.class);
                startActivity(intent);
                FlipperActivity.this.finish();
            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.show();
    }

    class SwipeGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                // right to left swipe
                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    mainViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_in));
                    mainViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_out));
                    mainViewFlipper.showNext();
                    return true;
                } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    mainViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.right_in));
                    mainViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.right_out));
                    mainViewFlipper.showPrevious();
                    return true;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return false;
        }
    }

    public void setScore() {
        if (selectedQuestions == 0)
            return;
        Log.i("number selected", selectedQuestions + " ");

        double geoMean = webyScores.getGeoMeanScore(scores, selectedQuestions);
        int score = webyScores.getScore(scores);
        prevScore = score;
        Log.i("score, weight", score + " " + geoMean);

        int width = scoreView.getWidth();
        int adjustedScore = score - 150;
        int left = (width * adjustedScore) / 350;
        Log.i("left", left + ", " + width);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(left, 0, 0, 0);
        ivMarker.setVisibility(View.VISIBLE);
        setScoreColor(score);
        animate(lp, left);
        checkScore();
    }

    private void checkScore() {
        if (selectedQuestions >= 10) {
            fbDone.setVisibility(View.VISIBLE);
        } else {
            fbDone.setVisibility(View.GONE);
        }

        if (selectedQuestions % 5==0) {
            if(guest==null){
                androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
                guest = new Guest();
                guest.setGuestUserId(androidId);
                guest.setTimestamp(new Date(System.currentTimeMillis()));
            }

            guest.setAnswers(answers);
            db.collection(Guest.TABLE_NAME).document(androidId).set(guest).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Log.i("FlipperActivity", "db add success");
                    } else {
                        Log.i("FlipperActivity", "db add fail");
                    }
                }
            });
        }
    }

    public void onClickNextActivity(View v) {
        if (v.getVisibility() == View.VISIBLE) {
            Intent intent;
            if (getIntent().getBooleanExtra("retake", false))
                intent = new Intent(getBaseContext(), WellnessSolutionActivity.class);
            else
                intent = new Intent(getBaseContext(), SignUpActivity.class);
            wibeFrontendServices.setWellnessScore(prevScore);
            tinyDB.putListString("wellnessAnswers", Arrays.asList(answers));
            List<Integer> intList = new ArrayList<Integer>();
            for (int index = 0; index < scores.length; index++) {
                intList.add(scores[index]);
            }
            tinyDB.putListInt("wellnessAnsScores", intList);
            startActivity(intent);
            finish();
        }
    }

    private void setScoreColor(int score) {
        if (score < 250) {
            scoreId.setBackgroundColor(0xFFED0C3D);
        } else if (score < 350) {
            scoreId.setBackgroundColor(0xFFFA6C00);
        } else if (score < 450) {
            scoreId.setBackgroundColor(0xFFECE819);
        } else {
            scoreId.setBackgroundColor(0xFF39D704);
        }
    }

    private void animate(final RelativeLayout.LayoutParams params, final int newLeftMargin) {
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ivMarker.getLayoutParams();
                params.leftMargin = (int) (((newLeftMargin - prevMargin) * interpolatedTime) + prevMargin);
                ivMarker.setLayoutParams(params);
            }
        };
        a.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                prevMargin = newLeftMargin;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        a.setDuration(500); // in ms
        ivMarker.startAnimation(a);
    }

    //15
    public void onClickSpouseWork(View v) {
        if (isSelected[14]) {

        } else {
            isSelected[14] = true;
            selectedQuestions++;
        }
        answers[14] = ((TextView) v).getText().toString();
        selectSpouseWork(v);
        switch (v.getId()) {
            case R.id.btnWorking:
                scores[14] = webyScores.scoreForSpouseWork[0];
                setScore();
                break;
            case R.id.btnNotWorking:
                scores[14] = webyScores.scoreForSpouseWork[1];
                setScore();
                break;
            case R.id.btnNotApplicable:
                scores[14] = webyScores.scoreForSpouseWork[2];
                setScore();
                break;
            default:
                break;
        }
        checkPageTwo();
    }

    //14
    public void onClickMarriage(View v) {
        if (isSelected[13]) {

        } else {
            isSelected[13] = true;
            selectedQuestions++;
        }
        selectMarriage(v);
        answers[13] = ((TextView) v).getText().toString();
        switch (v.getId()) {
            case R.id.btnSingle:
                scores[13] = webyScores.scoreForMarriage[0];
                setScore();
                onClickSpouseWork(findViewById(R.id.btnNotApplicable));
                sChildAge.setSelection(6);
                break;
            case R.id.btnMarried:
                scores[13] = webyScores.scoreForMarriage[1];
                setScore();
                break;
            case R.id.btnWidow:
                scores[13] = webyScores.scoreForMarriage[2];
                setScore();
                break;
            case R.id.btnDivorced:
                scores[13] = webyScores.scoreForMarriage[3];
                setScore();
                break;
            default:
                break;
        }
        checkPageTwo();
    }

    //13
    public void onClickMeetingHour(View v) {
        if (isSelected[12]) {

        } else {
            isSelected[12] = true;
            selectedQuestions++;
        }
        answers[12] = ((TextView) v).getText().toString();
        selectMeetingHour(v);
        switch (v.getId()) {
            case R.id.btnMeet2:
                scores[12] = webyScores.scoreForMeetingHour[0];
                setScore();
                break;
            case R.id.btnMeet4:
                scores[12] = webyScores.scoreForMeetingHour[1];
                setScore();
                break;
            case R.id.btnMeet6:
                scores[12] = webyScores.scoreForMeetingHour[2];
                setScore();
                break;
            case R.id.btnMeet8:
                scores[12] = webyScores.scoreForMeetingHour[3];
                setScore();
                break;
            default:
                break;
        }
        checkPageThree();
    }

    //12
    public void onClickPersonalityType(View v) {
        if (isSelected[11]) {

        } else {
            isSelected[11] = true;
            selectedQuestions++;
        }
        answers[11] = ((TextView) v).getText().toString();
        selectPersonalityType(v);
        switch (v.getId()) {
            case R.id.btnInto:
                scores[11] = webyScores.scoreForPersonality[0];
                setScore();
                break;
            case R.id.btnExtro:
                scores[11] = webyScores.scoreForPersonality[1];
                setScore();
                break;
            case R.id.btnPartyAnimal:
                scores[11] = webyScores.scoreForPersonality[2];
                setScore();
                break;
            default:
                break;
        }
        checkPageOne();
    }

    //11
    public void onClickAge(View v) {
        if (isSelected[10]) {

        } else {
            isSelected[10] = true;
            selectedQuestions++;
        }
        answers[10] = ((TextView) v).getText().toString();
        selectAge(v);
        switch (v.getId()) {
            case R.id.btnA20:
                scores[10] = webyScores.scoreForAge[0];
                setScore();
                break;
            case R.id.btnA30:
                scores[10] = webyScores.scoreForAge[1];
                setScore();
                break;
            case R.id.btnA40:
                scores[10] = webyScores.scoreForAge[2];
                setScore();
                break;
            case R.id.btnA50:
                scores[10] = webyScores.scoreForAge[3];
                setScore();
                break;
            case R.id.btnA60:
                scores[10] = webyScores.scoreForAge[4];
                setScore();
                break;
            default:
                break;
        }
        checkPageTwo();
    }

    //10
    public void onClickGender(View v) {
        if (isSelected[9]) {

        } else {
            isSelected[9] = true;
            selectedQuestions++;
        }
        answers[9] = ((TextView) v).getText().toString();
        selectGender(v);
        switch (v.getId()) {
            case R.id.btnFemale:
                scores[9] = webyScores.scoreForGender[0];
                setScore();
                break;
            case R.id.btnMale:
                scores[9] = webyScores.scoreForGender[1];
                setScore();
                break;
            default:
                break;
        }
        checkPageTwo();
    }

    //9
    public void onClickOTHour(View v) {
        if (isSelected[8]) {

        } else {
            isSelected[8] = true;
            selectedQuestions++;
        }
        answers[8] = ((TextView) v).getText().toString();
        selectOTHour(v);
        switch (v.getId()) {
            case R.id.btnOT10:
                scores[8] = webyScores.scoreForOTHour[0];
                setScore();
                break;
            case R.id.btnOT20:
                scores[8] = webyScores.scoreForOTHour[1];
                setScore();
                break;
            case R.id.btnOT30:
                scores[8] = webyScores.scoreForOTHour[2];
                setScore();
                break;
            case R.id.btnOT40:
                scores[8] = webyScores.scoreForOTHour[3];
                setScore();
                break;
            case R.id.btnOTSometimes:
                scores[8] = webyScores.scoreForOTHour[4];
                setScore();
                break;
            default:
                break;
        }
        checkPageThree();
    }

    //8
    public void onClickWorkTravel(View v) {
        if (isSelected[7]) {

        } else {
            isSelected[7] = true;
            selectedQuestions++;
        }
        answers[7] = ((TextView) v).getText().toString();
        selectTravel(v);
        switch (v.getId()) {
            case R.id.btnTravel25:
                scores[7] = webyScores.scoreForWorkTravel[0];
                setScore();
                break;
            case R.id.btnTravel50:
                scores[7] = webyScores.scoreForWorkTravel[1];
                setScore();
                break;
            case R.id.btnTravel75:
                scores[7] = webyScores.scoreForWorkTravel[2];
                setScore();
                break;
            case R.id.btnTravel100:
                scores[7] = webyScores.scoreForWorkTravel[3];
                setScore();
                break;
            case R.id.btnTravelNone:
                scores[7] = webyScores.scoreForWorkTravel[4];
                setScore();
                break;
            default:
                break;
        }
        checkPageThree();
    }

    //7
    public void onClickSmoking(View v) {
        if (isSelected[6]) {

        } else {
            isSelected[6] = true;
            selectedQuestions++;
        }
        answers[6] = ((TextView) v).getText().toString();
        selectSmoking(v);
        switch (v.getId()) {
            case R.id.btnNoSmoke:
                scores[6] = webyScores.scoreForSmoking[0];
                setScore();
                break;
            case R.id.btnSmokeOne:
                scores[6] = webyScores.scoreForSmoking[1];
                setScore();
                break;
            case R.id.btnSmoke5:
                scores[6] = webyScores.scoreForSmoking[2];
                setScore();
                break;
            default:
                break;
        }
    }

    //6
    public void onClickDrinking(View v) {
        if (isSelected[5]) {

        } else {
            isSelected[5] = true;
            selectedQuestions++;
        }
        answers[5] = ((TextView) v).getText().toString();
        selectDrinking(v);
        switch (v.getId()) {
            case R.id.btnNoDrinking:
                scores[5] = webyScores.scoreForDrinking[0];
                setScore();
                break;
            case R.id.btnSocialDrink:
                scores[5] = webyScores.scoreForDrinking[1];
                ;
                setScore();
                break;
            case R.id.btnHeavyDrink:
                scores[5] = webyScores.scoreForDrinking[2];
                ;
                setScore();
                break;
            default:
                break;
        }
    }

    //5
    public void onClickBP(View v) {
        if (isSelected[4]) {

        } else {
            isSelected[4] = true;
            selectedQuestions++;
        }
        answers[4] = ((TextView) v).getText().toString();
        selectBP(v);
        switch (v.getId()) {
            case R.id.btnLowBP:
                scores[4] = webyScores.scoreForBP[0];
                setScore();
                break;
            case R.id.btnNormalBP:
                scores[4] = webyScores.scoreForBP[1];
                setScore();
                break;
            case R.id.btnHighBP:
                scores[4] = webyScores.scoreForBP[2];
                setScore();
                break;
            default:
                break;
        }
    }

    //4
    public void onClickSugar(View v) {
        if (isSelected[3]) {

        } else {
            isSelected[3] = true;
            selectedQuestions++;
        }
        answers[3] = ((TextView) v).getText().toString();
        selectSugar(v);
        switch (v.getId()) {
            case R.id.btnLowSugar:
                scores[3] = webyScores.scoreForSuagr[0];
                setScore();
                break;
            case R.id.btnNormalSugar:
                scores[3] = webyScores.scoreForSuagr[1];
                setScore();
                break;
            case R.id.btnHighSugar:
                scores[3] = webyScores.scoreForSuagr[2];
                setScore();
                break;
            default:
                break;
        }
    }

    //3
    public void onQuestionThree(View v) {
        if (isSelected[2]) {

        } else {
            isSelected[2] = true;
            selectedQuestions++;
        }
        answers[2] = ((TextView) v).getText().toString();
        selectQuestion3(v);
        switch (v.getId()) {
            case R.id.btn1:
                scores[2] = webyScores.scoreForQuestionThree[0];
                setScore();
                break;
            case R.id.btn3:
                scores[2] = webyScores.scoreForQuestionThree[1];
                setScore();
                break;
            case R.id.btnNone:
                scores[2] = webyScores.scoreForQuestionThree[2];
                setScore();
                break;
            case R.id.btn7plus:
                scores[2] = webyScores.scoreForQuestionThree[3];
                setScore();
                break;
            default:
                break;
        }
        checkPageOne();
    }

    //2
    public void onQuestionTwo(View v) {
        if (isSelected[1]) {

        } else {
            isSelected[1] = true;
            selectedQuestions++;
        }
        answers[1] = ((TextView) v).getText().toString();
        selectQuestion2(v);
        switch (v.getId()) {
            case R.id.btn19:
                scores[1] = webyScores.scoreForQuestionTwo[0];
                setScore();
                break;
            case R.id.btn26:
                scores[1] = webyScores.scoreForQuestionTwo[1];
                setScore();
                break;
            case R.id.btn18:
                scores[1] = webyScores.scoreForQuestionTwo[2];
                setScore();
                break;
            default:
                break;
        }
        checkPageOne();
    }

    //1
    public void onQuestionOne(View v) {
        if (isSelected[0]) {

        } else {
            isSelected[0] = true;
            selectedQuestions++;
        }
        answers[0] = ((TextView) v).getText().toString();
        selectQuestion1(v);
        switch (v.getId()) {
            case R.id.btn4:
                scores[0] = webyScores.scoreForQuestionOne[0];
                setScore();
                break;
            case R.id.btn5:
                scores[0] = webyScores.scoreForQuestionOne[1];
                setScore();
                break;
            case R.id.btn6:
                scores[0] = webyScores.scoreForQuestionOne[2];
                setScore();
                break;
            case R.id.btn7:
                scores[0] = webyScores.scoreForQuestionOne[3];
                setScore();
                break;
            case R.id.btn8:
                scores[0] = webyScores.scoreForQuestionOne[4];
                setScore();
                break;
            default:
                break;
        }
        checkPageOne();
    }

    private void checkPageOne() {
        Log.i("isSelected", isSelected[0] + "," + isSelected[1] + "," + isSelected[2] + "," + isSelected[11] + "," + isSelected[19]);
        if (isSelected[0] && isSelected[1] && isSelected[2] && isSelected[11] && isSelected[19]) {
            rrLoading.setVisibility(View.VISIBLE);
            Handler handler = new Handler();
            Runnable obj = new Runnable() {
                @Override
                public void run() {
                    rrLoading.setVisibility(View.GONE);
                    mainViewFlipper.showNext();
                }
            };
            handler.postDelayed(obj, 1500);

        }
    }

    private void checkPageTwo() {

        if (isSelected[18] && isSelected[14] && isSelected[13] && isSelected[10] && isSelected[9]) {
            rrLoading.setVisibility(View.VISIBLE);
            Handler handler = new Handler();
            Runnable obj = new Runnable() {
                @Override
                public void run() {
                    rrLoading.setVisibility(View.GONE);
                    mainViewFlipper.showNext();
                }
            };
            handler.postDelayed(obj, 1500);
        }
    }

    private void checkPageThree() {
        if (isSelected[7] && isSelected[8] && isSelected[12] && isSelected[16] && isSelected[17]) {
            rrLoading.setVisibility(View.VISIBLE);
            Handler handler = new Handler();
            Runnable obj = new Runnable() {
                @Override
                public void run() {
                    rrLoading.setVisibility(View.GONE);
                    mainViewFlipper.showNext();
                }
            };
            handler.postDelayed(obj, 1500);
        }
    }

    private void selectSpouseWork(View v) {
        findViewById(R.id.btnWorking).setBackgroundResource(R.drawable.button_normal);
        findViewById(R.id.btnNotWorking).setBackgroundResource(R.drawable.button_normal);
        findViewById(R.id.btnNotApplicable).setBackgroundResource(R.drawable.button_normal);

        ((TextView) findViewById(R.id.btnWorking)).setTextColor(getResources().getColor(R.color.colorPrimary));
        ((TextView) findViewById(R.id.btnNotWorking)).setTextColor(getResources().getColor(R.color.colorPrimary));
        ((TextView) findViewById(R.id.btnNotApplicable)).setTextColor(getResources().getColor(R.color.colorPrimary));

        v.setBackgroundResource(R.drawable.button_selected);
        ((TextView) v).setTextColor(getResources().getColor(R.color.colorWhite));
    }

    private void selectMarriage(View v) {
        findViewById(R.id.btnSingle).setBackgroundResource(R.drawable.button_normal);
        findViewById(R.id.btnMarried).setBackgroundResource(R.drawable.button_normal);
        findViewById(R.id.btnWidow).setBackgroundResource(R.drawable.button_normal);
        findViewById(R.id.btnDivorced).setBackgroundResource(R.drawable.button_normal);

        ((TextView) findViewById(R.id.btnSingle)).setTextColor(getResources().getColor(R.color.colorPrimary));
        ((TextView) findViewById(R.id.btnMarried)).setTextColor(getResources().getColor(R.color.colorPrimary));
        ((TextView) findViewById(R.id.btnWidow)).setTextColor(getResources().getColor(R.color.colorPrimary));
        ((TextView) findViewById(R.id.btnDivorced)).setTextColor(getResources().getColor(R.color.colorPrimary));

        v.setBackgroundResource(R.drawable.button_selected);
        ((TextView) v).setTextColor(getResources().getColor(R.color.colorWhite));
    }

    private void selectMeetingHour(View v) {
        findViewById(R.id.btnMeet2).setBackgroundResource(R.drawable.button_normal);
        findViewById(R.id.btnMeet4).setBackgroundResource(R.drawable.button_normal);
        findViewById(R.id.btnMeet6).setBackgroundResource(R.drawable.button_normal);
        findViewById(R.id.btnMeet8).setBackgroundResource(R.drawable.button_normal);

        ((TextView) findViewById(R.id.btnMeet2)).setTextColor(getResources().getColor(R.color.colorPrimary));
        ((TextView) findViewById(R.id.btnMeet4)).setTextColor(getResources().getColor(R.color.colorPrimary));
        ((TextView) findViewById(R.id.btnMeet6)).setTextColor(getResources().getColor(R.color.colorPrimary));
        ((TextView) findViewById(R.id.btnMeet8)).setTextColor(getResources().getColor(R.color.colorPrimary));

        v.setBackgroundResource(R.drawable.button_selected);
        ((TextView) v).setTextColor(getResources().getColor(R.color.colorWhite));
    }

    private void selectPersonalityType(View v) {
        findViewById(R.id.btnInto).setBackgroundResource(R.drawable.button_normal);
        findViewById(R.id.btnExtro).setBackgroundResource(R.drawable.button_normal);
        findViewById(R.id.btnPartyAnimal).setBackgroundResource(R.drawable.button_normal);

        ((TextView) findViewById(R.id.btnInto)).setTextColor(getResources().getColor(R.color.colorPrimary));
        ((TextView) findViewById(R.id.btnExtro)).setTextColor(getResources().getColor(R.color.colorPrimary));
        ((TextView) findViewById(R.id.btnPartyAnimal)).setTextColor(getResources().getColor(R.color.colorPrimary));

        v.setBackgroundResource(R.drawable.button_selected);
        ((TextView) v).setTextColor(getResources().getColor(R.color.colorWhite));
    }

    private void selectAge(View v) {
        findViewById(R.id.btnA20).setBackgroundResource(R.drawable.button_normal);
        findViewById(R.id.btnA30).setBackgroundResource(R.drawable.button_normal);
        findViewById(R.id.btnA40).setBackgroundResource(R.drawable.button_normal);
        findViewById(R.id.btnA50).setBackgroundResource(R.drawable.button_normal);
        findViewById(R.id.btnA60).setBackgroundResource(R.drawable.button_normal);

        ((TextView) findViewById(R.id.btnA20)).setTextColor(getResources().getColor(R.color.colorPrimary));
        ((TextView) findViewById(R.id.btnA30)).setTextColor(getResources().getColor(R.color.colorPrimary));
        ((TextView) findViewById(R.id.btnA40)).setTextColor(getResources().getColor(R.color.colorPrimary));
        ((TextView) findViewById(R.id.btnA50)).setTextColor(getResources().getColor(R.color.colorPrimary));
        ((TextView) findViewById(R.id.btnA60)).setTextColor(getResources().getColor(R.color.colorPrimary));

        v.setBackgroundResource(R.drawable.button_selected);
        ((TextView) v).setTextColor(getResources().getColor(R.color.colorWhite));
    }

    private void selectGender(View v) {
        findViewById(R.id.btnMale).setBackgroundResource(R.drawable.button_normal);
        findViewById(R.id.btnFemale).setBackgroundResource(R.drawable.button_normal);

        ((TextView) findViewById(R.id.btnMale)).setTextColor(getResources().getColor(R.color.colorPrimary));
        ((TextView) findViewById(R.id.btnFemale)).setTextColor(getResources().getColor(R.color.colorPrimary));

        v.setBackgroundResource(R.drawable.button_selected);
        ((TextView) v).setTextColor(getResources().getColor(R.color.colorWhite));
    }

    private void selectOTHour(View v) {
        findViewById(R.id.btnOT10).setBackgroundResource(R.drawable.button_normal);
        findViewById(R.id.btnOT20).setBackgroundResource(R.drawable.button_normal);
        findViewById(R.id.btnOT30).setBackgroundResource(R.drawable.button_normal);
        findViewById(R.id.btnOT40).setBackgroundResource(R.drawable.button_normal);
        findViewById(R.id.btnOTSometimes).setBackgroundResource(R.drawable.button_normal);

        ((TextView) findViewById(R.id.btnOT10)).setTextColor(getResources().getColor(R.color.colorPrimary));
        ((TextView) findViewById(R.id.btnOT20)).setTextColor(getResources().getColor(R.color.colorPrimary));
        ((TextView) findViewById(R.id.btnOT30)).setTextColor(getResources().getColor(R.color.colorPrimary));
        ((TextView) findViewById(R.id.btnOT40)).setTextColor(getResources().getColor(R.color.colorPrimary));
        ((TextView) findViewById(R.id.btnOTSometimes)).setTextColor(getResources().getColor(R.color.colorPrimary));

        v.setBackgroundResource(R.drawable.button_selected);
        ((TextView) v).setTextColor(getResources().getColor(R.color.colorWhite));
    }

    private void selectTravel(View v) {
        findViewById(R.id.btnTravel25).setBackgroundResource(R.drawable.button_normal);
        findViewById(R.id.btnTravel50).setBackgroundResource(R.drawable.button_normal);
        findViewById(R.id.btnTravel75).setBackgroundResource(R.drawable.button_normal);
        findViewById(R.id.btnTravel100).setBackgroundResource(R.drawable.button_normal);
        findViewById(R.id.btnTravelNone).setBackgroundResource(R.drawable.button_normal);

        ((TextView) findViewById(R.id.btnTravel25)).setTextColor(getResources().getColor(R.color.colorPrimary));
        ((TextView) findViewById(R.id.btnTravel50)).setTextColor(getResources().getColor(R.color.colorPrimary));
        ((TextView) findViewById(R.id.btnTravel75)).setTextColor(getResources().getColor(R.color.colorPrimary));
        ((TextView) findViewById(R.id.btnTravel100)).setTextColor(getResources().getColor(R.color.colorPrimary));
        ((TextView) findViewById(R.id.btnTravelNone)).setTextColor(getResources().getColor(R.color.colorPrimary));

        v.setBackgroundResource(R.drawable.button_selected);
        ((TextView) v).setTextColor(getResources().getColor(R.color.colorWhite));
    }

    private void selectSmoking(View v) {
        findViewById(R.id.btnNoSmoke).setBackgroundResource(R.drawable.button_normal);
        findViewById(R.id.btnSmokeOne).setBackgroundResource(R.drawable.button_normal);
        findViewById(R.id.btnSmoke5).setBackgroundResource(R.drawable.button_normal);

        ((TextView) findViewById(R.id.btnNoSmoke)).setTextColor(getResources().getColor(R.color.colorPrimary));
        ((TextView) findViewById(R.id.btnSmokeOne)).setTextColor(getResources().getColor(R.color.colorPrimary));
        ((TextView) findViewById(R.id.btnSmoke5)).setTextColor(getResources().getColor(R.color.colorPrimary));

        v.setBackgroundResource(R.drawable.button_selected);
        ((TextView) v).setTextColor(getResources().getColor(R.color.colorWhite));
    }

    private void selectDrinking(View v) {
        findViewById(R.id.btnNoDrinking).setBackgroundResource(R.drawable.button_normal);
        findViewById(R.id.btnSocialDrink).setBackgroundResource(R.drawable.button_normal);
        findViewById(R.id.btnHeavyDrink).setBackgroundResource(R.drawable.button_normal);

        ((TextView) findViewById(R.id.btnNoDrinking)).setTextColor(getResources().getColor(R.color.colorPrimary));
        ((TextView) findViewById(R.id.btnSocialDrink)).setTextColor(getResources().getColor(R.color.colorPrimary));
        ((TextView) findViewById(R.id.btnHeavyDrink)).setTextColor(getResources().getColor(R.color.colorPrimary));

        v.setBackgroundResource(R.drawable.button_selected);
        ((TextView) v).setTextColor(getResources().getColor(R.color.colorWhite));
    }

    private void selectBP(View v) {
        findViewById(R.id.btnLowBP).setBackgroundResource(R.drawable.button_normal);
        findViewById(R.id.btnNormalBP).setBackgroundResource(R.drawable.button_normal);
        findViewById(R.id.btnHighBP).setBackgroundResource(R.drawable.button_normal);

        ((TextView) findViewById(R.id.btnLowBP)).setTextColor(getResources().getColor(R.color.colorPrimary));
        ((TextView) findViewById(R.id.btnNormalBP)).setTextColor(getResources().getColor(R.color.colorPrimary));
        ((TextView) findViewById(R.id.btnHighBP)).setTextColor(getResources().getColor(R.color.colorPrimary));

        v.setBackgroundResource(R.drawable.button_selected);
        ((TextView) v).setTextColor(getResources().getColor(R.color.colorWhite));
    }

    private void selectSugar(View v) {
        findViewById(R.id.btnLowSugar).setBackgroundResource(R.drawable.button_normal);
        findViewById(R.id.btnNormalSugar).setBackgroundResource(R.drawable.button_normal);
        findViewById(R.id.btnHighSugar).setBackgroundResource(R.drawable.button_normal);

        ((TextView) findViewById(R.id.btnLowSugar)).setTextColor(getResources().getColor(R.color.colorPrimary));
        ((TextView) findViewById(R.id.btnNormalSugar)).setTextColor(getResources().getColor(R.color.colorPrimary));
        ((TextView) findViewById(R.id.btnHighSugar)).setTextColor(getResources().getColor(R.color.colorPrimary));

        v.setBackgroundResource(R.drawable.button_selected);
        ((TextView) v).setTextColor(getResources().getColor(R.color.colorWhite));
    }

    private void selectQuestion3(View v) {
        btn1.setBackgroundResource(R.drawable.button_normal);
        btn3.setBackgroundResource(R.drawable.button_normal);
        btnNone.setBackgroundResource(R.drawable.button_normal);
        btn7plus.setBackgroundResource(R.drawable.button_normal);

        btn1.setTextColor(getResources().getColor(R.color.colorPrimary));
        btn3.setTextColor(getResources().getColor(R.color.colorPrimary));
        btnNone.setTextColor(getResources().getColor(R.color.colorPrimary));
        btn7plus.setTextColor(getResources().getColor(R.color.colorPrimary));

        v.setBackgroundResource(R.drawable.button_selected);
        ((TextView) v).setTextColor(getResources().getColor(R.color.colorWhite));
    }

    private void selectQuestion2(View v) {
        btn26.setBackgroundResource(R.drawable.button_normal);
        btn19.setBackgroundResource(R.drawable.button_normal);
        btn18.setBackgroundResource(R.drawable.button_normal);

        btn26.setTextColor(getResources().getColor(R.color.colorPrimary));
        btn19.setTextColor(getResources().getColor(R.color.colorPrimary));
        btn18.setTextColor(getResources().getColor(R.color.colorPrimary));

        v.setBackgroundResource(R.drawable.button_selected);
        ((TextView) v).setTextColor(getResources().getColor(R.color.colorWhite));
    }

    private void selectQuestion1(View v) {
        btn4.setBackgroundResource(R.drawable.button_normal);
        btn5.setBackgroundResource(R.drawable.button_normal);
        btn6.setBackgroundResource(R.drawable.button_normal);
        btn7.setBackgroundResource(R.drawable.button_normal);
        btn8.setBackgroundResource(R.drawable.button_normal);

        btn4.setTextColor(getResources().getColor(R.color.colorPrimary));
        btn5.setTextColor(getResources().getColor(R.color.colorPrimary));
        btn6.setTextColor(getResources().getColor(R.color.colorPrimary));
        btn7.setTextColor(getResources().getColor(R.color.colorPrimary));
        btn8.setTextColor(getResources().getColor(R.color.colorPrimary));

        v.setBackgroundResource(R.drawable.button_selected);
        ((TextView) v).setTextColor(getResources().getColor(R.color.colorWhite));
    }

    public void setButtons() {
        Log.i("setButtons()", "size " + webyScores.buttonList.size());
        int[] savedScore;
        int[] buttonIds;
        for (int j = 0; j < webyScores.buttonList.size(); j++) {
            Log.i("setButtons()", "j " + j);
            savedScore = webyScores.scoreList.get(j);
            buttonIds = webyScores.buttonList.get(j);
            for (int i = 0; i < savedScore.length; i++) {
                if (savedScore[i] == scores[j]) {
                    setAnswer(j, buttonIds[i]);
                    break;
                }
            }
        }
    }

    private void setAnswer(int j, int buttonId) {

        switch (j) {
            case 0:
                selectQuestion1(findViewById(buttonId));
                break;
            case 1:
                selectQuestion2(findViewById(buttonId));
                break;
            case 2:
                selectQuestion3(findViewById(buttonId));
                break;
            case 3:
                selectSugar(findViewById(buttonId));
                break;
            case 4:
                selectBP(findViewById(buttonId));
                break;
            case 5:
                selectDrinking(findViewById(buttonId));
                break;
            case 6:
                selectSmoking(findViewById(buttonId));
                break;
            case 7:
                selectTravel(findViewById(buttonId));
                break;
            case 8:
                selectOTHour(findViewById(buttonId));
                break;
            case 9:
                selectGender(findViewById(buttonId));
                break;
            case 10:
                selectAge(findViewById(buttonId));
                break;
            case 11:
                selectPersonalityType(findViewById(buttonId));
                break;
            case 12:
                selectMeetingHour(findViewById(buttonId));
                break;
            case 13:
                selectMarriage(findViewById(buttonId));
                break;
            case 14:
                selectSpouseWork(findViewById(buttonId));
                break;
            default:
                break;
        }

    }
}
