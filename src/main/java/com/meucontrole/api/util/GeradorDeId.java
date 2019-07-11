package com.meucontrole.api.util;

import java.util.Random;

public class GeradorDeId {

    private static GeradorDeId geradorDeId;
    private char[] caracteres;
    private Random random;

    public GeradorDeId() {
        caracteres = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        random = new Random();
    }

    public static GeradorDeId getInstance() {
        if (geradorDeId == null)
            geradorDeId = new GeradorDeId();
        return geradorDeId;
    }

    public String gerar() {
        char[] id = new char[15];
        for (int i = 0; i < id.length; i++) {
            id[i] = caracteres[random.nextInt(caracteres.length)];
        }
        return new String(id);
    }

}
