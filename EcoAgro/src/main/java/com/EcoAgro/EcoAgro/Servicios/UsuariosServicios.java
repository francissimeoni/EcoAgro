package com.EcoAgro.EcoAgro.Servicios;

import com.EcoAgro.EcoAgro.Entidades.Usuarios;
import com.EcoAgro.EcoAgro.Entidades.Zonas;
import com.EcoAgro.EcoAgro.Enums.Rol;
import com.EcoAgro.EcoAgro.Repositorios.UsuariosRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import javax.transaction.Transactional;
import com.EcoAgro.EcoAgro.Excepciones.Excepciones;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuariosServicios {

    @Autowired
    UsuariosRepositorio uR;

    /////// ----> crud
    @Transactional
    public void CrearUsuario(String Usuario, String Contraseña, Rol rol,
            Zonas zonas, Date fechaDeRegistro, String correo,
            String telefono, boolean ActivoSiNo) throws Excepciones {
        Usuarios ObjUsuarios = new Usuarios();

        validaciones(Usuario, Contraseña, correo, telefono);

        ObjUsuarios.setUsuario(Usuario);
        ObjUsuarios.setContraseña(Contraseña);
        ObjUsuarios.setRol(rol);
        ObjUsuarios.setZonas(zonas);
        ObjUsuarios.setFechaDeRegistro(fechaDeRegistro);
        ObjUsuarios.setCorreo(correo);
        ObjUsuarios.setTelefono(telefono);
        ObjUsuarios.setActivoSiNo(ActivoSiNo);

        uR.save(ObjUsuarios);

    }

    @Transactional
    public void ModificarUsuario(String id, String Usuario, String Contraseña, Rol rol,
            Zonas zonas, Date fechaDeRegistro, String correo,
            String telefono, boolean ActivoSiNo) throws Excepciones {
        Usuarios ObjUsuarios = new Usuarios();

        ObjUsuarios = ObtenerUsuariosPorId(id);
        ObjUsuarios.setUsuario(Usuario);
        ObjUsuarios.setContraseña(Contraseña);
        ObjUsuarios.setRol(rol);
        ObjUsuarios.setZonas(zonas);
        ObjUsuarios.setFechaDeRegistro(fechaDeRegistro);
        ObjUsuarios.setCorreo(correo);
        ObjUsuarios.setTelefono(telefono);
        ObjUsuarios.setActivoSiNo(ActivoSiNo);
        uR.save(ObjUsuarios);

    }

    @Transactional
    public void ElimianrUsuario(String id) throws Excepciones {
        Usuarios ObjUsuarios = new Usuarios();

        ObjUsuarios = ObtenerUsuariosPorId(id);
        uR.delete(ObjUsuarios);

    }

    @Transactional
    public void ActualizarUsuarioActivoSiNo(boolean valor, String id) throws Excepciones {
//Si se recibe true quiere decir que hay que activarlo
// Si recibe false quiere decir que hay que desactivarlo

        if (id.equalsIgnoreCase("")) {
            throw new Excepciones("Tenes que seleccionar un usuario para desactivarlo");
        } else {
            Usuarios ObjUsuarios = new Usuarios();

            ObjUsuarios = ObtenerUsuariosPorId(id);
            ObjUsuarios.setActivoSiNo(valor);
            uR.save(ObjUsuarios);

        }

    }

    /////// ----> fin crud
    /////// ----> consultas personalizadas
    public Usuarios ObtenerUsuariosPorId(String id) throws Excepciones {

        Optional<Usuarios> z = uR.findById(id);

        if (z.isPresent()) {
            return z.get();

        } else {
            throw new Excepciones("No se encontró un usuario con esa id");
        }

    }

    public ArrayList<Usuarios> DevolverListaDeUsuariosCompleta() {

        ArrayList<Usuarios> arrUs = new ArrayList<>();
        arrUs.addAll(uR.findAll());

        return arrUs;
    }

    /*
    public ArrayList<Usuarios> DevolverUsuariosActivos() {
        ArrayList<Usuarios> arr = new ArrayList<>();

        arr = uR.RetornarActivos();

        return arr;
    }

    public ArrayList<Usuarios> DevolverUsuariosInactivos() {
        ArrayList<Usuarios> arr = new ArrayList<>();
        arr = uR.RetornarInactivos();

        return arr;
    }
     */
    /////// ----> fin de consultas
    /////// ----> validaciones
    public void validaciones(String usuario, String contraseña,
            String correo, String telefono) throws Excepciones, Excepciones {

        if (usuario.equalsIgnoreCase("")) {
            throw new Excepciones("Para registrarse debe brindar un nombre de usuario");
        }

        if (contraseña.equalsIgnoreCase("")) {
            throw new Excepciones("要註冊，您必須提供密碼");//Para registrarse debe brindar una contraseña
        }

        if (correo.toString().equalsIgnoreCase("")) {
            throw new Excepciones("Para registrarse debe brindar un correo electronico");
        }

        if (telefono.equalsIgnoreCase("")) {
            throw new Excepciones("Para registrarse debe brindar un telefono");
        }

    }
    /////// ----> fin validaciones

    /////// ----> funciones de user detail service
    public void UserDetailDetail() {

    }

    public UserDetails LogearUsuarioSpring(Usuarios usuario) throws UsernameNotFoundException {

        List<GrantedAuthority> permisos = new ArrayList();

        GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());
        permisos.add(p);

        User user = new User(usuario.getUsuario(), usuario.getContraseña(), permisos);

        return user;

    }

    public Boolean usuarioLogin(String parametroUsr, String contraseña) throws Excepciones {

        validaciones(parametroUsr, contraseña, "+", "+");

        Usuarios usuario = uR.buscarPorEmailoUsuario(parametroUsr);

        if (usuario.getCorreo().equalsIgnoreCase("")) {

            System.out.println("el usuario" + parametroUsr + "no existe");
            return false;

        } else {
            System.out.println("el usuario" + parametroUsr + " existe");
            if (!usuario.getContraseña().equals(contraseña)) {
                System.out.println("clave de usuario:" + usuario.getContraseña() + ", parametro enviado de clave " + contraseña);
                LogearUsuarioSpring(usuario);

                return true;
            } else {
                System.out.println("error de contraseñas");
                System.out.println("clave de usuario:" + usuario.getContraseña() + ", parametro enviado de clave " + contraseña);
                return false;

            }

        }
    }
}
