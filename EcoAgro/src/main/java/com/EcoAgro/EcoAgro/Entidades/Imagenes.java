
package com.EcoAgro.EcoAgro.Entidades;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

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
public class Imagenes {
    
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idImagen;
    /*
    private String nombre;
    private String mime;
    
    //@Lob @Basic(fetch = FetchType.LAZY)
    private Byte[] contenido;
    
    private Boolean alta = true;
*/
}
