package com.dbserver.desafiovotacao.repository;

import com.dbserver.desafiovotacao.entity.SessaoVotacao;
import com.dbserver.desafiovotacao.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {
	
    Optional<Voto> findBySessaoAndAssociadoCpf(SessaoVotacao sessao, String cpf); //TODO buscaPorSessaoECpf
    
    List<Voto> findAllBySessao(SessaoVotacao sessao); //TODO buscaTodosPorSessao
    
}