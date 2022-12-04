
package com.EcoAgro.EcoAgro.Repositorios;

import com.EcoAgro.EcoAgro.Entidades.Posteos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PosteosRepositorio extends JpaRepository<Posteos, String> {

    @Query(nativeQuery = true, value = "SELECT * FROM Posteos limit 1")
    public Posteos ObtenerPostMayorVisualizado();

    @Query(nativeQuery = true, value = "select * from posteos limit 1")
    public Posteos ObtenerPostMayorLikeado();

}
