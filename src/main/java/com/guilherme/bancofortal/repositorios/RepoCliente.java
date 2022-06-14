package com.guilherme.bancofortal.repositorios;

import com.guilherme.bancofortal.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoCliente extends JpaRepository<Cliente, Integer> {


}
