package com.EcoAgro.EcoAgro.Controladores;

import com.EcoAgro.EcoAgro.Entidades.Usuarios;
import com.EcoAgro.EcoAgro.Servicios.UsuariosServicios;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productor")
public class ControladorProductor {

    @Autowired
    UsuariosServicios uS;

    @GetMapping("/PaginaPrincipal")
    public String PaginaPrincipal(ModelMap modelo, HttpSession session) {

        return "Index.html";

    }

    @GetMapping("/dashboard")
    public String dashboard(ModelMap modelo) {

        return "dashBoardProductor.html";

    }

}
