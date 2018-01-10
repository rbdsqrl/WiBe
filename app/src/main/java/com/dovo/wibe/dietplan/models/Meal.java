package com.dovo.wibe.dietplan.models;

import java.util.List;

/**
 * Created by Narendran on 21-07-2017.
 */

public class Meal {

    private String meal;
    private List<FoodItem> foodItem;
    private String timeOfMeal;
    private String completedTimeOfMeal;
    private boolean isCompleted;
    private boolean isMealChanged;

    public Meal() {
    }

    public Meal(String meal, List<FoodItem> foodItem, String timeOfMeal, String completedTimeOfMeal, boolean isCompleted, boolean isMealChanged) {
        this.meal = meal;
        this.foodItem = foodItem;
        this.timeOfMeal = timeOfMeal;
        this.completedTimeOfMeal = completedTimeOfMeal;
        this.isCompleted = isCompleted;
        this.isMealChanged = isMealChanged;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public List<FoodItem> getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(List<FoodItem> foodItem) {
        this.foodItem = foodItem;
    }

    public String getTimeOfMeal() {
        return timeOfMeal;
    }

    public void setTimeOfMeal(String timeOfMeal) {
        this.timeOfMeal = timeOfMeal;
    }

    public String getCompletedTimeOfMeal() {
        return completedTimeOfMeal;
    }

    public void setCompletedTimeOfMeal(String completedTimeOfMeal) {
        this.completedTimeOfMeal = completedTimeOfMeal;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public boolean isMealChanged() {
        return isMealChanged;
    }

    public void setMealChanged(boolean mealChanged) {
        isMealChanged = mealChanged;
    }
}
