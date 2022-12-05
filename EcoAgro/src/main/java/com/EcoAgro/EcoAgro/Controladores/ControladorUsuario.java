package com.EcoAgro.EcoAgro.Controladores;

import com.EcoAgro.EcoAgro.Entidades.Usuarios;
import com.EcoAgro.EcoAgro.Entidades.Zonas;
import com.EcoAgro.EcoAgro.Enums.Rol;
import com.EcoAgro.EcoAgro.Excepciones.Excepciones;
import com.EcoAgro.EcoAgro.Repositorios.SolicitudesDeActualizacionDeRolesRepository;
import com.EcoAgro.EcoAgro.Servicios.SolicitudesDeActualizacionDeRolesServicio;
import com.EcoAgro.EcoAgro.Servicios.UsuariosServicios;
import com.EcoAgro.EcoAgro.Servicios.ZonasServicios;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    SolicitudesDeActualizacionDeRolesRepository sarR;

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

    @GetMapping("/SolicitarActualizacionDeRol/{rolU}")
    public String actualizarRol(@PathVariable String rolU, ModelMap modelo, @RequestParam String IdUsuario,
            @RequestParam String rol,
            HttpSession session) {

        Usuarios logueado = (Usuarios) session.getAttribute("SesionDeUsuario");

        if (rolU.equalsIgnoreCase("ADMINISTRADOR")) {
            sarS.CrearSolicitud(logueado, Rol.ADMINISTRADOR);
        }

        if (rolU.equalsIgnoreCase("PRODUCTOR")) {
            sarS.CrearSolicitud(logueado, Rol.PRODUCTOR);
        }

        if (rolU.equalsIgnoreCase("USUARIO")) {
            sarS.CrearSolicitud(logueado, Rol.USUARIO);
        }

        return "frmNuevoUsuario.html";

    }

    @GetMapping("/gestionarRol")
    public void gestionarRol(ModelMap modelo, @RequestParam String IdUsuario,
            @RequestParam String rol,
            Boolean AutorizoSiNo) throws Excepciones {

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

    @GetMapping("/actualizarRoles")
    public String actualizarRoles(ModelMap modelo) {

        modelo.put("solicitudes", sarR.findAll());
        return "tablaGestionDeUsuarios.html";
    }
}
