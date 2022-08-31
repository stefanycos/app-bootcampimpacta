package br.com.impacta.moedinhas.api.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static br.com.impacta.moedinhas.api.handler.utils.CustomExceptionUtility.buildErrorBody;
import static br.com.impacta.moedinhas.api.handler.utils.CustomExceptionUtility.setResponseProperties;

@Slf4j
public class CustomAuthenticationExceptionHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.error("Authentication error: {}", authException.getMessage());

        setResponseProperties(response);
        response.getWriter().write(buildErrorBody(authException));
    }
}
