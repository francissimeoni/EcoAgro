package com.EcoAgro.EcoAgro.Entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.EcoAgro.EcoAgro.Enums.Rol;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
@Setter
@Entity
public class SolicitudesDeActualizacionDeRoles {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idSolicitud;
    private String IdUsuario;
    private Rol rolSolicitado;

}
