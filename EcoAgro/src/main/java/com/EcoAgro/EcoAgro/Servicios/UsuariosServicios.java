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
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UsuariosServicios implements UserDetailsService {

    @Autowired
    UsuariosRepositorio uR;

    /////// ----> crud
    @Transactional
    public void CrearUsuario(String Usuario, String Contraseña, Rol rol,
            Zonas zonas, Date fechaDeRegistro, String correo,
            String telefono, boolean ActivoSiNo) throws Excepciones {
        Usuarios ObjUsuarios = new Usuarios();

        
        validaciones(Usuario, Contraseña, correo, telefono);
        //SeguridadDeClave(Contraseña);

        ObjUsuarios.setUsuario(Usuario);
        ObjUsuarios.setContraseña(new BCryptPasswordEncoder().encode(Contraseña));
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
        ObjUsuarios.setContraseña(new BCryptPasswordEncoder().encode(Contraseña));;
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

    /////// ----> funciones de user detail service
    /////// ----> login
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuarios usuario = uR.buscarPorEmailoUsuario(username);

        if (usuario != null) {

            List<GrantedAuthority> permisos = new ArrayList();

            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());
            permisos.add(p);

            //una vez que ya se logueo, guardamos el usuario para utilizar sus datos durante la sesion
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);

            session.setAttribute("SesionDeUsuario", usuario);
            
         

            User user = new User(usuario.getUsuario(), usuario.getContraseña(), permisos);
            return user;
        } else {
            return null;
        }

    }

    public Boolean usuarioLogin(String parametroUsr, String contraseña) throws Excepciones {

        validaciones(parametroUsr, contraseña, "+", "+");

        Usuarios usuario = uR.buscarPorEmailoUsuario(parametroUsr);

        if (usuario == null) {

            return false;

        } else {

            if (usuario.getContraseña().equalsIgnoreCase(contraseña)) {

                System.out.println("Usuario logueado");
                loadUserByUsername(usuario.getUsuario());

                return true;
            } else {
                System.out.println("Usuario no logueado");
                return false;

            }

        }
    }
    /////// ----> final login

/////// ----> validaciones
    public void validaciones(String usuario, String contraseña,
            String correo, String telefono) throws Excepciones, Excepciones {

        if (usuario.equalsIgnoreCase("")) {
            throw new Excepciones("El campo usuario no puede estar vacio");
        }

        if (contraseña.equalsIgnoreCase("")) {
            throw new Excepciones("El campo contraseña no puede estar vacio");
        }

        if (correo.toString().equalsIgnoreCase("")) {
            throw new Excepciones("El campo de correo electronico no puede estar vacio");
        }

        if (telefono.equalsIgnoreCase("")) {
            throw new Excepciones("El campo telefono no puede estar vacio");
        }

    }

    public void SeguridadDeClave(String clave) throws Excepciones {

        if (!scannerPassword(clave)) {
            throw new Excepciones(("La contraseña debe contener por lo menos un signo, una mayuscula y una longitud minima de 8 caracteres."));
        }
    }

    public Boolean scannerPassword(String Pass) {

        Integer contadorDeCaracteres = Pass.length();
        Boolean CaracterEspecial = false;
        Boolean CaracterMayuscula = false;
        Boolean CaracterNumerico = false;
        Boolean CaracterMinuscula = false;
        String Caracter;

        for (int i = 0; i < Pass.length() - 1; i++) {

            Caracter = Pass.substring(i, i + 1);

            if (CaracterEspecial == false) {
                CaracterEspecial = ControlarCaracteresEspeciales(Caracter);
            }

            if (CaracterNumerico == false) {
                CaracterNumerico = Caracter.matches("[0-9]*");
            }

            if (CaracterMayuscula == false) {
                if (Caracter.equals(Caracter.toUpperCase())) {
                    CaracterMayuscula = true;
                }
            }

            if (CaracterMinuscula == false) {
                if (Caracter.equals(Caracter.toLowerCase())) {
                    CaracterMinuscula = true;
                }
            }

        }

        if (contadorDeCaracteres < 8 || CaracterEspecial == false || CaracterNumerico == false
                || CaracterMayuscula == false || CaracterMinuscula == false) {
            return false;
        } else {
            return true;
        }

    }

    public Boolean ControlarCaracteresEspeciales(String Caracter) {

        Boolean val = false;

        switch (Caracter) {
            case "!":
                val = true;
                break;
            case "|":
                val = true;
                break;
            case "@":
                val = true;
                break;
            case "#":
                val = true;
                break;
            case "$":
                val = true;
                break;
            case "¡":
                val = true;
                break;
            case "%":
                val = true;
                break;
            case "/":
                val = true;
                break;
            case "(":
                val = true;
                break;
            case ")":
                val = true;
                break;
            case "=":
                val = true;
                break;
            case "?":
                val = true;
                break;
            case "¿":
                val = true;
                break;
            case "°":
                val = true;
                break;
            case "¬":
                val = true;
                break;
            case "*":
                val = true;
                break;
            case "+":
                val = true;
                break;
            case "-":
                val = true;
                break;
            default:
                val = false;
                break;
        }

        return val;

    }

    /////// ----> fin validaciones
}
