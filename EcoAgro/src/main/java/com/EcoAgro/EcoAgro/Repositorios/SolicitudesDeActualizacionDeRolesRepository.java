package com.EcoAgro.EcoAgro.Repositorios;

import com.EcoAgro.EcoAgro.Entidades.SolicitudesDeActualizacionDeRoles;
import com.EcoAgro.EcoAgro.Entidades.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface SolicitudesDeActualizacionDeRolesRepository extends JpaRepository<SolicitudesDeActualizacionDeRoles, String> {

    //  @Query("select s from SolicitudesDeActualizacionDeRoles s where s.IdUsuario= :parametro or u.usuario= :parametro")
    // public Usuarios buscarPorEmailoUsuario(@Param("parametro") String parametro);
}
