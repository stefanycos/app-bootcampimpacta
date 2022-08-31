package br.com.impacta.moedinhas.domain.service.impl;

import br.com.impacta.moedinhas.domain.TokenService;
import br.com.impacta.moedinhas.domain.exception.InternalErrorException;
import br.com.impacta.moedinhas.domain.model.Token;
import br.com.impacta.moedinhas.domain.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class TokenServiceImpl implements TokenService {

    @Value("${app.jwt.expiration}")
    private String expiration;

    @Value("${app.jwt.secret}")
    private String secret;

    @Override
    public Token createToken(Authentication authentication) {
        try {
            log.info("Generating new token");
            final User loggedUser = (User) authentication.getPrincipal();

            Date currentDate = new Date();
            Date expirationDate = new Date(currentDate.getTime() + Long.parseLong(expiration));

            final String token = Jwts.builder()
                    .setIssuer("Moedinhas App")
                    .setSubject(loggedUser.getId().toString())
                    .setIssuedAt(currentDate)
                    .setExpiration(expirationDate)
                    .signWith(SignatureAlgorithm.HS256, secret)
                    .compact();

            return Token.builder().token(token).role(loggedUser.getRole()).userId(loggedUser.getId()).build();
        } catch (final Exception exception) {
            log.error("Error on trying to create token. Message: {}", exception.getMessage());
            throw new InternalErrorException(exception.getMessage());
        }
    }

    @Override
    public boolean isValid(String token) {
        log.info("Validating received token");
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (final Exception e) {
            log.warn("Token is not valid. Details: {}", e.getMessage());
            return false;
        }
    }

    @Override
    public String retrieveUserId(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
