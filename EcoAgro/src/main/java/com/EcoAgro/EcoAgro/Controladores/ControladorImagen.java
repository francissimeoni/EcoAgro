package com.EcoAgro.EcoAgro.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.EcoAgro.EcoAgro.Entidades.Cultivos;
import com.EcoAgro.EcoAgro.Entidades.Posteos;
import com.EcoAgro.EcoAgro.Servicios.CultivosServicios;
import com.EcoAgro.EcoAgro.Servicios.EventosServicios;
import com.EcoAgro.EcoAgro.Servicios.PosteosServicios;

import org.springframework.http.ResponseEntity;

@Controller
@RequestMapping("/imagen")
public class ControladorImagen {

    @Autowired
    CultivosServicios cS;

    @Autowired
    EventosServicios eS;

    @Autowired
    PosteosServicios pS;

    @GetMapping("/posteo/{id}")
    public ResponseEntity<Byte[]> imagenPosteo(@PathVariable String id) {

        try {

            Cultivos cultivo = cS.ObtenerCultivoPorId(id);
            byte[] foto = cultivo.getImagen().getContenido();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);

            return new ResponseEntity(foto, headers, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            eS.NuevoEvento(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/post/{id}")
    public ResponseEntity<Byte[]> imagenPost(@PathVariable String id) {

        try {

            Posteos post = pS.ObtenerPosteosPorId(id);
            byte[] foto = post.getImagen().getContenido();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);

            return new ResponseEntity(foto, headers, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            eS.NuevoEvento(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

}
