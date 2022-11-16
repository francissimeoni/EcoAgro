package com.EcoAgro.EcoAgro.Servicios;

import com.EcoAgro.EcoAgro.Entidades.Zonas;
import com.EcoAgro.EcoAgro.Repositorios.ZonasRepositorio;
import java.util.ArrayList;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

public class ZonasServicios {

    @Autowired
    Zonas Objzonas = new Zonas();

    @Autowired
    ZonasRepositorio zR;

    @Transactional
    public void CrearZona(String nombreZona) {

        Objzonas.setNombre(nombreZona);
        zR.save(Objzonas);

    }

    @Transactional
    public void ModificarZona(String id, String nombreZona) {
        Objzonas = ObtenerDatosDeZonaPorId(id);
        Objzonas.setNombre(nombreZona);
        zR.save(Objzonas);
    }

    @Transactional
    public void EliminarZona(String id) {
        Objzonas.setNombre(id);
        zR.save(Objzonas);
    }

    public Zonas ObtenerDatosDeZonaPorId(String id) {

        Optional<Zonas> z = zR.findById(id);

        if (z.isPresent()) {
            return z.get();

        } else {
            return null;
        }

    }

    public ArrayList<Zonas> DevolverZonasFull() {

        ArrayList<Zonas> arrZ = new ArrayList<>();
        arrZ.addAll(zR.findAll());

        return arrZ;
    }

    public void validaciones(String nombrezona) {
    
    }
}
