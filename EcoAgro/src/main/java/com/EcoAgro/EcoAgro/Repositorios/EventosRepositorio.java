package com.EcoAgro.EcoAgro.Repositorios;

import com.EcoAgro.EcoAgro.Entidades.Eventos;
import com.EcoAgro.EcoAgro.Entidades.Usuarios;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EventosRepositorio extends JpaRepository<Eventos, String> {

    @Query("select e from Eventos e order by fecha asc")
    public List<Eventos> ListarPorFechaAscendente();

}
