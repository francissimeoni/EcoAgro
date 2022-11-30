package com.EcoAgro.EcoAgro.Entidades;

import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
public class Eventos {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idEvento;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    private String motivo;

}
