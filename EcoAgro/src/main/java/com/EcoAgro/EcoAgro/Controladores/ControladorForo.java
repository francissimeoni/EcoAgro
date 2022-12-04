
package com.EcoAgro.EcoAgro.Controladores;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.EcoAgro.EcoAgro.Entidades.Usuarios;

@Controller
@RequestMapping("/foro")
public class ControladorForo {

    @GetMapping("/PaginaPrincipal")
    public String PaginaPrincipal(ModelMap modelo, HttpSession session) {

        Usuarios logueado = (Usuarios) session.getAttribute("SesionDeUsuario");

        if (logueado != null) {

        } else {

        }

        return "indexForo.html";
    }

}
