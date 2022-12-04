package com.EcoAgro.EcoAgro.Controladores;

import com.EcoAgro.EcoAgro.Entidades.Zonas;
import com.EcoAgro.EcoAgro.Enums.Rol;
import com.EcoAgro.EcoAgro.Excepciones.Excepciones;
import com.EcoAgro.EcoAgro.Servicios.SolicitudesDeActualizacionDeRolesServicio;
import com.EcoAgro.EcoAgro.Servicios.UsuariosServicios;
import com.EcoAgro.EcoAgro.Servicios.ZonasServicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Usuarios")
public class ControladorUsuario {

    @Autowired
    UsuariosServicios uS;
    @Autowired
    ZonasServicios zS;
    @Autowired
    SolicitudesDeActualizacionDeRolesServicio sarS;

    @GetMapping("/CrearUsuario")
    public String CrearUsuario(ModelMap modelo) {

        modelo.put("zonas", zS.DevolverZonasFull());
        return "frmNuevoUsuario.html";

    }

    @PostMapping("/modificarUsuario")
    public String modificarUsuario(ModelMap modelo, String nombre, String contraseña, String rs,
            String correo, String telefono, Boolean activosiNo) throws Excepciones {

        uS.ModificarUsuario(nombre, contraseña, zS.ObtenerDatosDeZonaPorId(rs), correo, telefono,
                activosiNo);
        return "frmNuevoUsuario.html";

    }

    @GetMapping("/SolicitarActualizacionDeRol")
    public String actualizarRol(ModelMap modelo, @RequestParam String IdUsuario, @RequestParam String rol) {

        if (rol.equalsIgnoreCase("ADMINISTRADOR")) {
            sarS.CrearSolicitud(IdUsuario, Rol.ADMINISTRADOR);
        }

        if (rol.equalsIgnoreCase("PRODUCTOR")) {
            sarS.CrearSolicitud(IdUsuario, Rol.PRODUCTOR);
        }

        if (rol.equalsIgnoreCase("USUARIO")) {
            sarS.CrearSolicitud(IdUsuario, Rol.USUARIO);
        }

        return "frmNuevoUsuario.html";

    }

    @GetMapping("/gestionarRol")
    public void gestionarRol(ModelMap modelo, @RequestParam String IdUsuario, @RequestParam String rol, Boolean AutorizoSiNo) throws Excepciones {

        if (AutorizoSiNo == true) {

            try {

                if (rol.equalsIgnoreCase("ADMINISTRADOR")) {
                    uS.cambiarRol(Rol.ADMINISTRADOR, IdUsuario);
                }

                if (rol.equalsIgnoreCase("PRODUCTOR")) {
                    uS.cambiarRol(Rol.PRODUCTOR, IdUsuario);
                }

                if (rol.equalsIgnoreCase("USUARIO")) {
                    uS.cambiarRol(Rol.USUARIO, IdUsuario);
                }

                sarS.EliminarSolicitud(IdUsuario);

                modelo.put("exito", "exito");

            } catch (Exception e) {

                modelo.put("error", "error");

            }

        } else {

            try {
                sarS.EliminarSolicitud(IdUsuario);
                modelo.put("exito", "exito");

            } catch (Exception e) {

                modelo.put("error", "error");

            }

        }
    }
}
