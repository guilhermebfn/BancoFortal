package com.guilherme.bancofortal.service.impl;

import com.guilherme.bancofortal.entidades.Usuario;
import com.guilherme.bancofortal.repositorios.RepoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UsuarioServiceImpl implements UserDetailsService {

    @Autowired
    private RepoUsuario repoUsuario;

    @Transactional
    public Usuario gravar(Usuario usuario) {
        return repoUsuario.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String nomeUsuario) throws UsernameNotFoundException {
        Usuario usuario = repoUsuario.findByNomeUsuario(nomeUsuario).orElseThrow(() -> new UsernameNotFoundException("O usuário não foi encontrado no banco de dados"));

        return User.builder()
                .username(usuario.getNomeUsuario())
                .password(usuario.getSenha())
                .roles("USUARIO")
                .build();
    }
}
