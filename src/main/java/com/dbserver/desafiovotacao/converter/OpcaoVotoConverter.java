package com.dbserver.desafiovotacao.converter;

import com.dbserver.desafiovotacao.enums.SimNaoEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class OpcaoVotoConverter implements AttributeConverter<SimNaoEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(SimNaoEnum opcaoVoto) {
        return (opcaoVoto == null) ? null : opcaoVoto.getId();
    }

    @Override
    public SimNaoEnum convertToEntityAttribute(Integer id) {
        return (id == null) ? null : SimNaoEnum.porId(id);
    }
}
