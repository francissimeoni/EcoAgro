package com.EcoAgro.EcoAgro.Entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Comentarios {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idcomentarios;

    @ManyToOne
    @JoinColumn(name = "IdUsuario")
    private Usuarios usuario;

    private String comentario;
    private Integer meGusta;
    private Integer noMeGusta;
    private boolean activoSiNo;
}
