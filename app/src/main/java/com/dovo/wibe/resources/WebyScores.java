package com.dovo.wibe.resources;

import android.util.Log;

import com.dovo.wibe.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Narendran on 27-12-2017.
 */

public class WebyScores {
    public static List<int[]> scoreList;
    public static List<int[]> buttonList;
    private static WebyScores webyScores;

    public WebyScores() {
        scoreList = new ArrayList<>();
        buttonList = new ArrayList<>();

        scoreList.add(scoreForQuestionOne);
        scoreList.add(scoreForQuestionTwo);
        scoreList.add(scoreForQuestionThree);
        scoreList.add(scoreForSuagr);
        scoreList.add(scoreForBP);
        scoreList.add(scoreForDrinking);
        scoreList.add(scoreForSmoking);
        scoreList.add(scoreForWorkTravel);
        scoreList.add(scoreForOTHour);
        scoreList.add(scoreForGender);
        scoreList.add(scoreForAge);
        scoreList.add(scoreForPersonality);
        scoreList.add(scoreForMeetingHour);
        scoreList.add(scoreForMarriage);
        scoreList.add(scoreForSpouseWork);
        scoreList.add(scoreForActivity);
        scoreList.add(scoreForWorkRole);
        scoreList.add(scoreForWorkHour);
        scoreList.add(scoreForChildAge);
        scoreList.add(scoreForEmployment);

        buttonList.add(buttonForQuestionOne);
        buttonList.add(buttonForQuestionTwo);
        buttonList.add(buttonForQuestionThree);
        buttonList.add(buttonForSuagr);
        buttonList.add(buttonForBP);
        buttonList.add(buttonForDrinking);
        buttonList.add(buttonForSmoking);
        buttonList.add(buttonForWorkTravel);
        buttonList.add(buttonForOTHour);
        buttonList.add(buttonForGender);
        buttonList.add(buttonForAge);
        buttonList.add(buttonForPersonality);
        buttonList.add(buttonForMeetingHour);
        buttonList.add(buttonForMarriage);
        buttonList.add(buttonForSpouseWork);
    }

    public static WebyScores getInstance() {
        if (webyScores == null)
            webyScores = new WebyScores();
        return webyScores;
    }

    public static final int[] scoreForQuestionOne = {100, 200, 250, 350, 500}; //sleep u1
    public static final int[] scoreForQuestionTwo = {500, 200, 250}; //bmi u2
    public static final int[] scoreForQuestionThree = {350, 270, 500, 150}; //deadlines missed u3
    public static final int[] scoreForSuagr = {200, 500, 150};

    public static final int[] scoreForBP = {200, 500, 150}; //u4
    public static final int[] scoreForDrinking = {500, 250, 100}; //u5
    public static final int[] scoreForSmoking = {500, 200, 100}; //u6
    public static final int[] scoreForWorkTravel = {380, 300, 200, 100, 500}; //u7

    public static final int[] scoreForOTHour = {380, 300, 200, 100, 450}; //u8
    public static final int[] scoreForGender = {450, 400}; //u9
    public static final int[] scoreForAge = {450, 350, 300, 200, 100}; //u
    public static final int[] scoreForPersonality = {350, 450, 280};

    public static final int[] scoreForMeetingHour = {500, 300, 250, 100};
    public static final int[] scoreForMarriage = {380, 450, 350, 320};
    public static final int[] scoreForSpouseWork = {250, 500, 300};
    public static final int[] scoreForActivity = {150, 280, 370, 480};

    public static final int[] scoreForWorkRole = {350, 280, 300, 250, 280, 300, 400};
    public static final int[] scoreForWorkHour = {500, 300, 250, 200};
    public static final int[] scoreForChildAge = {100, 150, 200, 400, 500, 500};
    public static final int[] scoreForEmployment = {300,400,300,250,250,150};

    //BUTTONS 0-14
    public static final int[] buttonForQuestionOne = {R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8};
    public static final int[] buttonForQuestionTwo = {R.id.btn19, R.id.btn26, R.id.btn18};
    public static final int[] buttonForQuestionThree = {R.id.btn1, R.id.btn3, R.id.btnNone, R.id.btn7plus};
    public static final int[] buttonForSuagr = {R.id.btnLowSugar, R.id.btnNormalSugar, R.id.btnHighSugar};

    public static final int[] buttonForBP = {R.id.btnLowBP, R.id.btnNormalBP, R.id.btnHighBP};
    public static final int[] buttonForDrinking = {R.id.btnNoDrinking, R.id.btnSocialDrink, R.id.btnHeavyDrink};
    public static final int[] buttonForSmoking = {R.id.btnNoSmoke, R.id.btnSmokeOne, R.id.btnSmoke5};
    public static final int[] buttonForWorkTravel = {R.id.btnTravel25, R.id.btnTravel50, R.id.btnTravel75, R.id.btnTravel100, R.id.btnTravelNone};

    public static final int[] buttonForOTHour = {R.id.btnOT10, R.id.btnOT20, R.id.btnOT30, R.id.btnOT40, R.id.btnOTSometimes};
    public static final int[] buttonForGender = {R.id.btnFemale, R.id.btnMale};
    public static final int[] buttonForAge = {R.id.btnA20, R.id.btnA30, R.id.btnA40, R.id.btnA50, R.id.btnA60};
    public static final int[] buttonForPersonality = {R.id.btnInto, R.id.btnExtro, R.id.btnPartyAnimal};

    public static final int[] buttonForMeetingHour = {R.id.btnMeet2, R.id.btnMeet4, R.id.btnMeet6, R.id.btnMeet8};
    public static final int[] buttonForMarriage = {R.id.btnSingle, R.id.btnMarried, R.id.btnWidow, R.id.btnDivorced};
    public static final int[] buttonForSpouseWork = {R.id.btnWorking, R.id.btnNotWorking, R.id.btnNotApplicable};

    /***** COEFFICIENT FORMULA******
     *
       1 - U_HEALTH =  U_GENERAL + U_MARITAL + U_EMPLOYMENT + U_WORK_HABIT;

       where U_MARITAL = U_HEALTH/2 and  U_WORK_HABIT = U_HEALTH/3

     U_HEALTH = 0.3920...

     */
    public static final double U_HEALTH = 0.87*6/13; //1 2 4 5 6 7 16
    public static final double U_GENERAL = .11; //10 11 12
    public static double U_MARITAL = U_HEALTH/2; //14 15 19
    public static final double U_EMPLOYMENT = 0.2; //20
    public static double U_WORK_HABIT = 2*U_HEALTH/3; //8 9  13 17 18 2

    private static final int[] healthIndex = {0, 1, 3, 4, 5, 6, 15};
    private static final int[] generalIndex = {9, 10, 11};
    private static final int[] maritalIndex = {13, 14, 18};
    private static final int[] employmentIndex = {19};
    private static final int[] workIndex = {2,7, 8, 12, 16, 17};

    public int getScore(int[] scores) {
        int score = 0;
        double healthScore = 0;
        double generalScore = 0;
        double maritalScore = 0;
        double employmentScore = 0;
        double workScore = 0;
        int total = 0;

        for (int i = 0; i < healthIndex.length; i++) {
            healthScore = healthScore + scores[healthIndex[i]];
            if (scores[healthIndex[i]] != 0) {
                total++;
            }
        }

        if (total > 1)
            healthScore = healthScore / total;
        if(healthScore==0)
            healthScore = 340;

        total = 0;

        for (int i = 0; i < workIndex.length; i++) {
            workScore = workScore + scores[workIndex[i]];
            if (scores[workIndex[i]] != 0) {
                total++;
            }
        }

        if (total > 1)
            workScore = workScore / total;
        if(workScore==0)
            workScore = 340;

        total = 0;

        for (int i = 0; i < maritalIndex.length; i++) {
            maritalScore = maritalScore + scores[maritalIndex[i]];
            if (scores[maritalIndex[i]] != 0) {
                total++;
            }
        }
        if (total > 1)
            maritalScore = maritalScore / total;
        if(maritalScore==0)
            maritalScore = 340;

        total = 0;

        for (int i = 0; i < generalIndex.length; i++) {
            generalScore = generalScore + scores[generalIndex[i]];
            if (scores[generalIndex[i]] != 0) {
                total++;
            }
        }
        if (total > 1)
            generalScore = generalScore / total;
        if(generalScore==0)
            generalScore = 340;

        employmentScore = scores[employmentIndex[0]];
        if(employmentScore==0)
            employmentScore = 340;

        double totalScore = U_EMPLOYMENT*employmentScore + U_HEALTH*healthScore + U_MARITAL*maritalScore + U_GENERAL*generalScore + U_WORK_HABIT*workScore;
        Log.i("weby score", totalScore +"");
        score = (int) totalScore;
        return score;
    }

    public double getGeoMeanScore(int[] scores, int selectedQuestions){
        double  product = 1.0;
        for(int i =0; i<20;i++){
            if(scores[i]!=0)
                product = product * scores[i];
        }
        return Math.pow(product, 1.0 / selectedQuestions);
    }

}
