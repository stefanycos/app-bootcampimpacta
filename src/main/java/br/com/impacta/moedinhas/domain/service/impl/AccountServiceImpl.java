package br.com.impacta.moedinhas.domain.service.impl;

import br.com.impacta.moedinhas.domain.model.Account;
import br.com.impacta.moedinhas.domain.model.Role;
import br.com.impacta.moedinhas.domain.service.AccountService;
import br.com.impacta.moedinhas.infrastructure.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public void create(Account account) {
        if (this.isUserTypeResponsible(account))
            return;

        accountRepository.save(account);
    }

    private boolean isUserTypeResponsible(Account account) {
        return account.getUser().getRole().equals(Role.RESPONSIBLE);
    }

}
