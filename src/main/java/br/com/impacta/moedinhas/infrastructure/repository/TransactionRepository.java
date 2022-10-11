package br.com.impacta.moedinhas.infrastructure.repository;

import br.com.impacta.moedinhas.domain.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    @Query(value = "select * from transactions t where t.account_id = :accountId", nativeQuery = true)
    Page<Transaction> findTransactionsByAccount(Pageable pageable, @Param("accountId") UUID accountId);

}
