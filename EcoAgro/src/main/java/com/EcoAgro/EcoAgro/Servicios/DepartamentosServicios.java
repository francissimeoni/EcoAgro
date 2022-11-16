package com.EcoAgro.EcoAgro.Servicios;

import com.EcoAgro.EcoAgro.Entidades.Departamentos;
import com.EcoAgro.EcoAgro.Excepciones.Excepciones;
import com.EcoAgro.EcoAgro.Repositorios.DepartamentoseRepositorio;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class DepartamentosServicios {

    @Autowired
    DepartamentoseRepositorio dR;

    public void CrearDepartamento(String Departamento) throws Excepciones {

        Departamentos DptoTemp = new Departamentos();

        Validar(Departamento);

        DptoTemp.setNombre(Departamento);
        dR.save(DptoTemp);

    }

    public void Validar(String nombre) throws Excepciones {

        if (nombre.equalsIgnoreCase("")) {
            throw new Excepciones("¡El campo de nombre de departamento no puede estar vacio!");
        }

    }

}
