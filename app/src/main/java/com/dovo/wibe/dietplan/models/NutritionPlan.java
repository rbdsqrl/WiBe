package com.dovo.wibe.dietplan.models;



/**
 * Created by Narendran on 22-08-2017.
 */
public class NutritionPlan {


    private Long nutritionPlanId;
    public boolean isActive;
    public String planName;
    public String doctorPhoneNo;
    public FoodRecord foodRecord;


    public NutritionPlan() {
    }

    public NutritionPlan(String doctorPhoneNo, String planName, FoodRecord foodRecordDefinition) {
        this.planName = planName;
        this.foodRecord = foodRecordDefinition;
        this.doctorPhoneNo = doctorPhoneNo;
    }

    public String getDoctorPhoneNo() {
        return doctorPhoneNo;
    }

    public void setDoctorPhoneNo(String doctorPhoneNo) {
        this.doctorPhoneNo = doctorPhoneNo;
    }

    public Long getNutritionPlanId() {
        return nutritionPlanId;
    }

    public void setNutritionPlanId(Long nutritionPlanId) {
        this.nutritionPlanId = nutritionPlanId;
    }

    public FoodRecord getFoodRecord() {
        return foodRecord;
    }

    public void setFoodRecord(FoodRecord foodRecord) {
        this.foodRecord = foodRecord;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

}
