package com.EcoAgro.EcoAgro.Controladores;

import com.EcoAgro.EcoAgro.Entidades.Usuarios;
import com.EcoAgro.EcoAgro.Enums.Rol;
import com.EcoAgro.EcoAgro.Excepciones.Excepciones;
import com.EcoAgro.EcoAgro.Servicios.EventosServicios;
import com.EcoAgro.EcoAgro.Servicios.UsuariosServicios;
import com.EcoAgro.EcoAgro.Servicios.ZonasServicios;
import java.util.Date;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    EventosServicios eS;

    @GetMapping("/actualizarZonaUsuario")
    public String actualizarZonaUsuario(ModelMap modelo, String usr, String correo, String contraseña,
            Boolean activoSiNo, Date fechaDeRegistro, Rol rol, String telefono, String usuario, String idZona) throws Excepciones {

        try {
            
        uS.ModificarUsuario(usuario, contraseña, rol, zS.ObtenerDatosDeZonaPorId(idZona), fechaDeRegistro, correo, telefono, true);
        modelo.put("exito", "¡Datos modificados con exito!");
        
        } catch (Exception e) {
        
        }
        
        
        return null;

    }

    //@PreAuthorize("HasAnyRole('ROLE.ADMINISTRADOR','ROLE.PRODUCTOR')")
    @GetMapping("/PaginaPrincipal")
    public String PaginaPrincipal(ModelMap modelo, HttpSession session) throws Excepciones {

        Usuarios logueado = (Usuarios) session.getAttribute("SesionDeUsuario");
        Integer numero;
        if (logueado != null) {

            if (logueado.getRol().toString().equals("ADMINISTRADOR")) {
                modelo.put("sesion", "admin");

                return "index.html";

            } else {

                return "index.html";
            }

        } else {

            return "index.html";
        }
    }

    // @PreAuthorize("HasAnyRole('ROLE.ADMINISTRADOR','ROLE.PRODUCTOR')")
    //@GetMapping("/CrearZona")
    //public String CrearZona() throws Excepciones {
    /*  
        
        zS.CrearZona("SudOeste");
        zS.CrearZona("Centro Sur");
        zS.CrearZona("Oeste");
        zS.CrearZona("Centro");
        zS.CrearZona("Centro este");
        zS.CrearZona("Noroeste");

        // return "index.html";
        
     */
    //}
    @PostMapping("/PersistirUsuario")
    public String persistirUser(@RequestParam String usr,
            @RequestParam String pass,
            @RequestParam String email,
            @RequestParam String telefono, ModelMap modelo) throws Excepciones {

        try {
            System.out.println("usr");
            uS.CrearUsuario(usr, pass, Rol.ADMINISTRADOR, zS.ObtenerDatosDeZonaPorId("1"), null, email, telefono, true);
            modelo.put("exito", "Usuario cargado con exito");
            return "UsuarioCargadoConExito.html";

        } catch (Exception e) {
            modelo.put("error", "Hubo algun error en la carga del usuario");
            return "UsuarioCargadoConExito.html";
        }

    }

    @GetMapping("/Contacto")
    public String Contacto(ModelMap modelo) {

        return "contacto.html";
    }
    
     @GetMapping("/editarPerfil")
    public String editarPerfil(ModelMap modelo) {

        return "FrmEditarPerfil.html";
    }

    @GetMapping("/QuienesSomos")
    public String QuienesSomos(ModelMap modelo) {

        return "quienesSomos.html";
    }

    @GetMapping("/RedirectMain")
    public String redireccionandoUsuario() throws InterruptedException {

        Thread.sleep(2 * 1000);
        return "index.html";

    }

    @GetMapping("/PreguntasFrecuentes")
    public String PreguntasFrecuentes() {

        return "preguntas.html";

    }

    @GetMapping("/login")
    public String PaginaLogin(ModelMap modelo) {

        return "iniciarsesion.html";

    }

    @GetMapping("/failLoad")
    public String failLoad(ModelMap modelo) throws Excepciones {

        modelo.put("error", "Error de usuario y contraseña, verifique los parametros y vuelva a intentarlo");
        eS.NuevoEvento("Error de usuario y contraseña, verifique los parametros y vuelva a intentarlo");
        return "iniciarsesion.html";

    }

}
