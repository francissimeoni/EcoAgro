package com.EcoAgro.EcoAgro.Entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
    @OneToOne
    private Usuarios usuario;

    private Rol rolSolicitado;

}
