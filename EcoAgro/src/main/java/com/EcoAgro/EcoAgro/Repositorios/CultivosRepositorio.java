package com.EcoAgro.EcoAgro.Repositorios;

import com.EcoAgro.EcoAgro.Entidades.Cultivos;
import com.EcoAgro.EcoAgro.Entidades.Usuarios;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CultivosRepositorio extends JpaRepository<Cultivos, String> {

    @Query(nativeQuery = true, value = "SELECT * FROM Cultivos limit 3")
    public List<Cultivos> Obtener3Noticias();

}
