package com.guilherme.bancofortal.controladores;

import com.guilherme.bancofortal.dto.UsuarioDTO;
import com.guilherme.bancofortal.entidades.Usuario;
import com.guilherme.bancofortal.repositorios.RepoUsuario;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class ControladorUsuario {

    private RepoUsuario repoUsuario;

    @PostMapping("cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrar(@RequestBody Usuario usuario) {
        repoUsuario.save(usuario);
    }

//    @GetMapping("entrar")
//    public UsuarioDTO entrar() {
//
//    }
}
