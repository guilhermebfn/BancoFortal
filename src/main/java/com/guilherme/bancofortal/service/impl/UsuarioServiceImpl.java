package com.guilherme.bancofortal.service.impl;

import com.guilherme.bancofortal.entidades.Usuario;
import com.guilherme.bancofortal.exceptions.SenhaInvalidaException;
import com.guilherme.bancofortal.repositorios.RepoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UsuarioServiceImpl implements UserDetailsService {

    @Autowired
    private RepoUsuario repoUsuario;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Transactional
    public Usuario gravar(Usuario usuario) {
        return repoUsuario.save(usuario);
    }

    public UserDetails autenticar(Usuario usuario) {
        UserDetails user = loadUserByUsername(usuario.getNomeUsuario());

        boolean senhasIguais = passwordEncoder().matches(usuario.getSenha(), user.getPassword());
        if (senhasIguais) {
            return user;
        }

        throw new SenhaInvalidaException("Senha inválida");
    }

    @Override
    public UserDetails loadUserByUsername(String nomeUsuario) throws UsernameNotFoundException {
        Usuario usuario = repoUsuario.findByNomeUsuario(nomeUsuario).orElseThrow(() -> new UsernameNotFoundException("O usuário não foi encontrado no banco de dados"));

        return User.builder()
                .username(usuario.getNomeUsuario())
                .password(usuario.getSenha())
                .roles(new String[]{"USUARIO"})
                .build();
    }
}
