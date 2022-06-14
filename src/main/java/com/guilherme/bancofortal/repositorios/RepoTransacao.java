package com.guilherme.bancofortal.repositorios;

import com.guilherme.bancofortal.entidades.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoTransacao extends JpaRepository<Transacao, Integer> {
}
