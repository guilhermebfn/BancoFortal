package com.guilherme.bancofortal.service.jwt;

import com.guilherme.bancofortal.service.impl.UsuarioServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

    private JwtService jwtService;
    private UsuarioServiceImpl servicoUsuario;

    public JwtAuthFilter(JwtService jwtService, UsuarioServiceImpl servicoUsuario) {
        this.jwtService = jwtService;
        this.servicoUsuario = servicoUsuario;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        if (authorization != null && authorization.startsWith("Bearer")) {
            String token = authorization.split(" ")[1];
            boolean estaValido = jwtService.tokenValido(token);

            if (estaValido) {
                String nomeUsuario = jwtService.obterNomeUsuario(token);
                UserDetails usuario = servicoUsuario.loadUserByUsername(nomeUsuario);
                var user = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                user.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(user);
            }

            filterChain.doFilter(request, response);
        }
    }
}
