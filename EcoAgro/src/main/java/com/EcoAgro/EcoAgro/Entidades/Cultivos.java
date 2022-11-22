package com.EcoAgro.EcoAgro.Entidades;

import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class Cultivos {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idcultivo;

    private String nombre;

    @Temporal(TemporalType.DATE)
    private Date fechaSiembra;
    @Temporal(TemporalType.DATE)
    private Date fechaCosecha;

    @ManyToOne
    @JoinColumn(name = "idCategorias", insertable = true, updatable = true)
    private Categorias Categorias;

    private Integer densidadPlantacion;
    private Integer requerimientosHidricos;
    private Integer temperaturaCrecimiento;
    private Boolean siembraDirectaSiNo;
    private Integer temperaturaMinima;
    private Integer temperaturaOptima;
    private Integer MeGusta;

   
    @ManyToOne
    private Imagenes Imagenes;

     }
