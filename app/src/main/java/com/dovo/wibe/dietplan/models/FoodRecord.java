package com.dovo.wibe.dietplan.models;

/**
 * Created by Narendran on 21-07-2017.
 */

public class FoodRecord {
    private double dailyCalorieIntake;
    private double actualCalorieIntake;
    private Meal preBreakfast;
    private Meal breakfast;
    private Meal lunch;
    private Meal dinner;
    private Meal mornSnack;
    private Meal eveSnack;
    private OutOfDietFoods outOfPlanFood;
    private boolean isChanged;


    public FoodRecord() {
    }

    public FoodRecord(double dailyCalorieIntake, Meal preBreakfast, Meal breakfast, Meal lunch, Meal dinner, Meal mornSnack, Meal eveSnack, OutOfDietFoods outOfPlanFood, boolean isChanged) {
        this.dailyCalorieIntake = dailyCalorieIntake;
        this.preBreakfast = preBreakfast;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
        this.mornSnack = mornSnack;
        this.eveSnack = eveSnack;
        this.outOfPlanFood = outOfPlanFood;
        this.isChanged = isChanged;
    }

    public FoodRecord(Meal breakfast, Meal lunch, Meal dinner, Meal mornSnack, Meal eveSnack, OutOfDietFoods outOfPlanFood, double dailyCalorieIntake, boolean isChanged) {
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
        this.mornSnack = mornSnack;
        this.eveSnack = eveSnack;
        this.outOfPlanFood = outOfPlanFood;
        this.dailyCalorieIntake = dailyCalorieIntake;
        this.isChanged = isChanged;
    }

    public Meal getPreBreakfast() {
        return preBreakfast;
    }

    public void setPreBreakfast(Meal preBreakfast) {
        this.preBreakfast = preBreakfast;
    }

    public double getDailyCalorieIntake() {
        return dailyCalorieIntake;
    }

    public void setDailyCalorieIntake(double dailyCalorieIntake) {
        this.dailyCalorieIntake = dailyCalorieIntake;
    }

    public double getActualCalorieIntake() {
        return actualCalorieIntake;
    }

    public void setActualCalorieIntake(double actualCalorieIntake) {
        this.actualCalorieIntake = actualCalorieIntake;
    }

    public Meal getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(Meal breakfast) {
        this.breakfast = breakfast;
    }

    public Meal getLunch() {
        return lunch;
    }

    public void setLunch(Meal lunch) {
        this.lunch = lunch;
    }

    public Meal getDinner() {
        return dinner;
    }

    public void setDinner(Meal dinner) {
        this.dinner = dinner;
    }

    public Meal getMornSnack() {
        return mornSnack;
    }

    public void setMornSnack(Meal mornSnack) {
        this.mornSnack = mornSnack;
    }

    public Meal getEveSnack() {
        return eveSnack;
    }

    public void setEveSnack(Meal eveSnack) {
        this.eveSnack = eveSnack;
    }

    public OutOfDietFoods getOutOfPlanFood() {
        return outOfPlanFood;
    }

    public void setOutOfPlanFood(OutOfDietFoods outOfPlanFood) {
        this.outOfPlanFood = outOfPlanFood;
    }

    public boolean isChanged() {
        return isChanged;
    }

    public void setChanged(boolean changed) {
        isChanged = changed;
    }
}
