package com.meucontrole.api.enums;

public enum TipoDeTransacao {

    DESPESA(1),
    RECEITA(2);

    private Integer value;

    TipoDeTransacao(Integer value) {
        this.value = value;
    }

    public static TipoDeTransacao get(Integer value) {
        TipoDeTransacao tipoDeTransacao = null;
        TipoDeTransacao[] tipos = TipoDeTransacao.values();
        for (int i = 0; i < tipos.length && tipoDeTransacao == null; i++) {
            if (tipos[i].getValue().equals(value)) {
                tipoDeTransacao = tipos[i];
            }
        }
        if (tipoDeTransacao == null) {
            throw new IllegalArgumentException("Unknown " + value);
        }
        return tipoDeTransacao;
    }

    public Integer getValue() {
        return value;
    }
}
