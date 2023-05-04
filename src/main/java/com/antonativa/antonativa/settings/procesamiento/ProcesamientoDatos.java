package com.antonativa.antonativa.settings.procesamiento;


import com.antonativa.antonativa.settings.conversores.Conversores;

public class ProcesamientoDatos {

    public static String procesarDatosBalanza(byte[] bytes) {

        String datoPeso = Conversores.byteToString(bytes);

        datoPeso = datoPeso.substring(3, 7);

        datoPeso = String.valueOf(Double.parseDouble(datoPeso) / 100);

        return datoPeso;

    }

}
