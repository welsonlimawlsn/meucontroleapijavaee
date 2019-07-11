package com.meucontrole.api.services;

import com.meucontrole.api.entidades.UsuarioDaAplicacao;
import com.meucontrole.api.exceptions.UnauthorizedException;
import com.meucontrole.api.util.Mensagem;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

import javax.crypto.SecretKey;
import javax.ejb.Singleton;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

@Singleton
public class TokenService {

    private SecretKey chaveSecreta = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String gerar(UsuarioDaAplicacao usuarioDaAplicacao) {
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneOffset.UTC).plusMinutes(15);
        return "Bearer " + Jwts.builder()
                .setSubject(usuarioDaAplicacao.getEmail())
                .signWith(chaveSecreta)
                .setExpiration(Date.from(zonedDateTime.toInstant()))
                .compact();
    }

    public String getSubject(String token) throws UnauthorizedException {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(chaveSecreta)
                    .parseClaimsJws(token.replace("Bearer ", ""))
                    .getBody();
            return body.getSubject();
        } catch (ExpiredJwtException e) {
            throw new UnauthorizedException(Mensagem.TOKEN_EXPIRADO);
        } catch (SignatureException e) {
            throw new UnauthorizedException(Mensagem.VOCE_NAO_TEM_PERMISSAO_PARA_ACESSAR_ISTO, e);
        }
    }

}
