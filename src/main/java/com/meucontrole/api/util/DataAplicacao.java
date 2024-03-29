package com.meucontrole.api.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DataAplicacao {

    private DataAplicacao() {
    }

    public static LocalDate localDateNow() {
        return LocalDate.now(ZoneOffset.UTC);
    }

    public static LocalDateTime localDateTimeNow() {
        return LocalDateTime.now(ZoneOffset.UTC);
    }

}
