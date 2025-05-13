package com.dbserver.desafiovotacao.entity;

import javax.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class SessaoVotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime abertura;

    private LocalDateTime fechamento;

    @ManyToOne
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;
}
