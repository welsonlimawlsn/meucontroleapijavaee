package com.meucontrole.api.util;

public enum Message {

    CONTA_JA_ATIVADA("Essa conta já está ativado.");

    private String message;

    Message(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
