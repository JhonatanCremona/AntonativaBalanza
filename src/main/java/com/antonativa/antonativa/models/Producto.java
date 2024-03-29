package com.antonativa.antonativa.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String lote;
    private LocalDate fechaVencimiento;
    private String pesoNeto;
    private LocalDateTime fechaActual;
    private String operario;
    private String unidades;

    @Override
    public String toString() {
        return  "Producto: " + nombre + "\n" +
                "Lote: " + lote + "\t" +
                "PesoNeto: " + pesoNeto + "\n" +
                "Fecha Vencimiento: " + fechaVencimiento.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "\n" +
                "Fecha: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

}
