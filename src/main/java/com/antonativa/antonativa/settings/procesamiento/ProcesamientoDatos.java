package com.antonativa.antonativa.settings.procesamiento;


import com.antonativa.antonativa.settings.conversores.Conversores;

public class ProcesamientoDatos {

    public static String procesarDatosBalanza(byte[] bytes) {

        String datoPeso = Conversores.byteToString(bytes);

        datoPeso = datoPeso.substring(7, 13);

        datoPeso = String.valueOf(Double.parseDouble(datoPeso));

        return datoPeso;

    }

}
