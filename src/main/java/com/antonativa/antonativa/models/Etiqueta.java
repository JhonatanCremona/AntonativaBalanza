package com.antonativa.antonativa.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    private boolean estado = false;

    public Etiqueta() {

        fechaActual = LocalDateTime.now();

    }

    @Override
    public String toString() {
        return  "ID: " + id + "\n" +
                "Nombre Etiqueta: " + nameEtiqueta + "\n" +
                "Producto: " + producto + "\n" +
                "Lote: " + lote + "\n" +
                "Fecha Vencimiento: " + fechaVencimiento + "\n" +
                "PesoNeto: " + pesoNeto + "\n" +
                "Operario: " + operario + "\n" +
                "FechaActual: " + fechaActual.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + "\n" +
                "Estado: " + estado;
    }

}
