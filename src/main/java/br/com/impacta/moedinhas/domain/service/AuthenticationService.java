package br.com.impacta.moedinhas.domain.service;

import br.com.impacta.moedinhas.domain.model.User;
import org.springframework.security.core.Authentication;

import java.util.Optional;
import java.util.UUID;

public interface AuthenticationService {

    User findById(UUID id);

    Optional<User> findByEmail(String email);

    User createToken(final Authentication authentication);
}
