package com.EcoAgro.EcoAgro.Servicios;

import com.EcoAgro.EcoAgro.Entidades.Categorias;
import com.EcoAgro.EcoAgro.Entidades.Cultivos;
import com.EcoAgro.EcoAgro.Entidades.Imagen;
import com.EcoAgro.EcoAgro.Excepciones.Excepciones;
import com.EcoAgro.EcoAgro.Repositorios.CategoriaRepositorio;
import com.EcoAgro.EcoAgro.Repositorios.CultivosRepositorio;
import java.util.ArrayList;
import com.EcoAgro.EcoAgro.Repositorios.ImagenRepositorio;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CultivosServicios {

    @Autowired
    private CultivosRepositorio cultivosRepositorio;

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Autowired
    private ImagenRepositorio imagenesRepositorio;

    @Autowired
    private ImagenesServicios imagenesServicios;

    @Autowired
    private CategoriasServicios categoriasServicios;

    @Transactional
    public void crearCultivo(String nombre,
            String información, MultipartFile archivo) throws Excepciones {

        Cultivos cultivo = new Cultivos();
        Categorias categoria = categoriasServicios.crearCategoria(nombre);
        Imagen imagen = imagenesServicios.guardarImagen(archivo);

        cultivo.setNombre(nombre);
        cultivo.setCategorias(categoria);
        cultivo.setImagen(imagen);
        cultivo.setInformacion(información);

        cultivosRepositorio.save(cultivo);

    }

    @Transactional
    public void modificarCultivo(String idCultivos, String nombre, String idCategorias,
            String información, MultipartFile archivo) throws Excepciones {

        Categorias categoria = new Categorias();

        Optional<Categorias> opCategorias = categoriaRepositorio.findById(idCategorias);
        if (opCategorias.isPresent()) {
            categoria = opCategorias.get();
        }

        Optional<Cultivos> respuesta = cultivosRepositorio.findById(idCultivos);

        Imagen imagen = new Imagen();
        String idImagen;
        if (respuesta.isPresent()) {

            Cultivos cultivo = respuesta.get();
            if (cultivo.getImagen() != null) {
                idImagen = cultivo.getImagen().getId();
                imagen = imagenesServicios.actualizarImagen(idImagen, archivo);
            }
            cultivo.setNombre(nombre);
            cultivo.setCategorias(categoria);
            cultivo.setImagen(imagen);

            cultivosRepositorio.save(cultivo);
        }
    }

    public void eliminarCultivo(String idCultivos) throws Excepciones {

        Cultivos cultivo = new Cultivos();

        Optional<Cultivos> opCultivos = cultivosRepositorio.findById(idCultivos);
        if (opCultivos.isPresent()) {
            cultivo = opCultivos.get();
        }

        cultivosRepositorio.delete(cultivo);
    }

    public ArrayList<Cultivos> UltimasNoticias() {

        return null;

    }

}
