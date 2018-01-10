package com.dovo.wibe.dietplan.models;
import java.util.Date;
import java.util.List;

/**
 * Created by Narendran on 21-07-2017.
 */

public class NutritionDiaryDefinition {

    private Long diaryDefinitionId;
    public String doctorPhoneNo;
    public String patientPhoneNo;
    public String monitoringFrequency;
    public Date startDate;
    public long durationOfPlan;
    NutritionPlan nutritionPlan;
    List<String> pendingDates;
    public boolean isEmpty;
    public String dateOfDiary;
    public boolean isActive;

    public NutritionDiaryDefinition() {

    }

    public NutritionDiaryDefinition(NutritionPlan nutritionPlan, String planName, FoodRecord foodRecordDefinition, String doctorPhoneNo, String patientPhoneNo, String monitoringFrequency, Date startDate, long durationOfPlan) {
        this.doctorPhoneNo = doctorPhoneNo;
        this.patientPhoneNo = patientPhoneNo;
        this.monitoringFrequency = monitoringFrequency;
        this.startDate = startDate;
        this.durationOfPlan = durationOfPlan;
        this.nutritionPlan = nutritionPlan;
    }

    public NutritionPlan getNutritionPlan() {
        return nutritionPlan;
    }

    public void setNutritionPlan(NutritionPlan nutritionPlan) {
        this.nutritionPlan = nutritionPlan;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public String getDateOfDiary() {
        return dateOfDiary;
    }

    public void setDateOfDiary(String dateOfDiary) {
        this.dateOfDiary = dateOfDiary;
    }

    public Long getDiaryDefinitionId() {
        return diaryDefinitionId;
    }

    public void setDiaryDefinitionId(Long diaryDefinitionId) {
        this.diaryDefinitionId = diaryDefinitionId;
    }


    public String getDoctorPhoneNo() {
        return doctorPhoneNo;
    }


    public List<String> getPendingDates() {
        return pendingDates;
    }

    public void setPendingDates(List<String> pendingDates) {
        this.pendingDates = pendingDates;
    }

    public void setDoctorPhoneNo(String doctorPhoneNo) {
        this.doctorPhoneNo = doctorPhoneNo;
    }

    public String getPatientPhoneNo() {
        return patientPhoneNo;
    }

    public void setPatientPhoneNo(String patientPhoneNo) {
        this.patientPhoneNo = patientPhoneNo;
    }

    public String getMonitoringFrequency() {
        return monitoringFrequency;
    }

    public void setMonitoringFrequency(String monitoringFrequency) {
        this.monitoringFrequency = monitoringFrequency;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public long getDurationOfPlan() {
        return durationOfPlan;
    }

    public void setDurationOfPlan(long durationOfPlan) {
        this.durationOfPlan = durationOfPlan;
    }


}
