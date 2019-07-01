package com.meucontrole.api.enums;

public enum TransactionType {

    EXPENSE(1),
    INCOME(2);

    private Integer value;

    TransactionType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static TransactionType get(Integer value) {
        TransactionType transactionType = null;
        TransactionType[] types = TransactionType.values();
        for (int i = 0; i < types.length && transactionType == null; i++) {
            if (types[i].getValue().equals(value)) {
                transactionType = types[i];
            }
        }
        if (transactionType == null) {
            throw new IllegalArgumentException("Unknown " + value);
        }
        return transactionType;
    }
}
