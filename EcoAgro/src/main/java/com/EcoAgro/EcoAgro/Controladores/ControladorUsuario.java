package com.EcoAgro.EcoAgro.Controladores;

import org.springframework.stereotype.Controller;
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
