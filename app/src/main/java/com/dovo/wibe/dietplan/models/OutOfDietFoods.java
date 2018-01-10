package com.dovo.wibe.dietplan.models;

import java.util.List;

/**
 * Created by Narendran on 21-07-2017.
 */

public class OutOfDietFoods {

    List<String> foodItems;
    List<String> mealTimes;
    List<String> portions;

    public OutOfDietFoods() {
    }

    public OutOfDietFoods(List<String> foodItems, List<String> mealTimes, List<String> portions) {
        this.foodItems = foodItems;
        this.mealTimes = mealTimes;
        this.portions = portions;
    }

    public List<String> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(List<String> foodItems) {
        this.foodItems = foodItems;
    }

    public List<String> getMealTimes() {
        return mealTimes;
    }

    public void setMealTimes(List<String> mealTimes) {
        this.mealTimes = mealTimes;
    }

    public List<String> getPortions() {
        return portions;
    }

    public void setPortions(List<String> portions) {
        this.portions = portions;
    }
}
