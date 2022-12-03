
package com.EcoAgro.EcoAgro.Servicios;

import com.EcoAgro.EcoAgro.Entidades.Imagen;
import com.EcoAgro.EcoAgro.Excepciones.Excepciones;
import com.EcoAgro.EcoAgro.Repositorios.ImagenRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImagenesServicios {

    @Autowired
    private ImagenRepositorio iR;

    @Transactional
    public Imagen guardarImagen(MultipartFile archivo) throws Excepciones {
        if (archivo != null) {
            try {
                Imagen imagen = new Imagen();
                imagen.setMime(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setContenido(archivo.getBytes());

                return iR.save(imagen);
            } catch (Exception e) {
                System.err.println(e.getMessage());
                throw new Excepciones("No se pudo realizar esta acción");
            }
        }
        return null;
    }

    @Transactional
    public Imagen actualizarImagen(String idImagen, MultipartFile archivo) throws Excepciones {
        if (archivo != null) {
            try {
                Imagen imagen = new Imagen();

                if (idImagen != null) {
                    Optional<Imagen> respuesta = iR.findById(idImagen);
                    if (respuesta.isPresent()) {
                        imagen = respuesta.get();
                        imagen.setMime(archivo.getContentType());
                        imagen.setNombre(archivo.getName());
                        imagen.setContenido(archivo.getBytes());
                        return iR.save(imagen);
                    }
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
                throw new Excepciones("No se pudo realizar esta acción");
            }

        }

        return null;

    }

    public List<Imagen> buscarImagenes() {

        List<Imagen> imagenes = iR.buscarFotos();

        return imagenes;
    }

    public Imagen buscarPorId(String id) {
        Optional<Imagen> respuesta = iR.findById(id);

        if (respuesta.isPresent()) {
            Imagen imagen = respuesta.get();
            return imagen;
        }
        return null;

    }
}
