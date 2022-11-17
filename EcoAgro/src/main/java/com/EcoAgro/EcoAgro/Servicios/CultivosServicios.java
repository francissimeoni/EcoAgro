package com.EcoAgro.EcoAgro.Servicios;

import com.EcoAgro.EcoAgro.Entidades.Categorias;
import com.EcoAgro.EcoAgro.Entidades.Cultivos;
import com.EcoAgro.EcoAgro.Entidades.Imagenes;
import com.EcoAgro.EcoAgro.Repositorios.CategoriaRepositorio;
import com.EcoAgro.EcoAgro.Repositorios.CultivosRepositorio;
import com.EcoAgro.EcoAgro.Repositorios.ImagenesRepositorio;
import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

public class CultivosServicios {

    @Autowired
    private CultivosRepositorio cultivosRepositorio;

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Autowired
    private ImagenesRepositorio imagenesRepositorio;

    @Transactional
    public void crearCultivo(String idCultivos, String nombre, String idCategorias, String idImagenes,
            Date fechaSiembra, Date fechaCosecha, float densidadPlantacion, float requerimientosHidricos,
            float temperaturaCrecimiento, float temperaturaMinima, float temperaturaOptima,
            boolean siembraDirectaSiNo, Integer meGusta) {

        Cultivos cultivo = new Cultivos();
        Categorias categoria = new Categorias();
        Imagenes imagen = new Imagenes();

        Optional<Categorias> opCategorias = categoriaRepositorio.findById(idCategorias);
        if (opCategorias.isPresent()) {
            categoria = opCategorias.get();
        }
        Optional<Imagenes> opImagenes = imagenesRepositorio.findById(idImagenes);
        if (opImagenes.isPresent()) {
            imagen = opImagenes.get();
        }

        cultivo.setIdcultivo(idCultivos);
        cultivo.setNombre(nombre);
        cultivo.setCategorias(categoria);
        cultivo.setImagenes(imagen);
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
}
