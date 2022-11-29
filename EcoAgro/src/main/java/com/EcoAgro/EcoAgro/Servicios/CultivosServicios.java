package com.EcoAgro.EcoAgro.Servicios;

import com.EcoAgro.EcoAgro.Entidades.Categorias;
import com.EcoAgro.EcoAgro.Entidades.Cultivos;
import com.EcoAgro.EcoAgro.Entidades.Imagen;
import com.EcoAgro.EcoAgro.Excepciones.Excepciones;
import com.EcoAgro.EcoAgro.Repositorios.CategoriaRepositorio;
import com.EcoAgro.EcoAgro.Repositorios.CultivosRepositorio;
import com.EcoAgro.EcoAgro.Repositorios.ImagenRepositorio;
import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CultivosServicios {

    @Autowired
    private CultivosRepositorio cultivosRepositorio;

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Autowired
    private ImagenRepositorio imagenesRepositorio;

    @Transactional
    public void crearCultivo(String idCultivos, String nombre, String idCategorias, String idImagenes,
            Date fechaSiembra, Date fechaCosecha, float densidadPlantacion, float requerimientosHidricos,
            float temperaturaCrecimiento, float temperaturaMinima, float temperaturaOptima,
            boolean siembraDirectaSiNo, Integer meGusta) throws Excepciones {

        Cultivos cultivo = new Cultivos();
        Categorias categoria = new Categorias();
        Imagen imagen = new Imagen();

        Optional<Categorias> opCategorias = categoriaRepositorio.findById(idCategorias);
        if (opCategorias.isPresent()) {
            categoria = opCategorias.get();
        }
        
        /*
        Optional<Imagenes> opImagenes = imagenesRepositorio.findById(idImagenes);
        if (opImagenes.isPresent()) {
            imagen = opImagenes.get();
        }
        */

        cultivo.setIdcultivo(idCultivos);
        cultivo.setNombre(nombre);
        cultivo.setCategorias(categoria);
        //cultivo.setImagenes(imagen);
        cultivo.setFechaSiembra(fechaSiembra);
        cultivo.setFechaCosecha(fechaCosecha);
        cultivo.setDensidadPlantacion(densidadPlantacion);
        cultivo.setRequerimientosHidricos(requerimientosHidricos);
        cultivo.setTemperaturaCrecimiento(temperaturaCrecimiento);
        cultivo.setTemperaturaMinima(temperaturaMinima);
        cultivo.setTemperaturaOptima(temperaturaOptima);
        cultivo.setSiembraDirectaSiNo(siembraDirectaSiNo);
        cultivo.setMeGusta(meGusta);

        cultivosRepositorio.save(cultivo);

    }

    @Transactional
    public void modificarCultivo(String idCultivos, String nombre, String idCategorias, String idImagenes,
            Date fechaSiembra, Date fechaCosecha, float densidadPlantacion, float requerimientosHidricos,
            float temperaturaCrecimiento, float temperaturaMinima, float temperaturaOptima,
            boolean siembraDirectaSiNo, Integer meGusta) throws Excepciones {

        Cultivos cultivo = new Cultivos();
        Categorias categoria = new Categorias();
        Imagen imagen = new Imagen();

        Optional<Categorias> opCategorias = categoriaRepositorio.findById(idCategorias);
        if (opCategorias.isPresent()) {
            categoria = opCategorias.get();
        }
        Optional<Imagen> opImagenes = imagenesRepositorio.findById(idImagenes);
        if (opImagenes.isPresent()) {
            imagen = opImagenes.get();
        }

        Optional<Cultivos> opCultivos = cultivosRepositorio.findById(idCultivos);
        if (opCultivos.isPresent()) {
            cultivo = opCultivos.get();
        }
        cultivo.setNombre(nombre);
        cultivo.setCategorias(categoria);
        cultivo.setImagen(imagen);
        cultivo.setFechaSiembra(fechaSiembra);
        cultivo.setFechaCosecha(fechaCosecha);
        cultivo.setDensidadPlantacion(densidadPlantacion);
        cultivo.setRequerimientosHidricos(requerimientosHidricos);
        cultivo.setTemperaturaCrecimiento(temperaturaCrecimiento);
        cultivo.setTemperaturaMinima(temperaturaMinima);
        cultivo.setTemperaturaOptima(temperaturaOptima);
        cultivo.setSiembraDirectaSiNo(siembraDirectaSiNo);
        cultivo.setMeGusta(meGusta);

        cultivosRepositorio.save(cultivo);

    }

    public void eliminarCultivo(String idCultivos) throws Excepciones {

        Cultivos cultivo = new Cultivos();

        Optional<Cultivos> opCultivos = cultivosRepositorio.findById(idCultivos);
        if (opCultivos.isPresent()) {
            cultivo = opCultivos.get();
        }

        cultivosRepositorio.delete(cultivo);
    }
}
