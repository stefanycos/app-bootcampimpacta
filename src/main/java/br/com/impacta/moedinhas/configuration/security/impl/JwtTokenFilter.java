package br.com.impacta.moedinhas.configuration.security.impl;

import br.com.impacta.moedinhas.domain.TokenService;
import br.com.impacta.moedinhas.domain.model.User;
import br.com.impacta.moedinhas.domain.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@AllArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;

    private AuthenticationService authenticationService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = this.retrieveToken(request);
        boolean isValid = tokenService.isValid(token);

        if (isValid) {
            this.authenticateUser(token);
        }

        filterChain.doFilter(request, response);
    }

    private void authenticateUser(String token) {
        String userId = tokenService.retrieveUserId(token);
        User user = authenticationService.findById(UUID.fromString(userId));
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String retrieveToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7, token.length());
    }
}
