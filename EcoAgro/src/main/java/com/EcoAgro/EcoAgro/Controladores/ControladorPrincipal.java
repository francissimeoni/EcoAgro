package com.EcoAgro.EcoAgro.Controladores;

import com.EcoAgro.EcoAgro.Entidades.Usuarios;
import com.EcoAgro.EcoAgro.Entidades.Zonas;
import com.EcoAgro.EcoAgro.Enums.Rol;
import com.EcoAgro.EcoAgro.Excepciones.Excepciones;
import com.EcoAgro.EcoAgro.Repositorios.CultivosRepositorio;
import com.EcoAgro.EcoAgro.Servicios.CultivosServicios;
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
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @Autowired
    CultivosRepositorio clR;

    // @PreAuthorize("HasAnyRole('ROLE.ADMINISTRADOR','ROLE.PRODUCTOR')")
    @GetMapping("/PaginaPrincipal")
    public String PaginaPrincipal(ModelMap modelo, HttpSession session) throws Excepciones {

        Usuarios logueado = (Usuarios) session.getAttribute("SesionDeUsuario");

        modelo.put("noticias", clR.Obtener3Noticias());

        return "index.html";

    }

    // @PreAuthorize("HasAnyRole('ROLE.ADMINISTRADOR','ROLE.PRODUCTOR')")
    // @GetMapping("/CrearZona")
    // public String CrearZona() throws Excepciones {
    /*
     * 
     * zS.CrearZona("SudOeste");
     * zS.CrearZona("Centro Sur");
     * zS.CrearZona("Oeste");
     * zS.CrearZona("Centro");
     * zS.CrearZona("Centro este");
     * zS.CrearZona("Noroeste");
     * 
     * // return "index.html";
     * 
     */
    // }
    @PostMapping("/PersistirUsuario")
    public String persistirUser(@RequestParam String usr,
            @RequestParam String pass,
            @RequestParam String email,
            @RequestParam String telefono, String zona, ModelMap modelo) throws Excepciones {

        System.out.println(zona);

        try {
            System.out.println("usr");
            uS.CrearUsuario(usr, pass, Rol.USUARIO, zS.ObtenerDatosDeZonaPorId("1"),
                    null, email, telefono, true);
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
