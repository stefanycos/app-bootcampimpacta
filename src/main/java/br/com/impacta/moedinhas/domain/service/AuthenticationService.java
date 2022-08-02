package br.com.impacta.moedinhas.domain.service;

import br.com.impacta.moedinhas.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public interface AuthenticationService {

    User findById(UUID id);

    Optional<User> findByEmail(String email);
}
