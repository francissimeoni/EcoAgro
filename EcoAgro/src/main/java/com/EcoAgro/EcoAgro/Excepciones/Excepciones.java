package com.EcoAgro.EcoAgro.Excepciones;

import com.EcoAgro.EcoAgro.Servicios.EventosServicios;

public class Excepciones extends Exception {

    EventosServicios eS;

    public Excepciones(String msg) {
        super(msg);
        eS.NuevoEvento(msg);
    }

}
