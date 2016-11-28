package com.exemple.profedam.memory.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.exemple.profedam.memory.model.Carta;
import com.exemple.profedam.memory.model.Partida;

import java.util.ArrayList;

public class GeneralListener implements AdapterView.OnItemClickListener, Runnable{

    private TaulerActivity tauler;
    private Carta cartaOnClick;
    private boolean isActive = true;
    private ArrayList<Carta> listaCartasFront;
    private int contador=0;


    public GeneralListener(TaulerActivity tauler) {
        this.tauler = tauler;
    }

    public int getContador() {
        return contador;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {


        Partida partida = tauler.getPartida();
        if (isActive) {
            //view.setVisibility(View.INVISIBLE);

            cartaOnClick = tauler.getPartida().getLlistaCartes().get(position);

            cartaOnClick.girar();

            tauler.refrescarTablero();

            listaCartasFront = partida.mostrarCartasFront();

            if (listaCartasFront.size() == 2 && listaCartasFront.get(0).getFrontImage() != listaCartasFront.get(1).getFrontImage()) {
                //Si entra en el if, las cartas no son correctas y espera 0,5 segundos en girarlas de nuevo
                isActive = false;

                Handler delay = new Handler();
                delay.postDelayed(this, 500);

            } else if (listaCartasFront.size() == 2) {
                listaCartasFront.get(0).setEstat(Carta.Estat.FIXED);
                listaCartasFront.get(1).setEstat(Carta.Estat.FIXED);
                Toast.makeText(this.tauler, "CORRECTE!", Toast.LENGTH_SHORT).show();


                comprovarFinal();
            }
        }
    }
    boolean comprovarFinal(){ /*Metodo que cuenta cada vez que se ejecuta el número de cartas que se han
        levantado, en caso de que sea igual al número de cartas en mesa devuelve un true.
*/
        contador++;
        if (contador==tauler.getPartida().getNumeroCartes()/2){
            Intent i = new Intent(tauler, Resultat.class);

            i.putExtra("Segons", tauler.getCronometro().getSegonsRestants());
            tauler.startActivity(i);
        }
    return true;
    }

    @Override
    public void run() { //Metodo para girar las cartas.
        listaCartasFront.get(0).girar();
        listaCartasFront.get(1).girar();
        tauler.refrescarTablero();
        isActive = true;

    }


}

