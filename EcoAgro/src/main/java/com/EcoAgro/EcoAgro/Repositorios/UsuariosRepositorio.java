package com.EcoAgro.EcoAgro.Repositorios;

import com.EcoAgro.EcoAgro.Entidades.Usuarios;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRepositorio extends JpaRepository<Usuarios, String> {

    
     @Query("select u from Usuarios u where u.correo = :correo")
    public Usuarios buscarPorEmail(@Param("correo")String correo);
    
   /* @Query("SELECT U FROM USUARIOS U WHERE ACTIVOSINO = FALSE")
    public ArrayList<Usuarios> RetornarInactivos();

    @Query("SELECT U FROM USUARIOS U WHERE ACTIVOSINO = TRUE")
    public ArrayList<Usuarios> RetornarActivos();
   */
}
