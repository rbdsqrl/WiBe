package com.dovo.wibe.services;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dovo.wibe.resources.WebyScores;

public class BaseWibeActivity extends AppCompatActivity {
    public WibeFrontendServices wibeFrontendServices;
    public WebyScores webyScores;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wibeFrontendServices = WibeFrontendServices.getInstance(getBaseContext());
        webyScores = WebyScores.getInstance();
    }
}
