package com.EcoAgro.EcoAgro.Controladores;

import com.EcoAgro.EcoAgro.Entidades.Zonas;
import com.EcoAgro.EcoAgro.Enums.Rol;
import com.EcoAgro.EcoAgro.Excepciones.Excepciones;
import com.EcoAgro.EcoAgro.Servicios.UsuariosServicios;
import com.EcoAgro.EcoAgro.Servicios.ZonasServicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Usuarios")
public class ControladorUsuario {

    @Autowired
    UsuariosServicios uS;
    @Autowired
    ZonasServicios zS;

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

}
