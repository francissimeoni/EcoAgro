package com.EcoAgro.EcoAgro.Controladores;

import com.EcoAgro.EcoAgro.Entidades.Usuarios;
import com.EcoAgro.EcoAgro.Enums.Rol;
import com.EcoAgro.EcoAgro.Excepciones.Excepciones;
import com.EcoAgro.EcoAgro.Servicios.UsuariosServicios;
import com.EcoAgro.EcoAgro.Servicios.ZonasServicios;
import java.util.Date;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class ControladorPrincipal {

    @Autowired
    UsuariosServicios uS;

    @Autowired
    ZonasServicios zS;

   

    @PreAuthorize("HasAnyRole('ROLE.ADMINISTRADOR','ROLE.PRODUCTOR')")
    @GetMapping("/CrearZona")
    public void CrearZona() throws Excepciones {

        /*  
        
        zS.CrearZona("SudOeste");
        zS.CrearZona("Centro Sur");
        zS.CrearZona("Oeste");
        zS.CrearZona("Centro");
        zS.CrearZona("Centro este");
        zS.CrearZona("Noroeste");

        // return "index.html";
        
         */
    }

    @GetMapping("/CrearUsuario")
    public String CrearUsuario() {

        return "frmNuevoUsuario.html";
    }

    @PostMapping("/PersistirUsuario")
    public String persistirUser(@RequestParam String usr, @RequestParam String pass, @RequestParam String email, @RequestParam String telefono) throws Excepciones {

        System.out.println("usr");
        uS.CrearUsuario(usr, pass, Rol.ADMINISTRADOR, zS.ObtenerDatosDeZonaPorId("1"), null, email, telefono, true);

        return "index.html";
    }

    @GetMapping("/PaginaPrincipal")
    public String PaginaPrincipal() {
        // HttpSession session 
        /* Usuarios logueado = (Usuarios) session.getAttribute("SesionDeUsuario");

        if (logueado.getRol().toString().equals("ADMINISTRADOR")) {
            return "redirect:/admin/dashboard";
        }
         */
        return "index.html";

    }

    @GetMapping("/login")
    public String PaginaLogin() {

        return "iniciarsesion.html";
    }

}
