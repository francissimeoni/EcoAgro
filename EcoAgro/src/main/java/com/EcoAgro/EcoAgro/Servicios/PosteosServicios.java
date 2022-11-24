
package com.EcoAgro.EcoAgro.Servicios;

import com.EcoAgro.EcoAgro.Entidades.Categorias;
import com.EcoAgro.EcoAgro.Entidades.Posteos;
import com.EcoAgro.EcoAgro.Entidades.Usuarios;
import com.EcoAgro.EcoAgro.Excepciones.Excepciones;
import com.EcoAgro.EcoAgro.Repositorios.PosteosRepositorio;
import com.EcoAgro.EcoAgro.Repositorios.UsuariosRepositorio;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PosteosServicios {
    
    
    @Autowired
    private PosteosRepositorio pR;
    @Autowired
    private UsuariosRepositorio uR;
    @Autowired
    private UsuariosServicios uS;
    @Autowired
    private CategoriasServicios cS;
    
    @Transactional
    public void crearPosteo(String IdUsuario, String titulo, String idCategoria,
            String posteoTexto) throws Excepciones{
        Posteos p = new Posteos();
        Usuarios u = uS.ObtenerUsuariosPorId(IdUsuario);
        Categorias c = cS.ObtenerCategoriaPorId(titulo);
        
        p.setTitulo(titulo);
        p.setPosteoTexto(posteoTexto);
        p.setUsuario(u);
        p.setCategoria(c);
        //Por defecto
        p.setMeGusta(0);
        p.setCompartidos(0);
        p.setVisualizaciones(0);
        p.setActivoSiNo(true);
        
        pR.save(p);
    } 
    
    @Transactional
    public void modificarPosteo(String IdPosteo, String titulo,
        String posteoTexto){
        
        Posteos p = ObtenerPosteosPorId(IdPosteo);
        p.setTitulo(titulo);
        p.setPosteoTexto(posteoTexto);
        
        pR.save(p);
    }
    
    @Transactional
    public void darDeBaja(String IdPosteo){
        Posteos p = ObtenerPosteosPorId(IdPosteo);
        p.setActivoSiNo(false);
        pR.save(p);
    }
  
    @Transactional
    public void eliminarPosteo(String IdPosteo){
        
        Posteos p = ObtenerPosteosPorId(IdPosteo);
        pR.delete(p);
    }
    
    
    
    public Posteos ObtenerPosteosPorId(String id) {

        Optional<Posteos> p = pR.findById(id);

        if (p.isPresent()) {
            return p.get();

        } else {
            return null;
        }
    }
    
    public void validaciones(String titulo, String posteoTexto, String correo) throws Excepciones, Excepciones {
        
        if (titulo.equalsIgnoreCase("")) {
            throw new Excepciones("El Posteo debe de contener un título.");
        }
        if (posteoTexto.equalsIgnoreCase("")) {
            throw new Excepciones("El Posteo debe incluir una oración al menos.");
        }

    }
    
}
