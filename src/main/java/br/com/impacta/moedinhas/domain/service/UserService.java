package br.com.impacta.moedinhas.domain.service;

import br.com.impacta.moedinhas.domain.model.User;

import java.util.UUID;

public interface UserService {

    User create(User user);

    boolean exists(User user);

    void delete(UUID id);

    User update(UUID id, User user);

    User findById(UUID id);

    User getUserDependent(UUID id);

    void defineResponsible(UUID idDependent, String responsibleEmail);

    void defineDependent(String dependentEmail, UUID idResponsible);

}
