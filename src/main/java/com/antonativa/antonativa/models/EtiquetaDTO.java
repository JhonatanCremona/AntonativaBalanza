package com.antonativa.antonativa.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EtiquetaDTO {
    private Long id;
    private String nameEtiqueta;
    private String producto;
    private String lote;
    private LocalDate fechaVencimiento;
    private String operario;
    private boolean estado;
    private String unidades;

}
