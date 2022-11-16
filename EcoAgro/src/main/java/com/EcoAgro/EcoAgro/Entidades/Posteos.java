package com.EcoAgro.EcoAgro.Entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
public class Posteos {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idPosteo;
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "idCategoria", insertable = true, updatable = true)
    private Categorias categoria;

    private Integer MeGusta;
    private Integer compartidos;

    @ManyToOne
    @JoinColumn(name = "idUsuario", insertable = true, updatable = true)
    private Usuarios usuario;
    private Integer visualizaciones;
    private String posteoTexto;
    private boolean activoSiNo;
}
