package com.EcoAgro.EcoAgro.Entidades;

import com.EcoAgro.EcoAgro.Enums.Rol;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;


@ToString
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Usuarios {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idUsuario;
    private String usuario;
    private String contraseña;
    private Rol rol;

    @OneToOne
    @JoinColumn(name = "idZonas")
    private Zonas zonas;

    @Temporal(TemporalType.DATE)
    private Date fechaDeRegistro;
    private String correo;
    private String telefono;
    private boolean activoSiNo;

}
