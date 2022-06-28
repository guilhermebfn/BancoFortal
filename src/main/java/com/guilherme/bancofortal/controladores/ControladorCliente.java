package com.guilherme.bancofortal.controladores;

import com.guilherme.bancofortal.dto.ClienteDTO;
import com.guilherme.bancofortal.entidades.Cliente;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;
import com.guilherme.bancofortal.repositorios.RepoCliente;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/cliente")
public class ControladorCliente {

    private RepoCliente repoCliente;

    // Incompleto. Método para validar a entrada do cliente no aplicativo
    @GetMapping("entrar")
    public Cliente entrar(ClienteDTO dto) {
        String usuario = dto.getUsuario();
        String senha = dto.getSenha();

//        if (repoCliente.find) {
//        }

        return null;
    }

    @PostMapping("cadastro")
    @ResponseStatus(NO_CONTENT)
    public Cliente cadastrar(@RequestBody Cliente cliente) {
        repoCliente.save(cliente);
        return cliente;
    }



}
