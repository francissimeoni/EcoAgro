package com.EcoAgro.EcoAgro.Servicios;

import com.EcoAgro.EcoAgro.Entidades.SolicitudesDeActualizacionDeRoles;
import com.EcoAgro.EcoAgro.Entidades.Usuarios;
import com.EcoAgro.EcoAgro.Enums.Rol;
import com.EcoAgro.EcoAgro.Excepciones.Excepciones;
import com.EcoAgro.EcoAgro.Repositorios.SolicitudesDeActualizacionDeRolesRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class SolicitudesDeActualizacionDeRolesServicio {

    @Autowired
    SolicitudesDeActualizacionDeRolesRepository sar;

    @Autowired
    EventosServicios eS;

    @Transactional
    public void CrearSolicitud(Usuarios usuario, Rol rol) {

        try {
            SolicitudesDeActualizacionDeRoles solicitud = new SolicitudesDeActualizacionDeRoles();
            solicitud.setUsuario(usuario);
            solicitud.setRolSolicitado(rol);
            sar.save(solicitud);
        } catch (Exception e) {

            eS.NuevoEvento(e.getMessage());

        }

    }

    @Transactional
    public void EliminarSolicitud(String IdSolicitud) throws Excepciones {

        Optional<SolicitudesDeActualizacionDeRoles> op = sar.findById(IdSolicitud);

        if (op.isPresent()) {
            sar.delete(op.get());
        } else {

            throw new Excepciones("No se encuentra usuario con esa id");
        }

    }

}
