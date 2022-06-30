package com.guilherme.bancofortal.controladores;

import com.guilherme.bancofortal.entidades.Cliente;
import com.guilherme.bancofortal.exceptions.ClienteNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.guilherme.bancofortal.repositorios.RepoCliente;

import java.math.BigDecimal;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/cliente")
public class ControladorCliente {

    @Autowired
    private RepoCliente repoCliente;

    // Incompleto. Método para validar a entrada do cliente no aplicativo
    @GetMapping("/{id}")
    @ResponseBody
    public Cliente entrar(@PathVariable int id) {
        Optional<Cliente> cliente = repoCliente.findById(id);
        return cliente.orElseThrow(ClienteNaoEncontradoException::new);

        // Testar o método
    }

    @PostMapping("/cadastro")
    @ResponseBody
    @ResponseStatus(CREATED)
    public Cliente cadastrar(@RequestBody Cliente cliente) {
        return repoCliente.save(cliente);
    }



}
