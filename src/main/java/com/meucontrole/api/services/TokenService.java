package com.meucontrole.api.services;

import com.meucontrole.api.entities.ApplicationUser;
import com.meucontrole.api.exceptions.UnauthorizedException;
import com.meucontrole.api.util.Message;
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

    private SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String generateToken(ApplicationUser applicationUser) {
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneOffset.UTC).plusMinutes(15);
        return "Bearer " + Jwts.builder()
                .setSubject(applicationUser.getEmail())
                .signWith(secretKey)
                .setExpiration(Date.from(zonedDateTime.toInstant()))
                .compact();
    }

    public String getSubject(String token) throws UnauthorizedException {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token.replace("Bearer ", ""))
                    .getBody();
            return body.getSubject();
        } catch (ExpiredJwtException e) {
            throw new UnauthorizedException(Message.TOKEN_EXPIRADO);
        } catch (SignatureException e) {
            throw new UnauthorizedException(Message.VOCE_NAO_TEM_PERMISSAO_PARA_ACESSAR_ISTO, e);
        }
    }

}
