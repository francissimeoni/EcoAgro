package com.EcoAgro.EcoAgro.Controladores;

import com.EcoAgro.EcoAgro.Entidades.Categorias;
import com.EcoAgro.EcoAgro.Entidades.Cultivos;
import com.EcoAgro.EcoAgro.Entidades.Usuarios;
import com.EcoAgro.EcoAgro.Entidades.Zonas;
import com.EcoAgro.EcoAgro.Excepciones.Excepciones;
import com.EcoAgro.EcoAgro.Repositorios.CategoriaRepositorio;
import com.EcoAgro.EcoAgro.Repositorios.CultivosRepositorio;
import com.EcoAgro.EcoAgro.Repositorios.ZonasRepositorio;
import com.EcoAgro.EcoAgro.Servicios.CategoriasServicios;
import com.EcoAgro.EcoAgro.Servicios.CultivosServicios;
import com.EcoAgro.EcoAgro.Servicios.ZonasServicios;

import antlr.collections.List;

import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/Cultivos")
public class ControladorCultivo {

    @Autowired
    CategoriasServicios cS;

    @Autowired
    CategoriaRepositorio cR;

    @Autowired
    CultivosServicios cSrv;

    @Autowired
    ZonasServicios zS;

    @Autowired
    ZonasRepositorio zR;

    @Autowired
    CultivosRepositorio clR;

    @GetMapping("/PaginaPrincipal")
    public String PaginaPrincipalCultivos(HttpSession session, ModelMap modelo) {

        Usuarios logueado = (Usuarios) session.getAttribute("SesionDeUsuario");

        if (logueado != null) {

            if (logueado.getRol().toString().equals("ADMINISTRADOR")) {
                modelo.put("sesion", "admin");
                modelo.put("noticias", clR.findAll());
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
    public String PersistirCultivo(@RequestParam(required = true) String nombre,
            @RequestParam(required = true) String informacion,
            @RequestParam(required = false) MultipartFile img, @RequestParam(required = true) String categoria,
            @RequestParam(required = true) String zona, ModelMap modelo)
            throws Excepciones {
        System.out.println("Titulo: " + nombre);

        try {
            cSrv.crearCultivo(nombre, informacion, img, zS.ObtenerDatosDeZonaPorId(zona),
                    cS.ObtenerCategoriaPorId(categoria));

            System.out.println("cago la fruta");
            return "redirect:/PaginaPrincipal";

        } catch (Excepciones e) {
            modelo.put("error", "hubo un error al cargar el posteo");
            System.out.println(e.getMessage());
            System.out.println("no cago la fruta");
            return "frmNuevoCultivo.html";

        }

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
    public String NuevoCultivo(ModelMap modelo) {

        modelo.put("categorias", cR.findAll());
        modelo.put("zonas", zR.findAll());

        return "frmNuevoCultivo";

    }
}
