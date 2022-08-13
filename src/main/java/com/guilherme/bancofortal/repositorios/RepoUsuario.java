package com.guilherme.bancofortal.repositorios;

import com.guilherme.bancofortal.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepoUsuario extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByNomeUsuario(String nomeUsuario);
}
