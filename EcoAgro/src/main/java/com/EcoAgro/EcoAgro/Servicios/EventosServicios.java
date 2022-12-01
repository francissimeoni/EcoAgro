package com.EcoAgro.EcoAgro.Servicios;

import com.EcoAgro.EcoAgro.Entidades.Eventos;
import com.EcoAgro.EcoAgro.Entidades.Usuarios;
import com.EcoAgro.EcoAgro.Repositorios.EventosRepositorio;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class EventosServicios {

    @Autowired
    EventosRepositorio eventoRepositorio;

    @Transactional
    public void NuevoEvento(String error) {

        Eventos evt = new Eventos();
        Date fecha = new Date();

        Integer año = fecha.getYear();
        Integer mes = fecha.getMonth();
        Integer dia = fecha.getDay() - 3;
        Integer hora = fecha.getHours() - 3;
        Integer minutos = fecha.getMinutes();
        Integer segundos = fecha.getSeconds();

        Date fechaEditada = new Date(año, mes, dia, hora, minutos, segundos);

        System.out.println(fechaEditada);

        evt.setFecha(fechaEditada);
        evt.setMotivo(error);

        System.out.println(evt.getFecha());

        eventoRepositorio.save(evt);

    }

    public List<Eventos> mostrarEventos() {
        List<Eventos> lista = eventoRepositorio.ListarPorFechaAscendente();
        return lista;
    }
}
