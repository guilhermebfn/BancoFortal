package com.guilherme.bancofortal.service.jwt;

import com.guilherme.bancofortal.entidades.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {

    @Value("${security.jwt.expiracao}")
    private String expString;

    private final Key chave = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String gerarToken(Usuario usuario) {
        long expiracao = Long.parseLong(expString);
        LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expiracao);
        Date data = Date.from(dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant());

        return Jwts.builder()
                .setSubject(usuario.getNomeUsuario())
                .setExpiration(data)
                .signWith(chave)
                .compact();
    }

    private Claims obterClaims(String token) throws ExpiredJwtException {
        var parser = Jwts.parserBuilder()
                .setSigningKey(chave)
                .build();

        return parser.parseClaimsJws(token)
                .getBody();
    }

    public boolean tokenValido(String token) {
        try {
            var claims = obterClaims(token);
            Date dataExpiracao = claims.getExpiration();
            var data = dataExpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return LocalDateTime.now().isBefore(data);
        } catch (ExpiredJwtException e) {
            return false;
        }
    }

    public String obterNomeUsuario(String token) throws ExpiredJwtException {
        return obterClaims(token).getSubject();
    }

    public static void main(String[] args) {
        var jwtService = new JwtService();
        jwtService.expString = "30";
        var usuario = new Usuario(1, "Guilherme", "oi");
        String token = jwtService.gerarToken(usuario);

        boolean isTokenValido = jwtService.tokenValido(token);
        System.out.println("Token válido: " + isTokenValido);

        System.out.println(jwtService.obterNomeUsuario(token));
    }
}
