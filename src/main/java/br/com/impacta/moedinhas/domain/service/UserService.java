package br.com.impacta.moedinhas.domain.service;

import br.com.impacta.moedinhas.domain.model.User;

import java.util.Optional;

public interface UserService {

    User create(User user);

    boolean exists(User user);

    Optional<User> findByEmail(String email);

}
