package com.EcoAgro.EcoAgro.Controladores;

import com.EcoAgro.EcoAgro.Entidades.Cultivos;
import com.EcoAgro.EcoAgro.Servicios.CategoriasServicios;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public String PaginaPrincipalCultivos() {

        return "cultivos.html";
    }

    @GetMapping("/VerCultivo")
    public String verCultivo() {

        return "info.html";
    }

    
    @PostMapping("/PersistirCultivo")
    public void PersistirCultivo(@RequestParam String nombre, @RequestParam Date siembra,
    @RequestParam Date cosecha, @RequestParam Float dp, @RequestParam Float rh, @RequestParam Float tc,
    @RequestParam Boolean sd, @RequestParam Float tm, @RequestParam Float toc){
    
        
        
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
}
