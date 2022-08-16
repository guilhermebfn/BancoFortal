package com.guilherme.bancofortal.controladores;

import com.guilherme.bancofortal.dto.TokenDTO;
import com.guilherme.bancofortal.dto.UsuarioDTO;
import com.guilherme.bancofortal.entidades.Usuario;
import com.guilherme.bancofortal.exceptions.SenhaInvalidaException;
import com.guilherme.bancofortal.service.impl.UsuarioServiceImpl;
import com.guilherme.bancofortal.service.jwt.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class ControladorUsuario {

    private final UsuarioServiceImpl servicoUsuario;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public ControladorUsuario(UsuarioServiceImpl servicoUsuario, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.servicoUsuario = servicoUsuario;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }


    @PostMapping("/cadastro")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrar(@RequestBody @Valid Usuario usuario) {
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        servicoUsuario.gravar(usuario);
    }

    @PostMapping("entrar")
    public TokenDTO entrar(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            Usuario usuario = new Usuario(null, usuarioDTO.getNomeUsuario(), usuarioDTO.getSenha());
            servicoUsuario.autenticar(usuario);
            String token = jwtService.gerarToken(usuario);

            return new TokenDTO(usuarioDTO.getNomeUsuario(), token);
        } catch (UsernameNotFoundException | SenhaInvalidaException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
