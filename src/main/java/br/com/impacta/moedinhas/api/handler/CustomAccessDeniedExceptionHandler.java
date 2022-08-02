package br.com.impacta.moedinhas.api.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static br.com.impacta.moedinhas.api.handler.utils.CustomExceptionUtility.buildErrorBody;
import static br.com.impacta.moedinhas.api.handler.utils.CustomExceptionUtility.setResponseProperties;

@Slf4j
public class CustomAccessDeniedExceptionHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.error("Authentication error: {}", accessDeniedException.getMessage());

        setResponseProperties(response);
        response.getWriter().write(buildErrorBody(accessDeniedException));
    }
}
