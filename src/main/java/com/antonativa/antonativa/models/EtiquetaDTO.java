package com.antonativa.antonativa.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
public class EtiquetaDTO {
    private String nameEtiqueta;
    private String producto;
    private String lote;
    private Date fechaVencimiento;
    private String operario;
}
