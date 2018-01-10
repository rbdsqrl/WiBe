package com.dovo.wibe.dietplan.models;

/**
 * Created by Narendran on 22-08-2017.
 */

public class NutritionDiary {

    private String nutritionDiaryId; //patientPhoneNo+date
    private String date;
    private String status;
    private boolean isReviewed;
    private boolean isChanged;
    private boolean isSubmitted;
    private String noteFromPatient;
    private NutritionDiaryDefinition nutritionDiaryDefinition;
    private String doctorPhoneNo;
    private String patientPhoneNo;

    public NutritionDiary() {
    }

    public NutritionDiary(NutritionDiaryDefinition nutritionDiaryDefinition, String date, String status, boolean isChanged, boolean isSubmitted) {
        this.patientPhoneNo = nutritionDiaryDefinition.getPatientPhoneNo();
        this.date = date;
        this.status = status;
        this.isChanged = isChanged;
        this.isSubmitted = isSubmitted;
        this.isReviewed = false;
        this.nutritionDiaryDefinition = nutritionDiaryDefinition;
        this.doctorPhoneNo =  nutritionDiaryDefinition.getDoctorPhoneNo();
    }

    public boolean isReviewed() {
        return isReviewed;
    }

    public String getPatientPhoneNo() {
        return patientPhoneNo;
    }

    public void setPatientPhoneNo(String patientPhoneNo) {
        this.patientPhoneNo = patientPhoneNo;
    }

    public void setReviewed(boolean reviewed) {
        isReviewed = reviewed;
    }

    public String getNutritionDiaryId() {
        return nutritionDiaryId;
    }

    public void setNutritionDiaryId(String nutritionDiaryId) {
        this.nutritionDiaryId = nutritionDiaryId;
    }

    public String getDoctorPhoneNo() {
        return doctorPhoneNo;
    }

    public void setDoctorPhoneNo(String doctorPhoneNo) {
        this.doctorPhoneNo = doctorPhoneNo;
    }

    public String getDate() {
        return date;
    }

    public String getNoteFromPatient() {
        return noteFromPatient;
    }

    public void setNoteFromPatient(String noteFromPatient) {
        this.noteFromPatient = noteFromPatient;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isChanged() {
        return isChanged;
    }

    public void setChanged(boolean changed) {
        isChanged = changed;
    }

    public boolean isSubmitted() {
        return isSubmitted;
    }

    public void setSubmitted(boolean submitted) {
        isSubmitted = submitted;
    }

    public NutritionDiaryDefinition getNutritionDiaryDefinition() {
        return nutritionDiaryDefinition;
    }

    public void setNutritionDiaryDefinition(NutritionDiaryDefinition nutritionDiaryDefinition) {
        this.nutritionDiaryDefinition = nutritionDiaryDefinition;
    }
}
