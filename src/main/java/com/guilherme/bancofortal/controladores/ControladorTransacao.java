package com.guilherme.bancofortal.controladores;

import com.guilherme.bancofortal.dto.TransacaoDTO;
import com.guilherme.bancofortal.entidades.Transacao;
import com.guilherme.bancofortal.exceptions.ClienteNaoEncontradoException;
import com.guilherme.bancofortal.exceptions.TransferenciaInvalidaException;
import com.guilherme.bancofortal.repositorios.RepoCliente;
import com.guilherme.bancofortal.repositorios.RepoTransacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/transacao")
public class ControladorTransacao {

    @Autowired
    private RepoTransacao repoTransacao;

    @Autowired
    private RepoCliente repoCliente;

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public void transferencia(@RequestBody TransacaoDTO transacao) throws TransferenciaInvalidaException, ClienteNaoEncontradoException {
        var pagador = repoCliente.findById(transacao.getIdPagador())
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado"));
        var recebedor = repoCliente.findById(transacao.getIdRecebedor())
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado"));

        BigDecimal valorBigDecimal = BigDecimal.valueOf(transacao.getValor());

        boolean transacaoInvalida = pagador.getSaldo().compareTo(valorBigDecimal) < 0;
        if (transacaoInvalida) {
            throw new TransferenciaInvalidaException("Saldo insuficiente");
        }

        BigDecimal novoSaldoPagador = pagador.getSaldo().subtract(valorBigDecimal);
        pagador.setSaldo(novoSaldoPagador);

        BigDecimal novoSaldoRecebedor = recebedor.getSaldo().add(valorBigDecimal);
        recebedor.setSaldo(novoSaldoRecebedor);

        repoTransacao.save(new Transacao(valorBigDecimal, pagador, recebedor, LocalDateTime.now()));
    }
}
