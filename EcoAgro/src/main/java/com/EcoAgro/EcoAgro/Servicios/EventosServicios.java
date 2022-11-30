package com.EcoAgro.EcoAgro.Servicios;

import com.EcoAgro.EcoAgro.Entidades.Eventos;
import com.EcoAgro.EcoAgro.Entidades.Usuarios;
import com.EcoAgro.EcoAgro.Interfaces.CapturadorErrores;
import com.EcoAgro.EcoAgro.Repositorios.EventosRepositorio;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class EventosServicios implements CapturadorErrores {
    
    @Autowired
    EventosRepositorio eR;
    
    public void NuevoEvento(String error) {
        
        try {
            Calendar c1 = GregorianCalendar.getInstance(Locale.CANADA);
            Eventos e = new Eventos();
            
            System.out.println(c1);
            e.setFecha(c1.getTime());
            e.setMotivo(error);
            
            eR.save(e);
        } catch (Exception e) {
            RegistrarEvento(e.getMessage());
        }
        
    }
    
    public List<Eventos> mostrarEventos() {
        
        List<Eventos> lista;
        lista = eR.ListarPorFechaAscendente();
        
        return lista;
        
    }
    
    @Override
    public void RegistrarEvento(String error) {
        EventosServicios eS = new EventosServicios();
        eS.NuevoEvento(error);
    }
    
}
