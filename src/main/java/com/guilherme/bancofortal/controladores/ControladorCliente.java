package com.guilherme.bancofortal.controladores;

import com.guilherme.bancofortal.entidades.Cliente;
import com.guilherme.bancofortal.exceptions.ClienteNaoEncontradoException;
import com.guilherme.bancofortal.repositorios.RepoCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/cliente")
public class ControladorCliente {

    @Autowired
    private RepoCliente repoCliente;

    @GetMapping("/{id}")
    @ResponseBody
    public Cliente buscarCliente(@PathVariable int id) {
        Optional<Cliente> cliente = repoCliente.findById(id);
        return cliente.orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado"));
    }

    @PostMapping("/cadastro")
    @ResponseBody
    @ResponseStatus(CREATED)
    public Cliente cadastrar(@RequestBody Cliente cliente) {
        return repoCliente.save(cliente);
    }
}
