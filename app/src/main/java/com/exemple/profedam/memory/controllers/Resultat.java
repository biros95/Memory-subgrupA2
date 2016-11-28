package com.exemple.profedam.memory.controllers;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.exemple.profedam.memory.R;

/**
 * Created by MarcosPortatil on 24/11/2016.
 */

public class Resultat extends Activity {
    private TaulerActivity tauler;

    private long punts;

    private SharedPreferences preferences;

    private String names[];
    private long score[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);


        TextView t = (TextView) findViewById(R.id.txt_guanyador);
        if ((getIntent().getLongExtra("Segons", 0) / 1000) > 1) {
            t.setText("Has ganado! y te han sobrado: " + String.valueOf(getIntent().getLongExtra("Segons", 0) / 1000) + " segundos");

        } else {
            t.setText("Lo sentimos, has perdido");
        }


    }
}

