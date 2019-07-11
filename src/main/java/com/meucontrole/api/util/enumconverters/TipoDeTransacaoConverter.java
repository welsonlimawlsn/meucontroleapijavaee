package com.meucontrole.api.util.enumconverters;

import com.meucontrole.api.enums.TipoDeTransacao;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TipoDeTransacaoConverter implements AttributeConverter<TipoDeTransacao, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TipoDeTransacao attribute) {
        return attribute.getValue();
    }

    @Override
    public TipoDeTransacao convertToEntityAttribute(Integer dbData) {
        return TipoDeTransacao.get(dbData);
    }

}
