package com.EcoAgro.EcoAgro.Repositorios;

import com.EcoAgro.EcoAgro.Entidades.Cultivos;
import com.EcoAgro.EcoAgro.Entidades.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CultivosRepositorio extends JpaRepository<Cultivos, String> {

   // @Query("select c from Cultivos c limit 0,2")
    //public Cultivos traerCultivos();

}
