package com.EcoAgro.EcoAgro.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Cultivos")
public class ControladorCultivo {

    @GetMapping("/PaginaPrincipal")
    public String PaginaPrincipalCultivos() {

        return "cultivos.html";
    }
}
