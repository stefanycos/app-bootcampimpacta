package br.com.impacta.moedinhas.domain.service;

import br.com.impacta.moedinhas.domain.model.User;

import java.util.UUID;

public interface UserService {

    User create(User user);

    boolean exists(User user);

    void delete(UUID id);

    User update(UUID id, User user);

    User findById(UUID id);

}
