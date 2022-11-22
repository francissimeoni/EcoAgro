package com.EcoAgro.EcoAgro.Servicios;

import com.EcoAgro.EcoAgro.Entidades.Categorias;
import com.EcoAgro.EcoAgro.Entidades.Cultivos;
import com.EcoAgro.EcoAgro.Entidades.Imagenes;
import com.EcoAgro.EcoAgro.Excepciones.Excepciones;
import com.EcoAgro.EcoAgro.Repositorios.CategoriaRepositorio;
import com.EcoAgro.EcoAgro.Repositorios.CultivosRepositorio;
import com.EcoAgro.EcoAgro.Repositorios.ImagenesRepositorio;
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
    private ImagenesRepositorio imagenesRepositorio;

    @Transactional
    public void crearCultivo(String idCultivos, String nombre, String idCategorias, String idImagenes,
            Date fechaSiembra, Date fechaCosecha, Integer densidadPlantacion, Integer requerimientosHidricos,
            Integer temperaturaCrecimiento, Integer temperaturaMinima, Integer temperaturaOptima,
            boolean siembraDirectaSiNo, Integer meGusta) throws Excepciones {

        validar(idCultivos, nombre, idCategorias, idImagenes,
                fechaSiembra, fechaCosecha, densidadPlantacion, requerimientosHidricos,
                temperaturaCrecimiento, temperaturaMinima, temperaturaOptima,
                siembraDirectaSiNo, meGusta);

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

    @Transactional
    public void modificarCultivo(String idCultivos, String nombre, String idCategorias, String idImagenes,
            Date fechaSiembra, Date fechaCosecha, Integer densidadPlantacion, Integer requerimientosHidricos,
            Integer temperaturaCrecimiento, Integer temperaturaMinima, Integer temperaturaOptima,
            boolean siembraDirectaSiNo, Integer meGusta) throws Excepciones {

        validar(idCultivos, nombre, idCategorias, idImagenes,
                fechaSiembra, fechaCosecha, densidadPlantacion, requerimientosHidricos,
                temperaturaCrecimiento, temperaturaMinima, temperaturaOptima,
                siembraDirectaSiNo, meGusta);

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

        Optional<Cultivos> opCultivos = cultivosRepositorio.findById(idCultivos);
        if (opCultivos.isPresent()) {
            cultivo = opCultivos.get();
        }
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

    public void eliminarCultivo(String idCultivos) throws Excepciones {

        Cultivos cultivo = new Cultivos();

        Optional<Cultivos> opCultivos = cultivosRepositorio.findById(idCultivos);
        if (opCultivos.isPresent()) {
            cultivo = opCultivos.get();
        }

        cultivosRepositorio.delete(cultivo);
    }

    private void validar(String idCultivos, String nombre, String idCategorias, String idImagenes,
            Date fechaSiembra, Date fechaCosecha, Integer densidadPlantacion, Integer requerimientosHidricos,
            Integer temperaturaCrecimiento, Integer temperaturaMinima, Integer temperaturaOptima,
            boolean siembraDirectaSiNo, Integer meGusta) throws Excepciones {

        if (idCultivos.isEmpty() || idCultivos == null) {
            throw new Excepciones("la idCultivos no puede ser nula");
        }

        if (nombre.isEmpty() || nombre == null) {
            throw new Excepciones("el nombre no puede ser nulo");
        }

        if (idCategorias.isEmpty() || idCategorias == null) {
            throw new Excepciones("la idCategorias no puede ser nula");
        }

        if (idImagenes.isEmpty() || idImagenes == null) {
            throw new Excepciones("la idImagenes no puede ser nula");
        }

        if (fechaSiembra == null) {
            throw new Excepciones("fechaSiembra no puede ser nulo");
        }

        if (fechaCosecha == null) {
            throw new Excepciones("fechaCosecha no puede ser nulo");
        }

        if (densidadPlantacion == null) {
            throw new Excepciones("densidadPlantacion no puede ser nulo");
        }

        if (temperaturaCrecimiento == null) {
            throw new Excepciones("temperaturaCrecimiento no puede ser nulo");
        }

        if (requerimientosHidricos == null) {
            throw new Excepciones("requerimientosHidricos no puede ser nulo");
        }

        if (temperaturaMinima == null) {
            throw new Excepciones("temperaturaMinima no puede ser nulo");
        }

        if (temperaturaOptima == null) {
            throw new Excepciones("temperaturaOptima no puede ser nulo");
        }

        if (meGusta == null) {
            throw new Excepciones("meGusta no puede ser nulo");
        }

    }
}
