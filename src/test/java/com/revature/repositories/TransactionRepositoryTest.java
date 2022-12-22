package com.revature.repositories;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
class TransactionRepositoryTest {

    @Autowired
    TransactionRepository subject;

    // all args constructor for User, new User(id=1, email="test@email.com", firstName="Test", lastName="User", password="password", DOB="01-15-88", address="123 Test St", phone="123-456-7890")
    // all args constructor for Account, new Account(id=1, name="Test Account", balance=100.00, description="Test Account", created=2021-05-19T00:00, user=new User() )
    // all args constructor for Transaction, new Transaction(id=1, amount=100.00, description="Test Transaction", type=TransactionType.Income, account=existingAccount)

    @Test
    public void findByAccount_test() {
        // TransactionRepository method stub of findByAccount
        // List<Transaction> findByAccount(Account account);
    }

    @Test
    public void getAllByType_test(){
        // TransactionRepository method stub of getAllByType
        // List<Transaction> getAllByType(TransactionType income)
    }

    @Test
    public void findByAccountAndType_test(){
        // TransactionRepository method stub of findByAccountAndType
        // List<Transaction> findByAccountAndType(Account account, TransactionType income)
    }

    @Test
    public void findByAccountOrderByIdDesc_test(){
        // TransactionRepository method stub of findByAccountOrderByIdDesc
        // List<Transaction> findByAccountOrderByIdDesc(Account account)
    }

    @Test
    public void findTopByAccountOrderByIdDesc_test(){
        // TransactionRepository method stub of findTopByAccountOrderByIdDesc
        // List<Transaction> findTopByAccountOrderByIdDesc(Account account)
    }
}