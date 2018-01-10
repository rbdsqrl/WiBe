package com.dovo.wibe.dietplan.models;

/**
 * Created by Narendran on 25-08-2017.
 */

public class FoodInfo {
    private String foodName;
    private String servingAmount;
    private String servingUnit;
    private String calories;
    private String carbohydrate;
    private String protein;
    private String fat;
    private String fiber;
    private String servingDescription;
    private String photoPath;

    public FoodInfo() {
    }

    public FoodInfo(String servingDescription,String foodName, String servingAmount, String servingUnit, String calories, String carbohydrate, String protein, String fat, String fiber) {
        this.foodName = foodName;
        this.servingDescription = servingDescription;
        this.servingAmount = servingAmount;
        this.servingUnit = servingUnit;
        this.calories = calories;
        this.carbohydrate = carbohydrate;
        this.protein = protein;
        this.fat = fat;
        this.fiber = fiber;
    }

    public String getServingDescription() {
        return servingDescription;
    }

    public void setServingDescription(String servingDescription) {
        this.servingDescription = servingDescription;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getServingAmount() {
        return servingAmount;
    }

    public void setServingAmount(String servingAmount) {
        this.servingAmount = servingAmount;
    }

    public String getServingUnit() {
        return servingUnit;
    }

    public void setServingUnit(String servingUnit) {
        this.servingUnit = servingUnit;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(String carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getFiber() {
        return fiber;
    }

    public void setFiber(String fiber) {
        this.fiber = fiber;
    }
}