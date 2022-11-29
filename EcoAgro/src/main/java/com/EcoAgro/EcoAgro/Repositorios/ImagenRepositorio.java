/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.EcoAgro.EcoAgro.Repositorios;

import com.EcoAgro.EcoAgro.Entidades.Imagen;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Usuario
 */
@Repository
public interface ImagenRepositorio extends JpaRepository<Imagen, String>{
    
    @Query("SELECT i FROM Imagen i WHERE i.alta = true")
    List<Imagen> buscarFotos();
    
    
}
