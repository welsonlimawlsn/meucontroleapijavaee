package com.meucontrole.api.util;

public enum Message {

    CONTA_JA_ATIVADA("Essa conta já está ativado."),
    TRANSACAO_JA_CONSOLIDADA("Essa transação já foi consolidada."),
    DESPESA_PRECISA_SER_NEGATIVA("Uma despesa precisa ter um valor negativo."),
    RECEITA_PRECISA_SER_POSITIVO("Uma receita precisa ter um valor povitivo."),
    QUANTIDADE_MINIMA_TRANSACAO_COM_REPETICAO("Uma transação com repetição deve ter no minimo 2 repetições.");

    private String message;

    Message(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
