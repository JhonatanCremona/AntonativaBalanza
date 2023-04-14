package com.antonativa.antonativa.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
public class Etiqueta {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String nameEtiqueta;
    private String producto;
    private String lote;
    private Date fechaVencimiento;
    private String pesoNeto;
    private String operario;
    private LocalDateTime fechaActual;

    public Etiqueta() {

    }

}
