package com.antonativa.antonativa.settings.conversores;

import java.nio.charset.StandardCharsets;

public class Conversores {

    public static String byteToString(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }

}
