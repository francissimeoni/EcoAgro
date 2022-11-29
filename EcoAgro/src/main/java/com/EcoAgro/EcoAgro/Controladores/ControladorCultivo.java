package com.EcoAgro.EcoAgro.Controladores;

import com.EcoAgro.EcoAgro.Entidades.Cultivos;
import com.EcoAgro.EcoAgro.Entidades.Usuarios;
import com.EcoAgro.EcoAgro.Servicios.CategoriasServicios;
import java.util.Date;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Cultivos")
public class ControladorCultivo {

    @Autowired
    CategoriasServicios cS;

    @GetMapping("/PaginaPrincipal")
    public String PaginaPrincipalCultivos(HttpSession session, ModelMap modelo) {

        Usuarios logueado = (Usuarios) session.getAttribute("SesionDeUsuario");

        if (logueado != null) {

            if (logueado.getRol().toString().equals("ADMINISTRADOR")) {
                modelo.put("sesion", "admin");
                return "cultivos.html";

            } else {
                return "cultivos.html";
            }

        } else {

            return "cultivos.html";
        }

    }

    @GetMapping("/VerCultivo")
    public String verCultivo(HttpSession session, ModelMap modelo) {
        Usuarios logueado = (Usuarios) session.getAttribute("SesionDeUsuario");

        if (logueado != null) {

            if (logueado.getRol().toString().equals("ADMINISTRADOR")) {
                modelo.put("sesion", "admin");
                return "info.html";

            } else {

                return "info.html";
            }

        } else {

            return "info.html";
        }

    }

    @PostMapping("/PersistirCultivo")
    public void PersistirCultivo(@RequestParam String nombre, @RequestParam Date siembra,
            @RequestParam Date cosecha, @RequestParam Float dp, @RequestParam Float rh, @RequestParam Float tc,
            @RequestParam Boolean sd, @RequestParam Float tm, @RequestParam Float toc) {

    }

    @GetMapping("/CargarCategoria")
    public String cargarCategoria() {

        cS.crearCategoria("Legumbres");
        cS.crearCategoria("Hierbas");
        cS.crearCategoria("Frutas");
        cS.crearCategoria("Verduras");
        cS.crearCategoria("Hortalizas");
        cS.crearCategoria("Gramineas");

        return "info.html";
    }

    @GetMapping("/NuevoCultivo")
    public String NuevoCultivo(HttpSession session, ModelMap modelo) {
        Usuarios logueado = (Usuarios) session.getAttribute("SesionDeUsuario");

        if (logueado != null) {

            if (logueado.getRol().toString().equals("ADMINISTRADOR")) {
                modelo.put("sesion", "admin");
                return "frmNuevoCultivo";

            } else {

                return "frmNuevoCultivo";
            }

        } else {

            return "frmNuevoCultivo";
        }

    }
}
