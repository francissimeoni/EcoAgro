package com.EcoAgro.EcoAgro.Servicios;

import com.EcoAgro.EcoAgro.Entidades.Categorias;
import com.EcoAgro.EcoAgro.Repositorios.CategoriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CategoriasServicios {

    @Autowired
    CategoriaRepositorio cR;

    public Categorias crearCategoria(String nombre) {
        Categorias c = new Categorias();
        c.setNombre(nombre);

        return cR.save(c);

    }

    public Categorias ObtenerCategoriaPorId(String id) {

        Optional<Categorias> c = cR.findById(id);

        if (c.isPresent()) {
            return c.get();

        } else {
            return null;
        }

    }

}
