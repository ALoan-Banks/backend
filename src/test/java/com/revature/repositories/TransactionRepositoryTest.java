package com.revature.repositories;


import com.revature.models.Account;
import com.revature.models.Transaction;
import com.revature.models.TransactionType;
import com.revature.models.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
class TransactionRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    TransactionRepository subject;

    // all args constructor for User, new User(id=1, email="test@email.com", firstName="Test", lastName="User", password="password", DOB="01-15-88", address="123 Test St", phone="123-456-7890")
    // all args constructor for Account, new Account(id=1, name="Test Account", balance=100.00, description="Test Account", created=2021-05-19T00:00, user=new User() )
    // all args constructor for Transaction, new Transaction(id=1, amount=100.00, description="Test Transaction", type=TransactionType.Income, account=existingAccount)

    @Test
    public void findByAccount_test() {
        // Create test data
        User testUser = new User(0,"test@email.com", "Test", "User", "password", "01-15-88", "123 Test St", "123-456-7890");
        entityManager.persist(testUser);
        Account testAccount = new Account(0, "Test Account", 100.00, "Test Account", new Date(), testUser);
        Transaction testTransaction = new Transaction(0, 100.00, "Test Transaction", TransactionType.Income, testAccount);
        entityManager.persist(testAccount);
        entityManager.persist(testTransaction);
        entityManager.flush();

        // Call the method being tested
        List<Transaction> result = subject.findByAccount(testAccount);

        // Assert that the result is as expected
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);

        // Reattach the testTransaction object to the persistence context
        Transaction attachedTransaction = entityManager.merge(testTransaction);
        assertThat(result.get(0)).isEqualTo(attachedTransaction);
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