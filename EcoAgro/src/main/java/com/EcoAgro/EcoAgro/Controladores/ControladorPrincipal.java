package com.EcoAgro.EcoAgro.Controladores;

import com.EcoAgro.EcoAgro.Enums.Rol;
import com.EcoAgro.EcoAgro.Excepciones.Excepciones;
import com.EcoAgro.EcoAgro.Servicios.UsuariosServicios;
import com.EcoAgro.EcoAgro.Servicios.ZonasServicios;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ControladorPrincipal {

    @Autowired
    UsuariosServicios uS;

    @Autowired
    ZonasServicios zS;

    @GetMapping("/Userlogin")
    public String LogearUsuario(String usr, String pass) throws Excepciones {

        System.out.println(usr + " " + pass);

        if (uS.usuarioLogin(usr, pass) == true) {
            return "bienvendios.html";

        } else {
            return null;

        }

    }

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
    public void CrearUsuario() throws Excepciones {

       /*
        
        uS.CrearUsuario("Francis_simeoni", "Edurdo75", Rol.ADMINISTRADOR, zS.ObtenerDatosDeZonaPorId("1"), null, "fransissimeoni75@gmail.com", "03482-222779", true);
        uS.CrearUsuario("Daniel_Masin", "27422379", Rol.PRODUCTOR, zS.ObtenerDatosDeZonaPorId("2"), null, "masdaniel31@gmail.com", "03482-430416", true);
        uS.CrearUsuario("Israel_Poveda", "123456", Rol.USUARIO, zS.ObtenerDatosDeZonaPorId("3"), null, "masdaniel31@gmail.com", "03482-430416", true); //new Date(Long.getLong("2/6/2022"))

        // return "index.html";
        
        */
    }
}
