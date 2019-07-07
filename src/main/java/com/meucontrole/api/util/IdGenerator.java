package com.meucontrole.api.util;

import java.util.Random;

public class IdGenerator {

    private static IdGenerator idGenerator;
    private char[] characters;
    private Random random;

    public IdGenerator() {
        characters = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        random = new Random();
    }

    public static IdGenerator getInstance() {
        if (idGenerator == null)
            idGenerator = new IdGenerator();
        return idGenerator;
    }

    public String generate() {
        char[] id = new char[15];
        for (int i = 0; i < id.length; i++) {
            id[i] = characters[random.nextInt(characters.length)];
        }
        return new String(id);
    }

}
