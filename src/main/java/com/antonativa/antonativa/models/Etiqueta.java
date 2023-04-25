package com.antonativa.antonativa.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
public class Etiqueta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nameEtiqueta;
    private String producto;
    private String lote;
    private LocalDate fechaVencimiento;
    private String pesoNeto;
    private String operario;
    private LocalDateTime fechaActual;
    private boolean estado;

    public Etiqueta() {

        fechaActual = LocalDateTime.now();
        estado = false;

    }

    @Override
    public String toString() {
        return  "Producto: " + producto + "\n" +
                "Lote: " + lote + "\n" +
                "Fecha Vencimiento: " + fechaVencimiento.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "\n" +
                "PesoNeto: " + pesoNeto + "\n" +
                "Operario: " + operario + "\n" +
                "Fecha: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "\t" +
                "Hora: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

}
