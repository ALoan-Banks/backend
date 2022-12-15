package com.revature.repositories;

import com.revature.models.Account;
import com.revature.models.Transaction;
import com.revature.models.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findByAccount(Account account);

    //List<Transaction> getAllByType(TransactionType income);

    List<Transaction> findByAccountAndType(Account account, TransactionType income);
}
