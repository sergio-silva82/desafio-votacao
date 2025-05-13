package com.dbserver.desafiovotacao.entity;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.dbserver.desafiovotacao.enums.SimNaoEnum;
import com.dbserver.desafiovotacao.converter.OpcaoVotoConverter;

import lombok.Data;

@Entity
@Data
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String associadoCpf;

    @Convert(converter = OpcaoVotoConverter.class)
    private SimNaoEnum voto;
    
    @Convert(converter = OpcaoVotoConverter.class)
    private SimNaoEnum votoValido;

    @ManyToOne
    @JoinColumn(name = "sessao_id")
    private SessaoVotacao sessao;

}
