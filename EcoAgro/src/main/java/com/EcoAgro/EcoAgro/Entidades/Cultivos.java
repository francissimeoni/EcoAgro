package com.EcoAgro.EcoAgro.Entidades;

//import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;//
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
public class Cultivos {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idcultivo;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "idCategorias", insertable = true, updatable = true)
    private Categorias Categorias;

    @OneToOne
    @JoinColumn(name = "idZonas", insertable = true, updatable = true)
    private Zonas zona;

    private String informacion;

    @OneToOne
    private Imagen imagen;

}
