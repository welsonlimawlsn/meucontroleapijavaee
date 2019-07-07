package com.meucontrole.api.util.enumconverters;

import com.meucontrole.api.enums.TransactionType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TransactionTypeConverter implements AttributeConverter<TransactionType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TransactionType attribute) {
        return attribute.getValue();
    }

    @Override
    public TransactionType convertToEntityAttribute(Integer dbData) {
        return TransactionType.get(dbData);
    }

}
