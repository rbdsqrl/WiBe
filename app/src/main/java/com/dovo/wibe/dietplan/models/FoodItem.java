package com.dovo.wibe.dietplan.models;

/**
 * Created by Narendran on 21-07-2017.
 */

public class FoodItem {

    private String foodItem;
    private FoodInfo foodInfo;
    private int recommendedPortion;
    private String iconCategory;
    private int consumedPortion;
    private boolean isRecommendationMet;
    private boolean isFoodChanged;

    public FoodItem() {
    }

    public FoodItem(String foodItem, int recommendedPortion, String iconCategory, int consumedPortion, boolean isRecommendationMet, boolean isFoodChanged) {
        this.foodItem = foodItem;
        this.recommendedPortion = recommendedPortion;
        this.iconCategory = iconCategory;
        this.consumedPortion = consumedPortion;
        this.isRecommendationMet = isRecommendationMet;
        this.isFoodChanged = isFoodChanged;
    }

    public String getFoodItem() {
        return foodItem;
    }



    public FoodInfo getFoodInfo() {
        return foodInfo;
    }

    public void setFoodInfo(FoodInfo foodInfo) {
        this.foodInfo = foodInfo;
    }

    public void setFoodItem(String foodItem) {
        this.foodItem = foodItem;
    }

    public int getRecommendedPortion() {
        return recommendedPortion;
    }

    public void setRecommendedPortion(int recommendedPortion) {
        this.recommendedPortion = recommendedPortion;
    }

    public String getIconCategory() {
        return iconCategory;
    }

    public void setIconCategory(String iconCategory) {
        this.iconCategory = iconCategory;
    }

    public int getConsumedPortion() {
        return consumedPortion;
    }

    public void setConsumedPortion(int consumedPortion) {
        this.consumedPortion = consumedPortion;
    }

    public boolean isRecommendationMet() {
        return isRecommendationMet;
    }

    public void setRecommendationMet(boolean recommendationMet) {
        isRecommendationMet = recommendationMet;
    }

    public boolean isFoodChanged() {
        return isFoodChanged;
    }

    public void setFoodChanged(boolean foodChanged) {
        isFoodChanged = foodChanged;
    }
}
