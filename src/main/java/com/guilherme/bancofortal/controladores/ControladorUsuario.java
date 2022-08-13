package com.guilherme.bancofortal.controladores;

import com.guilherme.bancofortal.entidades.Usuario;
import com.guilherme.bancofortal.service.impl.UsuarioServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class ControladorUsuario {

    private final UsuarioServiceImpl servicoUsuario;
    private final PasswordEncoder passwordEncoder;

    public ControladorUsuario(UsuarioServiceImpl servicoUsuario, PasswordEncoder passwordEncoder) {
        this.servicoUsuario = servicoUsuario;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrar(@RequestBody @Valid Usuario usuario) {
        System.out.println(("Aqui"));

        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        servicoUsuario.gravar(usuario);
    }

//    @PostMapping("entrar")
//    public void entrar() {
//
//    }
}
