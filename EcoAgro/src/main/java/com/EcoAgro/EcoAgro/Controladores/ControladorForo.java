package com.EcoAgro.EcoAgro.Controladores;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.EcoAgro.EcoAgro.Entidades.Usuarios;
import com.EcoAgro.EcoAgro.Excepciones.Excepciones;
import com.EcoAgro.EcoAgro.Repositorios.CategoriaRepositorio;
import com.EcoAgro.EcoAgro.Repositorios.PosteosRepositorio;
import com.EcoAgro.EcoAgro.Servicios.CategoriasServicios;
import com.EcoAgro.EcoAgro.Servicios.EventosServicios;
import com.EcoAgro.EcoAgro.Servicios.PosteosServicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/foro")
public class ControladorForo {

    @Autowired
    CategoriaRepositorio cR;

    @Autowired
    CategoriasServicios cS;

    @Autowired
    PosteosServicios pS;

    @Autowired
    EventosServicios eS;

    @Autowired
    PosteosRepositorio pR;

    @GetMapping("/PaginaPrincipal")
    public String PaginaPrincipal(ModelMap modelo, HttpSession session) {

        modelo.put("categorias", cR.findAll());
        modelo.put("posteos", pR.findAll());
        modelo.put("mv", pR.ObtenerPostMayorVisualizado());
        // modelo.put("elmaslikeado", pR.ObtenerPostMayorLikeado());

        Usuarios logueado = (Usuarios) session.getAttribute("SesionDeUsuario");

        if (logueado != null) {

        } else {

        }

        return "indexForo.html";
    }

    @GetMapping("/crearPost")
    public String crearPost(ModelMap modelo) {

        modelo.put("categorias", cR.findAll());

        return "frmNuevoPosteo.html";

    }

    @PostMapping("/persistirPost")
    public String persistirPost(@RequestParam(required = true) String nombre,
            @RequestParam(required = true) String informacion,
            @RequestParam(required = false) MultipartFile img, @RequestParam(required = true) String categoria,
            ModelMap modelo, HttpSession session) throws Excepciones {

        try {
            Usuarios logueado = (Usuarios) session.getAttribute("SesionDeUsuario");

            pS.crearPosteo(logueado, nombre, cS.ObtenerCategoriaPorId(categoria), informacion, img);
            return "indexForo.html";

        } catch (Exception e) {
            eS.NuevoEvento(e.getMessage());
            modelo.put("error", "Hubo un error al cargar los datos. Revisa y volve a intentarlo.");
            return "frmNuevoPosteo.html";
        }

    }

}
