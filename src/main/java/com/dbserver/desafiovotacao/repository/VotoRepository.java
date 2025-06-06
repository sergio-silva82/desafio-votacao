package com.dbserver.desafiovotacao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbserver.desafiovotacao.entity.SessaoVotacao;
import com.dbserver.desafiovotacao.entity.Voto;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {
	
    Optional<Voto> getBySessaoAndAssociadoCpf(SessaoVotacao sessao, String associadoCpf);
    
    List<Voto> findAllBySessao(SessaoVotacao sessao);
    
}