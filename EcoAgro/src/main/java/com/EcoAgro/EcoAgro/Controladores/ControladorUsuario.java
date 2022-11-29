package com.EcoAgro.EcoAgro.Controladores;

import com.EcoAgro.EcoAgro.Servicios.UsuariosServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Usuarios")
public class ControladorUsuario {



    @GetMapping("/CrearUsuario")
    public String CrearUsuario() {

        return "frmNuevoUsuario.html";
    }

}
