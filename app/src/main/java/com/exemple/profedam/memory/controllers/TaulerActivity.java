package com.exemple.profedam.memory.controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import com.exemple.profedam.memory.R;
import com.exemple.profedam.memory.model.Partida;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import android.content.SharedPreferences;

public class TaulerActivity extends Activity {


    private GridView gv;
    private Partida partida;
    private ImageAdapter adapter;
    private Cronometro cronometro;


    public GridView getGv() {
        return gv;
    }

    public void setGv(GridView gv) {
        this.gv = gv;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    DateFormat dateForm = new SimpleDateFormat("dd MMMM yyyy");
    String dateOutput = dateForm.format(new Date());

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        gv = (GridView) findViewById(R.id.gridViewMemory);

        this.partida = new Partida(getIntent().getIntExtra("cartas", 12));
        cronometro = new Cronometro(3000 * getIntent().getIntExtra("cartas", 12), 1000, this);
        cronometro.start();

        adapter = new ImageAdapter(this, partida);
        GeneralListener listener = new GeneralListener(this);
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(listener);


    }

    public Cronometro getCronometro() {
        return cronometro;
    }


    public void refrescarTablero() {
        gv.setAdapter(adapter);
        gv.refreshDrawableState();
    }


}


